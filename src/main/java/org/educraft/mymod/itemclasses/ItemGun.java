package org.educraft.mymod.itemclasses;

import org.educraft.mymod.entities.EntityBullet;
import org.educraft.mymod.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemGun extends Item
{
	public static float damage;
	private final float spread;
	private final double speed;
	
	public ItemGun(float damage, float spread, double speed)
	{
		ItemGun.damage = damage;
		this.spread = spread;
		this.speed = speed;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack stack = this.findAmmo(playerIn);
		if(stack != ItemStack.EMPTY)
		{
			worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 1.0F, 0.5F + (itemRand.nextFloat()));
			if(!worldIn.isRemote)
			{
				EntityBullet bullet = new EntityBullet(worldIn, playerIn, spread, speed, damage);
				worldIn.spawnEntity(bullet);
			}
			else
			{
				playerIn.rotationPitch -= 5f;
			}
			stack.shrink(1);
			
			if(stack.isEmpty())
			{
				playerIn.inventory.deleteStack(stack);
			}
			
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isAmmo(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isAmmo(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isAmmo(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
	
	protected boolean isAmmo(ItemStack stack)
    {
        return stack.getItem() == ModItems.shotgun_shell;
    }
}
