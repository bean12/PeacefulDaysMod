package laco.mods.peaceful;

import laco.util.packet.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { Reference.CHANNEL_NAME }, packetHandler = PacketHandler.class)
public class PeacefulDays
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@Mod.Instance(Reference.MOD_ID)
	public PeacefulDays instance;

	public static int mobOreId;
	public static Block mobOre;

	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());
		config.load();
		mobOreId = config.get(Configuration.CATEGORY_BLOCK, "mobOreBlockId", 500).getInt();
		config.save();
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		mobOre = new BlockMobOre(mobOreId);
		GameRegistry.registerBlock(mobOre, ItemMobOre.class, "mobOre");
		GameRegistry.registerWorldGenerator(new MobOreGenerator());
		for (int meta = 0; meta < 7; meta++)
		{
			ItemStack mobOreStack = new ItemStack(mobOre, 1, meta);
			LanguageRegistry.addName(mobOreStack, mobOreNames[mobOreStack.getItemDamage()]);
		}
		RecipeRegistry.instance().addRecipe();
		proxy.registerRenderers();
	}

	private static final String[] mobOreNames = { "Creeper Ore", "Skeleton Ore", "Spider Ore", "Enderman Ore", "Blaze Ore", "Ghast Ore", "Slime Ore" };
}
