package mods.timaxa007.pack.furniture.event;

import mods.timaxa007.pack.furniture.PackFurniture;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventFurniture {
	//--------------------------------------------------------------------------------------------------------------
	@SubscribeEvent
	public void addNewBottle(PlayerInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		ItemStack current = player.getCurrentEquippedItem();
		if (player != null && world != null && event.action == event.action.RIGHT_CLICK_BLOCK) {
			if (current != null && current.getItem() == Items.glass_bottle && world.getBlock(event.x, event.y, event.z) == Blocks.stone) {
				--current.stackSize;
				//player.inventory.addItemStackToInventory(new ItemStack(PackStock.proxy.item_drinks, 1, 0));
				event.setResult(Result.ALLOW);
			}
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	@SubscribeEvent
	public void onLampOn(PlayerInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		ItemStack current = player.getCurrentEquippedItem();
		if (current == null) {
			if (player != null && world != null && !world.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK) {
				Block block = world.getBlock(event.x, event.y, event.z);
				if (block != null && block == PackFurniture.proxy.block_lamps_click_off) {
					System.out.println("on");
					event.setResult(Result.ALLOW);
				}
			}
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	@SubscribeEvent
	public void onLampOff(PlayerInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		ItemStack current = player.getCurrentEquippedItem();
		if (current == null) {
			if (player != null && world != null && !world.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK) {
				Block block = world.getBlock(event.x, event.y, event.z);
				if (block != null && block == PackFurniture.proxy.block_lamps_click_on) {
					System.out.println("off");
					event.setResult(Result.ALLOW);
				}
			}
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	/*@SubscribeEvent
	public void onLampOn(PlayerInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		if (player != null && world != null && !world.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK) {
			Block block = world.getBlock(event.x, event.y, event.z);
			TileEntity te = world.getTileEntity(event.x, event.y, event.z);
			if (block != null && te != null && te instanceof TileEntityLampsOff) {
				System.out.println("on");
				te = new TileEntityLampsOn();
				event.setResult(Result.ALLOW);
			}
		}
	}*/
	//--------------------------------------------------------------------------------------------------------------
	/*@SubscribeEvent
	public void onLampOff(PlayerInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		if (player != null && world != null && !world.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK) {
			Block block = world.getBlock(event.x, event.y, event.z);
			TileEntity te = world.getTileEntity(event.x, event.y, event.z);
			if (block != null && te != null && te instanceof TileEntityLampsOn) {
				System.out.println("off");
				te = new TileEntityLampsOff();
				event.setResult(Result.ALLOW);
			}
		}
	}*/
	//--------------------------------------------------------------------------------------------------------------
}
