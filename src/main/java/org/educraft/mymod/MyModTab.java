/*
 * This class creates a new screen in the Creative Mode item and block menu. This way you can create a screen
 * specifically for your mod items.
 */

package org.educraft.mymod;
import org.educraft.mymod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MyModTab extends CreativeTabs {

	public MyModTab() {
		super("tabMyMod");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.item_name);
	}

}
