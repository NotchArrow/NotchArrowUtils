package com.notcharrow.notcharrowutils.ticks;

public class TickRegistry {
	public static void registerTicks() {
		AutoAttackTickHandler.register();
		AutoFishTickHandler.register();
		AutoRefillTickHandler.register();
		AutoReplantTickHandler.register();
		AutoRocketTickHandler.register();
		AutoSprintTickHandler.register();
		AutoToolTickHandler.register();
		BreadcrumbsTickHandler.register();
		CameraLockTickHandler.register();
		CoordinateOverlayTickHandler.register();
		FastPlaceTickHandler.register();
		NightVisionTickHandler.register();
		PickupNotifierTickHandler.register();
		StatisticsTickHandler.register();
		StormPauseTickHandler.register();
	}
}
