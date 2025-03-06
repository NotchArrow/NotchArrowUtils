package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CalculatorVariables {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("calculatorvariables")
				.executes(CalculatorVariables::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			client.player.sendMessage(TextFormat.styledText(
					"""
					Default Variables:
					s (stack) = 64
					S (shulker) = 1728
					ans (answer) = previous result
					"""
			), false);
			client.player.sendMessage(TextFormat.styledText(
					"Custom Variables:\n" +
					ConfigManager.config.calculatorVar1Name + " = " + ConfigManager.config.calculatorVar1Value + "\n" +
					ConfigManager.config.calculatorVar2Name + " = " + ConfigManager.config.calculatorVar2Value + "\n" +
					ConfigManager.config.calculatorVar3Name + " = " + ConfigManager.config.calculatorVar3Value + "\n" +
					ConfigManager.config.calculatorVar4Name + " = " + ConfigManager.config.calculatorVar4Value + "\n" +
					ConfigManager.config.calculatorVar5Name + " = " + ConfigManager.config.calculatorVar5Value
			), false);
		}

		return 1;
	}
}
