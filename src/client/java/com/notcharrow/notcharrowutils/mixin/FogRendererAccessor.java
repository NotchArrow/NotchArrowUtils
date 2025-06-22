package com.notcharrow.notcharrowutils.mixin;

import net.minecraft.client.gl.MappableRingBuffer;
import net.minecraft.client.render.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.nio.ByteBuffer;

@Mixin(FogRenderer.class)
public interface FogRendererAccessor {
	@Invoker("applyFog")
	void callApplyFog(ByteBuffer buffer, int bufPos, Vector4f fogColor,
					  float environmentalStart, float environmentalEnd,
					  float renderDistanceStart, float renderDistanceEnd,
					  float skyEnd, float cloudEnd);

	@Accessor("fogBuffer")
	MappableRingBuffer getFogBuffer();
}
