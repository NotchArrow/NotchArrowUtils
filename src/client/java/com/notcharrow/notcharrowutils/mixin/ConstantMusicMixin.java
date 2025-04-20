package com.notcharrow.notcharrowutils.mixin;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicTracker.class)
public class ConstantMusicMixin {
	@Unique private static final MinecraftClient client = MinecraftClient.getInstance();
	@Shadow private int timeUntilNextSong;
	@Shadow private SoundInstance current;

	@Inject(method = "tick", at = @At("HEAD"))
	private void onTick(CallbackInfo ci) {
		if (ConfigManager.config.mixinConstantMusic) {
			if (!client.getSoundManager().isPlaying(current)) {
				timeUntilNextSong = 0;
			}

			if (timeUntilNextSong > 0) {
				timeUntilNextSong = 0; // prevent delay between tracks
			}
		}
	}
}
