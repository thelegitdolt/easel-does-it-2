package com.dolthhaven.easeldoesit.core.registry;

import com.dolthhaven.easeldoesit.common.inventory.EaselMenu;
import com.dolthhaven.easeldoesit.common.inventory.EaselScreen;
import com.dolthhaven.easeldoesit.core.EaselDoesIt;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EaselModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EaselDoesIt.MOD_ID);

    public static final RegistryObject<MenuType<EaselMenu>> EASEL_MENU = MENUS.register("easel_menu", () -> new MenuType<>(EaselMenu::new, FeatureFlags.VANILLA_SET));

    public static void registerScreens() {
        MenuScreens.register(EASEL_MENU.get(), EaselScreen::new);
    }
}
