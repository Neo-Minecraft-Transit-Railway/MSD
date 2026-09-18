package top.mcmtr.mod;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.registry.ItemRegistryObject;
import top.mcmtr.core.data.CatenaryType;
import top.mcmtr.mod.items.ItemCatenaryConnector;
import top.mcmtr.mod.items.ItemHold;
import top.mcmtr.mod.items.ItemModelChangeStick;
import top.mcmtr.mod.items.ItemRigidCatenaryConnector;

public class Items {
    static {
        CATENARY_CONNECTOR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "catenary_connector"), itemSettings -> new Item(new ItemCatenaryConnector(itemSettings.maxCount(1), true, CatenaryType.CATENARY)), CreativeModeTabs.EXTERNAL);
        ELECTRIC_CONNECTOR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "electric_connector"), itemSettings -> new Item(new ItemCatenaryConnector(itemSettings.maxCount(1), true, CatenaryType.ELECTRIC)), CreativeModeTabs.EXTERNAL);
        RIGID_SOFT_CATENARY_CONNECTOR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "rigid_soft_catenary_connector"), itemSettings -> new Item(new ItemCatenaryConnector(itemSettings.maxCount(1), true, CatenaryType.RIGID_SOFT_CATENARY)), CreativeModeTabs.EXTERNAL);
        RIGID_CATENARY_CONNECTOR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "rigid_catenary_connector"), itemSettings -> new Item(new ItemRigidCatenaryConnector(itemSettings.maxCount(1), true)), CreativeModeTabs.EXTERNAL);
        CATENARY_REMOVER = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "catenary_remover"), itemSettings -> new Item(new ItemCatenaryConnector(itemSettings.maxCount(1), false, CatenaryType.NONE)), CreativeModeTabs.EXTERNAL);
        RIGID_CATENARY_REMOVER = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "rigid_catenary_remover"), itemSettings -> new Item(new ItemRigidCatenaryConnector(itemSettings.maxCount(1), false)), CreativeModeTabs.EXTERNAL);
        STANDING_SIGN = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "yuuni_standing_sign"), itemSettings -> new Item(new ItemHold(Blocks.STANDING_SIGN.get(), itemSettings.maxCount(1), 2)), CreativeModeTabs.EXTERNAL);
        STANDING_SIGN_1 = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "yuuni_standing_sign_1"), itemSettings -> new Item(new ItemHold(Blocks.STANDING_SIGN_1.get(), itemSettings.maxCount(1), 2)), CreativeModeTabs.EXTERNAL);
        STANDING_SIGN_POLE = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "yuuni_standing_sign_pole"), itemSettings -> new Item(new ItemHold(Blocks.STANDING_SIGN_POLE.get(), itemSettings.maxCount(1), 2)), CreativeModeTabs.EXTERNAL);

        MODEL_CHANGE_STICK = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "model_change_stick"), itemSettings -> new Item(new ItemModelChangeStick(itemSettings.maxCount(1))), CreativeModeTabs.STATION);

        SURVEILLANCE_CAMERAS = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "surveillance_cameras"), itemSettings -> new Item(new ItemHold(Blocks.SURVEILLANCE_CAMERAS.get(), itemSettings.maxCount(1), 2)), CreativeModeTabs.STATION);
        HALL_SEAT = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "hall_seat_middle"), itemSettings -> new Item(new ItemHold(Blocks.HALL_SEAT.get(), itemSettings.maxCount(1), 3)), CreativeModeTabs.STATION);
        DECORATION_BOOK = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "decoration_book"), itemSettings -> new Item(new ItemHold(Blocks.DECORATION_BOOK.get(), itemSettings.maxCount(1), 2)), CreativeModeTabs.STATION);
        DECORATION_CEILING = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "decoration_ceiling"), itemSettings -> new Item(new ItemHold(Blocks.DECORATION_CEILING.get(), itemSettings.maxCount(1), 1)), CreativeModeTabs.STATION);
        DECORATION_CEILING_LIGHT = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "decoration_ceiling_light"), itemSettings -> new Item(new ItemHold(Blocks.DECORATION_CEILING_LIGHT.get(), itemSettings.maxCount(1), 1)), CreativeModeTabs.STATION);
        DECORATION_FLOOR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "decoration_floor"), itemSettings -> new Item(new ItemHold(Blocks.DECORATION_FLOOR.get(), itemSettings.maxCount(1), 1)), CreativeModeTabs.STATION);
        DECORATION_PC = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "decoration_pc"), itemSettings -> new Item(new ItemHold(Blocks.DECORATION_PC.get(), itemSettings.maxCount(1), 1)), CreativeModeTabs.STATION);
        DECORATION_STAIR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "decoration_stair"), itemSettings -> new Item(new ItemHold(Blocks.DECORATION_STAIR.get(), itemSettings.maxCount(1), 2)), CreativeModeTabs.STATION);
        DISPLAY_BOARD_HORIZONTALLY = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "display_board_horizontal"), itemSettings -> new Item(new ItemHold(Blocks.DISPLAY_BOARD_HORIZONTALLY.get(), itemSettings.maxCount(1), 6)), CreativeModeTabs.STATION);
        DISPLAY_BOARD_VERTICALLY = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "display_board_vertically"), itemSettings -> new Item(new ItemHold(Blocks.DISPLAY_BOARD_VERTICALLY.get(), itemSettings.maxCount(1), 6)), CreativeModeTabs.STATION);
        RAILING_STAIR_FLAT = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "railing_stair_flat"), itemSettings -> new Item(new ItemHold(Blocks.RAILING_STAIR_FLAT.get(), itemSettings.maxCount(1), 6)), CreativeModeTabs.STATION);
        RAILING_STAIR_FLAT_MIRROR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "railing_stair_flat_mirror"), itemSettings -> new Item(new ItemHold(Blocks.RAILING_STAIR_FLAT_MIRROR.get(), itemSettings.maxCount(1), 6)), CreativeModeTabs.STATION);
        RAILING_STAIR_GLASS = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "railing_stair_glass"), itemSettings -> new Item(new ItemHold(Blocks.RAILING_STAIR_GLASS.get(), itemSettings.maxCount(1), 6)), CreativeModeTabs.STATION);
        RAILING_STAIR_GLASS_MIRROR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "railing_stair_glass_mirror"), itemSettings -> new Item(new ItemHold(Blocks.RAILING_STAIR_GLASS_MIRROR.get(), itemSettings.maxCount(1), 6)), CreativeModeTabs.STATION);
    }

    public static final ItemRegistryObject CATENARY_CONNECTOR;
    public static final ItemRegistryObject ELECTRIC_CONNECTOR;
    public static final ItemRegistryObject RIGID_SOFT_CATENARY_CONNECTOR;
    public static final ItemRegistryObject RIGID_CATENARY_CONNECTOR;
    public static final ItemRegistryObject CATENARY_REMOVER;
    public static final ItemRegistryObject RIGID_CATENARY_REMOVER;
    public static final ItemRegistryObject MODEL_CHANGE_STICK;

    public static final ItemRegistryObject SURVEILLANCE_CAMERAS;
    public static final ItemRegistryObject HALL_SEAT;
    public static final ItemRegistryObject DECORATION_BOOK;
    public static final ItemRegistryObject DECORATION_CEILING;
    public static final ItemRegistryObject DECORATION_CEILING_LIGHT;
    public static final ItemRegistryObject DECORATION_FLOOR;
    public static final ItemRegistryObject DECORATION_PC;
    public static final ItemRegistryObject DECORATION_STAIR;
    public static final ItemRegistryObject DISPLAY_BOARD_HORIZONTALLY;
    public static final ItemRegistryObject DISPLAY_BOARD_VERTICALLY;
    public static final ItemRegistryObject RAILING_STAIR_FLAT;
    public static final ItemRegistryObject RAILING_STAIR_FLAT_MIRROR;
    public static final ItemRegistryObject RAILING_STAIR_GLASS;
    public static final ItemRegistryObject RAILING_STAIR_GLASS_MIRROR;
    public static final ItemRegistryObject STANDING_SIGN;
    public static final ItemRegistryObject STANDING_SIGN_1;
    public static final ItemRegistryObject STANDING_SIGN_POLE;

    public static void init() {
        Init.MSD_LOGGER.info("Registering MTR Station Decoration items");
    }
}