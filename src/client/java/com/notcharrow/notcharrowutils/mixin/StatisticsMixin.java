package com.notcharrow.notcharrowutils.mixin;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.ticks.StatisticsTickHandler;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.item.Item;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(GameMenuScreen.class)
public class StatisticsMixin {
	@Inject(method = "render", at = @At("TAIL"))
	private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.currentScreen != null && client.currentScreen.getClass() == GameMenuScreen.class && client.player != null
		&& client.world != null && client.isInSingleplayer() && ConfigManager.config.mixinStatistics) {
			Objects.requireNonNull(client.getNetworkHandler()).sendPacket(new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.REQUEST_STATS));

			context.fill(5, 5, 200, 90, 0x90000000);
			long elapsedTime = System.currentTimeMillis() - StatisticsTickHandler.playtimeStart;
			String formattedTime = String.format("%02dh %02dm %02ds",
					(elapsedTime / 3600000), // Hours
					(elapsedTime / 60000) % 60, // Minutes
					(elapsedTime / 1000) % 60); // Seconds
			context.drawText(client.textRenderer, "Playtime: " + formattedTime, 10, 10, 0xFFFFFF, true);

			int blocksMined = 0;
			for (Identifier id : Registries.BLOCK.getIds()) {
				Block block = Registries.BLOCK.get(id);
				blocksMined += client.player.getStatHandler().getStat(Stats.MINED, block);
			}
			blocksMined -= StatisticsTickHandler.blocksMinedStart;
			context.drawText(client.textRenderer, "Blocks Mined: " + blocksMined, 10, 30, 0xFFFFFF, true);

			int blocksTraveled = 0;
			List<Identifier> travelTypes = Arrays.asList(
					Stats.WALK_ONE_CM, Stats.AVIATE_ONE_CM, Stats.BOAT_ONE_CM, Stats.CROUCH_ONE_CM,
					Stats.HORSE_ONE_CM, Stats.MINECART_ONE_CM, Stats.PIG_ONE_CM, Stats.STRIDER_ONE_CM,
					Stats.SWIM_ONE_CM, Stats.CLIMB_ONE_CM, Stats.SPRINT_ONE_CM
			);
			for (Identifier stat: travelTypes) {
				blocksTraveled += client.player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(stat)) / 100;
			}
			blocksTraveled -= StatisticsTickHandler.blocksTraveledStart;
			context.drawText(client.textRenderer, "Blocks Traveled: " + blocksTraveled, 10, 50, 0xFFFFFF, true);

			int itemsGathered = 0;
			for (Identifier id : Registries.ITEM.getIds()) {
				Item item = Registries.ITEM.get(id);
				itemsGathered += client.player.getStatHandler().getStat(Stats.PICKED_UP, item);
			}
			itemsGathered -= StatisticsTickHandler.itemsGatheredStart;
			context.drawText(client.textRenderer, "Items Gathered: " + itemsGathered, 10, 70, 0xFFFFFF, true);
		}
	}

	@Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
	private void onRenderBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.currentScreen != null && client.currentScreen.getClass() == GameMenuScreen.class && client.player != null
				&& client.world != null && client.isInSingleplayer() && ConfigManager.config.mixinStatistics) {
			ci.cancel();
		}
	}
}

