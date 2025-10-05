package com.notcharrow.notcharrowutils.keybinds;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class KeybindRegistry {
	public static KeyBinding autoAttackKeybind;
	public static KeyBinding autoFishKeybind;
	public static KeyBinding autoRefillKeybind;
	public static KeyBinding autoRocketKeybind;
	public static KeyBinding autoSprintKeybind;
	public static KeyBinding autoToolKeybind;
	public static KeyBinding breadcrumbsKeybind;
	public static KeyBinding fastPlaceKeybind;
	public static KeyBinding hotbarCycleDownKeybind;
	public static KeyBinding hotbarCycleUpKeybind;
	public static KeyBinding nightVisionKeybind;
	public static KeyBinding noFogKeybind;
	public static KeyBinding worldIconKeybind;
	public static KeyBinding zoomKeybind;

	// region Secondary Hotbar Keybinds
	public static KeyBinding hotbarSlot1Keybind;
	public static KeyBinding hotbarSlot2Keybind;
	public static KeyBinding hotbarSlot3Keybind;
	public static KeyBinding hotbarSlot4Keybind;
	public static KeyBinding hotbarSlot5Keybind;
	public static KeyBinding hotbarSlot6Keybind;
	public static KeyBinding hotbarSlot7Keybind;
	public static KeyBinding hotbarSlot8Keybind;
	public static KeyBinding hotbarSlot9Keybind;
	// endregion Secondary Hotbar Keybinds

	public static void registerKeybinds() {
		KeyBinding.Category mainCategory = KeyBinding.Category.create(Identifier.of("notcharrowutils.main"));
		
		autoAttackKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoAttackKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		autoRefillKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoRefillKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		autoFishKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoFishKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		autoRocketKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoRocketKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		autoSprintKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoSprintKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		autoToolKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoToolKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		breadcrumbsKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.breadcrumbsKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		fastPlaceKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.fastPlaceKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		hotbarCycleUpKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbarCycleUpKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		hotbarCycleDownKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbarCycleDownKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		nightVisionKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.nightVisionKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		noFogKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.noFogKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		worldIconKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.worldIconKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		zoomKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.zoomKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				mainCategory));

		// region Secondary Hotbar Keybinds
		KeyBinding.Category hotbarCategory = KeyBinding.Category.create(Identifier.of("notcharrowutils.hotbar"));
		
		hotbarSlot1Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot1Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot2Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot2Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot3Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot3Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot4Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot4Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot5Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot5Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot6Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot6Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot7Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot7Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot8Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot8Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));

		hotbarSlot9Keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.hotbar.hotbarSlot9Keybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				hotbarCategory));
		// endregion Secondary Hotbar Keybinds
	}
}
