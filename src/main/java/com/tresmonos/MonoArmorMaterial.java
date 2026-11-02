package com.tresmonos;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Material "de adorno": no da protección ni se rompe, solo sirve para que
 * los objetos se puedan poner en la cabeza y se vean en el modelo del jugador.
 *
 * El nombre NO lleva "modid:" a propósito: en 1.20.1 Fabric busca la textura en
 * assets/minecraft/textures/models/armor/<nombre>_layer_1.png
 */
public enum MonoArmorMaterial implements ArmorMaterial {
    CINTA("tresmonos_cinta"),
    AUDIFONOS("tresmonos_audifonos"),
    BUFANDA("tresmonos_bufanda");

    private final String name;

    MonoArmorMaterial(String name) {
        this.name = name;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return 0.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
