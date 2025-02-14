package notcharrowutils.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import notcharrowutils.config.ConfigManager;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackgroundRenderer.class)
public class NoFogMixin {

	@Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
	private static void removeFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
		MinecraftClient client = MinecraftClient.getInstance();

		if (client.world != null && ConfigManager.config.mixinNoFog && viewDistance > 0) {
			Fog dummyFog = Fog.DUMMY;
			cir.setReturnValue(dummyFog);
		}
	}
}