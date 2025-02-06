package notcharrowutils.ticks;

public class TickRegistry {
	public static void registerTicks() {
		MobGrinderTickHandler.register();
		AutoToolTickHandler.register();
		CoordinateOverlayTickHandler.register();
		AutoFishTickHandler.register();
		BreadcrumbsTickHandler.register();
	}
}
