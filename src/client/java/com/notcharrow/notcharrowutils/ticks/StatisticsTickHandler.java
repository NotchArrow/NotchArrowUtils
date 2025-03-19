package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class StatisticsTickHandler {
	public static int blocksMinedStart;
	public static int blocksTraveledStart;
	public static int itemsGatheredStart;
	public static long playtimeStart;

	public static void register() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			if (ConfigManager.config.mixinStatistics) {
				blocksMinedStart = 0;
				for (Identifier id : Registries.BLOCK.getIds()) {
					Block block = Registries.BLOCK.get(id);
					blocksMinedStart += handler.player.getStatHandler().getStat(Stats.MINED, block);
				}

				blocksTraveledStart = 0;
				List<Identifier> travelTypes = Arrays.asList(
						Stats.WALK_ONE_CM, Stats.AVIATE_ONE_CM, Stats.BOAT_ONE_CM, Stats.CROUCH_ONE_CM,
						Stats.HORSE_ONE_CM, Stats.MINECART_ONE_CM, Stats.PIG_ONE_CM, Stats.STRIDER_ONE_CM,
						Stats.SWIM_ONE_CM, Stats.CLIMB_ONE_CM, Stats.SPRINT_ONE_CM
				);
				for (Identifier stat : travelTypes) {
					blocksTraveledStart += handler.player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(stat)) / 100;
				}

				itemsGatheredStart = 0;
				for (Identifier id : Registries.ITEM.getIds()) {
					Item item = Registries.ITEM.get(id);
					itemsGatheredStart += handler.player.getStatHandler().getStat(Stats.PICKED_UP, item);
				}

				playtimeStart = System.currentTimeMillis();
			}
		});
	}
}