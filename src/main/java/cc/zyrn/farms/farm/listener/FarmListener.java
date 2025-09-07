package cc.zyrn.farms.farm.listener;

import cc.zyrn.farms.Farms;
import cc.zyrn.farms.farm.Farm;
import cc.zyrn.farms.farm.FarmHandler;
import cc.zyrn.farms.farm.FarmType;
import cc.zyrn.farms.util.CC;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Optional;

@AllArgsConstructor
public class FarmListener implements Listener {

    private final Farms farms;
    private final FarmHandler farmHandler;

    @EventHandler
    public final void onBlockPlaceEvent(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Optional<FarmType> farmTypeOptional = farmHandler.getFarmType(player.getItemInUse());

        if (farmTypeOptional.isEmpty() || player.getItemInUse() == null)
            return;

        event.setBuild(false);
        event.setCancelled(true);

        final FarmType farmType = farmTypeOptional.get();
        final Location location = event.getBlockPlaced().getLocation();
        final Optional<Farm> optionalFarm = farmHandler.getFarm(location);

        if (optionalFarm.isPresent()) {
            player.sendMessage(CC.translate("&cThere is a farm here belonging to " +
                    farms.getServer().getOfflinePlayer(optionalFarm.get().getOwner()).getName()));
            return;
        }

        if (!farmHandler.createFarm(player, location, farmType))
            return;

        player.getItemInUse().setAmount(player.getItemInUse().getAmount() - 1);

    }

}
