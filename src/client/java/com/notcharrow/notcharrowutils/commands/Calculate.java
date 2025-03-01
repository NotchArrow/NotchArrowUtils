package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Calculate {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("calculate")
				.then(argument("expression", StringArgumentType.greedyString())
						.executes(Calculate::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		try {
			Expression expression = new ExpressionBuilder(StringArgumentType.getString(context, "expression"))
					.variables("s", "S")
					.build();

			expression.setVariable("s", 64);
			expression.setVariable("S", 1728);

			double result = expression.evaluate();
			client.player.sendMessage(TextFormat.styledText("Result: " + result), false);
			return 1;
		} catch (Exception e) {
			client.player.sendMessage(TextFormat.styledText("Invalid equation: " + e.getMessage()), false);
			return 0;
		}
	}
}