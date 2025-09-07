package cc.zyrn.farms.farm.task;

import cc.zyrn.farms.Farms;
import cc.zyrn.farms.farm.Farm;
import cc.zyrn.farms.util.Lasers;
import lombok.AllArgsConstructor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class FarmCropTask extends BukkitRunnable {

    private final Farm farm;

    private int delay = 0, stepDelay = 8;

    @Override
    public void run() {
        final Location center = farm.getCenterLocation();

        if (center == null || center.getWorld() == null)
            return;

        World world = center.getWorld();

        int cx = center.getBlockX();
        int cz = center.getBlockZ();

        int radius = farm.getFarmType().getRadius();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx * dx + dz * dz > radius * radius)
                    continue;

                int x = cx + dx;
                int z = cz + dz;

                Block top = world.getHighestBlockAt(x, z);
                int safety = 0;
                while (safety++ < 8 && (top.getType().isAir() || top.isPassable())) {
                    top = top.getRelative(BlockFace.DOWN);
                }

                if (!top.getType().equals(Material.DIRT) && !top.getType().equals(Material.GRASS_BLOCK)) continue;

                Block above = top.getRelative(BlockFace.UP);

                if (!above.isEmpty()) continue;

                top.setType(Material.FARMLAND, false);
                above.setType(Material.CARROTS, false);

                BlockData bd = above.getBlockData();

                if (bd instanceof Ageable age) {
                    age.setAge(7);
                    above.setBlockData(age, false);
                }

                Location headLoc = farm.getStand().getLocation().clone().add(0.0, 1.4, 0.0);
                Location target = above.getLocation().add(0.5, 0.5, 0.5);

                Lasers.drawBeam(Farms.getInstance(), headLoc, target, Color.LIME,
                        1.5f, 0.2, 8);
            }
        }

        delay += stepDelay;
    }

}
