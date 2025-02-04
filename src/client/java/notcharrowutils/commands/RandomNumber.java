package notcharrowutils.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import java.util.Random;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class RandomNumber {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static final Random random = new Random();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("random")
				.then(argument("Min", IntegerArgumentType.integer())
						.then(argument("Max", IntegerArgumentType.integer())
								.executes(RandomNumber::execute)));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		int min = IntegerArgumentType.getInteger(context, "Min");
		int max = IntegerArgumentType.getInteger(context, "Max");

		int result = random.nextInt(max - min + 1) + min;
		client.player.sendMessage(TextFormat.styledText(String.valueOf(result)), false);

		return 1;
	}
}
