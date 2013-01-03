package laco.mods.peaceful.client;

import laco.mods.peaceful.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
	}
}
