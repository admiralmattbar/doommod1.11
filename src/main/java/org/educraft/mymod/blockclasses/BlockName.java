/*
 * This is the class for the new block added by this mod, the Block Name. A new block in the game will need its own class just like an item. It is
 * important for you to add the block to your registry as shown in the Registry class and then reference it here (below you call the Registry values
 * in setUnlocalizedName and setRegistryName). You can set other values like the material (here made out of cake) and the things shown at the bottom.
 */

package org.educraft.mymod.blockclasses;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockName extends Block {
	
	public BlockName() {
		super(Material.CAKE); //pick a material for your block, does not have to be cake
		setUnlocalizedName("block_name");
		setRegistryName("block_block_name");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS); //places block in the Blocks section of Creative Tabs.
		
		
		//OTHER BLOCK OPTIONS
		
		this.setHardness(1.0F); 
		//How hard the block is, stone is 1.5 and dirt is 0.5
		
		this.setResistance(1.0F);
		//The block's resistance to explosions. Should be at least the value of the hardness.
		
		this.setSoundType(SoundType.METAL); 
		//The sound the block makes based on various materials. Though this block is CAKE, it will sound like STONE.
		
		this.setLightLevel(0.0F);
		//Light emitted by the block (highest is 1.0F)
		
		this.setLightOpacity(15);
		//How much this block stops light from passing through it. the values are 0 to 15.
		
	}
}