package com.tresmonos;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ModItems {
    /** Mono que no ve: la pantalla se pone negra. */
    public static final Item CINTA = new ArmorItem(
            MonoArmorMaterial.CINTA, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1));

    /** Mono que no oye: no escucha a nadie en Simple Voice Chat. */
    public static final Item AUDIFONOS = new ArmorItem(
            MonoArmorMaterial.AUDIFONOS, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1));

    /** Mono que no habla: nadie lo escucha en Simple Voice Chat. */
    public static final Item BUFANDA = new ArmorItem(
            MonoArmorMaterial.BUFANDA, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1));

    public static final ItemGroup GRUPO = FabricItemGroup.builder()
            .icon(() -> new ItemStack(CINTA))
            .displayName(Text.translatable("itemGroup.tresmonos.principal"))
            .entries((context, entries) -> {
                entries.add(CINTA);
                entries.add(AUDIFONOS);
                entries.add(BUFANDA);
            })
            .build();

    private ModItems() {
    }

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(TresMonosSabios.MOD_ID, "cinta"), CINTA);
        Registry.register(Registries.ITEM, new Identifier(TresMonosSabios.MOD_ID, "audifonos"), AUDIFONOS);
        Registry.register(Registries.ITEM, new Identifier(TresMonosSabios.MOD_ID, "bufanda"), BUFANDA);
        Registry.register(Registries.ITEM_GROUP, new Identifier(TresMonosSabios.MOD_ID, "principal"), GRUPO);
    }

    /** true si la entidad lleva puesto ese objeto en la cabeza. */
    public static boolean lleva(LivingEntity entidad, Item item) {
        return entidad.getEquippedStack(EquipmentSlot.HEAD).isOf(item);
    }
}
