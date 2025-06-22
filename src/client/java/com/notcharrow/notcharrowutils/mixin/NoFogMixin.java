package com.notcharrow.notcharrowutils.mixin;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class NoFogMixin {

	@Inject(method = "applyFog*", at = @At("HEAD"), cancellable = true)
	private void onApplyFog(Camera camera, int viewDistance, boolean thick, RenderTickCounter tickCounter,
							float skyDarkness, ClientWorld world, CallbackInfoReturnable<Vector4f> cir) {
		if (ConfigManager.config.mixinNoFog && world != null && viewDistance > 0) {
			Vector4f whiteFog = new Vector4f(1f, 1f, 1f, 1f);
			float distance = viewDistance * 16f;

			FogRendererAccessor accessor = (FogRendererAccessor) this;

			try (GpuBuffer.MappedView mappedView = RenderSystem.getDevice()
					.createCommandEncoder()
					.mapBuffer(accessor.getFogBuffer().getBlocking(), false, true)) {

				accessor.callApplyFog(mappedView.data(), 0,
						whiteFog,
						distance, distance,   // environmentalStart, environmentalEnd
						distance, distance,   // renderDistanceStart, renderDistanceEnd
						distance, distance);  // skyEnd, cloudEnd
			}
			cir.setReturnValue(whiteFog);
		}
	}
}