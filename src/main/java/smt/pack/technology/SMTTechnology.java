package smt.pack.technology;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = SMTTechnology.MODID, name = SMTTechnology.NAME, version = SMTTechnology.VERSION, dependencies = "required-after:SMT|CORE", useMetadata = true)
public class SMTTechnology {
	//-----------------------------------------------------------------------------------------------
	public static final String
	MODID = "SMT|TECHNOLOGY",
	NAME = "SMT: Technology",
	TAG = "smt_technology",
	VERSION = "0.001";
	//-----------------------------------------------------------------------------------------------
	@Mod.Instance(MODID) public static SMTTechnology instance;
	//-----------------------------------------------------------------------------------------------
	public static smt.pack.technology.config.Config config;
	public static smt.pack.technology.block.ListBlocks blocks;
	public static smt.pack.technology.item.ListItems items;
	//-----------------------------------------------------------------------------------------------
	public static final CreativeTabs tabTechnology = new CreativeTabs("tabTechnology") {
		@Override public net.minecraft.item.Item getTabIconItem() {return items.itemsTechnology;}
	};
	//-----------------------------------------------------------------------------------------------
	@Mod.EventHandler
	public void preInit(cpw.mods.fml.common.event.FMLPreInitializationEvent event) {
		applyMetadata(event.getModMetadata());
		config.main(event.getModConfigurationDirectory());
		blocks.init();
		items.init();
	}
	//-----------------------------------------------------------------------------------------------
	public void applyMetadata(ModMetadata meta) {
		meta.url = "http://forum.mcmodding.ru/viewtopic.php?id=4";
		meta.authorList = Lists.newArrayList("timaxa007", "Dragon2488");
		meta.description = "SMT: Technology.";
		meta.logoFile = "/smt_technology_logo.png";
		meta.autogenerated = false;
	}
	//-----------------------------------------------------------------------------------------------
}
