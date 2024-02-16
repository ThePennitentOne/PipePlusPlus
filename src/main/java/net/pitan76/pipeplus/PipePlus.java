package net.pitan76.pipeplus;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.registry.CompatRegistry;
import net.pitan76.pipeplus.blockentities.BlockEntities;
import net.pitan76.pipeplus.blocks.Blocks;
import net.pitan76.pipeplus.config.PipePlusConfig;
import net.pitan76.pipeplus.guis.PipePlusContainers;
import net.pitan76.pipeplus.items.Items;
import net.pitan76.pipeplus.parts.PipePlusParts;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PipePlus implements ModInitializer {

    public static final String MOD_ID = "pipeplus";
    public static final String MOD_NAME = "PipePlus";
    public static PipePlus instance;
    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroup PIPEPLUS_GROUP = CreativeTabBuilder.create(
            id("all")).
            setIcon(() -> new ItemStack(Items.COPPER_PIPE)).
            build();

    public static CompatRegistry registry = CompatRegistry.createRegistry(MOD_ID);

    public void onInitialize() {
        log(Level.INFO, "Initializing");
        instance = this;

        AutoConfig.register(PipePlusConfig.class, GsonConfigSerializer::new);
        PipePlusParts.init();
        BlockEntities.registerInit();
        Blocks.registerInit();
        Items.registerInit();
        PipePlusContainers.load();
        ServerNetwork.init();
        TeleportManager.register();
        ServerLifecycleEvents.SERVER_STOPPED.register((server -> {
            TeleportManager.instance.reset();
        }));
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static Identifier id(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static String pos2str(BlockPos pos) {
        return pos.getX() + "l" + pos.getY() + "l" + pos.getZ();

    }

}