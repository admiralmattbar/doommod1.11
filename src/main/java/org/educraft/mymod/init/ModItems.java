/*
 * This class is where you create and register new items. It also comes with a method by which any item you register here gets
 * attached to a texture that can give your item a unique look. Go through the methods below and and copy items in the style
 * given and you will be able to create many different types of items.
 */

/*
 * This class is where you start creating your items. Add them as variables, then initialize them, linking them to a class. You have to register them
 * and use the registerRender method to set up their textures (textures should be added to the resources folder).
 */

package org.educraft.mymod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.educraft.mymod.Reference;
import org.educraft.mymod.itemclasses.ItemFoodItem;
import org.educraft.mymod.itemclasses.ItemGun;
import org.educraft.mymod.itemclasses.ItemMaterialArmor;
import org.educraft.mymod.itemclasses.ItemName;
import org.educraft.mymod.itemclasses.ItemToolItem;

public class ModItems
{

	//Item variables go here
	
	//Random item that is just black square, use this for recipes.
	public static Item item_name;
	
	//Consumable items
	public static Item food_item;
	
	//Tool items
	public static Item tool_item;
	
	//Armor items
	public static Item material_helmet;
	public static Item material_chestplate;
	public static Item material_leggings;
	public static Item material_boots;
	
	//Material items
	public static Item material_ingot;
	
	//Weapon Items
	public static Item shotgun;
	public static Item shotgun_shell;
	
	
	//Create your own materials for tools and armor
	public static final Item.ToolMaterial material_name = EnumHelper.addToolMaterial("material_name", 3, 2000, 10.0F, 3.0F, 50);
	//public static final ItemArmor.ArmorMaterial armor_name;
	/*
	 * This creates a new material to make tools out of. Using EnumHelper to construct it, give the 1. Material name, 
	 * 2. Harvest level (1 to 3), 3. uses until it breaks, 4. strength against blocks (followed by a decimal and "F"), 5. Damage to entities (also a float),
	 * 6. How well the item responds to enchantments (1-100).
	 */
	
	public static final ItemArmor.ArmorMaterial armor_material_name = EnumHelper.addArmorMaterial("armor_material_name", //Name of armor material
																									Reference.MOD_ID + ":"+"armor_material_tex",  //Name of texture for material
																									2000, //How durable the armor material is (diamond is 33), how many hits it takes before breaking
																									new int[]{10, 10, 10, 10}, //Resisting damage for different parts 1. head, 2. chest, 3. legs, 4. boots
																																//Number is half hearts you do not lose when hit in that spot
																									25, //Enchantability of the material, this is the same as gold
																									SoundEvents.ITEM_ARMOR_EQUIP_GOLD, //When equipped sounds like gold
																									2.5F //Slightly tougher than diamond
																									);
	
	public static void init()
	{
		//Initialize items with class
		shotgun = new ItemGun(10F, 10.0F, 1000).setUnlocalizedName("shotgun").setRegistryName("item_shotgun").setCreativeTab(CreativeTabs.COMBAT);
		
		shotgun_shell = new Item().setUnlocalizedName("shotgun_shell").setRegistryName("item_shotgun_shell").setCreativeTab(CreativeTabs.COMBAT);
		
		item_name = new ItemName();
		//Any item will have to be initialized here like above. This explains that your item has its own class.
		
		food_item = new ItemFoodItem(); 
		//This is a food item. Create it's class extending ItemFood instead of Item.
		
		tool_item = new ItemToolItem(material_name); 
		/*This is a tool item. Create it's class extending ItemTool instead of Item. In the parentheses you give it a material. 
		 * You will make this for each material you want your item to be able to be made out of. This is made out of the new material created above.
		 * You can also make it out of WOOD, DIAMOND, STONE, GOLD, or IRON.
		 */
		
		material_helmet = new ItemMaterialArmor(armor_material_name, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("material_helmet").setRegistryName("item_material_helmet"); 
		//When calling the ItemMaterialArmor class you put the material name and body location in the parentheses.
		//This is a new helmet made out of the armor material made above.
		//Go to the Reference class and add MATERIAL_HELMET to your register and unlocalized name making method.
		//The number you see in the parentheses "1" for material_helmet is the texture layer. Set it for 1 for every piece of armor except the leggings, which should be set at 2.
		
		material_chestplate = new ItemMaterialArmor(armor_material_name, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("material_chestplate").setRegistryName("item_material_chestplate");
		material_leggings = new ItemMaterialArmor(armor_material_name, 2 /*notice leggings are different here*/, EntityEquipmentSlot.LEGS).setUnlocalizedName("material_leggings").setRegistryName("item_material_leggings"); 
		material_boots = new ItemMaterialArmor(armor_material_name, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("material_boots").setRegistryName("item_material_boots"); 
		
		material_ingot = new Item().setUnlocalizedName("material_ingot").setRegistryName("item_material_ingot").setCreativeTab(CreativeTabs.MATERIALS);
		
	}

	public static void register()
	{
		//Register items with forge
		GameRegistry.register(item_name);
		GameRegistry.register(food_item);
		GameRegistry.register(tool_item);
		GameRegistry.register(material_helmet);
		GameRegistry.register(material_chestplate);
		GameRegistry.register(material_leggings);
		GameRegistry.register(material_boots);
		GameRegistry.register(material_ingot);
		GameRegistry.register(shotgun);
		GameRegistry.register(shotgun_shell);

	}

	public static void registerRenders()
	{
		//List of items calling the registerRender method
		registerRender(item_name);
		registerRender(food_item);
		registerRender(tool_item);
		registerRender(material_helmet);
		registerRender(material_chestplate);
		registerRender(material_leggings);
		registerRender(material_boots);
		registerRender(material_ingot);
		registerRender(shotgun);
		registerRender(shotgun_shell);
	}

	private static void registerRender(Item item)
	{
		//Method used to register item with texture
		//There is no need to add to this method
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}