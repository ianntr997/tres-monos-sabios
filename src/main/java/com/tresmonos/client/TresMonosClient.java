package com.tresmonos.client;

import com.tresmonos.ModItems;
import com.tresmonos.TresMonosSabios;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class TresMonosClient implements ClientModInitializer {
    private static final Identifier OVERLAY =
            new Identifier(TresMonosSabios.MOD_ID, "textures/gui/cinta_overlay.png");
    // Tamaño real de cinta_overlay.png
    private static final int TEX_W = 935;
    private static final int TEX_H = 527;

    @Override
    public void onInitializeClient() {
        // Pantalla negra mientras se lleva la cinta (tapa todo, incluido el HUD y el chat).
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || !ModItems.lleva(mc.player, ModItems.CINTA)) {
                return;
            }
            context.drawTexture(OVERLAY,
                    0, 0,
                    context.getScaledWindowWidth(), context.getScaledWindowHeight(),
                    0.0F, 0.0F, TEX_W, TEX_H, TEX_W, TEX_H);
        });

        // Evita el truco de apretar F1 para ocultar el HUD (y con él la pantalla negra).
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            if (mc.player != null && mc.options.hudHidden && ModItems.lleva(mc.player, ModItems.CINTA)) {
                mc.options.hudHidden = false;
            }
        });

        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            String clave = null;
            if (stack.isOf(ModItems.CINTA)) clave = "tooltip.tresmonos.cinta";
            else if (stack.isOf(ModItems.AUDIFONOS)) clave = "tooltip.tresmonos.audifonos";
            else if (stack.isOf(ModItems.BUFANDA)) clave = "tooltip.tresmonos.bufanda";
            if (clave != null) {
                lines.add(Text.translatable(clave).formatted(Formatting.GRAY));
            }
        });
    }
}
