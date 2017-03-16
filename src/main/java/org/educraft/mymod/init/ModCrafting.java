/*
 * This is where you create crafting recipes for your items. Whether the recipe is shaped, shapeless or smelted matters.
 */

package org.educraft.mymod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public static void register() {
		//Crafting recipes go here
		
		//Shaped recipes
		//Use themethod shown below. The first item is a new ItemStack and that is what is created.
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.block_name), "III",  //The recipe uses characters spaced in the 3x3 grid of the crafting table. Identify the characters as shown below.
																		  "I I",
																		  "III", 'I', ModItems.item_name);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.jar), " C ",
																   "G G",
																   "CCC", 'C', Blocks.COAL_BLOCK, 'G', new ItemStack(Blocks.STAINED_GLASS, 1, 0));
		
				//Creating recipes for material armor
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.material_chestplate), "M M", "MMM", "MMM", 'M', ModItems.material_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.material_helmet), "MMM", "M M", 'M', ModItems.material_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.material_leggings), "MMM", "M M", "M M", 'M', ModItems.material_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.material_boots), "M M", "M M", 'M', ModItems.material_ingot);
		
			//Creating Recipes for material tool
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.tool_item), " M ", " L ", " M ", 'M', ModItems.material_ingot, 'L', Items.LEATHER);

		
		//Shapeless recipes
			//Shapeless recipes also start with the new ItemStack of the item being created. They then list the ingredients as a new Object[] inside brackets.
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.food_item, 1), new Object[] {Items.ROTTEN_FLESH, Items.ROTTEN_FLESH});
		
		
		//Smelting recipes
			//These recipes start with the ingredient and end with the product. Afterwards there is a number of experience gained followed by an F with no space.
		
		
		//Smelting Blocks
		GameRegistry.addSmelting(ModBlocks.block_name, new ItemStack(ModItems.item_name), 1.2F);
		
		GameRegistry.addSmelting(ModBlocks.material_ore, new ItemStack(ModItems.material_ingot), 1.2F);
		
		//Smelting Items
		GameRegistry.addSmelting(ModItems.item_name, new ItemStack(ModItems.food_item), 1.2F);
	}
}
