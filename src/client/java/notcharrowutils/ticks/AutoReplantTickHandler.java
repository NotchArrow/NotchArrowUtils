package notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import notcharrowutils.config.ConfigManager;

public class AutoReplantTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.crosshairTarget instanceof BlockHitResult hitResult
					&& client.interactionManager != null && client.world != null && ConfigManager.config.tickregistryAutoReplant) {

				BlockState blockState = client.world.getBlockState(hitResult.getBlockPos());
				if (client.options.useKey.isPressed()
						&& ((blockState.getBlock() instanceof CropBlock && ((CropBlock) blockState.getBlock()).isMature(blockState))
						|| (blockState.getBlock() == Blocks.NETHER_WART && blockState.get(NetherWartBlock.AGE) == 3))) {
					boolean isMainHandPlantable = client.player.getMainHandStack().getItem() instanceof BlockItem
							&& ((BlockItem) client.player.getMainHandStack().getItem()).getBlock() instanceof PlantBlock;
					boolean isOffHandPlantable = client.player.getOffHandStack().getItem() instanceof BlockItem
							&& ((BlockItem) client.player.getOffHandStack().getItem()).getBlock() instanceof PlantBlock;

					if (isMainHandPlantable) {
						client.interactionManager.attackBlock(hitResult.getBlockPos(), hitResult.getSide());
						client.interactionManager.interactBlock(client.player, Hand.MAIN_HAND, hitResult);
					} else if (isOffHandPlantable) {
						client.interactionManager.attackBlock(hitResult.getBlockPos(), hitResult.getSide());
						client.interactionManager.interactBlock(client.player, Hand.OFF_HAND, hitResult);
					}
				}
			}
		});
	}
}
