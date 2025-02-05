// TODO
// Add saving for preferences via a config file

package notcharrowutils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
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
	}
}