package cc.zyrn.farms.profile;

import cc.zyrn.farms.Farms;
import cc.zyrn.farms.farm.Farm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileHandler {

    private final Map<UUID, Profile> profileMap;
    private final Farms farms;

    public ProfileHandler(Farms farms) {
        this.farms = farms;
        this.profileMap = new HashMap<>();
    }

    public final void loadProfile(UUID uuid) {

    }

    public final void handleRemoval(Profile profile) {

    }

}
