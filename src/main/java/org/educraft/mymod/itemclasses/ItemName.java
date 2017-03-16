/*
 * This is what a typical item class looks like. The peculiar things about your items go in their own class that your item inherits from.
 * If you want your item to be in the game be sure to include it in ModItems.
 */

package org.educraft.mymod.itemclasses;

import org.educraft.mymod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemName extends Item {
	
	public ItemName() {
		setUnlocalizedName("item_name"); 
		setRegistryName("item_item_name");  
		setCreativeTab(CreativeTabs.MISC); //Places item in a special Creative Tab for this mod.
		this.setFull3D(); //Better for holding an item view. 
		this.setContainerItem(ModItems.item_name); //This returns an item_name item when you craft (like when you get your bucket back after making cake).
	}
}