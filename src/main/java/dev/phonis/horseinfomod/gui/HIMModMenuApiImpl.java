package dev.phonis.horseinfomod.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public
class HIMModMenuApiImpl implements ModMenuApi
{
    @Override
    public
    ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return ConfigScreen::getConfigScreen;
    }
}
