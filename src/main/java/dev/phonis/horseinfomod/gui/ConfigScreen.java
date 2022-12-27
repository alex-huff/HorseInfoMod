package dev.phonis.horseinfomod.gui;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

public
class ConfigScreen
{
    private static final TranslatableTextContent configTitle = new TranslatableTextContent(
        "title.horseinfomod.config");

    public static
    Screen getConfigScreen(Screen parent)
    {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTransparentBackground(true)
            .setTitle(MutableText.of(ConfigScreen.configTitle));
        return builder.build();
    }
}
