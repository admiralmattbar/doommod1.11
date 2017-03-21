package org.educraft.mymod.entities;

import com.google.common.base.Predicates;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBullet extends Entity implements IProjectile
{
	
	@SuppressWarnings("unchecked")
	private static final Predicate<Entity> BULLET_TARGETS = Predicates.and(new Predicate[] {EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_.canBeCollidedWith();
        }
    }});
    
		
	private EntityLivingBase shooter;
	private int ticksAlive = 100;
	private float damage;

	public Entity shootingEntity;

//Constructor
	public EntityBullet(World worldIn) 
	{
		super(worldIn);
		
	}
	
//Constructor (not sure why we need both)
	public EntityBullet(World worldIn, EntityLivingBase shooter, float size, double speed, float damage)
    {
        super(worldIn);
        this.setShooter(shooter);
        this.damage = damage;
        this.setSize(size, size);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

        Vec3d dir = shooter.getLookVec();
        this.motionX = dir.xCoord;
        this.motionY = dir.yCoord;
        this.motionZ = dir.zCoord;
        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);

    }
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if (!world.isRemote){
    		Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
    		Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        	RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
        	vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        	vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        	
        	if(raytraceresult != null ){
        		vec3d = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
        	}
        	
        	Entity entity = this.findEntityOnPath(vec3d1, vec3d);
        	
        	if(entity != null){
        		raytraceresult = new RayTraceResult(entity);
        	}
        	
        	if(raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer){
        		EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;
        		
        		if(this.shooter instanceof EntityPlayer && !((EntityPlayer) this.shooter).canAttackPlayer(entityplayer)){
        			raytraceresult = null;
        		}
        	}
        	
        	if(raytraceresult != null){
        		this.onHit(raytraceresult);;
        	}
        	
        	this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            
        	this.setPosition(this.posX, this.posY, this.posZ);
        	
        	ticksAlive--;
        	if(ticksAlive <= 0) 
        	{
        		this.setDead();
        	}
    	}
   }
    
    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expandXyz(1.0D), BULLET_TARGETS);
        double closestDistance = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity hitEntity = (Entity)list.get(i);

            if (hitEntity != this.shooter)
            {
                AxisAlignedBB axisalignedbb = hitEntity.getEntityBoundingBox().expandXyz(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double distanceToHit = start.squareDistanceTo(raytraceresult.hitVec);

                    if (distanceToHit < closestDistance || closestDistance == 0.0D)
                    {
                        entity = hitEntity;
                        closestDistance = distanceToHit;
                    }
                }
            }
        }

        return entity;
    }

	public void onHit(RayTraceResult raytraceResultIn)
	{
		Entity entity = raytraceResultIn.entityHit;
		
		if(entity != null){
			if(entity == shooter) return;
			float damage = this.damage;
			entity.attackEntityFrom(DamageSource.ANVIL, damage);
			this.setDead();
			return;
		}
		
		if(raytraceResultIn.getBlockPos() != null){
			IBlockState state = world.getBlockState(raytraceResultIn.getBlockPos());
			Block block = state.getBlock();
			if((block instanceof BlockBreakable || block instanceof BlockPane) && state.getMaterial() == Material.GLASS){
				world.destroyBlock(raytraceResultIn.getBlockPos(), false);
			}
			if(!block.isReplaceable(world, raytraceResultIn.getBlockPos())){
				this.setDead();
			}
		}
	}

	public EntityLivingBase getShooter() {
		return shooter;
	}

	public void setShooter(EntityLivingBase shooter) {
		this.shooter = shooter;
	}


	protected ItemStack getArrowStack() {
		return null;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setDouble("damage", this.damage);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		if (compound.hasKey("damage", 99))
        {
            this.damage = (float) compound.getDouble("damage");
        }
	}


	@Override
	protected void entityInit() {}


	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		
	}
}
