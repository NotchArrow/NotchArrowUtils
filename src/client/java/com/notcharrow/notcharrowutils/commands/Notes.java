package com.notcharrow.notcharrowutils.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import com.notcharrow.notcharrowutils.mixin.BookEditScreenAccessor;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Hand;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Notes {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static boolean openBook = false;
	private static BookEditScreen lastBookScreen = null;
	private static NbtList savedPages = new NbtList();
	private static ItemStack currentBook = new ItemStack(Items.WRITABLE_BOOK);
	private static final Gson GSON = new Gson();
	private static final Path NOTES_FILE = Path.of("config/notcharrowutils_notes.json");

	static {
		loadNotesFromFile();
	}

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("notes")
				.executes(Notes::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (client.player.getInventory().getMainHandStack().getItem() == Items.WRITABLE_BOOK) {
				client.player.sendMessage(TextFormat.styledText("Cannot open notes while holding a book."), false);
			}

			currentBook.setNbt(currentBook.getOrCreateNbt());
			currentBook.getNbt().put("pages", savedPages);


			openBook = true;
			ClientTickEvents.END_CLIENT_TICK.register(client -> {
				if (client.player != null && client.player.getInventory().getMainHandStack().getItem() != Items.WRITABLE_BOOK) {
					if (openBook) {
						openBook = false;
						client.setScreen(new BookEditScreen(
								client.player,
								currentBook,
								Hand.MAIN_HAND
						));
					}

					if (client.currentScreen instanceof BookEditScreen) {
						lastBookScreen = (BookEditScreen) client.currentScreen;
					} else if (lastBookScreen != null) {
						BookEditScreenAccessor accessor = (BookEditScreenAccessor) lastBookScreen;
						List<String> editedPages = accessor.getPages();

						savedPages.clear();
						for (String page : editedPages) {
							savedPages.add(NbtString.of(page));
						}

						saveNotesToFile();
						lastBookScreen = null;
					}
				}
			});
		}

		return 1;
	}

	private static void saveNotesToFile() {
		try {
			List<String> noteList = savedPages.stream()
					.filter(nbt -> nbt instanceof NbtString)
					.map(NbtElement::asString)
					.toList();

			String json = GSON.toJson(noteList);
			Files.writeString(NOTES_FILE, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadNotesFromFile() {
		savedPages.clear();
		if (Files.exists(NOTES_FILE)) {
			try {
				String json = Files.readString(NOTES_FILE);
				Type listType = new TypeToken<List<String>>() {}.getType();
				List<String> pages = GSON.fromJson(json, listType);
				for (String page : pages) {
					savedPages.add(NbtString.of(page));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			savedPages.add(NbtString.of("These notes persist across worlds, servers, and sessions. " +
					"Store coordinates, build ideas, and more!"));
		}
	}
}