package mods.timaxa007.pack.furniture.blocks;

import mods.timaxa007.lib.GetColors;
import mods.timaxa007.pack.furniture.PackFurniture;
import net.minecraft.item.ItemMultiTextureTile;

public class ItemBlockModelLampsOff extends ItemMultiTextureTile{
public ItemBlockModelLampsOff(int id) {
super(id, PackFurniture.proxy.block_model_lamps_off, GetColors.getNameColors);
}

}
