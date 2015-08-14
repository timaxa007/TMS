package timaxa007.module.substance.block;

import timaxa007.pack.furniture.PackFurniture;
import timaxa007.pack.conjoint.object.ModifiedBlock;
import net.minecraft.block.material.Material;

public class BlockSubstanceFakeWaterStatic extends ModifiedBlock {

	public BlockSubstanceFakeWaterStatic(String tag) {
		super(tag, Material.water);
		setCreativeTab(PackFurniture.tab_furniture);
		setHardness(100.0F);
	}

}
