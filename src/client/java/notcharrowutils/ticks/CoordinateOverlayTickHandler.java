package notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import notcharrowutils.config.ConfigManager;

public class CoordinateOverlayTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && ConfigManager.config.tickregistryCoordinateOverlay) {
				int x = (int) client.player.getX();
				int y = (int) client.player.getY();
				int z = (int) client.player.getZ();

				String coordinates = String.format("%d, %d, %d", x, y, z);
				client.inGameHud.setOverlayMessage(Text.of(coordinates), false);
			}
		});
	}
}
