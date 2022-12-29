package dev.phonis.horseinfomod.mixin;

import dev.phonis.horseinfomod.render.EntityRenderController;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract
class MixinEntityRenderer<T extends Entity>
{

    @Inject(at = @At(value = "HEAD"), method = "render", cancellable = true)
    void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                int light, CallbackInfo ci)
    {
        if (!EntityRenderController.shouldRenderNameTags()) ci.cancel();
    }

}
