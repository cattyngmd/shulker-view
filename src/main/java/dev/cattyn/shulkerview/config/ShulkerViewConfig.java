package dev.cattyn.shulkerview.config;

import eu.midnightdust.lib.config.MidnightConfig;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public final class ShulkerViewConfig extends MidnightConfig {
    @Entry(isSlider = true, min = 0, max = 100) public static int backgroundAlpha = 30;

    @Entry public static boolean compact = true;
    @Entry public static boolean bothSides = true;
    @Entry public static boolean tooltips = true;

    @Entry(isSlider = true, min = 1, max = 20) public static int scale = 10;
}
