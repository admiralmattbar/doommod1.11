/*
 * This class is where you create and register new blocks. It also comes with a method by which any item you register here gets
 * attached to a texture that can give your item a unique look. Go through the methods below and and copy block variables in the style
 * given and you will be able to create many different types of blocks.
 */

package org.educraft.mymod.init;

//import org.educraft.mymod.blockclasses.BlockDiceTile;
import org.educraft.mymod.blockclasses.BlockJar;
import org.educraft.mymod.blockclasses.BlockName;
import org.educraft.mymod.blockclasses.ModBlockOre;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBlock;

public class ModBlocks {

	//Block variables go here
	public static Block block_name;	
	public static Block jar;
	public static Block material_ore;
	public static Block multi_color;
	public static Block dice_block;
	

	public static void init() {
		//Initialize items with class
		block_name = new BlockName();
		jar = new BlockJar();
		material_ore = new ModBlockOre("material_ore", "block_material_ore", Material.ROCK, ModBlocks.material_ore, 1, 1);
		multi_color = new Block(Material.ROCK).setUnlocalizedName("multi_color").setRegistryName("block_multi_color").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		dice_block = new Block(Material.CAKE).setUnlocalizedName("dice_block").setRegistryName("block_dice_block").setCreativeTab(CreativeTabs.DECORATIONS);
	}

	public static void register() {
		//Register items with forge
		registerBlock(block_name);
		registerBlock(jar);
		registerBlock(material_ore);
		registerBlock(multi_color);
		registerBlock(dice_block);
	}

	private static void registerBlock(Block block) {
		GameRegistry.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}

	public static void registerRenders() {
		//List of items calling the registerRender method
		registerRender(block_name);
		registerRender(jar);
		registerRender(material_ore);
		registerRender(multi_color);
		registerRender(dice_block);
	}

	private static void registerRender(Block block) {
		//Method used to register item with texture
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}