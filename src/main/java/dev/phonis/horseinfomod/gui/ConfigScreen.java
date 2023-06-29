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
    private static final TranslatableTextContent configTitle                          = new TranslatableTextContent(
        "title.horseinfomod.config", null, null);
    private static final TranslatableTextContent visualCategoryName                   = new TranslatableTextContent(
        "category.horseinfomod.visual", null, null);
    private static final TranslatableTextContent keybindingsCategoryName              = new TranslatableTextContent(
        "category.horseinfomod.keybindings", null, null);
    private static final TranslatableTextContent renderScaleOption                    = new TranslatableTextContent(
        "option.horseinfomod.renderScale", null, null);
    private static final TranslatableTextContent marginOption                         = new TranslatableTextContent(
        "option.horseinfomod.margin", null, null);
    private static final TranslatableTextContent roundNumbersOption                   = new TranslatableTextContent(
        "option.horseinfomod.roundNumbers", null, null);
    private static final TranslatableTextContent roundingPrecisionOption              = new TranslatableTextContent(
        "option.horseinfomod.roundingPrecision", null, null);
    private static final TranslatableTextContent percentColor1Option                  = new TranslatableTextContent(
        "option.horseinfomod.percentColor1", null, null);
    private static final TranslatableTextContent percentColor2Option                  = new TranslatableTextContent(
        "option.horseinfomod.percentColor2", null, null);
    private static final TranslatableTextContent overlayBackgroundColorOption         = new TranslatableTextContent(
        "option.horseinfomod.overlayBackgroundColor", null, null);
    private static final TranslatableTextContent textColorOption                      = new TranslatableTextContent(
        "option.horseinfomod.textColor", null, null);
    private static final TranslatableTextContent overlayBackgroundTransparencyOption  = new TranslatableTextContent(
        "option.horseinfomod.overlayBackgroundTransparency", null, null);
    private static final TranslatableTextContent overlayForegroundTransparencyOption  = new TranslatableTextContent(
        "option.horseinfomod.overlayForegroundTransparency", null, null);
    private static final TranslatableTextContent renderScaleTooltip                   = new TranslatableTextContent(
        "tooltip.horseinfomod.renderScale", null, null);
    private static final TranslatableTextContent marginTooltip                        = new TranslatableTextContent(
        "tooltip.horseinfomod.margin", null, null);
    private static final TranslatableTextContent roundNumbersTooltip                  = new TranslatableTextContent(
        "tooltip.horseinfomod.roundNumbers", null, null);
    private static final TranslatableTextContent roundingPrecisionTooltip             = new TranslatableTextContent(
        "tooltip.horseinfomod.roundingPrecision", null, null);
    private static final TranslatableTextContent percentColor1Tooltip                 = new TranslatableTextContent(
        "tooltip.horseinfomod.percentColor1", null, null);
    private static final TranslatableTextContent percentColor2Tooltip                 = new TranslatableTextContent(
        "tooltip.horseinfomod.percentColor2", null, null);
    private static final TranslatableTextContent overlayBackgroundColorTooltip        = new TranslatableTextContent(
        "tooltip.horseinfomod.overlayBackgroundColor", null, null);
    private static final TranslatableTextContent textColorTooltip                     = new TranslatableTextContent(
        "tooltip.horseinfomod.textColor", null, null);
    private static final TranslatableTextContent overlayBackgroundTransparencyTooltip = new TranslatableTextContent(
        "tooltip.horseinfomod.overlayBackgroundTransparency", null, null);
    private static final TranslatableTextContent overlayForegroundTransparencyTooltip = new TranslatableTextContent(
        "tooltip.horseinfomod.overlayForegroundTransparency", null, null);
    private static final TranslatableTextContent hIMMenuBindingName                   = new TranslatableTextContent(
        "binding.horseinfomod.hIMMenu", null, null);
    private static final TranslatableTextContent activateOverlayBindingName           = new TranslatableTextContent(
        "binding.horseinfomod.hIMOverlay", null, null);
    private static final TranslatableTextContent printDetailsInChatName               = new TranslatableTextContent(
        "binding.horseinfomod.printDetailsInChat", null, null);

    public static
    Screen getConfigScreen(Screen parent)
    {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTransparentBackground(true)
            .setTitle(MutableText.of(ConfigScreen.configTitle));
        builder.setSavingRunnable(HIMConfig::trySave);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory visualsCategory = builder.getOrCreateCategory(MutableText.of(ConfigScreen.visualCategoryName));

        visualsCategory.addEntry(
            entryBuilder.startIntSlider(MutableText.of(ConfigScreen.renderScaleOption), HIMConfig.INSTANCE.renderScale,
                    0, 100).setDefaultValue(HIMConfig.defaultRenderScale)
                .setTooltip(MutableText.of(ConfigScreen.renderScaleTooltip))
                .setSaveConsumer((value) -> HIMConfig.INSTANCE.renderScale = value).build());

        visualsCategory.addEntry(
            entryBuilder.startIntSlider(MutableText.of(ConfigScreen.marginOption), HIMConfig.INSTANCE.margin, 0, 30)
                .setDefaultValue(HIMConfig.defaultMargin).setTooltip(MutableText.of(ConfigScreen.marginTooltip))
                .setSaveConsumer((value) -> HIMConfig.INSTANCE.margin = value).build());

        visualsCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(ConfigScreen.roundNumbersOption),
                HIMConfig.INSTANCE.roundNumbers).setTooltip(MutableText.of(ConfigScreen.roundNumbersTooltip))
            .setSaveConsumer((value) -> HIMConfig.INSTANCE.roundNumbers = value).build());

        visualsCategory.addEntry(entryBuilder.startIntSlider(MutableText.of(ConfigScreen.roundingPrecisionOption),
                HIMConfig.INSTANCE.roundingPrecision, 1, 6).setDefaultValue(HIMConfig.defaultRoundingPrecision)
            .setTooltip(MutableText.of(ConfigScreen.roundingPrecisionTooltip))
            .setSaveConsumer((value) -> HIMConfig.INSTANCE.roundingPrecision = value).build());

        visualsCategory.addEntry(entryBuilder.startColorField(MutableText.of(ConfigScreen.percentColor1Option),
                HIMConfig.INSTANCE.percentColor1.toRGBInt()).setDefaultValue(HIMConfig.defaultPercentColor1.toRGBInt())
            .setTooltip(MutableText.of(ConfigScreen.percentColor1Tooltip))
            .setSaveConsumer2((value) -> HIMConfig.INSTANCE.percentColor1.updateRGB(value)).build());

        visualsCategory.addEntry(entryBuilder.startColorField(MutableText.of(ConfigScreen.percentColor2Option),
                HIMConfig.INSTANCE.percentColor2.toRGBInt()).setDefaultValue(HIMConfig.defaultPercentColor2.toRGBInt())
            .setTooltip(MutableText.of(ConfigScreen.percentColor2Tooltip))
            .setSaveConsumer2((value) -> HIMConfig.INSTANCE.percentColor2.updateRGB(value)).build());

        visualsCategory.addEntry(entryBuilder.startColorField(MutableText.of(ConfigScreen.overlayBackgroundColorOption),
                HIMConfig.INSTANCE.overlayBackgroundColor.toRGBInt())
            .setDefaultValue(HIMConfig.defaultOverlayBackgroundColor.toRGBInt())
            .setTooltip(MutableText.of(ConfigScreen.overlayBackgroundColorTooltip))
            .setSaveConsumer2((value) -> HIMConfig.INSTANCE.overlayBackgroundColor.updateRGB(value)).build());

        visualsCategory.addEntry(entryBuilder.startColorField(MutableText.of(ConfigScreen.textColorOption),
                HIMConfig.INSTANCE.textColor.toRGBInt()).setDefaultValue(HIMConfig.defaultTextColor.toRGBInt())
            .setTooltip(MutableText.of(ConfigScreen.textColorTooltip))
            .setSaveConsumer2((value) -> HIMConfig.INSTANCE.textColor.updateRGB(value)).build());

        visualsCategory.addEntry(
            entryBuilder.startIntSlider(MutableText.of(ConfigScreen.overlayBackgroundTransparencyOption),
                    HIMConfig.INSTANCE.overlayBackgroundColor.a, 0, 255)
                .setDefaultValue(HIMConfig.defaultOverlayBackgroundColor.a)
                .setTooltip(MutableText.of(ConfigScreen.overlayBackgroundTransparencyTooltip))
                .setSaveConsumer((value) -> HIMConfig.INSTANCE.overlayBackgroundColor.updateA(value)).build());

        visualsCategory.addEntry(
            entryBuilder.startIntSlider(MutableText.of(ConfigScreen.overlayForegroundTransparencyOption),
                    HIMConfig.INSTANCE.getOverlayForegroundTransparency(), 0, 255)
                .setDefaultValue(HIMConfig.defaultOverlayForegroundTransparency)
                .setTooltip(MutableText.of(ConfigScreen.overlayForegroundTransparencyTooltip))
                .setSaveConsumer(HIMConfig.INSTANCE::setOverlayForegroundTransparency).build());

        ConfigCategory keybindingsCategory = builder.getOrCreateCategory(
            MutableText.of(ConfigScreen.keybindingsCategoryName));

        ConfigScreen.addKeybindingEntryToCategory(keybindingsCategory, entryBuilder,
            Keybindings.openConfigScreenKeyBinding, ConfigScreen.hIMMenuBindingName);
        ConfigScreen.addKeybindingEntryToCategory(keybindingsCategory, entryBuilder, Keybindings.activateOverlayBinding,
            ConfigScreen.activateOverlayBindingName);
        ConfigScreen.addKeybindingEntryToCategory(keybindingsCategory, entryBuilder, Keybindings.printDetailsInChat,
            ConfigScreen.printDetailsInChatName);

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
