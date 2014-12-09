package mods.timaxa007.pack.furniture.blocks;

import mods.timaxa007.pack.furniture.PackFurniture;
import mods.timaxa007.pack.furniture.te.TECylinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCylinder extends Block implements ITileEntityProvider {

public BlockCylinder(int id) {
super(id, Material.iron);
setCreativeTab(PackFurniture.proxy.tabFurniturePack);
setTextureName("iron_block");
setUnlocalizedName("cylinder");
}

@Override
public TileEntity createNewTileEntity(World world) {return new TECylinder();}

public int getRenderType() {return PackFurniture.proxy.render_block_cylinder_modelID;}
public boolean isOpaqueCube() {return false;}
public boolean renderAsNormalBlock() {return false;}

}
