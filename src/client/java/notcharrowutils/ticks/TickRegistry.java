package notcharrowutils.ticks;

public class TickRegistry {
	public static void registerTicks() {
		AutoAttackTickHandler.register();
		AutoToolTickHandler.register();
		AutoFishTickHandler.register();
		BreadcrumbsTickHandler.register();
		CoordinateOverlayTickHandler.register();
		NightVisionTickHandler.register();
	}
}
