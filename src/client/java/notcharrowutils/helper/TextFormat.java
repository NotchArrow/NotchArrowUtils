package notcharrowutils.helper;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TextFormat {
	public static Formatting COLOR = Formatting.DARK_AQUA;
	public static boolean BOLD = false;
	public static boolean ITALIC = false;
	public static boolean UNDERLINE = false;

	public static void setDefaults() {
		COLOR = Formatting.DARK_AQUA;
		BOLD = false;
		ITALIC = false;
		UNDERLINE = false;
	}

	public static Text styledText(String message) {
		MutableText styledText = Text.literal(message);
		if (BOLD) {
			styledText = styledText.formatted(Formatting.BOLD);
		}
		if (ITALIC) {
			styledText = styledText.formatted(Formatting.ITALIC);
		}
		if (UNDERLINE) {
			styledText = styledText.formatted(Formatting.UNDERLINE);
		}
		styledText = styledText.formatted(COLOR);
		return styledText;
	}
}
