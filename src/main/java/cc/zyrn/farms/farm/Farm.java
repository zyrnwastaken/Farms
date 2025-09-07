package cc.zyrn.farms.farm;

import cc.zyrn.farms.profile.Profile;
import lombok.Getter;
import net.minecraft.world.item.ItemArmorStand;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Farm {

    private final String id;

    private final Location centerLocation;
    private final FarmType farmType;

    private Entity stand;
    private int tickCounter;

    private final UUID owner;

    public Farm(UUID owner, Location centerLocation, FarmType farmType) {
        this.id = UUID.randomUUID().toString().substring(0, 6);

        this.owner = owner;
        this.centerLocation = centerLocation;
        this.farmType = farmType;

        this.spawn();
    }


    public final void spawn() {
        final World world = centerLocation.getWorld();

        this.tickCounter = 0;

        stand = world.spawn(centerLocation, ArmorStand.class, as -> {
            as.setInvisible(true);
            as.setSmall(true);
            as.setMarker(true);
            as.setGravity(false);
            Objects.requireNonNull(as.getEquipment()).setHelmet(farmType.getStack());
            as.setCollidable(false);
        });
    }

    public int incrementAndGetTick() {
        return tickCounter++;
    }


}
