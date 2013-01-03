package laco.mods.peaceful;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class MobOreGenerator implements IWorldGenerator
{
	public WorldGenerator[] mobOreGen;

	public MobOreGenerator()
	{
		int blockId = PeacefulDays.mobOreId;
		mobOreGen = new WorldGenMinable[7];
		mobOreGen[0] = new WorldGenMinable(blockId, 0, 8);
		mobOreGen[1] = new WorldGenMinable(blockId, 1, 8);
		mobOreGen[2] = new WorldGenMinable(blockId, 2, 8);
		mobOreGen[3] = new WorldGenMinable(blockId, 3, 7);
		mobOreGen[4] = new WorldGenMinable(blockId, 4, 7);
		mobOreGen[5] = new WorldGenMinable(blockId, 5, 7);
		mobOreGen[6] = new WorldGenMinable(blockId, 6, 8);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{		
		int chunkX_2 = chunkX << 4;
		int chunkZ_2 = chunkZ << 4;
        genOre(10, mobOreGen[0], 0, 64, random, world, chunkX_2, chunkZ_2);
        genOre(10, mobOreGen[1], 0, 64, random, world, chunkX_2, chunkZ_2);
        genOre(10, mobOreGen[2], 0, 64, random, world, chunkX_2, chunkZ_2);
        genOre(4, mobOreGen[3], 0, 32, random, world, chunkX_2, chunkZ_2);
        genOre(4, mobOreGen[4], 0, 16, random, world, chunkX_2, chunkZ_2);
        genOre(2, mobOreGen[5], 0, 16, random, world, chunkX_2, chunkZ_2);
        genOre(4, mobOreGen[6], 0, 32, random, world, chunkX_2, chunkZ_2);
	}
	
    public void genOre(int rarity, WorldGenerator worldGen, int low, int high, Random random, World world, int chunkX, int chunkZ)
    {
        for (int var5 = 0; var5 < rarity; ++var5)
        {
            int var6 = random.nextInt(16) + (chunkX);
            int var7 = random.nextInt(high - low) + low;
            int var8 = random.nextInt(16) + (chunkZ);
            worldGen.generate(world, random, var6, var7, var8);
        }
    }

}
