package mods.timaxa007.pack.techno.blocks;

import java.util.List;

import mods.timaxa007.lib.Option;
import mods.timaxa007.pack.techno.PackTechno;
import mods.timaxa007.pack.techno.te.TEModuleMovement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ItemBlockModuleMovement extends ItemBlock{

public ItemBlockModuleMovement() {
super();
this.setMaxDamage(0);
this.setHasSubtypes(true);
}

public boolean onItemUse(ItemStack is, EntityPlayer player, World wrd, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
if(!player.canPlayerEdit(x, y, z, meta, is)) {return false;}
else{
if(hitX==1.0F) {x++;}
if(hitX==0.0F) {x--;}
if(hitZ==1.0F) {z++;}
if(hitZ==0.0F) {z--;}
if(hitY==1.0F) {y++;}
if(hitY==0.0F) {y--;}

wrd.setBlock(x, y, z, PackTechno.proxy.block_module_movement, 0, 3);
TileEntity te=wrd.getTileEntity(x, y, z);
NBTTagCompound tag = is.getTagCompound();
if((te!=null)&&(te instanceof TEModuleMovement)) {

int l=MathHelper.floor_double((double)(player.rotationYaw*4.0F/360.0F)+0.5D)&3;
((TEModuleMovement)te).setRot(l);

if(tag!=null) {
((TEModuleMovement)te).setTypes(tag.getInteger("Type"));
}else{
((TEModuleMovement)te).setTypes(0);
}
}
return true;
}
}

public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
NBTTagCompound tag = is.getTagCompound();
if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)||Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
if(tag!=null) {
if(tag.hasKey("Type")) {
list.add("Type: "+tag.getInteger("Type")+".");
}
}
}else{
list.add(Option.prshift);
}
}

}
