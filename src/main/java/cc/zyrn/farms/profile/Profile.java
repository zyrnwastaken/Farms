package cc.zyrn.farms.profile;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;
    private final List<String> farmIds;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.farmIds = new ArrayList<>();
    }

    public final void addFarmId(String farmId) {
        farmIds.add(farmId);
    }

}
