package cc.zyrn.farms.farm;

import cc.zyrn.farms.profile.Profile;
import lombok.Getter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
public class Farm {

    private final Location centerLocation;
    private final FarmType farmType;

    private final UUID owner;

    public Farm(UUID owner, Location centerLocation, FarmType farmType) {
        this.owner = owner;
        this.centerLocation = centerLocation;
        this.farmType = farmType;

    }

}
