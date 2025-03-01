package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class TravelAngle {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("travelangle")
				.then(argument("X", IntegerArgumentType.integer())
				.then(argument("Z", IntegerArgumentType.integer())
				.executes(TravelAngle::execute)));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null && client.world != null) {
			int startX = client.player.getBlockX();
			int startZ = client.player.getBlockZ();
			int destinationX = IntegerArgumentType.getInteger(context, "X");
			int destinationZ = IntegerArgumentType.getInteger(context, "Z");

			double angle = Math.toDegrees(Math.atan2(destinationZ - startZ, destinationX - startX)) - 90 % 360;
			if (angle >= 180) {
				angle -= 360;
			}

			client.player.setAngles((float) angle, client.player.getPitch());

			angle = Math.round(angle * Math.pow(10, 1)) / Math.pow(10, 1); // Round to 1 decimal to match F3 for chat message
			client.player.sendMessage(TextFormat.styledText("Optimal Travel Angle for " + destinationX + ", " + destinationZ + ": " + angle), false);
			if (!ConfigManager.config.tickregistryCameraLock) {
				client.player.sendMessage(TextFormat.styledText("/utils cameralock will lock this camera position until toggled."), false);
			}
		}

		return 1;
	}
}