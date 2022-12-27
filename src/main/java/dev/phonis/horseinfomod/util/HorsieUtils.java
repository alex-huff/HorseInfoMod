package dev.phonis.horseinfomod.util;

import static dev.phonis.horseinfomod.util.RandomUtils.getMaxRandomNextDouble;

public
class HorsieUtils
{

    private static
    int getMaxHealth()
    {
        return 15 + 7 + 8;
    }

    private static
    double getMaxJumpStrength()
    {
        return 0.4000000059604645 + getMaxRandomNextDouble() * 0.2 + getMaxRandomNextDouble() * 0.2 +
               getMaxRandomNextDouble() * 0.2;
    }

    private static
    double getMaxMovementSpeed()
    {
        return (0.44999998807907104 + getMaxRandomNextDouble() * 0.3 + getMaxRandomNextDouble() * 0.3 +
                getMaxRandomNextDouble() * 0.3) * 0.25;
    }

}
