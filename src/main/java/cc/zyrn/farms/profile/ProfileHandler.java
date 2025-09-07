package cc.zyrn.farms.profile;

import cc.zyrn.farms.Farms;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    public final Optional<Profile> getProfile(UUID uuid) {
        return Optional.ofNullable(profileMap.get(uuid));
    }

}
