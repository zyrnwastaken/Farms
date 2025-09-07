package cc.zyrn.farms;

import cc.zyrn.farms.profile.ProfileHandler;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Farms extends JavaPlugin {

    @Getter
    private static Farms instance;

    private ProfileHandler profileHandler;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        profileHandler = new ProfileHandler(this);
    }

}
