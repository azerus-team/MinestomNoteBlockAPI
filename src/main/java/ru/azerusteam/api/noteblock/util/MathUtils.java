package ru.azerusteam.api.noteblock.util;


import net.minestom.server.utils.Position;

public class MathUtils {

    private static MathUtils instance;
    private double[] cos = new double[360];
    private double[] sin = new double[360];

    public MathUtils() {
        instance = this;
        for (int deg = 0; deg < 360; deg++) {
            cos[deg] = Math.cos(Math.toRadians(deg));
            sin[deg] = Math.sin(Math.toRadians(deg));
        }
    }

    private static double[] getCos() {
        return MathUtils.instance.cos;
    }

    private static double[] getSin() {
        return MathUtils.instance.sin;
    }

    public static Position stereoSourceLeft(Position location, float distance) {
        float yaw = location.getYaw();
        return location.clone().add(-getCos()[(int) (yaw + 360) % 360] * distance, 0, -getSin()[(int) (yaw + 360) % 360] * distance);
    }

    public static Position stereoSourceRight(Position location, float distance) {
        float yaw = location.getYaw();
        return location.clone().add(getCos()[(int) (yaw + 360) % 360] * distance, 0, getSin()[(int) (yaw + 360) % 360] * distance);
    }

    /**
     * Calculate new location for stereo
     *
     * @param location origin location
     * @param distance negative for left side, positive for right side
     * @return
     */
    public static Position stereoPan(Position location, float distance) {
        float yaw = location.getYaw();
        return location.clone().add(getCos()[(int) (yaw + 360) % 360] * distance, 0, getSin()[(int) (yaw + 360) % 360] * distance);
    }
}

