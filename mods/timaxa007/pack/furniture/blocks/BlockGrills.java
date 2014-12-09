package mods.timaxa007.pack.furniture.blocks;

import java.util.List;
import java.util.Random;

import mods.timaxa007.pack.furniture.PackFurniture;
import mods.timaxa007.pack.furniture.te.TEGrills;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrills extends BlockContainer{
private static boolean keepFurnaceInventory;
private final Random furnaceRand=new Random();
private boolean isActive;

public BlockGrills(int id) {
super(id, Material.iron);
setHardness(0.5F);
setCreativeTab(PackFurniture.proxy.tabFurniturePack);
setTextureName("planks_oak");
setUnlocalizedName("grills");
}

@Override
public TileEntity createNewTileEntity(World wrd) {return new TEGrills();}

public int getRenderType() {return -1;}
public boolean renderAsNormalBlock() {return false;}
public boolean isOpaqueCube() {return false;}
public int idPicked(World wrd, int x, int y, int z) {return 0;}

public ItemStack getPickBlock(MovingObjectPosition target, World wrd, int x, int y, int z) {
TileEntity te=wrd.getBlockTileEntity(x, y, z);
if((te!=null)&&(te instanceof TEGrills)) {
return addTag(((TEGrills)te).getTypes());
}
return addTag(0);
}

@Override
public boolean onBlockActivated(World wrd, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
TileEntity te=wrd.getBlockTileEntity(x, y, z);
//if(!wrd.isRemote) {return false;}
if(player.isSneaking()) {return false;}
if((te!=null)&&(te instanceof TEGrills)) {
player.openGui(PackFurniture.instance, PackFurniture.proxy.guiGrills, wrd, x, y, z);
return true;
}
return false;
}

public void breakBlock(World wrd, int x, int y, int z, int blkid, int blkmeta) {
if(!keepFurnaceInventory) {
TEGrills tileentityfurnace=(TEGrills)wrd.getBlockTileEntity(x, y, z);

if(tileentityfurnace!=null) {
for(int j1=0;j1 < tileentityfurnace.getSizeInventory();++j1) {
ItemStack itemstack=tileentityfurnace.getStackInSlot(j1);

if(itemstack!=null) {
float f=furnaceRand.nextFloat()*0.8F+0.1F;
float f1=furnaceRand.nextFloat()*0.8F+0.1F;
float f2=furnaceRand.nextFloat()*0.8F+0.1F;

while (itemstack.stackSize>0) {
int k1=furnaceRand.nextInt(21)+10;

if(k1>itemstack.stackSize) {k1=itemstack.stackSize;}

itemstack.stackSize-=k1;
EntityItem entityitem=new EntityItem(wrd, (double)((float)x+f), (double)((float)y+f1), (double)((float)z+f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

if(itemstack.hasTagCompound()) {
entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
}

float f3=0.05F;
entityitem.motionX=(double)((float)furnaceRand.nextGaussian()*f3);
entityitem.motionY=(double)((float)furnaceRand.nextGaussian()*f3+0.2F);
entityitem.motionZ=(double)((float)furnaceRand.nextGaussian()*f3);
wrd.spawnEntityInWorld(entityitem);
}
}
}

wrd.func_96440_m(x, y, z, blkid);
}
}

super.breakBlock(wrd, x, y, z, blkid, blkmeta);
}

@Override
public void onBlockPlacedBy(World wrd, int x, int y, int z, EntityLivingBase el, ItemStack is) {
TileEntity te=wrd.getBlockTileEntity(x, y, z);
NBTTagCompound tag = is.getTagCompound();
if((te!=null)&&(te instanceof TEGrills)) {

int l=MathHelper.floor_double((double)(el.rotationYaw*4.0F/360.0F)+0.5D)&3;
((TEGrills)te).setRot(l);

if(tag!=null) {
((TEGrills)te).setTypes(tag.getInteger("Type"));
}else{
((TEGrills)te).setTypes(0);
}

}
}

@SideOnly(Side.CLIENT)
public void getSubBlocks(int id, CreativeTabs table, List list) {
//for(int j=0;j<TileTexture.tt01.length;++j) {
int j=0;
list.add(addTag(j));
//}
//list.add(new ItemStack(id, 1, 0));
}

private static ItemStack addTag(int par1) {
ItemStack is=new ItemStack(PackFurniture.proxy.block_grills, 1, 0);
NBTTagCompound tag=new NBTTagCompound();
tag.setInteger("Type", par1);
is.setTagCompound(tag);
return is;
}

}
