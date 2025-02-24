package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Clock {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("clock")
				.executes(Clock::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null && client.world != null) {
			long day = client.world.getTime() / 24000;

			long time = client.world.getTimeOfDay();
			int hour = (int) time / 1000 + 6;
			if (hour > 23) {
				hour -= 24;
			}
			String minute = String.valueOf((int) (time / 16.6666667 % 60));
			if (minute.length() == 1) {
				minute = "0" + minute;
			}

			client.player.sendMessage(TextFormat.styledText("Day | " + day), false);
			client.player.sendMessage(TextFormat.styledText("Time | " + hour + ":" + minute), false);
		}

		return 1;
	}
}
