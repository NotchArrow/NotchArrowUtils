package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ExpInfo {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("expinfo")
				.executes(ExpInfo::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		client.player.sendMessage(TextFormat.styledText("You have " + client.player.totalExperience + " EXP"), false);
		double experienceProgressPercent = Math.round(client.player.experienceProgress * 100 * Math.pow(10, 2)) / Math.pow(10, 2);
		String experienceProgress = "You are " + experienceProgressPercent + "% of the way to level ";
		client.player.sendMessage(TextFormat.styledText(experienceProgress + (client.player.experienceLevel + 1)), false);

		return 1;
	}
}
