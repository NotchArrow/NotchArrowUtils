package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CommandRegistry {
	public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(
				literal("utils")
						.then(AfkOptimize.registerCommand())
						.then(AutoAttack.registerCommand())
						.then(AutoElytra.registerCommand())
						.then(AutoFish.registerCommand())
						.then(AutoRefill.registerCommand())
						.then(AutoReplant.registerCommand())
						.then(AutoRocket.registerCommand())
						.then(AutoSprint.registerCommand())
						.then(AutoTool.registerCommand())
						.then(Breadcrumbs.registerCommand())
						.then(Calculate.registerCommand())
						.then(CalculatorVariables.registerCommand())
						.then(CameraLock.registerCommand())
						.then(Clock.registerCommand())
						.then(Coinflip.registerCommand())
						.then(CoordinateOverlay.registerCommand())
						.then(DeathLocation.registerCommand())
						.then(EnchantInfo.registerCommand())
						.then(ExpInfo.registerCommand())
						.then(FastPlace.registerCommand())
						.then(Help.registerCommand())
						.then(InventoryInfo.registerCommand())
						.then(JobBlocks.registerCommand())
						.then(Jukebox.registerCommand())
						.then(MyNetherCoords.registerCommand())
						.then(MyOverworldCoords.registerCommand())
						.then(NightVision.registerCommand())
						.then(NoFog.registerCommand())
						//.then(Notes.registerCommand())
						.then(PickupNotifier.registerCommand())
						.then(ProjectileTrail.registerCommand())
						.then(RandomDecimal.registerCommand())
						.then(RandomNumber.registerCommand())
						.then(SmoothCamera.registerCommand())
						.then(Statistics.registerCommand())
						.then(StormPause.registerCommand())
						.then(TravelAngle.registerCommand())
		);
	}
}
