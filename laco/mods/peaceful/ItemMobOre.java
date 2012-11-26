package laco.mods.peaceful;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemMobOre extends ItemBlock
{

	public ItemMobOre(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setItemName("mobOre");
	}
	
	@Override
	public int getMetadata (int damageValue) 
	{
		return damageValue;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack) 
	{
		return getItemName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	private final static String[] subNames = 
	{
		"creeper", "skeleton",  "spider", "enderman", "blaze", "ghast", "slime"
	};

}
