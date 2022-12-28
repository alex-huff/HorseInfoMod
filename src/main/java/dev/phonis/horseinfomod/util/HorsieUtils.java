package dev.phonis.horseinfomod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.Optional;

import static dev.phonis.horseinfomod.util.RandomUtils.getMaxRandomNextDouble;

public
class HorsieUtils
{

    public static
    int getMaxHealth()
    {
        return 15 + 7 + 8;
    }

    public static
    double getMaxJumpStrength()
    {
        return 0.4000000059604645 + getMaxRandomNextDouble() * 0.2 + getMaxRandomNextDouble() * 0.2 +
               getMaxRandomNextDouble() * 0.2;
    }

    public static
    double getMaxMovementSpeed()
    {
        return (0.44999998807907104 + getMaxRandomNextDouble() * 0.3 + getMaxRandomNextDouble() * 0.3 +
                getMaxRandomNextDouble() * 0.3) * 0.25;
    }

    public static
    int getMaxCockSize()
    {
        return 64;
    }

    public static
    double getMovementSpeed(AbstractHorseEntity horsie)
    {
        return horsie.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
    }

    public static
    double getJumpStrength(AbstractHorseEntity horsie)
    {
        return horsie.getAttributeValue(EntityAttributes.HORSE_JUMP_STRENGTH);
    }

    public static
    double getHealth(AbstractHorseEntity horsie)
    {
        return horsie.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
    }

    public static
    double getMovementSpeedPercent(AbstractHorseEntity horsie)
    {
        return HorsieUtils.getMovementSpeed(horsie) / HorsieUtils.getMaxMovementSpeed();
    }

    public static
    double getJumpStrengthPercent(AbstractHorseEntity horsie)
    {
        return HorsieUtils.getJumpStrength(horsie) / HorsieUtils.getMaxJumpStrength();
    }

    public static
    double getHealthPercent(AbstractHorseEntity horsie)
    {
        return HorsieUtils.getHealth(horsie) / HorsieUtils.getMaxHealth();
    }

    public static
    double getCockSizePercent(AbstractHorseEntity horsie)
    {
        return HorsieUtils.getCockSize(horsie) / HorsieUtils.getMaxCockSize();
    }

    public static
    double getCockSize(AbstractHorseEntity horsie)
    {
        return (HorsieUtils.getMovementSpeed(horsie) / HorsieUtils.getMaxMovementSpeed() +
                HorsieUtils.getJumpStrength(horsie) / HorsieUtils.getMaxJumpStrength() +
                HorsieUtils.getHealth(horsie) / HorsieUtils.getMaxHealth()) / 3 * HorsieUtils.getMaxCockSize();
    }

    public static
    Optional<AbstractHorseEntity> getLookedAtHorse()
    {
        MinecraftClient client    = MinecraftClient.getInstance();
        HitResult       hitResult = client.crosshairTarget;
        if (hitResult == null || !hitResult.getType().equals(HitResult.Type.ENTITY))
        {
            return Optional.empty();
        }
        EntityHitResult entityHitResult = (EntityHitResult) hitResult;
        Entity          entity          = entityHitResult.getEntity();
        if (entity instanceof AbstractHorseEntity)
        {
            return Optional.of((AbstractHorseEntity) entity);
        }
        return Optional.empty();
    }

}
