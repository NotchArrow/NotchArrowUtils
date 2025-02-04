package notcharrowutils.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import java.util.Random;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class RandomDecimal {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static final Random random = new Random();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("randomdecimal")
				.then(argument("Min", DoubleArgumentType.doubleArg())
						.then(argument("Max", DoubleArgumentType.doubleArg())
								.executes(RandomDecimal::executeAutoRound)
								.then(argument("Decimal Places", IntegerArgumentType.integer())
										.executes(RandomDecimal::execute))));
	}

	private static int executeAutoRound(CommandContext<FabricClientCommandSource> context) {
		double min = DoubleArgumentType.getDouble(context, "Min");
		double max = DoubleArgumentType.getDouble(context, "Max");
		int decimalPlaces = 2;

		double randomFloat = min + (max - min) * random.nextDouble();
		randomFloat = Math.round(randomFloat * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);

		String result = "Result: " + randomFloat;
		client.player.sendMessage(TextFormat.styledText(result), false);

		return 1;
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		double min = DoubleArgumentType.getDouble(context, "Min");
		double max = DoubleArgumentType.getDouble(context, "Max");
		int decimalPlaces = IntegerArgumentType.getInteger(context, "Decimal Places");

		double randomFloat = min + (max - min) * random.nextDouble();
		randomFloat = Math.round(randomFloat * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);

		String result = "Result: " + randomFloat;
		client.player.sendMessage(TextFormat.styledText(result), false);

		return 1;
	}
}
