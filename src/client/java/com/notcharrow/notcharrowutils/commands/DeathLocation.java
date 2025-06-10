package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class DeathLocation {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("deathlocation")
				.executes(DeathLocation::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (client.player.getLastDeathPos().isPresent()) {
				BlockPos deathPos = client.player.getLastDeathPos().get().getPos();
				int x = deathPos.getX();
				int y = deathPos.getY();
				int z = deathPos.getZ();
				client.player.sendMessage(TextFormat.styledText("Death Location: " +
						x + ", " + y + ", " + z), false);

				RegistryKey<World> deathDimension = client.player.getLastDeathPos().get().getDimension();
				String deathDimensionName;
				if (deathDimension == World.OVERWORLD) {
					deathDimensionName = "Overworld";
				} else if (deathDimension == World.NETHER) {
					deathDimensionName = "Nether";
				} else if (deathDimension == World.END) {
					deathDimensionName = "End";
				} else {
					deathDimensionName = deathDimension.getValue().toString(); // Custom Dimensions
				}
				client.player.sendMessage(TextFormat.styledText("Dimension: " + deathDimensionName), false);

				if (deathDimension == World.OVERWORLD) {
					x /= 8;
					z /= 8;
					client.player.sendMessage(TextFormat.styledText("Nether Coordinates: " +
							x + ", " + y + ", " + z), false);
				} else if (deathDimension == World.NETHER) {
					x *= 8;
					z *= 8;
					client.player.sendMessage(TextFormat.styledText("Overworld Coordinates: " +
							x + ", " + y + ", " + z), false);
				}

			} else {
				client.player.sendMessage(TextFormat.styledText("No death found."), false);
			}
		}

		return 1;
	}
}
