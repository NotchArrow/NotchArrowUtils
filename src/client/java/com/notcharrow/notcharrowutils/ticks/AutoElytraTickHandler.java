package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.SlotActionType;

public class AutoElytraTickHandler {
	private static boolean swapBack = false;
	private static boolean swapOnJump = false;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.interactionManager != null && ConfigManager.config.tickregistryAutoElytra) {
				if (!client.player.isOnGround() && !client.options.jumpKey.isPressed() && !client.player.isInFluid()) {
					swapOnJump = true;
				}
				if (swapOnJump
						&& client.options.jumpKey.isPressed() && !client.player.isOnGround()
						&& client.player.getInventory().getStack(38).isIn(ItemTags.CHEST_ARMOR)) {
					int slot = -1;
					for (int i = 0; i < client.player.getInventory().size(); i++) {
						ItemStack stack = client.player.getInventory().getStack(i);
						if (stack.getItem() == Items.ELYTRA) {
							slot = i;
							break;
						}
					}
					if (slot != -1) {
						if (slot < 9) {
							slot += 36;
						} else if (slot == 40) {
							slot += 5;
						}
						client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, client.player);
						client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 6, 0, SlotActionType.PICKUP, client.player);
						client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, client.player);
						swapBack = true;
						swapOnJump = false;

						client.player.networkHandler.sendPacket(new ClientCommandC2SPacket(
								client.player, ClientCommandC2SPacket.Mode.START_FALL_FLYING
						));
					}
				}

				if (client.player.isOnGround()) {
					swapOnJump = false;
					if (swapBack && client.player.isOnGround() && client.player.getInventory().getStack(38).getItem() == Items.ELYTRA) {
						swapBack = false;

						int slot = -1;
						for (int i = 0; i < client.player.getInventory().size(); i++) {
							ItemStack stack = client.player.getInventory().getStack(i);
							if (stack.isIn(ItemTags.CHEST_ARMOR)) {
								slot = i;
								break;
							}
						}
						if (slot != -1) {
							if (slot < 9) {
								slot += 36;
							} else if (slot == 40) {
								slot += 5;
							}
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 6, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, client.player);
						}
					}
				}
			}
		});
	}
}
