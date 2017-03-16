package org.educraft.mymod.blockclasses;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlockOre extends Block {
	
	//Fields for blocks that drop things
	private Block drop;
	private int meta;
	private int least_quantity;
	private int most_quantity;
	
	protected ModBlockOre(String unlocalizedName, String registryName, Material mat, Block drop, int meta, int least_quantity, int most_quantity) {
		super(mat);
		setHarvestLevel("pickaxe", 1);
		setHardness(5.0F);
		setResistance(7.0F);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(registryName);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		//Setting the fields for blocks that drop things
		this.drop = drop;
		this.meta = meta;
		this.least_quantity = least_quantity;
		this.most_quantity = most_quantity;
		
	}
	
	public ModBlockOre(String unlocalizedName, String registryName, Material mat, Block drop, int least_quantity, int most_quantity) {
		this(unlocalizedName, registryName, mat, drop, 0, least_quantity, most_quantity);
	}
	
	protected ModBlockOre(String unlocalizedName, String registryName, Material mat, Block drop) {
		this(unlocalizedName, registryName, mat, drop, 1, 1);
	}
	
	public Block getBlockDropped(IBlockState blockstate, Random random, int fortune) {
		return this.drop;
	}
	
	@Override
	public int damageDropped(IBlockState blockstate) {
		return this.meta;
	}
	
	@Override
	public int quantityDropped(IBlockState blockstate, int fortune, Random random) {
		if(this.least_quantity >= this.most_quantity){
			return this.least_quantity;
		}
		
		return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}

}
