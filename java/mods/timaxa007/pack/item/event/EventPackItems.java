package mods.timaxa007.pack.item.event;

import mods.timaxa007.pack.item.item.ToolShield;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventPackItems {
	//--------------------------------------------------------------------------------------------------------------
	@SubscribeEvent
	public void onShieldsBlock(LivingHurtEvent e) {
		if (e.source instanceof EntityDamageSource) {
			EntityDamageSource dmgSource = (EntityDamageSource)e.source;
			Entity from = dmgSource.getEntity();//Кто наносит урон,
			EntityLivingBase to = e.entityLiving;//Кому наноситься урон,

			if (from instanceof EntityLivingBase && to instanceof EntityPlayer) {
				//Если кто наносит урон являеться игрок и кому наноситься урон являеться игрок, то...

				EntityLivingBase entity_from = (EntityLivingBase)from;
				ItemStack current_from = entity_from.getEquipmentInSlot(0);//Что дерижит кто наносит урон

				EntityPlayer player_to = (EntityPlayer)to;
				ItemStack current_to = player_to.getCurrentEquippedItem();//Что дерижит кому наносится урон

				if (current_to != null && current_to.getItem() instanceof ToolShield && player_to.isUsingItem()) {
					//Если рука кому наноситься урон, непуста и находиться щит и приэтом типа ПКМ
					e.ammount = 0;//Урона нуль
					if (current_from != null && current_from.getItem() instanceof ItemSword) {
						//Если рука кто наносит урон не пуста, и имеет в руке меч
						current_from.damageItem(((ToolShield)current_to.getItem()).getDamage()*100, entity_from);
						//то предмет который в руке будет поврежаться.
					}

				}

			}

		}
	}
	//--------------------------------------------------------------------------------------------------------------
}
