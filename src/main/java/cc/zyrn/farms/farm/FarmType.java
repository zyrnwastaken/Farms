package cc.zyrn.farms.farm;

import lombok.Getter;

@Getter
public enum FarmType {

    COMMON(5, 1), UNCOMMON(10, 2),
    RARE(15, 3), LEGENDARY(20,4);

    private final double radius, speedMultiplier;

    FarmType(double radius, double speedMultiplier) {
        this.radius = radius;
        this.speedMultiplier = speedMultiplier;
    }

}
