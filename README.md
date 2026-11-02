# Los Tres Monos Sabios (Fabric 1.20.1)

Tres objetos que se ponen en la **cabeza** (clic derecho con el objeto en la mano, o arrastrándolo al slot del casco):

| Objeto | ID | Efecto |
|---|---|---|
| Cinta del Mono que No Ve | `tresmonos:cinta` | Pantalla negra completa (la imagen de `textures/gui/cinta_overlay.png`). |
| Audífonos del Mono que No Oye | `tresmonos:audifonos` | No escucha a nadie por Simple Voice Chat (proximidad ni grupos). |
| Bufanda del Mono que No Habla | `tresmonos:bufanda` | Nadie lo escucha por Simple Voice Chat, pero él sí escucha. |

Para tenerlos: pestaña creativa **Los Tres Monos Sabios**, o `/give @s tresmonos:cinta` (igual con `audifonos` y `bufanda`).

## Requisitos
- Minecraft 1.20.1 con Fabric Loader y Fabric API.
- Simple Voice Chat (Fabric 1.20.1) **en el servidor y en los clientes**.
- El mod debe estar instalado en el **servidor y en todos los clientes** (la pantalla negra es del lado del cliente; el bloqueo de voz lo hace el servidor).

## Cómo compilarlo
1. Instala JDK 17 o superior.
2. En esta carpeta: `./gradlew build` (en Windows: `gradlew.bat build`).
3. El .jar queda en `build/libs/tres-monos-sabios-1.0.0.jar` (no uses el que termina en `-sources`).
4. Ponlo en la carpeta `mods` junto con Fabric API y Simple Voice Chat.

Si Gradle no encuentra `voicechat-api:2.5.0`, abre `gradle.properties` y cambia `voicechat_api_version` por la versión que aparezca en https://maven.maxhenkel.de/repository/public/de/maxhenkel/voicechat/voicechat-api/

## Cambiar la imagen de la cinta
Reemplaza `src/main/resources/assets/tresmonos/textures/gui/cinta_overlay.png`. Si cambias el tamaño de la imagen, actualiza `TEX_W` y `TEX_H` en `client/TresMonosClient.java`.
