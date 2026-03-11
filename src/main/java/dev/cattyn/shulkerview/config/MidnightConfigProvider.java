package dev.cattyn.shulkerview.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.util.math.ColorHelper;

public enum MidnightConfigProvider implements ConfigProvider {
    INSTANCE;

    @Override
    public void init() {
        MidnightConfig.init("shulker-view", ShulkerViewConfig.class);
    }

    @Override
    public boolean isCompact() {
        return ShulkerViewConfig.compact;
    }

    @Override
    public boolean isBothSides() {
        return ShulkerViewConfig.bothSides;
    }

    @Override
    public boolean isTooltips() {
        return ShulkerViewConfig.tooltips;
    }

    @Override
    public int getBackground() {
        return ColorHelper.withAlpha(ShulkerViewConfig.backgroundAlpha / 100f, 0);
    }

    @Override
    public float getScale() {
        return ShulkerViewConfig.scale / 10f;
    }
}
