package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.config.NotchArrowUtilsConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OverlayTickHandler {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private static final Identifier OVERLAY_LAYER = Identifier.of("com.notcharrow.notcharrowutils", "overlay");
	private static List<Text> overlayLines = new ArrayList<>();

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.world != null && client.crosshairTarget != null && client.getNetworkHandler() != null
					&& ConfigManager.config.tickregistryOverlay) {
				overlayLines = new ArrayList<>();

				if (ConfigManager.config.tickregistryOverlayCoordinates) {
					overlayLines.add(Text.of(client.player.getBlockX() + ", " + client.player.getBlockY() + ", " + client.player.getBlockZ()));
				}

				if (ConfigManager.config.tickregistryOverlayFPS) {
					overlayLines.add(Text.of("FPS: " + client.getCurrentFps()));
				}

				if (ConfigManager.config.tickregistryOverlayPing) {
					int ping = Objects.requireNonNull(client.getNetworkHandler().getPlayerListEntry(client.player.getUuid())).getLatency();
					overlayLines.add(Text.of(("Ping: " + ping + "ms")));
				}

				if (ConfigManager.config.tickregistryOverlaySpeed) {
					double xVelocity = client.player.getVelocity().x;
					double zVelocity = client.player.getVelocity().z;
					double totalVelocity = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(zVelocity, 2)) * 20.0;
					totalVelocity = Math.round(totalVelocity * Math.pow(10, 2)) / Math.pow(10, 2);
					String displayVelocity = String.valueOf(totalVelocity);
					if (displayVelocity.length() == 3) {
						displayVelocity += "0";
					}
					overlayLines.add(Text.of("Speed: " + displayVelocity + "m/s"));
				}

				if (ConfigManager.config.tickregistryOverlayFacing) {
					float yaw = client.player.getYaw();
					if (yaw < 0) {
						yaw += 360;
					}
					String cardinal;
					if (yaw >= 337.5 || yaw < 22.5) cardinal = "S";
					else if (yaw < 67.5) cardinal = "SW";
					else if (yaw < 112.5) cardinal = "W";
					else if (yaw < 157.5) cardinal = "NW";
					else if (yaw < 202.5) cardinal = "N";
					else if (yaw < 247.5) cardinal = "NE";
					else if (yaw < 292.5) cardinal = "E";
					else cardinal = "SE";

					String coordChange = "";
					Direction facing = Direction.fromHorizontalDegrees(client.player.getYaw());
					if (facing.getAxis() == Direction.Axis.X) {
						if (facing.getDirection() == Direction.AxisDirection.POSITIVE) {
							coordChange = "(+X)";
						} else {
							coordChange = "(-X)";
						}
					} else if (facing.getAxis() == Direction.Axis.Z) {
						if (facing.getDirection() == Direction.AxisDirection.POSITIVE) {
							coordChange = "(+Z)";
						} else {
							coordChange = "(-Z)";
						}
					}
					overlayLines.add(Text.of("Facing: " + cardinal + " " + coordChange));
				}

				if (ConfigManager.config.tickregistryOverlayDay) {
					long day = client.world.getTimeOfDay() / 24000;
					overlayLines.add(Text.of("Day: " + day));
				}

				if (ConfigManager.config.tickregistryOverlayTime) {
					long time = client.world.getTimeOfDay();
					int hour = (int) (time / 1000 + 6) % 24;
					String minute = String.valueOf((int) (time / 16.6666667 % 60));
					if (minute.length() == 1) {
						minute = "0" + minute;
					}
					overlayLines.add(Text.of("Time: " + hour + ":" + minute));
				}

				if (ConfigManager.config.tickregistryOverlayBiome) {
					String biome = "N/A";
					if (client.world.getBiome(client.player.getBlockPos()).getKey().isPresent()) {
						biome = client.world.getBiome(client.player.getBlockPos()).getKey().get().getValue().getPath();
						String[] parts = biome.split("_");
						StringBuilder displayName = new StringBuilder();
						for (String part : parts) {
							if (!part.isEmpty()) {
								displayName.append(Character.toUpperCase(part.charAt(0)))
										.append(part.substring(1))
										.append(" ");
							}
						}
						biome = displayName.toString().trim();
					}

					overlayLines.add(Text.of("Biome: " + biome));
				}

				if (ConfigManager.config.tickregistryOverlayTargetedBlock) {
					String blockName = "";
					if (client.crosshairTarget.getType() == HitResult.Type.BLOCK) {
						BlockHitResult blockHit = (BlockHitResult) client.crosshairTarget;
						Block block = client.world.getBlockState(blockHit.getBlockPos()).getBlock();
						blockName = block.getName().getString();
						blockName = Character.toUpperCase(blockName.charAt(0)) + blockName.substring(1);
					}

					overlayLines.add(Text.of("Targeted Block: " + blockName));
				}
			}
		});
		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, OVERLAY_LAYER, OverlayTickHandler::render));
	}

	private static void render(DrawContext context, RenderTickCounter tickCounter) {
		if (ConfigManager.config.tickregistryOverlay) {
			TextRenderer textRenderer = client.textRenderer;
			MatrixStack matrices = context.getMatrices();
			float scale = ConfigManager.config.tickregistryOverlayScale;
			int color = 0xFFFFFF;
			int y;
			if (ConfigManager.config.tickregistryOverlayLocation == NotchArrowUtilsConfig.OverlayLocation.TOP_LEFT ||
					ConfigManager.config.tickregistryOverlayLocation == NotchArrowUtilsConfig.OverlayLocation.TOP_RIGHT) {
				y = (int) (5 * scale);
			} else {
				y = (int) (client.getWindow().getScaledHeight() - 20 * scale);
			}

			for (Text displayText: overlayLines) {
				int x;
				if (ConfigManager.config.tickregistryOverlayLocation == NotchArrowUtilsConfig.OverlayLocation.TOP_LEFT ||
				ConfigManager.config.tickregistryOverlayLocation == NotchArrowUtilsConfig.OverlayLocation.BOTTOM_LEFT) {
					x = (int) (5 * scale);
				} else {
					x = (int) (client.getWindow().getScaledWidth() - textRenderer.getWidth(displayText) * scale - 5 * scale);
				}

				matrices.push();
				matrices.translate(x, y, 0);
				matrices.scale(scale, scale, 1.0f);

				context.drawText(textRenderer, displayText, 0, 0, color, ConfigManager.config.tickregistryOverlayTextShadow);

				matrices.pop();
				if (ConfigManager.config.tickregistryOverlayLocation == NotchArrowUtilsConfig.OverlayLocation.TOP_LEFT ||
						ConfigManager.config.tickregistryOverlayLocation == NotchArrowUtilsConfig.OverlayLocation.TOP_RIGHT) {
					y += (int) (10 * scale);
				} else {
					y -= (int) (10 * scale);
				}
			}
		}
	}
}