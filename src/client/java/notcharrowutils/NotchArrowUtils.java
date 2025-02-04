// TODO
// Add saving for preferences via a config file

package notcharrowutils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import notcharrowutils.commands.CommandRegistry;
import notcharrowutils.ticks.TickRegistry;

public class NotchArrowUtils implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Register All Commands
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			CommandRegistry.registerCommands(dispatcher);
		});

		// Register All TickEvents
		TickRegistry.registerTicks();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
		if (client.world != null && client.player != null) {
			for (int x = client.player.getBlockX() - 10; x < client.player.getBlockX() + 10; x++) {
				for (int y = client.player.getBlockY() - 10; y < client.player.getBlockY() + 10; y++) {
					for (int z = client.player.getBlockZ() - 10; z < client.player.getBlockZ() + 10; z++) {
						BlockPos pos = new BlockPos(x, y, z);
						BlockState blockState = client.world.getBlockState(pos);
						if (blockState.getBlock().getName().getString().equals("Diamond Ore")) {
							System.out.println(String.format("Diamond at (%d, %d, %d)", pos.getX(), pos.getY(), pos.getZ()));
						}
					}
				}
			}
		}});
	}
}