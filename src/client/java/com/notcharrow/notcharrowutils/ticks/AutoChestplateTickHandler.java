package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.SlotActionType;

public class AutoChestplateTickHandler {
	private static float playerHealth = -1;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && ConfigManager.config.tickregistryAutoChestplate) {
				if (client.player.getHealth() < playerHealth) {
					PlayerInventory playerInventory = client.player.getInventory();
					if (playerInventory.getArmorStack(2).getItem() == Items.ELYTRA) {
						int slot = -1;
						for (int i = 0; i < client.player.getInventory().size(); i++) {
							ItemStack stack = client.player.getInventory().getStack(i);
							if (!stack.isEmpty() && stack.isIn(ItemTags.CHEST_ARMOR) && stack.getItem() != Items.ELYTRA) {
								slot = i;
								break;
							}
						}
						if (slot < 9) {
							slot += 36;
						} else if (slot == 40) {
							slot += 5;
						}

						swapItems(client, slot);
						client.player.sendMessage(TextFormat.styledText("Swapped to Chestplate because of damage."), false);
					}
				}
				playerHealth = client.player.getHealth();
			}
		});
	}
	private static void swapItems(MinecraftClient client, int fromSlot) {
		if (client.interactionManager != null && client.player != null) {
			client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, fromSlot, 0, SlotActionType.PICKUP, client.player);
			client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 6, 0, SlotActionType.PICKUP, client.player);
			client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, fromSlot, 0, SlotActionType.PICKUP, client.player);
		}
	}
}
