package com.dolthhaven.easeldoesit.other.util;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class PaintingUtil {
    public static Optional<PaintingVariant> readPresetVariant(ItemStack stack) {
        if (!stack.is(Items.PAINTING)) return Optional.empty();

        CompoundTag tag = stack.getTag();
        if (tag == null) return Optional.empty();

        Optional<Holder<PaintingVariant>> painting = Painting.loadVariant(tag.getCompound("EntityTag"));

        if (painting.isPresent()) {
            return Optional.of(painting.orElseThrow().get());
        }
        else {
            return Optional.empty();
        }
    }

    public static ItemStack makePresetVariantPaintingStack(Supplier<PaintingVariant> variant) {
        return makePresetVariantPaintingStack(variant.get());
    }

    public static ItemStack makePresetVariantPaintingStack(PaintingVariant variant) {
        ItemStack paintingStack = new ItemStack(Items.PAINTING, 1);

        CompoundTag tag = paintingStack.getOrCreateTagElement("EntityTag");
        Painting.storeVariant(tag, getHolder(variant));

        return paintingStack;
    }

    public static List<PaintingVariant> getAllPaintingsOfDimensions(int width, int height) {
        return getAllPaintingsOfDimensions(width, height, false);
    }

    public static List<PaintingVariant> getAllPaintingsOfDimensions(int width, int height, boolean includeUnplaceable) {
        return ForgeRegistries.PAINTING_VARIANTS.getValues().stream()
                .filter(painting -> painting.getHeight() == height && painting.getWidth() == width)
                .filter(painting -> includeUnplaceable || getHolder(painting).is(PaintingVariantTags.PLACEABLE))
                .toList();
    }

    public static Holder<PaintingVariant> getHolder(PaintingVariant painting) {
        return ForgeRegistries.PAINTING_VARIANTS.getHolder(painting).orElseThrow();
    }

    public static Optional<Holder<PaintingVariant>> fromLanguageKey(String key) {
        String[] keys = key.split("\\.");
        return ForgeRegistries.PAINTING_VARIANTS.getHolder(
                new ResourceLocation(keys[1], keys[2])
        );
    }
}