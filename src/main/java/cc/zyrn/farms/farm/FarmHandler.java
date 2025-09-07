package cc.zyrn.farms.farm;

import cc.zyrn.farms.Farms;
import cc.zyrn.farms.farm.task.FarmAnimationTask;
import cc.zyrn.farms.profile.Profile;
import cc.zyrn.farms.util.CC;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class FarmHandler {

    private final Farms farms;
    private final List<Farm> farmsList;

    public FarmHandler(Farms farms) {
        this.farms = farms;
        this.farmsList = new ArrayList<>();

        farms.getServer().getScheduler().runTaskTimer(farms, new FarmAnimationTask(this), 1L, 1L);
    }

    public final boolean createFarm(Player player, Location location, FarmType farmType) {
        final Optional<Profile> optionalProfile = farms.getProfileHandler().getProfile(player.getUniqueId());

        if (optionalProfile.isEmpty()) {
            player.sendMessage(CC.translate("&cYour profile is not present!"));
            return false;
        }

        final Profile profile = optionalProfile.get();
        final Farm farm = new Farm(player.getUniqueId(), location, farmType);

        farmsList.add(farm);

        profile.addFarmId(farm.getId());
        return true;
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

    public final Optional<FarmType> getFarmType(ItemStack stack) {
        return Arrays.stream(FarmType.values()).filter(farmType -> farmType.getStack().isSimilar(stack))
                .findFirst();
    }

}
