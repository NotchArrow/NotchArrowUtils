package com.notcharrow.notcharrowutils.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerInventory;

public class SecondaryHotbarKeybinds {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {
				PlayerInventory inventory = client.player.getInventory();
				if (KeybindRegistry.hotbarSlot1Keybind.isPressed()) {
					inventory.setSelectedSlot(0);
				} else if (KeybindRegistry.hotbarSlot2Keybind.isPressed()) {
					inventory.setSelectedSlot(1);
				} else if (KeybindRegistry.hotbarSlot3Keybind.isPressed()) {
					inventory.setSelectedSlot(2);
				} else if (KeybindRegistry.hotbarSlot4Keybind.isPressed()) {
					inventory.setSelectedSlot(3);
				} else if (KeybindRegistry.hotbarSlot5Keybind.isPressed()) {
					inventory.setSelectedSlot(4);
				} else if (KeybindRegistry.hotbarSlot6Keybind.isPressed()) {
					inventory.setSelectedSlot(5);
				} else if (KeybindRegistry.hotbarSlot7Keybind.isPressed()) {
					inventory.setSelectedSlot(6);
				} else if (KeybindRegistry.hotbarSlot8Keybind.isPressed()) {
					inventory.setSelectedSlot(7);
				} else if (KeybindRegistry.hotbarSlot9Keybind.isPressed()) {
					inventory.setSelectedSlot(8);
				}
			}
		});
	}
}
