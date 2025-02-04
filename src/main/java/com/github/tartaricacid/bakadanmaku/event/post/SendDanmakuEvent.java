package com.github.tartaricacid.bakadanmaku.event.post;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;

public interface SendDanmakuEvent {
    Event<SendDanmakuEvent> EVENT = EventFactory.createArrayBacked(SendDanmakuEvent.class,
            listeners -> msg -> {
                for (SendDanmakuEvent listener : listeners) {
                    ActionResult result = listener.register(msg);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    static void register() {
        SendDanmakuEvent.EVENT.register(str -> {
            if (MinecraftClient.getInstance().player != null) {
                MinecraftClient.getInstance().player.sendMessage(new LiteralText(str), false);
            }
            return ActionResult.SUCCESS;
        });
    }

    ActionResult register(String msg);
}
