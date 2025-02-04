package notcharrowutils.ticks;

public class TickRegistry {
	public static boolean mobGrinderMode = false;
	public static boolean autoFishMode = false;
	public static boolean autoTool = false;
	public static boolean coordinateOverlay = false;
	public static boolean breadcrumbs = false;

	public static void registerTicks() {
		MobGrinderTickHandler.register();
		AutoToolTickHandler.register();
		CoordinateOverlayTickHandler.register();
		AutoFishTickHandler.register();
		BreadcrumbsTickHandler.register();
	}
}
