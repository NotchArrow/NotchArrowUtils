package notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;
import notcharrowutils.config.ConfigManager;

import java.util.Random;

public class AutoFishTickHandler {
	static Random random = new Random();
	static long waterContactTime = -1;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryAutoFishMode && client.player != null) {
				FishingBobberEntity bobber = client.player.fishHook;

				if (bobber != null) {

					if (bobber.isTouchingWater() && waterContactTime == -1) {
						waterContactTime = System.nanoTime();
						System.out.println(waterContactTime);
					}

					System.out.println(waterContactTime);
					if (bobber.getVelocity().y < -0.05 && waterContactTime != -1 && (System.nanoTime() - waterContactTime > 1_000_000_000)) {
						client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
						waterContactTime = -1;
						try {
							Thread.sleep(random.nextInt(1000 - 500 + 1) + 500);
						} catch (InterruptedException ignored) {
						}
						client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
					}
				}
			}
		});
	}
}
