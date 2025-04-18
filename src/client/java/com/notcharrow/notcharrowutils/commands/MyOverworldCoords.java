package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class MyOverworldCoords {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("myoverworldcoords")
				.executes(MyOverworldCoords::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			int x = (int) client.player.getX();
			int y = (int) client.player.getY();
			int z = (int) client.player.getZ();

			int netherX = (int) Math.round(client.player.getX() * 8);
			int netherZ = (int) Math.round(client.player.getZ() * 8);

			String coordinates = String.format("X: %d, Y: %d, Z: %d in the Overworld is X: %d Y: %d, Z: %d", x, y, z, netherX, y, netherZ);
			client.player.sendMessage(TextFormat.styledText(coordinates), false);
			return 1;
		}
		return 0;
	}
}
