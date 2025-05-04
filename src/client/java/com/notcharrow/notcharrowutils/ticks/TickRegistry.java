package com.notcharrow.notcharrowutils.ticks;

public class TickRegistry {
	public static void registerTicks() {
		AutoAttackTickHandler.register();
		AutoElytraTickHandler.register();
		AutoFishTickHandler.register();
		AutoRefillTickHandler.register();
		AutoReplantTickHandler.register();
		AutoRocketTickHandler.register();
		AutoSprintTickHandler.register();
		AutoToolTickHandler.register();
		BreadcrumbsTickHandler.register();
		CameraLockTickHandler.register();
		CoordinateOverlayTickHandler.register();
		DurabilityWarningTickHandler.register();
		FastPlaceTickHandler.register();
		NightVisionTickHandler.register();
		OverlayTickHandler.register();
		PickupNotifierTickHandler.register();
		ProjectileTrailTickHandler.register();
		StatisticsTickHandler.register();
		StormPauseTickHandler.register();
	}
}
