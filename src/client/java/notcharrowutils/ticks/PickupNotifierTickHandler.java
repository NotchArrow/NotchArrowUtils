package notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import notcharrowutils.config.ConfigManager;
import org.joml.Matrix4f;

import java.util.*;

public class PickupNotifierTickHandler {
	private static final Map<String, Integer> pickupDisplay = new HashMap<>();
	private static final Map<String, Integer> pickupTimers = new HashMap<>();
	private static final Map<String, Integer> previousCounts = new HashMap<>();

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) return;

			if (!ConfigManager.config.tickregistryPickupNotifier) {
				pickupDisplay.clear();
				pickupTimers.clear();
				return;
			}

			final int DISPLAY_TIME = ConfigManager.config.tickregistryPickupNotifierTime * 20;

			Map<String, Integer> currentCounts = new HashMap<>();
			for (ItemStack stack : client.player.getInventory().main) {
				if (!stack.isEmpty()) {
					String itemName = stack.getItem().getName().getString();
					currentCounts.put(itemName, currentCounts.getOrDefault(itemName, 0) + stack.getCount());
				}
			}

			// Item pickups and drops
			for (String itemName : currentCounts.keySet()) {
				int count = currentCounts.get(itemName);
				int previousCount = previousCounts.getOrDefault(itemName, 0);

				if (count > previousCount) {
					int pickedUpAmount = count - previousCount;
					pickupDisplay.merge(itemName, pickedUpAmount, Integer::sum);
					pickupTimers.put(itemName, DISPLAY_TIME);
				} else if (count < previousCount) {
					int droppedAmount = previousCount - count;
					int newTotal = pickupDisplay.getOrDefault(itemName, 0) - droppedAmount;

					if (newTotal != 0) {
						pickupDisplay.put(itemName, newTotal);
						pickupTimers.put(itemName, DISPLAY_TIME);
					} else {
						pickupDisplay.remove(itemName);
						pickupTimers.remove(itemName);
					}
				}
			}

			for (String itemName : previousCounts.keySet()) {
				// If an item was in the previous inventory but is now missing, it was fully dropped
				if (!currentCounts.containsKey(itemName) && previousCounts.get(itemName) > 0) {
					int droppedAmount = previousCounts.get(itemName);
					pickupDisplay.put(itemName, pickupDisplay.getOrDefault(itemName, 0) - droppedAmount);
					pickupTimers.put(itemName, DISPLAY_TIME);
				}
			}

			previousCounts.clear();
			previousCounts.putAll(currentCounts);

			// Decrement timers & remove expired messages
			Iterator<Map.Entry<String, Integer>> iterator = pickupTimers.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Integer> entry = iterator.next();
				String itemName = entry.getKey();
				int timeLeft = entry.getValue() - 1;

				if (timeLeft <= 0) {
					iterator.remove();
					pickupDisplay.remove(itemName);
					pickupTimers.remove(itemName);
				} else {
					entry.setValue(timeLeft);
				}
			}
		});

		HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client.player == null || pickupDisplay.isEmpty()) return;

			TextRenderer textRenderer = client.textRenderer;
			VertexConsumerProvider.Immediate vertexConsumers = client.getBufferBuilders().getEffectVertexConsumers();
			Matrix4f matrix = matrices.getMatrices().peek().getPositionMatrix();

			int y;
			int yIncrement;
			List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(pickupDisplay.entrySet());
			if (Objects.equals(ConfigManager.config.tickregistryPickupNotifierLocation, "TOP_LEFT")
			|| Objects.equals(ConfigManager.config.tickregistryPickupNotifierLocation, "TOP_RIGHT")) {
				y = 20;
				yIncrement = 10;
				sortedEntries.sort(Comparator.comparing((Map.Entry<String, Integer> e) -> e.getValue() < 0 ? 1 : 0)
						.thenComparing(e -> e.getKey().toLowerCase()));
			} else {
				y = client.getWindow().getScaledHeight() - 20;
				yIncrement = -10;
				sortedEntries.sort(Comparator.comparing((Map.Entry<String, Integer> e) -> e.getValue() < 0 ? 1 : 0)
						.thenComparing(e -> e.getKey().toLowerCase()).reversed());
			}

			for (Map.Entry<String, Integer> entry : sortedEntries) {
				String itemName = entry.getKey();
				int count = entry.getValue();

				String displayText;
				int color;
				if (count > 0) {
					displayText = "+ " + itemName + " x" + count;
					color = 0x55FF55;
				} else if (count < 0) {
					displayText = "- " + itemName + " x" + Math.abs(count);
					color = 0xFF5555;
				} else {
					continue;
				}

				int x;
				if (ConfigManager.config.tickregistryPickupNotifierLocation.equals("TOP_RIGHT")
				|| ConfigManager.config.tickregistryPickupNotifierLocation.equals("BOTTOM_RIGHT")) {
					int textWidth = textRenderer.getWidth(displayText);
					x = client.getWindow().getScaledWidth() - textWidth - 10;
				} else {
					x = 10;
				}

				textRenderer.draw(
						Text.literal(displayText),
						x, y, color, true,
						matrix, vertexConsumers,
						TextRenderer.TextLayerType.NORMAL, 0, 15728880
				);
				y += yIncrement;
			}
			vertexConsumers.draw();
		});
	}
}