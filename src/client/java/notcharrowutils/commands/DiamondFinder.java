package notcharrowutils.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class DiamondFinder {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("diamondfinder")
				.then(argument("Distance", IntegerArgumentType.integer())
						.executes(DiamondFinder::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		final int distance = IntegerArgumentType.getInteger(context, "Distance");

		if (client.world != null && client.player != null) {
			for (int x = client.player.getBlockX() - distance; x <= client.player.getBlockX() + distance; x++) {
				for (int y = client.player.getBlockY() - distance; y <= client.player.getBlockY() + distance; y++) {
					for (int z = client.player.getBlockZ() - distance; z <= client.player.getBlockZ() + distance; z++) {
						BlockPos pos = new BlockPos(x, y, z);
						BlockState blockState = client.world.getBlockState(pos);
						if (blockState.getBlock().getName().getString().equals("Diamond Ore")) {
							client.player.sendMessage(TextFormat.styledText(String.format("Diamond at (%d, %d, %d)", pos.getX(), pos.getY(), pos.getZ())), false);
						}
					}
				}
			}
		}

		return 1;
	}
}
