package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Help {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("help")
				.executes(Help::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			client.player.sendMessage(
					Text.literal("https://modrinth.com/mod/notcharrowutils")
							.setStyle(Style.EMPTY.withClickEvent(
									new ClickEvent(ClickEvent.Action.OPEN_URL, "https://modrinth.com/mod/notcharrowutils")
							)), false
			);
		}

		return 1;
	}
}
