package mods.timaxa007.pack.weapon.blocks;

import mods.timaxa007.pack.weapon.PackWeapon;
import mods.timaxa007.pack.weapon.te.TEClaymore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockClaymore extends BlockContainer {

public BlockClaymore(int id) {
super(id, Material.circuits);
setCreativeTab(PackWeapon.proxy.tabWeaponPack);
setHardness(0.5F);
setResistance(1.0F);
setBlockBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.5F, 0.8125F);
setUnlocalizedName("claymore");
}

@Override
public TileEntity createNewTileEntity(World wrd) {return new TEClaymore();}

public int getRenderType() {return -1;}
public boolean renderAsNormalBlock() {return false;}
public boolean isOpaqueCube() {return false;}

public void onEntityCollidedWithBlock(World wrd, int x, int y, int z, Entity entity) {
if(entity instanceof EntityLivingBase) {
wrd.createExplosion(entity, x, y, z, 5, true);
}else{}
}

public void onBlockDestroyedByExplosion(World wrd, int x, int y, int z, Explosion explos) {
if(!wrd.isRemote);
}


@Override
public void onBlockPlacedBy(World wrd, int x, int y, int z, EntityLivingBase el, ItemStack is) {
int l = MathHelper.floor_double((double)(el.rotationYaw*4.0F/360.0F)+0.5D)&3;
wrd.setBlock(x, y, z, blockID, l, 3);
}

public AxisAlignedBB getCollisionBoundingBoxFromPool(World wrd, int x, int y, int z) {return null;}

}
