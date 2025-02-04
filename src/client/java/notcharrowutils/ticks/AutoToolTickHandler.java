package notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class AutoToolTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.crosshairTarget != null && TickRegistry.autoTool) {
				if (client.crosshairTarget.getType() == HitResult.Type.BLOCK
						&& client.options.attackKey.isPressed()
						&& client.interactionManager.isBreakingBlock()) {
					BlockHitResult blockHit = (BlockHitResult) client.crosshairTarget;
					Block block = client.world.getBlockState(blockHit.getBlockPos()).getBlock();

					int bestSlot = -1;
					float bestSpeed = 0;

					for (int i = 0; i < 9; i++) {
						ItemStack stack = client.player.getInventory().getStack(i);

						if (!stack.isEmpty() && stack.getItem().isSuitableFor(block.getDefaultState())) {
							float speed = stack.getMiningSpeedMultiplier(block.getDefaultState());
							if (speed > bestSpeed) {
								bestSpeed = speed;
								bestSlot = i;
							}
						}
					}

					if (bestSlot != -1 && bestSlot != client.player.getInventory().selectedSlot) {
						client.player.getInventory().selectedSlot = bestSlot;
					}
				}
			}
		});
	}
}
