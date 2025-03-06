package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Calculate {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static double result = 0;

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("calculate")
				.then(argument("expression", StringArgumentType.greedyString())
						.executes(Calculate::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			String var1 = ConfigManager.config.calculatorVar1Name;
			String var2 = ConfigManager.config.calculatorVar2Name;
			String var3 = ConfigManager.config.calculatorVar3Name;
			String var4 = ConfigManager.config.calculatorVar4Name;
			String var5 = ConfigManager.config.calculatorVar5Name;

			try {
				Expression expression = new ExpressionBuilder(StringArgumentType.getString(context, "expression"))
						.variables("s", "S", "ans", var1, var2, var3, var4, var5)
						.build();

				expression.setVariable("s", 64);
				expression.setVariable("S", 1728);
				expression.setVariable("ans", result);
				expression.setVariable(var1, ConfigManager.config.calculatorVar1Value);
				expression.setVariable(var2, ConfigManager.config.calculatorVar2Value);
				expression.setVariable(var3, ConfigManager.config.calculatorVar3Value);
				expression.setVariable(var4, ConfigManager.config.calculatorVar4Value);
				expression.setVariable(var5, ConfigManager.config.calculatorVar5Value);

				result = expression.evaluate();
				client.player.sendMessage(TextFormat.styledText("Result: " + result), false);
			} catch (Exception e) {
				client.player.sendMessage(TextFormat.styledText("Invalid equation: " + e.getMessage()), false);
			}
		}

		return 1;
	}
}