package notcharrowutils.ticks;

public class TickRegistry {
	public static void registerTicks() {
		AutoAttackTickHandler.register();
		AutoFishTickHandler.register();
		AutoReplantTickHandler.register();
		AutoSprintTickHandler.register();
		AutoToolTickHandler.register();
		BreadcrumbsTickHandler.register();
		CoordinateOverlayTickHandler.register();
		FastPlaceTickHandler.register();
		NightVisionTickHandler.register();
	}
}
