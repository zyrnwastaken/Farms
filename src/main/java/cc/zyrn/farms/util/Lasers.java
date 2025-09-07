package cc.zyrn.farms.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class Lasers {

    /**
     * Draws a straight "laser" from -> to for a few ticks using REDSTONE dust particles.
     *
     * @param plugin your plugin
     * @param from world-space start (e.g., head location + eye offset)
     * @param to world-space end (e.g., block center above the farmland)
     * @param color particle color
     * @param thickness dust size (1.0f–3.0f looks good)
     * @param segmentStep how far between particle points, in blocks (e.g., 0.2)
     * @param lifeTicks how many ticks to redraw (e.g., 6–10)
     */
    public static void drawBeam(JavaPlugin plugin,
                                Location from,
                                Location to,
                                org.bukkit.Color color,
                                float thickness,
                                double segmentStep,
                                int lifeTicks) {

        if (from.getWorld() == null || to.getWorld() == null) return;
        if (!from.getWorld().equals(to.getWorld())) return;

        final World world = from.getWorld();
        final Vector delta = to.toVector().subtract(from.toVector());
        final double length = delta.length();
        if (length <= 1.0e-5) return;

        final Vector step = delta.clone().normalize().multiply(segmentStep);
        final int points = Math.max(1, (int) Math.ceil(length / segmentStep));
        final Particle.DustOptions dust = new Particle.DustOptions(color, thickness);

        new BukkitRunnable() {
            int t = 0;
            @Override public void run() {
                if (t++ >= lifeTicks) { cancel(); return; }

                Location p = from.clone();
                for (int i = 0; i <= points; i++) {
                    world.spawnParticle(Particle.REDSTONE, p, 1, dust);
                    p.add(step);
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}
