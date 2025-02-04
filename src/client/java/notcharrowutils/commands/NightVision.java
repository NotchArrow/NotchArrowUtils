package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class NightVision {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static boolean fullbrightEnabled = false;

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("nightvision")
				.executes(NightVision::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (!fullbrightEnabled) {
			client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false, false));
			client.player.sendMessage(TextFormat.styledText("Nightvision enabled."));
		} else {
			client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
			client.player.sendMessage(TextFormat.styledText("Nightvision disabled."));
		}
		fullbrightEnabled = !fullbrightEnabled;

		return 1;
	}
}
