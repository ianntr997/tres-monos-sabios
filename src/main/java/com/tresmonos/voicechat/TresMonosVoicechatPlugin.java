package com.tresmonos.voicechat;

import com.tresmonos.ModItems;
import com.tresmonos.TresMonosSabios;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EntitySoundPacketEvent;
import de.maxhenkel.voicechat.api.events.Event;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.LocationalSoundPacketEvent;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import de.maxhenkel.voicechat.api.events.StaticSoundPacketEvent;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Plugin de Simple Voice Chat (se carga por el entrypoint "voicechat").
 *
 *  - Audífonos: se cancelan TODOS los paquetes de sonido que iban a llegar a quien los lleva
 *    (proximidad, direccional y grupos), así que no oye a nadie.
 *  - Bufanda: se cancela el paquete del micrófono de quien la lleva, así que nadie lo oye,
 *    pero él sigue escuchando normal.
 */
public class TresMonosVoicechatPlugin implements VoicechatPlugin {

    @Override
    public String getPluginId() {
        return TresMonosSabios.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        // Nada que preparar.
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(MicrophonePacketEvent.class, this::alHablar);
        registration.registerEvent(EntitySoundPacketEvent.class, this::alRecibirEntidad);
        registration.registerEvent(LocationalSoundPacketEvent.class, this::alRecibirLocalizado);
        registration.registerEvent(StaticSoundPacketEvent.class, this::alRecibirEstatico);
    }

    // ---- Bufanda: no puede hablar ----
    private void alHablar(MicrophonePacketEvent evento) {
        ServerPlayerEntity emisor = aJugador(evento.getSenderConnection());
        if (emisor != null && ModItems.lleva(emisor, ModItems.BUFANDA)) {
            evento.cancel();
        }
    }

    // ---- Audífonos: no puede escuchar ----
    private void alRecibirEntidad(EntitySoundPacketEvent evento) {
        bloquearSiTieneAudifonos(evento.getReceiverConnection(), evento);
    }

    private void alRecibirLocalizado(LocationalSoundPacketEvent evento) {
        bloquearSiTieneAudifonos(evento.getReceiverConnection(), evento);
    }

    private void alRecibirEstatico(StaticSoundPacketEvent evento) {
        bloquearSiTieneAudifonos(evento.getReceiverConnection(), evento);
    }

    private void bloquearSiTieneAudifonos(VoicechatConnection receptor, Event evento) {
        ServerPlayerEntity jugador = aJugador(receptor);
        if (jugador != null && ModItems.lleva(jugador, ModItems.AUDIFONOS)) {
            evento.cancel();
        }
    }

    private static ServerPlayerEntity aJugador(VoicechatConnection conexion) {
        if (conexion == null || conexion.getPlayer() == null) {
            return null;
        }
        Object objeto = conexion.getPlayer().getPlayer();
        return objeto instanceof ServerPlayerEntity jugador ? jugador : null;
    }
}
