package com.notcharrow.notcharrowutils.helper;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TextFormat {

	public static Text styledText(String message) {
		MutableText styledText = Text.literal(message);

		Style textStyle = Style.EMPTY
				.withBold(ConfigManager.config.textformatBold)
				.withItalic(ConfigManager.config.textformatItalic)
				.withUnderline(ConfigManager.config.textformatUnderline)
				.withColor(Formatting.byName(ConfigManager.config.textformatColor));
		styledText.setStyle(textStyle);

		if (ConfigManager.config.textformatPrefix) {
			MutableText prefixText = Text.literal("[Utils] ");

			Style prefixStyle = Style.EMPTY
					.withBold(ConfigManager.config.textformatBoldPrefix)
					.withItalic(ConfigManager.config.textformatItalicPrefix)
					.withUnderline(ConfigManager.config.textformatUnderlinePrefix)
					.withColor(Formatting.byName(ConfigManager.config.textformatColorPrefix));
			prefixText.setStyle(prefixStyle);

			styledText = prefixText.append(styledText);
		}

		return styledText;
	}
}
