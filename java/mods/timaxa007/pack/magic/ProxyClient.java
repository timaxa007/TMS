package mods.timaxa007.pack.magic;

import mods.timaxa007.pack.magic.event.EventMagicClient;
import mods.timaxa007.pack.magic.event.EventMana;
import mods.timaxa007.pack.magic.lib.Spells;
import mods.timaxa007.tms.util.IProxy;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon implements IProxy {

	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(new EventMagicClient());

	}

	public void init() {
		super.init();

		MinecraftForge.EVENT_BUS.register(new Spells.EventSpellsClient());
		FMLCommonHandler.instance().bus().register(new EventMana.Client());

		render.init();

	}

}
