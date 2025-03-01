package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import java.util.Random;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Coinflip {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static final Random random = new Random();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("coinflip")
				.executes(Coinflip::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (random.nextDouble() < 0.5) {
			client.player.sendMessage(TextFormat.styledText("Heads!"), false);
		} else {
			client.player.sendMessage(TextFormat.styledText("Tails!"), false);
		}

		return 1;
	}
}
