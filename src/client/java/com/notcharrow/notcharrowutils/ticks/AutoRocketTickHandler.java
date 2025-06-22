package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class AutoRocketTickHandler {
	private static long lastRocketTime = 0;
	private static boolean safeLanding = false;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.interactionManager != null && ConfigManager.config.tickregistryAutoRocket) {
				if (client.player.getMainHandStack().getItem() == Items.FIREWORK_ROCKET && client.player.isGliding()) {
					if (System.currentTimeMillis() - lastRocketTime > ConfigManager.config.tickregistryAutoRocketMinDelay * 1000L
							&& client.player.getBlockY() < ConfigManager.config.tickregistryAutoRocketMinY) {

						client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
						lastRocketTime = System.currentTimeMillis();

						client.player.setPitch(-30);

						if (client.player.getMainHandStack().getCount() == 1) { // Switch to new stack if needed
							for (int i = 0; i < 9; i++) {
								if (client.player.getInventory().getStack(i).getItem() == Items.FIREWORK_ROCKET) {
									client.player.getInventory().setSelectedSlot(i);
									break;
								}
							}
						}
					}

					if (ConfigManager.config.tickRegistryAutoRocketSafeLanding) {
						ItemStack elytra = client.player.getEquippedStack(EquipmentSlot.CHEST);
						if (elytra.getMaxDamage() - elytra.getDamage() < (client.player.getBlockY() + 64) / 4) { // Safe landing if low durability
							ConfigManager.config.tickregistryAutoRocket = false;
							safeLanding = true;
							ConfigManager.saveConfig();

							client.player.playSound(SoundEvents.BLOCK_ANVIL_USE, Float.MAX_VALUE, 1.0F);
							client.inGameHud.setTitleTicks(20, 200, 10);
							client.inGameHud.setTitle(TextFormat.styledText("Warning!"));
							client.inGameHud.setSubtitle(TextFormat.styledText("Elytra durability low. AutoRocket disabled to attempt safe landing."));
							client.player.sendMessage(TextFormat.styledText("Elytra durability low. AutoRocket disabled to attempt safe landing."), false);

							client.player.setPitch(30);
						}
					}

					Vec3d velocity = client.player.getVelocity();
					if (velocity.y < 0.2 && System.currentTimeMillis() - lastRocketTime > 100 && ConfigManager.config.tickregistryAutoRocket) { // Tilt down when ascent slows
						client.player.setPitch(10);
					}
				}
			} else if (client.player != null && client.interactionManager != null && client.world != null
				&& safeLanding && ConfigManager.config.tickregistryAutoRocketDisconnectOnSafeLanding) {
				if (client.player.isOnGround()) {
					safeLanding = false;
					if (client.isInSingleplayer()) {
						client.openGameMenu(true);
					} else {
						client.world.disconnect(Text.of("NotchArrowUtils disconnect after safe elytra landing."));
					}
				}
			}
		});
	}
}
