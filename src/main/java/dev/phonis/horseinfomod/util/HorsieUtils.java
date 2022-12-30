package dev.phonis.horseinfomod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.Objects;
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
    int getMaxScore()
    {
        return 1;
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
    double getScorePercent(AbstractHorseEntity horsie)
    {
        return HorsieUtils.getScore(horsie) / HorsieUtils.getMaxScore();
    }

    public static
    double getScore(AbstractHorseEntity horsie)
    {
        return (HorsieUtils.getMovementSpeed(horsie) / HorsieUtils.getMaxMovementSpeed() +
                HorsieUtils.getJumpStrength(horsie) / HorsieUtils.getMaxJumpStrength() +
                HorsieUtils.getHealth(horsie) / HorsieUtils.getMaxHealth()) / 3;
    }

    public static
    String getColorString(AbstractHorseEntity horsie)
    {
        if (horsie instanceof HorseEntity horsieHorse)
        {
            return switch (horsieHorse.getColor())
                {
                    case CHESTNUT -> "Chestnut";
                    case BLACK -> "Black";
                    case BROWN -> "Brown";
                    case CREAMY -> "Creamy";
                    case DARKBROWN -> "Dark brown";
                    case GRAY -> "Gray";
                    case WHITE -> "White";
                };
        }
        return "N/A";
    }

    public static
    String getMarkingString(AbstractHorseEntity horsie)
    {
        if (horsie instanceof HorseEntity horsieHorse)
        {
            return switch (horsieHorse.getMarking())
                {
                    case BLACK_DOTS -> "Black dots";
                    case WHITE_DOTS -> "White spots";
                    case WHITE_FIELD -> "White field";
                    case WHITE -> "White stockings and blaze";
                    case NONE -> "None";
                };
        }
        return "N/A";
    }

    public static
    String getCustomNameString(AbstractHorseEntity horsie)
    {
        if (horsie.hasCustomName())
        {
            return Objects.requireNonNull(horsie.getCustomName()).getString();
        }
        return "N/A";
    }

    public static
    void printHorseDetailsInChat(AbstractHorseEntity horsie)
    {
        MinecraftClient    minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player          = minecraftClient.player;
        String speedMessage = "Speed: " + StringUtils.formatDouble(HorsieUtils.getMovementSpeed(horsie)) + " / " +
                              StringUtils.formatDouble(HorsieUtils.getMaxMovementSpeed());
        String jumpMessage = "Jump: " + StringUtils.formatDouble(HorsieUtils.getJumpStrength(horsie)) + " / " +
                             StringUtils.formatDouble(HorsieUtils.getMaxJumpStrength());
        String healthMessage = "Health: " + StringUtils.formatDouble(HorsieUtils.getHealth(horsie)) + " / " +
                               StringUtils.formatDouble(HorsieUtils.getMaxHealth());
        String scoreMessage = "Score: " + StringUtils.formatDouble(HorsieUtils.getScore(horsie)) + " / " +
                              StringUtils.formatDouble(HorsieUtils.getMaxScore());
        if (player == null)
        {
            return;
        }
        player.sendMessage(Text.of(speedMessage));
        player.sendMessage(Text.of(jumpMessage));
        player.sendMessage(Text.of(healthMessage));
        player.sendMessage(Text.of(scoreMessage));
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
