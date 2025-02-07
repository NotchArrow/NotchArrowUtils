package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Help {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("help")
				.executes(Help::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		client.player.sendMessage(TextFormat.styledText("https://github.com/NotchArrow/notcharrowutils/wiki/Commands"));
		return 1;
	}
}
