package dev.phonis.horseinfomod.keybindings;

import dev.phonis.horseinfomod.gui.ConfigScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public
class Keybindings
{
    private static final String     category                   = "category.horseinfomod.horseInfoMod";
    public static final  KeyBinding openConfigScreenKeyBinding = KeyBindingHelper.registerKeyBinding(
        new KeyBinding("binding.horseinfomod.hIMMenu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, Keybindings.category));
    public static final  KeyBinding activateOverlayBinding     = KeyBindingHelper.registerKeyBinding(
        new KeyBinding("binding.horseinfomod.hIMOverlay", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_LEFT_ALT,
            Keybindings.category));

    public static
    void handle(MinecraftClient client)
    {
        while (Keybindings.openConfigScreenKeyBinding.wasPressed())
        {
            client.setScreen(ConfigScreen.getConfigScreen(client.currentScreen));
        }
    }

}
