package cc.zyrn.farms.farm.task;

import cc.zyrn.farms.farm.Farm;
import cc.zyrn.farms.farm.FarmHandler;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class FarmAnimationTask extends BukkitRunnable {

    private final FarmHandler farmHandler;

    @Override
    public void run() {
        double amplitude = 0.25;
        double speed = 0.15;

        for (Farm farm : farmHandler.getFarmsList()) {
            final Entity stand = farm.getStand();

            if (stand == null || stand.isDead() || !stand.isValid()) {
                continue;
            }

            int tick = farm.incrementAndGetTick();
            double y = amplitude * Math.sin(tick * speed);

            Location base = stand.getLocation();

            Location clone = base.clone();
            clone.setY(base.getY() + y);

            stand.teleport(clone);
        }
    }


}

