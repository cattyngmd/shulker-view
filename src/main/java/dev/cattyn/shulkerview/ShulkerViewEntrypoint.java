package dev.cattyn.shulkerview;

import dev.cattyn.shulkerview.config.MidnightConfigProvider;
import dev.cattyn.shulkerview.handler.RenderHandler;
import dev.cattyn.shulkerview.handler.UpdateHandler;
import net.fabricmc.api.ClientModInitializer;

public class ShulkerViewEntrypoint implements ClientModInitializer {
    private static ShulkerViewEntrypoint INSTANCE;
    private RenderHandler renderHandler;
    private UpdateHandler updateHandler;

    @Override public void onInitializeClient() {
        MidnightConfigProvider.INSTANCE.init();
        this.updateHandler = new UpdateHandler(MidnightConfigProvider.INSTANCE);
        this.renderHandler = new RenderHandler(MidnightConfigProvider.INSTANCE, updateHandler);
        INSTANCE = this;
    }

    public RenderHandler getRenderHandler() {
        return renderHandler;
    }

    public UpdateHandler getUpdateHandler() {
        return updateHandler;
    }

    public static ShulkerViewEntrypoint getInstance() {
        return INSTANCE;
    }
}
