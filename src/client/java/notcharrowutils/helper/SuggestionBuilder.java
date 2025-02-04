package notcharrowutils.helper;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import java.util.List;

public class SuggestionBuilder {
	public static SuggestionProvider<FabricClientCommandSource> createSuggestionProvider(List<String> suggestions) {
		return (context, builder) -> {
			for (String suggestion : suggestions) {
				builder.suggest(suggestion);
			}
			return builder.buildFuture();
		};
	}
}
