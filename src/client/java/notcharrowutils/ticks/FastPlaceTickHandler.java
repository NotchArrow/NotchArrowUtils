package notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import notcharrowutils.config.ConfigManager;

public class FastPlaceTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.crosshairTarget instanceof BlockHitResult hitResult
					&& client.interactionManager != null && ConfigManager.config.tickregistryFastPlace) {
				if (client.options.useKey.isPressed() && client.player.getMainHandStack().getItem() instanceof BlockItem) {
					if (client.world.getBlockState(hitResult.getBlockPos()).isSolidBlock(client.world, hitResult.getBlockPos())
						|| client.isInSingleplayer() && ConfigManager.config.tickregistryFloatingFastPlace) {
						client.interactionManager.interactBlock(client.player, Hand.MAIN_HAND, hitResult);
					}
				}
			}
		});
	}
}
