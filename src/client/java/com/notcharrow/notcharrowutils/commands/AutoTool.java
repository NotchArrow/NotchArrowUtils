package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AutoTool {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("autotool")
				.executes(AutoTool::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.tickregistryAutoTool = !ConfigManager.config.tickregistryAutoTool;
		ConfigManager.saveConfig();
		if (client.player != null) {
			if (ConfigManager.config.tickregistryAutoTool) {
				client.player.sendMessage(TextFormat.styledText("Auto tool is now enabled."), false);
				if (!ConfigManager.config.tickregistryAutoToolFromInventory) {
					client.player.sendMessage(TextFormat.styledText("All tools used must be in your hotbar."), false);
				} else {
					client.player.sendMessage(TextFormat.styledText("Tools will be pulled from your inventory into slot " +
							ConfigManager.config.tickregistryAutoToolSlot + "."), false);
				}
			} else {
				client.player.sendMessage(TextFormat.styledText("Auto tool is now disabled."), false);
			}
		}

		return 1;
	}
}
