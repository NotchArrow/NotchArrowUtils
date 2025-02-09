package notcharrowutils.ticks;

public class TickRegistry {
	public static void registerTicks() {
		AutoAttackTickHandler.register();
		AutoToolTickHandler.register();
		AutoFishTickHandler.register();
		AutoSprintTickHandler.register();
		BreadcrumbsTickHandler.register();
		CoordinateOverlayTickHandler.register();
		FastPlaceTickHandler.register();
		NightVisionTickHandler.register();
	}
}
