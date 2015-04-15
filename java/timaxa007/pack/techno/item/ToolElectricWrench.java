package timaxa007.pack.techno.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import timaxa007.module.control_button.api.IActionMouse;
import timaxa007.module.control_button.api.IActionPrimaryKey;
import timaxa007.pack.techno.PackTechno;
import timaxa007.pack.techno.packet.MessageTechnoTool;
import timaxa007.pack.techno.util.ITechnoTool;
import timaxa007.tms.util.ModifiedItem;
import timaxa007.tms.util.UtilString;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToolElectricWrench extends ModifiedItem implements IActionMouse, IActionPrimaryKey, ITechnoTool {
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT) public static boolean isLeftClick;
	@SideOnly(Side.CLIENT) public static boolean isRightClick;

	@SideOnly(Side.CLIENT) public static boolean isReload;
	@SideOnly(Side.CLIENT) public static boolean isCharge;
	@SideOnly(Side.CLIENT) public static boolean isMode;
	@SideOnly(Side.CLIENT) public static boolean isAction;
	@SideOnly(Side.CLIENT) public static boolean isModeIn;
	@SideOnly(Side.CLIENT) public static boolean isModeOut;

	private static String[] modes = new String[] {
		"None", 			//0
		"Dismantling", 		//1
		"Configuration",	//2
		"Crafting"			//3
	};
	//--------------------------------------------------------------------------------------------------------------
	public ToolElectricWrench(String tag) {
		super(tag);
		setCreativeTab(PackTechno.tab_techno);
		setMaxDamage(1000);
		setMaxStackSize(1);
		setNoRepair();
		setTextureName("timaxa007:tool/electric/wrench");
	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onLeftClickTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onLeftClickClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isLeftClick = isPress;

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onRightClickTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onRightClickClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isRightClick = isPress;

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onReloadTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onReloadClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isReload = isPress;

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onChargeTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onChargeClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isCharge = isPress;

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onModeTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onModeClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isMode = isPress;

		if (isPress && !isLeftClick) {
			NBTTagCompound nbt = is.getTagCompound();

			if (nbt != null && nbt.hasKey("ModeID")) {
				int nbn = nbt.getInteger("ModeID");

				if (nbn >= 2) nbn = 0; else nbn++;

				player.addChatMessage(new ChatComponentText(
						EnumChatFormatting.GOLD + "[Wrench]: " + EnumChatFormatting.RESET + modes[nbn] + ".")
						);

				PackTechno.network.sendToServer(new MessageTechnoTool(1));

			}

		}

	}

	@Override
	public void mode(ItemStack is, World world, EntityPlayer player) {
		NBTTagCompound nbt = is.getTagCompound();

		if (nbt != null && nbt.hasKey("ModeID")) {
			int nbn = nbt.getInteger("ModeID");

			if (nbn >= 2) nbn = 0; else nbn++;

			nbt.setInteger("ModeID", nbn);
			is.setTagCompound(nbt);
		}

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onActionTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onActionClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isAction = isPress;

		if (isPress && !isLeftClick) {
			PackTechno.network.sendToServer(new MessageTechnoTool(2));
		}

	}

	@Override
	public void working(ItemStack is, World world, EntityPlayer player) {
		NBTTagCompound nbt = is.getTagCompound();

		if (nbt != null) {
			if (nbt.hasKey("Working")) {
				nbt.setBoolean("Working", (nbt.getBoolean("Working") ? false : true));
			} else {
				nbt.setBoolean("Working", true);
			}
		}

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onModeInTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onModeInClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isModeIn = isPress;

	}
	//--------------------------------------------------------------------------------------------------------------
	@SideOnly(Side.CLIENT)
	public void onModeOutTickClient(ItemStack is, World world, EntityPlayer player, int tick) {

	}

	@SideOnly(Side.CLIENT)
	public void onModeOutClient(ItemStack is, World world, EntityPlayer player, boolean isPress) {
		isModeOut = isPress;

	}
	//--------------------------------------------------------------------------------------------------------------
	public float getDigSpeed(ItemStack is, Block block, int metadata) {
		NBTTagCompound nbt = is.getTagCompound();
		boolean isWorking = false;

		if (nbt != null) {

			if (nbt.hasKey("Working")) {
				isWorking = nbt.getBoolean("Working");

				Material material = block.getMaterial();
				if (material == PackTechno.techno_block)
					return isWorking ? 10.0F : 1.0F;

				//for hing speed with Upgrade or setting speed-up

				//Upgrade


			}
		}
		return isWorking ? 0.5F : 0.1F;
	}

	public boolean hitEntity(ItemStack is, EntityLivingBase entity1, EntityLivingBase entity2) {
		NBTTagCompound nbt = is.getTagCompound();
		is.damageItem((entity2.worldObj.rand.nextInt(3) + 2), entity2);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack is, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
		NBTTagCompound nbt = is.getTagCompound();

		Material material = block.getMaterial();
		if (material == PackTechno.techno_block) {
			is.damageItem(1, entity);
			return true;
		}

		is.damageItem((int)(/*world.getBlock(x, y, z)*/block.getBlockHardness(world, x, y, z) * 100), entity);
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {return true;}
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack is) {return true;}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		return super.onItemRightClick(is, world, player);
	}

	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		NBTTagCompound nbt = is.getTagCompound();
		if (UtilString.isShiftKeyDown()) {
			if (nbt != null) {
				if (nbt.hasKey("ModeID"))
					list.add("ModeID: " + modes[nbt.getInteger("ModeID")] + " / " + nbt.getInteger("ModeID") + ".");

				if (nbt.hasKey("Working"))
					list.add("Working: " + (nbt.getBoolean("Working") ? "on" : "off") + ".");
			}
		} else list.add(UtilString.hldshiftinf);

	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs table, List list) {
		list.add(addNBT());
		//list.add(new ItemStack(id, 1, 0));
	}

	public static ItemStack addNBT() {
		ItemStack is = new ItemStack(PackTechno.item.tool_electric_wrench, 1, 0);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("ModeID", 0);
		nbt.setByte("RTM", (byte)1);
		nbt.setBoolean("Working", false);
		is.setTagCompound(nbt);
		return is;
	}

}