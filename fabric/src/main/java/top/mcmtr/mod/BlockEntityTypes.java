package top.mcmtr.mod;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;
import top.mcmtr.mod.blocks.*;
import top.mcmtr.mod.blocks.old.*;

public class BlockEntityTypes {
    static {
        NEW_CATENARY_NODE = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "new_catenary_node"), BlockCatenaryNode.BlockCatenaryNodeEntity::new, Blocks.NEW_CATENARY_NODE::get);
        RIGID_CATENARY_NODE = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "rigid_catenary_node"), BlockRigidCatenaryNode.BlockRigidCatenaryNodeEntity::new, Blocks.RIGID_CATENARY_NODE::get);

        CATENARY_WITH_LONG = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_long"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_LONG, pos, state), Blocks.CATENARY_WITH_LONG::get);
        CATENARY_WITH_LONG_TOP = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_long_top"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_LONG_TOP, pos, state), Blocks.CATENARY_WITH_LONG_TOP::get);
        CATENARY_WITH_SHORT = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_short"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_SHORT, pos, state), Blocks.CATENARY_WITH_SHORT::get);
        CATENARY_WITH_SHORT_TOP = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_short_top"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_SHORT_TOP, pos, state), Blocks.CATENARY_WITH_SHORT_TOP::get);
        CATENARY_WITH_LONG_COUNTERWEIGHT = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_long_counterweight"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_LONG_COUNTERWEIGHT, pos, state), Blocks.CATENARY_WITH_LONG_COUNTERWEIGHT::get);
        CATENARY_WITH_LONG_COUNTERWEIGHT_MIRROR = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_long_counterweight_mirror"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_LONG_COUNTERWEIGHT_MIRROR, pos, state), Blocks.CATENARY_WITH_LONG_COUNTERWEIGHT_MIRROR::get);
        CATENARY_WITH_SHORT_COUNTERWEIGHT = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_short_counterweight"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_SHORT_COUNTERWEIGHT, pos, state), Blocks.CATENARY_WITH_SHORT_COUNTERWEIGHT::get);
        CATENARY_WITH_SHORT_COUNTERWEIGHT_MIRROR = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_with_short_counterweight_mirror"), (pos, state) -> new BlockCatenaryWithModel.BlockCatenaryWithModelEntity(BlockCatenaryWithModel.CatenaryModel.CATENARY_SHORT_COUNTERWEIGHT_MIRROR, pos, state), Blocks.CATENARY_WITH_SHORT_COUNTERWEIGHT_MIRROR::get);

        YAMANOTE_RAILWAY_SIGN_ENTITY_2_EVEN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_2_even"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(2, false, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_2_EVEN::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_2_ODD = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_2_odd"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(2, true, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_2_ODD::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_3_EVEN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_3_even"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(3, false, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_3_EVEN::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_3_ODD = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_3_odd"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(3, true, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_3_ODD::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_4_EVEN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_4_even"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(4, false, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_4_EVEN::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_4_ODD = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_4_odd"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(4, true, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_4_ODD::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_5_EVEN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_5_even"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(5, false, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_5_EVEN::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_5_ODD = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_5_odd"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(5, true, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_5_ODD::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_6_EVEN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_6_even"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(6, false, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_6_EVEN::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_6_ODD = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_6_odd"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(6, true, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_6_ODD::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_7_EVEN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_7_even"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(7, false, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_7_EVEN::get);
        YAMANOTE_RAILWAY_SIGN_ENTITY_7_ODD = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_railway_sign_7_odd"), (pos, state) -> new BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity(7, true, pos, state), Blocks.YAMANOTE_RAILWAY_SIGN_7_ODD::get);

        YUUNI_PIDS = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yuuni_pids"), (pos, state) -> new BlockYuuniPIDS.BlockYuuniPIDSEntity(2, pos, state), Blocks.YUUNI_PIDS::get);
        YUUNI_2_PIDS = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yuuni_2_pids"), (pos, state) -> new BlockYuuniPIDS.BlockYuuniPIDSEntity(1, pos, state), Blocks.YUUNI_2_PIDS::get);
        YAMANOTE_4_PIDS = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_4_pids"), (pos, state) -> new BlockYamanotePIDS.BlockYamanotePIDSEntity(4, pos, state), Blocks.YAMANOTE_4_PIDS::get);
        YAMANOTE_5_PIDS = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_5_pids"), (pos, state) -> new BlockYamanotePIDS.BlockYamanotePIDSEntity(5, pos, state), Blocks.YAMANOTE_5_PIDS::get);
        YAMANOTE_6_PIDS = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_6_pids"), (pos, state) -> new BlockYamanotePIDS.BlockYamanotePIDSEntity(6, pos, state), Blocks.YAMANOTE_6_PIDS::get);
        YAMANOTE_7_PIDS = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yamanote_7_pids"), (pos, state) -> new BlockYamanotePIDS.BlockYamanotePIDSEntity(7, pos, state), Blocks.YAMANOTE_7_PIDS::get);
        STANDING_SIGN = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yuuni_standing_sign"), (pos, state) -> new BlockStandingSign.BlockStandingSignEntity(pos, state, 3), Blocks.STANDING_SIGN::get);
        STANDING_SIGN_1 = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "yuuni_standing_sign_1"), (pos, state) -> new BlockStandingSign1.BlockStandingSign1Entity(pos, state, 1), Blocks.STANDING_SIGN_1::get);

        CATENARY_NODE = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_node"), BlockOldCatenaryNode.BlockOldCatenaryNodeEntity::new, Blocks.CATENARY_NODE::get);
        CATENARY_NODE_STYLE_2 = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "catenary_node_style_2"), BlockOldCatenaryNodeStyle2.BlockOldCatenaryNodeStyle2Entity::new, Blocks.CATENARY_NODE_STYLE_2::get);
        SHORT_CATENARY_NODE = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "short_catenary_node"), BlockOldShortCatenaryNode.BlockOldShortCatenaryNodeEntity::new, Blocks.SHORT_CATENARY_NODE::get);
        SHORT_CATENARY_NODE_STYLE_2 = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "short_catenary_node_style_2"), BlockOldShortCatenaryNodeStyle2.BlockOldShortCatenaryNodeStyle2Entity::new, Blocks.SHORT_CATENARY_NODE_STYLE_2::get);
        ELECTRIC_NODE = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "electric_node"), BlockOldElectricNode.BlockOldElectricNodeEntity::new, Blocks.ELECTRIC_NODE::get);
        TRANS_CATENARY_NODE = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "trans_catenary_node"), BlockOldTransCatenaryNode.BlockOldTransCatenaryNodeEntity::new, Blocks.TRANS_CATENARY_NODE::get);
    }

    public static final BlockEntityTypeRegistryObject<BlockCatenaryNode.BlockCatenaryNodeEntity> NEW_CATENARY_NODE;
    public static final BlockEntityTypeRegistryObject<BlockRigidCatenaryNode.BlockRigidCatenaryNodeEntity> RIGID_CATENARY_NODE;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_LONG;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_LONG_TOP;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_SHORT;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_SHORT_TOP;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_LONG_COUNTERWEIGHT;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_LONG_COUNTERWEIGHT_MIRROR;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_SHORT_COUNTERWEIGHT;
    public static final BlockEntityTypeRegistryObject<BlockCatenaryWithModel.BlockCatenaryWithModelEntity> CATENARY_WITH_SHORT_COUNTERWEIGHT_MIRROR;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_2_EVEN;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_2_ODD;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_3_EVEN;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_3_ODD;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_4_EVEN;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_4_ODD;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_5_EVEN;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_5_ODD;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_6_EVEN;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_6_ODD;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_7_EVEN;
    public static final BlockEntityTypeRegistryObject<BlockYamanoteRailwaySign.BlockYamanoteRailwaySignEntity> YAMANOTE_RAILWAY_SIGN_ENTITY_7_ODD;
    public static final BlockEntityTypeRegistryObject<BlockYuuniPIDS.BlockYuuniPIDSEntity> YUUNI_PIDS;
    public static final BlockEntityTypeRegistryObject<BlockYuuniPIDS.BlockYuuniPIDSEntity> YUUNI_2_PIDS;
    public static final BlockEntityTypeRegistryObject<BlockYamanotePIDS.BlockYamanotePIDSEntity> YAMANOTE_4_PIDS;
    public static final BlockEntityTypeRegistryObject<BlockYamanotePIDS.BlockYamanotePIDSEntity> YAMANOTE_5_PIDS;
    public static final BlockEntityTypeRegistryObject<BlockYamanotePIDS.BlockYamanotePIDSEntity> YAMANOTE_6_PIDS;
    public static final BlockEntityTypeRegistryObject<BlockYamanotePIDS.BlockYamanotePIDSEntity> YAMANOTE_7_PIDS;
    public static final BlockEntityTypeRegistryObject<BlockStandingSign.BlockStandingSignEntity> STANDING_SIGN;
    public static final BlockEntityTypeRegistryObject<BlockStandingSign1.BlockStandingSign1Entity> STANDING_SIGN_1;
    public static final BlockEntityTypeRegistryObject<BlockOldCatenaryNode.BlockOldCatenaryNodeEntity> CATENARY_NODE;
    public static final BlockEntityTypeRegistryObject<BlockOldCatenaryNodeStyle2.BlockOldCatenaryNodeStyle2Entity> CATENARY_NODE_STYLE_2;
    public static final BlockEntityTypeRegistryObject<BlockOldShortCatenaryNode.BlockOldShortCatenaryNodeEntity> SHORT_CATENARY_NODE;
    public static final BlockEntityTypeRegistryObject<BlockOldShortCatenaryNodeStyle2.BlockOldShortCatenaryNodeStyle2Entity> SHORT_CATENARY_NODE_STYLE_2;
    public static final BlockEntityTypeRegistryObject<BlockOldElectricNode.BlockOldElectricNodeEntity> ELECTRIC_NODE;
    public static final BlockEntityTypeRegistryObject<BlockOldTransCatenaryNode.BlockOldTransCatenaryNodeEntity> TRANS_CATENARY_NODE;

    public static void init() {
        Init.MSD_LOGGER.info("Registering MTR Station Decoration block entity types");
    }
}