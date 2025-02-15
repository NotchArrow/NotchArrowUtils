package notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.SuggestionBuilder;
import notcharrowutils.helper.TextFormat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class EnchantInfo {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	private static final List<String> enchantmentSuggestions = Arrays.asList("aqua_affinity", "bane_of_arthropods", "blast_protection",
			"breach", "channeling", "curse_of_binding", "curse_of_vanishing", "density", "depth_strider", "efficiency", "feather_falling",
			"fire_aspect", "fire_protection", "flame", "fortune", "frost_walker", "impaling", "infinity", "knockback",
			"loyalty", "looting", "luck_of_the_sea", "lure", "mending", "multishot", "piercing", "power", "projectile_protection",
			"protection", "punch", "quick_charge", "respiration", "riptide", "sharpness", "silk_touch", "smite", "soul_speed", "swift_sneak",
			"sweeping_edge", "thorns", "unbreaking", "wind_burst");
	private static final SuggestionProvider<FabricClientCommandSource> enchantmentSuggestionsProvider = SuggestionBuilder.createSuggestionProvider(enchantmentSuggestions);

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("enchantinfo")
				.then(argument("enchantment", StringArgumentType.string())
						.suggests(enchantmentSuggestionsProvider)
									.executes(EnchantInfo::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		Map<String, String> enchantments = new HashMap<>();

		enchantments.put("aqua_affinity", "Applies to: Helmets\nMax Level: I\nDescription: Increases the speed of mining underwater.");
		enchantments.put("bane_of_arthropods", "Applies to: Swords\nMax Level: V\nDescription: Increases damage against arthropod mobs (spiders, bees, etc.).\nConflicts with: Smite, Sharpness");
		enchantments.put("blast_protection", "Applies to: Armor\nMax Level: IV\nDescription: Reduces damage taken from explosions.");
		enchantments.put("breach", "Applies to: Mace\nMax Level: IV\nDescription: Reduces the effectiveness of the armor on the target.\nConflicts with: Density, Sharpness, Smite, Bane of Arthropods");
		enchantments.put("channeling", "Applies to: Tridents\nMax Level: I\nDescription: Summons a lightning bolt when thrown during a thunderstorm.");
		enchantments.put("curse_of_binding", "Applies to: Armor\nMax Level: I\nDescription: Causes the item to be bound to the player, preventing removal until the player dies.");
		enchantments.put("curse_of_vanishing", "Applies to: All items\nMax Level: I\nDescription: Causes the item to disappear upon the player's death.");
		enchantments.put("density", "Applies to: Mace\nMax Level: V\nDescription: Increases damage dealt per block fallen.\nConflicts with: Breach, Bane of Arthropods, Smite, Sharpness");
		enchantments.put("depth_strider", "Applies to: Boots\nMax Level: III\nDescription: Increases movement speed while underwater.");
		enchantments.put("efficiency", "Applies to: Tools\nMax Level: V\nDescription: Allows tools to break blocks faster.");
		enchantments.put("feather_falling", "Applies to: Boots\nMax Level: IV\nDescription: Reduces fall damage taken when wearing boots.");
		enchantments.put("fire_aspect", "Applies to: Swords\nMax Level: II\nDescription: Sets mobs and players on fire when hit.");
		enchantments.put("fire_protection", "Applies to: Armor\nMax Level: IV\nDescription: Reduces damage taken from fire and lava.");
		enchantments.put("flame", "Applies to: Bows\nMax Level: I\nDescription: Sets arrows shot from bows on fire.");
		enchantments.put("fortune", "Applies to: Tools\nMax Level: III\nDescription: Increases the drop rate of certain items from blocks.\nConflicts with: Silk Touch");
		enchantments.put("frost_walker", "Applies to: Boots\nMax Level: II\nDescription: Freezes water when walking on it.");
		enchantments.put("impaling", "Applies to: Tridents\nMax Level: V\nDescription: Increases damage to aquatic mobs.");
		enchantments.put("infinity", "Applies to: Bows\nMax Level: I\nDescription: Gives infinite arrows, but only one arrow is required in the inventory.\nConflicts with: Mending");
		enchantments.put("knockback", "Applies to: Weapons\nMax Level: II\nDescription: Increases the knockback dealt when hitting mobs or players.");
		enchantments.put("loyalty", "Applies to: Tridents\nMax Level: III\nDescription: Causes tridents to return to the player after being thrown.");
		enchantments.put("looting", "Applies to: Swords\nMax Level: III\nDescription: Increases the drop rate of rare items from mobs.");
		enchantments.put("luck_of_the_sea", "Applies to: Fishing Rod\nMax Level: III\nDescription: Increases the chances of obtaining valuable items while fishing.");
		enchantments.put("lure", "Applies to: Fishing Rod\nMax Level: III\nDescription: Increases the rate of fish biting the hook.");
		enchantments.put("mending", "Applies to: Tools, Weapons, Armor\nMax Level: I\nDescription: Repairs the item using collected experience.\nConflicts with: Infinity");
		enchantments.put("multishot", "Applies to: Crossbows\nMax Level: I\nDescription: Shoots three arrows at once in a spread pattern.\nConflicts with: Piercing");
		enchantments.put("piercing", "Applies to: Crossbows\nMax Level: IV\nDescription: Arrows fired from crossbows can pass through multiple entities.\nConflicts with: Multishot");
		enchantments.put("power", "Applies to: Bows\nMax Level: V\nDescription: Increases the damage of arrows shot from bows.");
		enchantments.put("projectile_protection", "Applies to: Armor\nMax Level: IV\nDescription: Reduces damage taken from projectiles (arrows, fire charges, etc.).");
		enchantments.put("protection", "Applies to: Armor\nMax Level: IV\nDescription: Reduces damage taken from various sources.");
		enchantments.put("punch", "Applies to: Bows\nMax Level: II\nDescription: Increases the knockback of arrows shot from bows.");
		enchantments.put("quick_charge", "Applies to: Crossbows\nMax Level: III\nDescription: Reduces the time needed to reload the crossbow.");
		enchantments.put("respiration", "Applies to: Helmets\nMax Level: III\nDescription: Increases the amount of time you can breathe underwater.");
		enchantments.put("riptide", "Applies to: Tridents\nMax Level: III\nDescription: Propels the player through water or rain when thrown.");
		enchantments.put("sharpness", "Applies to: Swords, Axes\nMax Level: V\nDescription: Increases damage dealt by weapons.\nConflicts with: Smite, Bane of Arthropods");
		enchantments.put("silk_touch", "Applies to: Tools\nMax Level: I\nDescription: Allows blocks to be mined in their original state (e.g., mining ores gives the ore block instead of its item).\nConflicts with: Fortune");
		enchantments.put("smite", "Applies to: Swords\nMax Level: V\nDescription: Increases damage against undead mobs (zombies, skeletons, etc.).\nConflicts with: Sharpness, Bane of Arthropods");
		enchantments.put("soul_speed", "Applies to: Boots\nMax Level: III\nDescription: Increases movement speed on soul sand and soul soil.");
		enchantments.put("swift_sneak", "Applies to: Pants\nMax Level: III\nDescription: Increases movement speed while sneaking.");
		enchantments.put("sweeping_edge", "Applies to: Swords\nMax Level: III\nDescription: Increases the damage of sweeping attacks with swords.");
		enchantments.put("thorns", "Applies to: Armor\nMax Level: III\nDescription: Damages attackers when they hit the player.");
		enchantments.put("unbreaking", "Applies to: Tools, Armor\nMax Level: III\nDescription: Increases the durability of items, reducing the chance of them breaking.");
		enchantments.put("wind_burst", "Applies to: Mace\nMax Level: III\nDescription: Launches the attacker upwards upon executing a smash attack.");

		String enchantment = StringArgumentType.getString(context, "enchantment");
		client.player.sendMessage(TextFormat.styledText(enchantments.get(enchantment)), false);

		return 1;
	}
}
