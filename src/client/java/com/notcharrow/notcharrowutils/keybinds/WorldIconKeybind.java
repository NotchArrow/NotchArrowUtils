package com.notcharrow.notcharrowutils.keybinds;

import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.util.Util;
import net.minecraft.util.WorldSavePath;

import java.io.IOException;
import java.nio.file.Path;

public class WorldIconKeybind {
	private static long lastCaptureTime = 0;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (KeybindRegistry.worldIconKeybind.isPressed()) {
				if (client.worldRenderer.getCompletedChunkCount() > 10 &&
						client.worldRenderer.isTerrainRenderComplete() &&
						client.getServer() != null && client.player != null
					&& System.currentTimeMillis() - lastCaptureTime > 1000) {

					lastCaptureTime = System.currentTimeMillis();

					ScreenshotRecorder.takeScreenshot(client.getFramebuffer(), (NativeImage nativeImage) -> {
						Path path = client.getServer().getSavePath(WorldSavePath.ICON_PNG);

						Util.getIoWorkerExecutor().execute(() -> {
							int i = nativeImage.getWidth();
							int j = nativeImage.getHeight();
							int k = 0;
							int l = 0;
							if (i > j) {
								k = (i - j) / 2;
								i = j;
							} else {
								l = (j - i) / 2;
								j = i;
							}

							try (NativeImage resized = new NativeImage(64, 64, false)) {
								nativeImage.resizeSubRectTo(k, l, i, j, resized);
								resized.writeTo(path);
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								nativeImage.close();
							}
						});
					});

					client.player.sendMessage(TextFormat.styledText("World Icon Updated Successfully!"), false);
				}
			}
		});
	}
}
