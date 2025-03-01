package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AfkOptimize {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static boolean afkOptimized = false;
	static boolean vSyncDefault = false;
	static int maxFPSDefault = 60;
	static double masterVolumeDefault = 1.0;

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("afkoptimize")
				.executes(AfkOptimize::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (!afkOptimized) {
			vSyncDefault = client.options.getEnableVsync().getValue();
			maxFPSDefault = client.options.getMaxFps().getValue();
			masterVolumeDefault = client.options.getSoundVolumeOption(SoundCategory.MASTER).getValue();
			client.options.getEnableVsync().setValue(false);
			client.options.getMaxFps().setValue(10);
			client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(0.0);
			client.player.sendMessage(TextFormat.styledText("FPS Limited and sound muted. Run the command again to revert."), false);
		} else {
			client.options.getEnableVsync().setValue(vSyncDefault);
			client.options.getMaxFps().setValue(maxFPSDefault);
			client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(masterVolumeDefault);
			client.player.sendMessage(TextFormat.styledText("Settings reverted."), false);
		}
		afkOptimized = !afkOptimized;

		return 1;
	}
}
