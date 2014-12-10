package mods.timaxa007.pack.furniture.blocks;

import java.util.List;

import mods.timaxa007.pack.furniture.PackFurniture;
import mods.timaxa007.pack.furniture.te.TECnstorDoors;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCnstorDoor extends Block implements ITileEntityProvider {

@SideOnly(Side.CLIENT) private IIcon[] icon_array;

public BlockCnstorDoor() {
super(Material.glass);
setCreativeTab(PackFurniture.proxy.tabFurniturePack);
setHardness(1.0F);
setResistance(3.5F);
setLightOpacity(0);
setStepSound(soundTypeWood);
setBlockTextureName("timaxa007:woodFrame");
setBlockName("cnstor.door");
}

@Override
public TileEntity createNewTileEntity(World world, int meta) {return new TECnstorDoors();}

public int idPicked(World world, int x, int y, int z) {return 0;}

public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
TileEntity te = world.getTileEntity(x, y, z);
if (te != null && te instanceof TECnstorDoors) {
return addTag(world.getBlock(x, y, z), ((TECnstorDoors)te).getSubID(), ((TECnstorDoors)te).getColorBlock());
} else {
return addTag(world.getBlock(x, y, z), 0, 0xFFFFFF);
}
}

@Override
public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
TileEntity te = world.getTileEntity(x, y, z);
NBTTagCompound tag = is.getTagCompound();
if (te != null && te instanceof TECnstorDoors && tag != null) {
if (tag.hasKey("SubID")) {((TECnstorDoors)te).setSubID((int)tag.getByte("SubID"));}
if (tag.hasKey("ColorBlock")) {((TECnstorDoors)te).setColorBlock(tag.getInteger("ColorBlock"));}
if (entity instanceof EntityPlayer) {((TECnstorDoors)te).setOwner(((EntityPlayer)entity).getDisplayName());}
}
}

@Override
public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
int ib = 0;
TileEntity te = world.getTileEntity(x, y, z);
//if(!world.isRemote) {return false;}
if (te == null || player.isSneaking()) {return false;}

if (te != null && te instanceof TECnstorDoors) {
ItemStack current = player.getCurrentEquippedItem();

if (ib == 0) {//

if (current != null && current.getItem() == PackFurniture.proxy.items_for_furniture) {
//if (!player.capabilities.isCreativeMode) {--current.stackSize;}
NBTTagCompound tag = current.getTagCompound();
if (tag != null && tag.hasKey("CordX") && tag.hasKey("CordY") && tag.hasKey("CordZ")) {
if (tag.getInteger("CordX") == x && tag.getInteger("CordY") == y && tag.getInteger("CordZ") == z) {
((TECnstorDoors)te).openDoor(true);
} else {
//if (world.isRemote) {player.addChatMessage("This key does not fit into this door.");}
return false;
}
} else {
if (tag == null) {tag = new NBTTagCompound();}
tag.setInteger("CordX", x);
tag.setInteger("CordY", y);
tag.setInteger("CordZ", z);
current.setTagCompound(tag);
}
}

}//

if (ib == 1) {//

if (world.provider.isDaytime()) {
if (player.getDisplayName().equals(((TECnstorDoors)te).getOwner())) {
((TECnstorDoors)te).openDoor(true);
} else {
return false;
}
} else {
((TECnstorDoors)te).openDoor(true);
}

}//

if (current != null && current.getItem() instanceof ItemAxe) {
current.setItemDamage(current.getItemDamage() + 1);
((TECnstorDoors)te).breakDoor(true);
}

return true;
}

return false;
}

@Override
public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
if (!world.isRemote) {
TileEntity te = world.getTileEntity(x, y, z);
if (te != null && te instanceof TECnstorDoors && !player.capabilities.isCreativeMode) {
dropBlockAsItem(world, x, y, z, addTag(world.getBlock(x, y, z), ((TECnstorDoors)te).getSubID(), ((TECnstorDoors)te).getColorBlock()));
world.removeTileEntity(x, y, z);
world.setBlockToAir(x, y, z);
}
}
}

@SideOnly(Side.CLIENT)
public void getSubBlocks(Item id, CreativeTabs table, List list) {

list.add(addTag(id, 0, 0xFFFFFF));

//list.add(new ItemStack(id, 1, 0));
}

private static ItemStack addTag(Block par1, int par2, int par3) {
return addTag(Item.getItemFromBlock(par1), par2, par3);
}

private static ItemStack addTag(Item par1, int par2, int par3) {
ItemStack is = new ItemStack(par1, 1, 0);
NBTTagCompound tag = new NBTTagCompound();
tag.setByte("SubID", (byte)par2);
tag.setInteger("ColorBlock", par3);
is.setTagCompound(tag);
return is;
}
/*
@SideOnly(Side.CLIENT)
public void registerIcons(IIconRegister ir) {
icon_array = new IIcon[type_block.length];
blockIcon = ir.registerIcon("planks_oak");
for (int i = 0; i < icon_array.length; ++i) {
icon_array[i] = ir.registerIcon("timaxa007:" + "wood/" + type_block[i] + "_overlay");
}
}
*/
}
