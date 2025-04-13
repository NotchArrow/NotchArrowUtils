package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.component.type.WritableBookContentComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.RawFilteredPair;
import net.minecraft.util.Hand;

import java.util.ArrayList;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Notes {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static boolean openBook = false;
	private static BookEditScreen lastBookScreen = null;
	private static ArrayList<RawFilteredPair<String>> savedPages = new ArrayList<>();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("notes")
				.executes(Notes::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			openBook = true;
			ClientTickEvents.END_CLIENT_TICK.register(client -> {
				if (openBook) {
					openBook = false;
					client.setScreen(new BookEditScreen(
							client.player,
							new ItemStack(Items.WRITABLE_BOOK),
							Hand.MAIN_HAND,
							new WritableBookContentComponent(savedPages)
					));
				}
				if (client.currentScreen instanceof BookEditScreen) {
					lastBookScreen = (BookEditScreen) client.currentScreen;
				} else if (lastBookScreen != null) {
					//WritableBookContentComponent content = lastBookScreen.getBookContent();
					//savedPages = new ArrayList<>(content.pages());
					lastBookScreen = null;
				}
			});
		}

		return 1;
	}
}
