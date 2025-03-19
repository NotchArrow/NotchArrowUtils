package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class AutoToolTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.crosshairTarget != null
					&& client.interactionManager != null && client.world != null
					&& ConfigManager.config.tickregistryAutoTool) {
				if (client.crosshairTarget.getType() == HitResult.Type.BLOCK
						&& client.options.attackKey.isPressed()
						&& client.interactionManager.isBreakingBlock()) {
					BlockHitResult blockHit = (BlockHitResult) client.crosshairTarget;
					Block block = client.world.getBlockState(blockHit.getBlockPos()).getBlock();

					int currentSlot = client.player.getInventory().selectedSlot;
					int bestSlot = -1;
					float bestSpeed = 0;

					// Loop through hotbar or full inventory
					int start = 0;
					int end = !ConfigManager.config.tickregistryAutoToolFromInventory ? 9 : client.player.getInventory().size();

					for (int i = start; i < end; i++) {
						ItemStack stack = client.player.getInventory().getStack(i);

						if (!stack.isEmpty() && stack.isSuitableFor(block.getDefaultState())) {
							float speed = stack.getMiningSpeedMultiplier(block.getDefaultState());
							if (speed > bestSpeed) {
								bestSpeed = speed;
								bestSlot = i;
							}
						}
					}

					if (bestSlot != -1) {
						// If tool is in hotbar but not in preferred slot, switch directly
						if (bestSlot < 9 && bestSlot != currentSlot) {
							client.player.getInventory().selectedSlot = bestSlot;
						} else if (bestSlot >= 9) {
							// If tool is in inventory, move it to toolSlot
							swapItemToHotbar(client, bestSlot, ConfigManager.config.tickregistryAutoToolSlot - 1);
							client.player.getInventory().selectedSlot = ConfigManager.config.tickregistryAutoToolSlot - 1;
						}
					}
				}
			}
		});
	}

	private static void swapItemToHotbar(MinecraftClient client, int fromSlot, int toSlot) {
		if (client.interactionManager != null && client.player != null) {
			client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, fromSlot, toSlot, SlotActionType.SWAP, client.player);
		}
	}
}
