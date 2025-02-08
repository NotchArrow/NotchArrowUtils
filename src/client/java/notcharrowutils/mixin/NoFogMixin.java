package notcharrowutils.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import notcharrowutils.config.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class NoFogMixin {
	@Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
	private static void removeFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();

		if (ConfigManager.config.mixinNoFog && client.world != null && viewDistance != 0) {
			ci.cancel();
		}
	}
}