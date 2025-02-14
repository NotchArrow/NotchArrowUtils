package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import notcharrowutils.helper.TextFormat;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class InventoryInfo {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("inventoryinfo")
				.executes(InventoryInfo::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		Map<Item, Integer> items = new HashMap<>();
		PlayerInventory playerInventory = client.player.getInventory();
		for (int slot = 0; slot < playerInventory.size(); slot++) {
			ItemStack stack = playerInventory.getStack(slot);
			if (!stack.isEmpty()) {
				items.merge(stack.getItem(), stack.getCount(), Integer::sum);
			}
		}

		// Sort items descending in quantity and alphabetically
		List<Map.Entry<Item, Integer>> sortedItems = items.entrySet().stream()
				.sorted(Comparator.comparingInt(Map.Entry<Item, Integer>::getValue).reversed()
						.thenComparing(entry -> entry.getKey().getName().getString()))
				.toList();

		String result = sortedItems.stream()
				.map(entry -> {
					int count = entry.getValue();
					int stacks = count / 64;
					int remainder = count % 64;

					// Format the result string based on stacks and remainder
					String formatted = entry.getKey().getName().getString() + " x " + count;
					if (stacks > 0 && remainder > 0) {
						formatted += " (" + stacks + "s + " + remainder + ")";
					} else if (stacks > 0) {
						formatted += " (" + stacks + "s)";
					}
					return formatted;
				})
				.collect(Collectors.joining("\n"));

		client.player.sendMessage(TextFormat.styledText("Inventory Contents:"), false);
		client.player.sendMessage(TextFormat.styledText(result), false);

		return 1;
	}
}
