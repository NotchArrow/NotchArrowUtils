package com.notcharrow.notcharrowutils.keybinds;

import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.SlotActionType;

public class ElytraKeybind {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (KeybindRegistry.elytraKeybind.wasPressed()
					&& client.player != null && client.interactionManager != null) {
				int slot = -1;
				String itemEquipped = "";
				for (int i = 0; i < client.player.getInventory().size(); i++) {
					ItemStack stack = client.player.getInventory().getStack(i);
					if (!stack.isEmpty() && (stack.isIn(ItemTags.CHEST_ARMOR) || stack.getItem() == Items.ELYTRA)) {
						if (stack.getItem() == Items.ELYTRA) {
							itemEquipped = "Elytra equipped.";
						} else {
							itemEquipped = "Chestplate equipped.";
						}
						slot = i;
						break;
					}
				}
				if (!itemEquipped.isEmpty() && slot != 38) {
					if (slot < 9) {
						slot += 36;
					} else if (slot == 40) {
						slot += 5;
					}
					client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, client.player);
					client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 6, 0, SlotActionType.PICKUP, client.player);
					client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, client.player);
					client.player.sendMessage(TextFormat.styledText(itemEquipped), false);
				}
			}
		});
	}
}
