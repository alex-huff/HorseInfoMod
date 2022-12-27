package dev.phonis.horseinfomod;

import dev.phonis.horseinfomod.keybindings.Keybindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public
class HorseInfoMod implements ClientModInitializer
{

    @Override
    public
    void onInitializeClient()
    {
        ClientTickEvents.END_CLIENT_TICK.register(Keybindings::handle);
        Keybindings.handle(MinecraftClient.getInstance()); // Force Keybindings class to be loaded
    }

}
