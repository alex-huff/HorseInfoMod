package dev.phonis.horseinfomod;

import dev.phonis.horseinfomod.keybindings.Keybindings;
import dev.phonis.horseinfomod.render.HorseOverlayRenderer;
import dev.phonis.horseinfomod.util.HorsieUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public
class HorseInfoMod implements ClientModInitializer
{

    @Override
    public
    void onInitializeClient()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if (Keybindings.activateOverlayBinding.isPressed())
            {
                HorsieUtils.getLookedAtHorse()
                    .ifPresent((horsie) -> HorseOverlayRenderer.renderHorseOverlay(matrixStack, tickDelta, horsie));
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(Keybindings::handle);
        Keybindings.handle(MinecraftClient.getInstance()); // Force Keybindings class to be loaded
    }

}
