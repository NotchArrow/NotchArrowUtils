package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SmoothCamera {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("smoothcamera")
				.executes(SmoothCamera::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (!client.options.smoothCameraEnabled) {
				client.options.smoothCameraEnabled = true;
				client.player.sendMessage(TextFormat.styledText("Smooth Camera enabled."), false);
				client.player.sendMessage(TextFormat.styledText("Run the command again to revert."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("Smooth Camera disabled."), false);
				client.options.smoothCameraEnabled = false;
			}
		}

		return 1;
	}
}