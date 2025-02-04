package notcharrowutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CommandRegistry {
	public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(
				literal("utils")
						.then(RandomNumber.registerCommand())
						.then(RandomDecimal.registerCommand())
						.then(Coinflip.registerCommand())
						.then(MyNetherCoords.registerCommand())
						.then(MyOverworldCoords.registerCommand())
						.then(ExpInfo.registerCommand())
						.then(EnchantInfo.registerCommand())
						.then(InventoryInfo.registerCommand())
						.then(NightVision.registerCommand())
						.then(AfkOptimize.registerCommand())
						.then(Preferences.registerCommand())
						.then(Calculate.registerCommand())
						.then(CoordinateOverlay.registerCommand())
						.then(AutoTool.registerCommand())
						.then(AutoFish.registerCommand())
						.then(MobGrinder.registerCommand())
						.then(Help.registerCommand())
						.then(DiamondFinder.registerCommand())
						.then(Breadcrumbs.registerCommand())
		);
	}
}
