package cc.zyrn.farms.profile;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

}
