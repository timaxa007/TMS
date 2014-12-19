package mods.timaxa007.pack.stock.blocks;

import java.util.List;
import java.util.Random;

import mods.timaxa007.pack.stock.PackStock;
import mods.timaxa007.pack.stock.tile.TileEntityApiary;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockApiary extends BlockContainer {

	public static String[] type_beehives = new String[] {
		"Apiary", 
		"Heater", 
		"Cooler", 
		"Humidifier", 
		"Drying"
	};

	public BlockApiary() {
		super(Material.glass);
		setCreativeTab(PackStock.tab_apis);
		setHardness(0.5F);
		setResistance(1.0F);
		setBlockTextureName("planks_oak");
		setBlockName("apiary");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityApiary();
	}

	public int getRenderType() {return PackStock.proxy.render_block_apiary_modelID;}

	public int quantityDropped(Random random) {
		return 0;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int idPicked(World world, int x, int y, int z) {
		return 0;
	}

	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityApiary)
			return addTag(((TileEntityApiary)te).getName(), ((TileEntityApiary)te).getType());
		return null;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		TileEntity te = world.getTileEntity(x, y, z);
		NBTTagCompound tag = is.getTagCompound();

		if (te != null && te instanceof TileEntityApiary) {
			if (tag != null) {
				if (tag.hasKey("NameID")) ((TileEntityApiary)te).setName(tag.getString("NameID"));
				if (tag.hasKey("TypeID")) ((TileEntityApiary)te).setType(tag.getInteger("TypeID"));
			}

		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs table, List list) {
		for (int j = 0; j < type_beehives.length; ++j)
			list.add(addTag(type_beehives[j], 0));
		//list.add(new ItemStack(id, 1, 0));
	}

	private static ItemStack addTag(String par1, int par2) {
		ItemStack is = new ItemStack(PackStock.proxy.block_apiary, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("NameID", par1);
		tag.setInteger("TypeID", par2);
		is.setTagCompound(tag);
		return is;
	}

}
