package org.educraft.mymod.entities;

import com.google.common.base.Predicates;

import java.util.List;

import javax.annotation.Nullable;

import org.educraft.mymod.itemclasses.ItemGun;
import org.educraft.mymod.utilclasses.ModDamageSource;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBullet extends Entity implements IProjectile
{
	private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(new Predicate[] {EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_.canBeCollidedWith();
        }
    }});
		
	private EntityLivingBase shooter;
	private int ticksAlive = 100;
	private double damage;

	public Entity shootingEntity;

	private int ticksInAir;
    /** The amount of knockback an arrow applies when it hits a mob. */


	public EntityBullet(World worldIn) 
	{
		super(worldIn);
		
	}
	
	
	public EntityBullet(World worldIn, EntityLivingBase shooter, float size, double speed, float damage)
    {
        super(worldIn);
        this.setShooter(shooter);
        this.damage = ItemGun.damage;
        this.setSize(size, size);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        Vec3d dir = shooter.getLookVec();
        this.motionX = dir.xCoord;
        this.motionY = dir.yCoord;
        this.motionZ = dir.zCoord;
        //this.setAim();

    }
	
	
	/*
    public void setAim()
    {
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        double accelX = 10 + this.rand.nextGaussian() * 0.4D;
        double accelY = 10 + this.rand.nextGaussian() * 0.4D;
        double accelZ = 10 + this.rand.nextGaussian() * 0.4D;
        double d0 = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ *accelZ);
        this.motionX = accelX / d0 * 0.1D;
        this.motionY = accelY / d0 * 0.1D;
        this.motionZ = accelZ / d0 * 0.1D;

    }
    */
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	
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
    
    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expandXyz(1.0D), ARROW_TARGETS);
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = (Entity)list.get(i);

            if (entity1 != this.shootingEntity || this.ticksInAir >= 5)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expandXyz(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        return entity;
    }

	@Override
	protected void entityInit() 
	{
		
	}


	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) 
	{
		
	}

	protected void onHit(RayTraceResult raytraceResultIn)
	{
		if (this.shootingEntity == null)
        {
            ModDamageSource.causeBulletDamage(this, this);
        }
        else
        {
            ModDamageSource.causeBulletDamage(this, this.shootingEntity);
        }
	}

	public EntityLivingBase getShooter() {
		return shooter;
	}

	public void setShooter(EntityLivingBase shooter) {
		this.shooter = shooter;
	}


	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		compound.setDouble("damage", this.damage);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		if (compound.hasKey("damage", 99))
        {
            this.damage = compound.getDouble("damage");
        }
	}
	
}
