package cc.zyrn.farms.hoe;

public enum HoeType {

    IRON(1, 1, 1),
    GOLD(1.5, 1.5, 1.5),
    DIAMOND(1.7, 1.7, 1.7),
    ROSE(2, 2, 2);

    private final double cropsMultiplier, xpMultiplier, coinsMultiplier;

    HoeType(double cropsMultiplier, double xpMultiplier, double coinsMultiplier) {
        this.cropsMultiplier = cropsMultiplier;
        this.xpMultiplier = xpMultiplier;
        this.coinsMultiplier = coinsMultiplier;
    }

}
