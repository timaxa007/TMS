package mods.timaxa007.pack.stock.blocks;

import java.util.List;

import mods.timaxa007.lib.Option;
import mods.timaxa007.pack.stock.PackStock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;

public class ItemBlockPizza extends ItemMultiTexture {

public ItemBlockPizza(Block id) {
super(id, PackStock.proxy.block_pizzas, BlockPizzas.typePizzas);
}

public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
NBTTagCompound tag = is.getTagCompound();
if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
list.add(Option.getText("Material")+": Pizza.");
	if(tag!=null) {
	if(tag.getInteger("Amount")!=0) {list.add("Amount: "+tag.getInteger("Amount")+".");}else{;}
	if(tag.getInteger("Type")!=0) {list.add(Option.getText("Type")+": "+tag.getInteger("Type")+".");}else{;}
	}else{;}
}else{
list.add(Option.prshift);
}
}

}
