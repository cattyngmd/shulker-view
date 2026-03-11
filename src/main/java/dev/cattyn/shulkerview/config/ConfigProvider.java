package dev.cattyn.shulkerview.config;

public interface ConfigProvider {
    void init();

    boolean isCompact();

    boolean isBothSides();

    boolean isTooltips();

    int getBackground();

    float getScale();
}
