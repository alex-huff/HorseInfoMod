package dev.phonis.horseinfomod.util;

public
class RandomUtils
{

    /*
    The maximum random value that Minecraft's Random implementation can return on Random::nextDouble()
     */
    public static
    double getMaxRandomNextDouble()
    {
        return (double) (((long) 0x03FFFFFF << 27) + (long) 0x07FFFFFF) * 1.1102230246251565E-16;
    }

}
