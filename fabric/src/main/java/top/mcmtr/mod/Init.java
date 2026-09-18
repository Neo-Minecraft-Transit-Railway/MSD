package top.mcmtr.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.servlet.QueueObject;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.holder.WorldSavePath;
import org.mtr.mapping.mapper.MinecraftServerHelper;
import org.mtr.mapping.registry.Registry;
import org.mtr.mod.config.Config;
import top.mcmtr.core.MSDMain;
import top.mcmtr.mod.packet.*;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class Init implements Utilities {
    private static MSDMain main;
    private static long lastSavedMillis;
    public static final String MOD_ID = "msd";
    public static final Logger MSD_LOGGER = LogManager.getLogger("MTR-Station-Decoration");
    public static final Registry REGISTRY = new Registry();
    private static final ObjectArrayList<String> WORLD_ID_LIST = new ObjectArrayList<>();
    public static final int AUTOSAVE_INTERVAL = 30000;

    public static void init() {
        Items.init();
        Blocks.init();
        BlockEntityTypes.init();
        CreativeModeTabs.init();

        REGISTRY.setupPackets(new Identifier(MOD_ID, "packet"));
        REGISTRY.registerPacket(MSDPacketDeleteData.class, MSDPacketDeleteData::new);
        REGISTRY.registerPacket(MSDPacketRequestData.class, MSDPacketRequestData::new);
        REGISTRY.registerPacket(MSDPacketUpdateData.class, MSDPacketUpdateData::new);
        REGISTRY.registerPacket(MSDPacketResetData.class, MSDPacketResetData::new);
        REGISTRY.registerPacket(MSDPacketOpenCatenaryScreen.class, MSDPacketOpenCatenaryScreen::new);
        REGISTRY.registerPacket(MSDPacketUpdateCatenaryNode.class, MSDPacketUpdateCatenaryNode::new);
        REGISTRY.registerPacket(MSDPacketUpdateYamanoteRailwaySignConfig.class, MSDPacketUpdateYamanoteRailwaySignConfig::new);
        REGISTRY.registerPacket(MSDPacketOpenBlockEntityScreen.class, MSDPacketOpenBlockEntityScreen::new);
        REGISTRY.registerPacket(MSDPacketOpenCustomScreen.class, MSDPacketOpenCustomScreen::new);
        REGISTRY.registerPacket(MSDPacketUpdateCustomText.class, MSDPacketUpdateCustomText::new);
        REGISTRY.registerPacket(MSDPacketUpdateModel.class, MSDPacketUpdateModel::new);
        REGISTRY.registerPacket(MSDPacketOpenCatenaryWithModelScreen.class, MSDPacketOpenCatenaryWithModelScreen::new);

        REGISTRY.eventRegistry.registerServerStarted(minecraftServer -> {
            WORLD_ID_LIST.clear();
            MinecraftServerHelper.iterateWorlds(minecraftServer, serverWorld -> WORLD_ID_LIST.add(getWorldId(new World(serverWorld.data))));
            lastSavedMillis = System.currentTimeMillis();
            Config.init(minecraftServer.getRunDirectory());
            main = new MSDMain(minecraftServer.getSavePath(WorldSavePath.getRootMapped()).resolve("msd"), Config.getServer().getUseThreadedSimulation(), WORLD_ID_LIST.toArray(new String[0]));
        });

        REGISTRY.eventRegistry.registerStartServerTick(() -> {
            if (main != null) {
                if (!Config.getServer().getUseThreadedSimulation()) {
                    main.manualTick();
                }

                final long currentMillis = System.currentTimeMillis();
                if (currentMillis - lastSavedMillis > AUTOSAVE_INTERVAL) {
                    main.save();
                    lastSavedMillis = currentMillis;
                }
            }
        });

        REGISTRY.eventRegistry.registerPlayerDisconnect((minecraftServer, serverPlayerEntity) -> {
            if (main != null) {
                main.save();
            }
        });

        REGISTRY.eventRegistry.registerServerStopping(minecraftServer -> {
            if (main != null) {
                main.stop();
            }
        });

        REGISTRY.init();
    }

    public static <T extends SerializedDataBase> void sendMessageC2S(String key, @Nullable MinecraftServer minecraftServer, @Nullable World world, SerializedDataBase data, @Nullable Consumer<T> consumer, @Nullable Class<T> responseDataClass) {
        if (main != null) {
            main.sendMessageC2S(world == null ? null : WORLD_ID_LIST.indexOf(getWorldId(world)), new QueueObject(key, data, consumer == null || minecraftServer == null ? null : responseData -> minecraftServer.execute(() -> consumer.accept(responseData)), responseDataClass));
        }
    }

    private static String getWorldId(World world) {
        final Identifier identifier = MinecraftServerHelper.getWorldId(world);
        return String.format("%s/%s", identifier.getNamespace(), identifier.getPath());
    }

    public static void logException(Exception e, String message) {
        MSD_LOGGER.error(message, e);
    }
}