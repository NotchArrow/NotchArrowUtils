package com.notcharrow.notcharrowutils.keybinds;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeybindRegistry {
	public static KeyBinding autoAttackKeybind;
	public static KeyBinding autoFishKeybind;
	public static KeyBinding autoRefillKeybind;
	public static KeyBinding autoRocketKeybind;
	public static KeyBinding autoSprintKeybind;
	public static KeyBinding autoToolKeybind;
	public static KeyBinding breadcrumbsKeybind;
	public static KeyBinding fastPlaceKeybind;
	public static KeyBinding nightVisionKeybind;
	public static KeyBinding noFogKeybind;

	public static void registerKeybinds() {
		autoAttackKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoAttackKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		autoRefillKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoRefillKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		autoFishKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoFishKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		autoRocketKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoRocketKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		autoSprintKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoSprintKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		autoToolKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.autoToolKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		breadcrumbsKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.breadcrumbsKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		fastPlaceKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.fastPlaceKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		nightVisionKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.nightVisionKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));

		noFogKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.notcharrowutils.noFogKeybind",
				InputUtil.UNKNOWN_KEY.getCode(),
				"category.notcharrowutils"));
	}
}
