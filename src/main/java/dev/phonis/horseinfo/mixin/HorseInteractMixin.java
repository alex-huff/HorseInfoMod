package dev.phonis.horseinfo.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorseEntity.class)
public
class HorseInteractMixin
{
	@Inject(at = @At("HEAD"), method = "putPlayerOnBack")
	private
	void onHorseInteract(final PlayerEntity player, CallbackInfo info)
	{
		AbstractHorseEntity he = ((AbstractHorseEntity) (Object) this);

		if (he.world.isClient())
		{
			double speed        = he.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
			double jumpStrength = he.getAttributeValue(EntityAttributes.HORSE_JUMP_STRENGTH);

			player.sendMessage(Text.of("Speed: " + speed + " Jump: " + jumpStrength), false);
		}
	}
}
