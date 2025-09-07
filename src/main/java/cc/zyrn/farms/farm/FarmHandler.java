package cc.zyrn.farms.farm;

import cc.zyrn.farms.Farms;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FarmHandler {

    private final Farms farms;
    private final List<Farm> farmsList;

    public FarmHandler(Farms farms) {
        this.farms = farms;
        this.farmsList = new ArrayList<>();
    }

    public final boolean isFarm(Location location, double radiusCheck) {
        return farmsList.stream().anyMatch(farm ->
                (farm.getCenterLocation().distance(location) + radiusCheck) <= farm.getFarmType().getRadius());
    }

    public final Optional<Farm> getFarm(Location location) {
        return farmsList.stream().filter(farm -> farm.getCenterLocation().distance(location) <= farm.getFarmType().getRadius())
                .findFirst();
    }

    public final Set<Farm> getFarms(Player player) {
        return farmsList.stream().filter(farm -> farm.getOwner() == player.getUniqueId())
                .collect(Collectors.toSet());
    }

}
