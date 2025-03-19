package com.notcharrow.notcharrowutils.keybinds;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class KeybindTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (KeybindRegistry.autoAttackKeybind.wasPressed()) {
				ConfigManager.config.tickregistryAutoAttack = !ConfigManager.config.tickregistryAutoAttack;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.autoFishKeybind.wasPressed()) {
				ConfigManager.config.tickregistryAutoFish = !ConfigManager.config.tickregistryAutoFish;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.autoRefillKeybind.wasPressed()) {
				ConfigManager.config.tickregistryAutoRefill = !ConfigManager.config.tickregistryAutoRefill;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.autoRocketKeybind.wasPressed()) {
				ConfigManager.config.tickregistryAutoRocket = !ConfigManager.config.tickregistryAutoRocket;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.autoSprintKeybind.wasPressed()) {
				ConfigManager.config.tickregistryAutoSprint = !ConfigManager.config.tickregistryAutoSprint;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.autoToolKeybind.wasPressed()) {
				ConfigManager.config.tickregistryAutoTool = !ConfigManager.config.tickregistryAutoTool;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.breadcrumbsKeybind.wasPressed()) {
				ConfigManager.config.tickregistryBreadcrumbs = !ConfigManager.config.tickregistryBreadcrumbs;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.fastPlaceKeybind.wasPressed()) {
				ConfigManager.config.tickregistryFastPlace = !ConfigManager.config.tickregistryFastPlace;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.nightVisionKeybind.wasPressed()) {
				ConfigManager.config.tickregistryNightVision = !ConfigManager.config.tickregistryNightVision;
				ConfigManager.saveConfig();
			} else if (KeybindRegistry.noFogKeybind.wasPressed()) {
				ConfigManager.config.mixinNoFog = !ConfigManager.config.mixinNoFog;
				ConfigManager.saveConfig();
			}
		});
	}
}