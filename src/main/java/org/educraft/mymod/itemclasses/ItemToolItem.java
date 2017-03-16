/*
 * This class creates a tool type item. At first it is useless because it is not EFFECTIVE_ON any blocks (you will have to add those), 
 */

package org.educraft.mymod.itemclasses;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemToolItem extends ItemTool {
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {}); 
	//This determines what blocks this tool is effective on. Right now it is only Oak Doors. Add blocks you want to use this tool on separated by comas.
	
	private static float attackDamageIn = 2.0F; 
	//Set this variable to a float value (something with a decimal followed by F) to determine the damage of the item.
	
	private static float attackSpeedIn = 2.0F; 
	//This is a float that determines the speed of the item.
	
	private static Item.ToolMaterial material; 
	//This determines the material of the item. If you want to be able to construct this item out of different materials, leave this alone.

	
	public ItemToolItem(Item.ToolMaterial material) {
		super(attackDamageIn, attackSpeedIn, material, EFFECTIVE_ON);
		setUnlocalizedName("tool_item"); //This uses a method created in Reference. Add your item there.
		setRegistryName("item_tool_item");
		setCreativeTab(CreativeTabs.TOOLS); //Places item in the tool tab for Creative Tabs.
		this.setFull3D();
	}
	
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = ItemToolItem.material.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }

}
