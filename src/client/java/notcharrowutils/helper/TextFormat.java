package notcharrowutils.helper;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import notcharrowutils.config.ConfigManager;

public class TextFormat {

	public static Text styledText(String message) {
		MutableText styledText = Text.literal(message);
		if (ConfigManager.config.textformatBold) {
			styledText = styledText.formatted(Formatting.BOLD);
		}
		if (ConfigManager.config.textformatItalic) {
			styledText = styledText.formatted(Formatting.ITALIC);
		}
		if (ConfigManager.config.textformatUnderline) {
			styledText = styledText.formatted(Formatting.UNDERLINE);
		}
		styledText = styledText.formatted(Formatting.byName(ConfigManager.config.textformatColor));
		return styledText;
	}
}
