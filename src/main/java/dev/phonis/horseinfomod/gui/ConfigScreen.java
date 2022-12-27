package dev.phonis.horseinfomod.gui;

import dev.phonis.horseinfomod.config.HIMConfig;
import dev.phonis.horseinfomod.keybindings.Keybindings;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

public
class ConfigScreen
{
    private static final TranslatableTextContent configTitle                = new TranslatableTextContent(
        "title.horseinfomod.config");
    private static final TranslatableTextContent visualCategoryName         = new TranslatableTextContent(
        "category.horseinfomod.visual");
    private static final TranslatableTextContent keybindingsCategoryName    = new TranslatableTextContent(
        "category.horseinfomod.keybindings");
    private static final TranslatableTextContent renderScaleOption          = new TranslatableTextContent(
        "option.horseinfomod.renderScale");
    private static final TranslatableTextContent renderScaleTooltip         = new TranslatableTextContent(
        "tooltip.horseinfomod.renderScale");
    private static final TranslatableTextContent hIMMenuBindingName         = new TranslatableTextContent(
        "binding.horseinfomod.hIMMenu");
    private static final TranslatableTextContent activateOverlayBindingName = new TranslatableTextContent(
        "binding.horseinfomod.hIMOverlay");

    public static
    Screen getConfigScreen(Screen parent)
    {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTransparentBackground(true)
            .setTitle(MutableText.of(ConfigScreen.configTitle));
        builder.setSavingRunnable(HIMConfig::trySave);

        ConfigEntryBuilder entryBuilder    = builder.entryBuilder();
        ConfigCategory     visualsCategory = builder.getOrCreateCategory(
            MutableText.of(ConfigScreen.visualCategoryName));

        visualsCategory.addEntry(
            entryBuilder.startIntSlider(MutableText.of(ConfigScreen.renderScaleOption), HIMConfig.INSTANCE.renderScale,
                    0, 100).setDefaultValue(HIMConfig.defaultRenderScale)
                .setTooltip(MutableText.of(ConfigScreen.renderScaleTooltip))
                .setSaveConsumer((value) -> HIMConfig.INSTANCE.renderScale = value).build());

        ConfigCategory keybindingsCategory = builder.getOrCreateCategory(
            MutableText.of(ConfigScreen.keybindingsCategoryName));

        ConfigScreen.addKeybindingEntryToCategory(keybindingsCategory, entryBuilder,
            Keybindings.openConfigScreenKeyBinding, ConfigScreen.hIMMenuBindingName);
        ConfigScreen.addKeybindingEntryToCategory(keybindingsCategory, entryBuilder,
            Keybindings.activateOverlayBinding, ConfigScreen.activateOverlayBindingName);

        return builder.build();
    }

    private static
    void addKeybindingEntryToCategory(ConfigCategory category, ConfigEntryBuilder entryBuilder, KeyBinding keyBinding,
                                      TranslatableTextContent translationKey)
    {
        category.addEntry(ConfigScreen.getKeybindingOption(entryBuilder, keyBinding, translationKey));
    }

    private static
    KeyCodeEntry getKeybindingOption(ConfigEntryBuilder entryBuilder, KeyBinding keyBinding,
                                     TranslatableTextContent translationKey)
    {
        return entryBuilder.startKeyCodeField(MutableText.of(translationKey),
                KeyBindingHelper.getBoundKeyOf(keyBinding)).setDefaultValue(keyBinding.getDefaultKey())
            .setKeySaveConsumer((code) ->
            {
                keyBinding.setBoundKey(code);
                KeyBinding.updateKeysByCode();
                MinecraftClient.getInstance().options.write();
            }).build();
    }

}
