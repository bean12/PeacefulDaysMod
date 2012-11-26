package laco.mods.peaceful;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	private RecipeRegistry(){};
	
	private static RecipeRegistry _instance;
	
	public static RecipeRegistry instance()
	{
		if(_instance == null)
		{
			_instance = new RecipeRegistry();
		}
		return _instance;
	}
	
	public void addRecipe()
	{
		addOreRecipes();
		addOtherRecipes();
	}
	
	private void addOreRecipes()
	{
		for(int i = 0; i < 7; i++)
		{
			addOreRecipe(i, new ItemStack(Item.itemsList[BlockMobOre.getNormalDropItem(i)]));		
		}
	}
	
	private void addOreRecipe(int meta, ItemStack stack)
	{
		GameRegistry.addRecipe(new ItemStack(PeacefulDays.mobOre, 4, meta),
				new Object[]{"XXX", "XYX", "XXX", 
			Character.valueOf('X'), stack, Character.valueOf('Y'), Block.stone});
	}
	
	private void addOtherRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(Item.netherStar),
				new Object[]{" X ", "XYX", " X ", 
			Character.valueOf('X'), Item.diamond, Character.valueOf('Y'), Item.magmaCream});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.blazeRod),
				new Object[]{new ItemStack(Item.blazePowder), new ItemStack(Item.blazePowder)});
		
	}
}
