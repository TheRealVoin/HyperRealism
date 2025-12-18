package net.therealvoin.hyperrealism.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.therealvoin.hyperrealism.init.ModBlocks;
import net.therealvoin.hyperrealism.init.ModItems;
import net.therealvoin.hyperrealism.creativemodetab.CreativeModeTabs;


@Mod(HyperRealism.MOD_ID)
public class HyperRealism {
    public static final String MOD_ID = "hyperrealism";

    public HyperRealism(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        CreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}