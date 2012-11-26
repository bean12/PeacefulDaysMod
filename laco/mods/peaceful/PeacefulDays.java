package laco.mods.peaceful;

import net.minecraftforge.common.Configuration;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "PeacefulDays", name = "Peaceful Days", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "PeacefulDays" }, packetHandler = PacketHandler.class)
public class PeacefulDays
{
	@SidedProxy(clientSide = "laco.mods.peaceful.client.ClientProxy", serverSide = "laco.mods.peaceful.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("PeacefulDays")
	public PeacefulDays instance;

	public static int mobOreId;
	public static Block mobOre;

	@Init
	public void init(FMLInitializationEvent event)
	{
		mobOre = new BlockMobOre(mobOreId);
		GameRegistry.registerBlock(mobOre, ItemMobOre.class);
		GameRegistry.registerWorldGenerator(new MobOreGenerator());
		for (int meta = 0; meta < 7; meta++)
		{
			ItemStack mobOreStack = new ItemStack(mobOre, 1, meta);
			LanguageRegistry.addName(mobOreStack, mobOreNames[mobOreStack.getItemDamage()]);
		}
		RecipeRegistry.instance().addRecipe();
		proxy.registerRenderers();
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());
		config.load();
		mobOreId = config.get(Configuration.CATEGORY_BLOCK, "mobOreBlockId", 500).getInt();
		config.save();
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent evt)
	{
	}

	private static final String[] mobOreNames = { "Creeper Ore", "Skeleton Ore", "Spider Ore", "Enderman Ore", "Blaze Ore", "Ghast Ore", "Slime Ore" };
}
