package laco.mods.peaceful;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockMobOre extends Block
{

	public BlockMobOre(int blockId)
	{
		super(blockId, Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setBlockName("mobOre");
		setStepSound(soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata (int side, int metadata) 
	{
		return metadata;
	}
	
    @Override
	public int quantityDropped(Random par1Random)
    {
    	int i = par1Random.nextInt(100);
        return i < 80 ? 1 : 2;
    }    

    public static int getNormalDropItem(int metadata)
	{
		switch(metadata)
		{
			case 0: return Item.gunpowder.shiftedIndex;
			case 1: return Item.bone.shiftedIndex;
			case 2: return Item.silk.shiftedIndex;
			case 3: return Item.enderPearl.shiftedIndex;
			case 4: return Item.blazePowder.shiftedIndex;
			case 5: return Item.ghastTear.shiftedIndex;
			case 6: return Item.slimeBall.shiftedIndex;
			default:
				return -1;
		}
	}
    
    public static int getRareDropItem(int metadata)
	{
		switch(metadata)
		{
			case 0: return Item.gunpowder.shiftedIndex;
			case 1: return Item.arrow.shiftedIndex;
			case 2: return Item.spiderEye.shiftedIndex;
			case 3: return Item.enderPearl.shiftedIndex;
			case 4: return Item.blazePowder.shiftedIndex;
			case 5: return Item.fireballCharge.shiftedIndex;
			case 6: return Item.slimeBall.shiftedIndex;
			default:
				return -1;
		}
	}
    
    @Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }

    @Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
		ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

		if (world.rand.nextInt(100) < 5)
		{
			ret.add(new ItemStack(getRareDropItem(metadata), 1, 0));
		}
		return ret;
    }

	@Override
	@SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return getNormalDropItem(par1World.getBlockMetadata(par2, par3, par4));
    }
	
	@Override
	public int idDropped (int meta, Random r, int i)
	{
		return getNormalDropItem(meta);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) 
	{
		for (int ix = 0; ix < 7; ix++) 
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public String getTextureFile () 
	{
		return CommonProxy.BLOCK_PNG;
	}
}
