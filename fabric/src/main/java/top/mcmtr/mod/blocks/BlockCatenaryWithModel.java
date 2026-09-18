package top.mcmtr.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.IBlock;
import top.mcmtr.core.data.OffsetPosition;
import top.mcmtr.mod.BlockEntityTypes;
import top.mcmtr.mod.Init;
import top.mcmtr.mod.packet.MSDPacketDeleteData;
import top.mcmtr.mod.packet.MSDPacketOpenCatenaryWithModelScreen;

import javax.annotation.Nullable;
import java.util.List;

public final class BlockCatenaryWithModel extends BlockNodeBase implements DirectionHelper {
    private final CatenaryModel catenaryModel;

    public BlockCatenaryWithModel(CatenaryModel catenaryModel) {
        this.catenaryModel = catenaryModel;
    }

    @Override
    public void onBreak2(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            MSDPacketDeleteData.sendDirectlyToServerCatenaryNodePosition(ServerWorld.cast(world), org.mtr.mod.Init.blockPosToPosition(pos));
        }
    }

    @Override
    public BlockRenderType getRenderType2(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(FACING);
        properties.add(IS_CONNECTED);
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        return getDefaultState2().with(new Property<>(IS_CONNECTED.data), false).with(new Property<>(FACING.data), ctx.getPlayerFacing().data);
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
            final BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity != null && blockEntity.data instanceof BlockCatenaryWithModel.BlockCatenaryWithModelEntity) {
                Init.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new MSDPacketOpenCatenaryWithModelScreen(pos, state.get(new Property<>(IS_CONNECTED.data))));
            }
        });
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockCatenaryWithModelEntity(this.catenaryModel, blockPos, blockState);
    }

    @Override
    public String getTranslationKey2() {
        return "block.msd.blockCatenaryWithModel";
    }

    @Override
    public void addTooltips(ItemStack stack, @Nullable BlockView world, List<MutableText> tooltip, TooltipContext options) {
        if (this.catenaryModel.isLong()) {
            tooltip.add(TextHelper.translatable("tooltip.msd.catenary_reverse").formatted(TextFormatting.GOLD));
        } else {
            tooltip.add(TextHelper.translatable("tooltip.msd.catenary_positive").formatted(TextFormatting.GOLD));
        }
        if (this.catenaryModel.isTop()) {
            tooltip.add(TextHelper.translatable("tooltip.msd.catenary_top").formatted(TextFormatting.GRAY));
        }
        if (this.catenaryModel.isHasCounterweight()) {
            tooltip.add(TextHelper.translatable("tooltip.msd.catenary_counterweight").formatted(TextFormatting.GRAY));
        }
        if (this.catenaryModel.isMirror()) {
            tooltip.add(TextHelper.translatable("tooltip.msd.mirror").formatted(TextFormatting.GRAY));
        }
    }

    public static final class BlockCatenaryWithModelEntity extends BlockNodeBaseEntity {
        private static final String KEY_OFFSET_POSITION = "msd_offset_position_";
        private static final String KEY_ROTATE_POSITION = "msd_rotate_position_";
        private final OffsetPosition offsetPosition;
        private final OffsetPosition rotation;

        public BlockCatenaryWithModelEntity(CatenaryModel catenaryModel, BlockPos blockPos, BlockState blockState) {
            super(getType(catenaryModel), blockPos, blockState);
            this.offsetPosition = new OffsetPosition(0, 0, 0);
            this.rotation = new OffsetPosition(0, 0, 0);
        }

        public void setOffsetPosition(OffsetPosition offsetPosition, OffsetPosition rotation) {
            this.offsetPosition.setX(offsetPosition.getX());
            this.offsetPosition.setY(offsetPosition.getY());
            this.offsetPosition.setZ(offsetPosition.getZ());
            this.rotation.setX(rotation.getX());
            this.rotation.setY(rotation.getY());
            this.rotation.setZ(rotation.getZ());
            markDirty2();
        }

        public OffsetPosition getOffsetPosition() {
            return this.offsetPosition;
        }

        public OffsetPosition getRotation() {
            return this.rotation;
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            this.offsetPosition.setX(compoundTag.getDouble(KEY_OFFSET_POSITION + "x"));
            this.offsetPosition.setY(compoundTag.getDouble(KEY_OFFSET_POSITION + "y"));
            this.offsetPosition.setZ(compoundTag.getDouble(KEY_OFFSET_POSITION + "z"));
            this.rotation.setX(compoundTag.getDouble(KEY_ROTATE_POSITION + "x"));
            this.rotation.setY(compoundTag.getDouble(KEY_ROTATE_POSITION + "y"));
            this.rotation.setZ(compoundTag.getDouble(KEY_ROTATE_POSITION + "z"));
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            compoundTag.putDouble(KEY_OFFSET_POSITION + "x", this.offsetPosition.getX());
            compoundTag.putDouble(KEY_OFFSET_POSITION + "y", this.offsetPosition.getY());
            compoundTag.putDouble(KEY_OFFSET_POSITION + "z", this.offsetPosition.getZ());
            compoundTag.putDouble(KEY_ROTATE_POSITION + "x", this.rotation.getX());
            compoundTag.putDouble(KEY_ROTATE_POSITION + "y", this.rotation.getY());
            compoundTag.putDouble(KEY_ROTATE_POSITION + "z", this.rotation.getZ());
        }

        private static BlockEntityType<? extends BlockNodeBaseEntity> getType(CatenaryModel catenaryModel) {
            switch (catenaryModel) {
                case CATENARY_SHORT:
                    return BlockEntityTypes.CATENARY_WITH_SHORT.get();
                case CATENARY_LONG_TOP:
                    return BlockEntityTypes.CATENARY_WITH_LONG_TOP.get();
                case CATENARY_SHORT_TOP:
                    return BlockEntityTypes.CATENARY_WITH_SHORT_TOP.get();
                case CATENARY_LONG_COUNTERWEIGHT:
                    return BlockEntityTypes.CATENARY_WITH_LONG_COUNTERWEIGHT.get();
                case CATENARY_SHORT_COUNTERWEIGHT:
                    return BlockEntityTypes.CATENARY_WITH_SHORT_COUNTERWEIGHT.get();
                case CATENARY_LONG_COUNTERWEIGHT_MIRROR:
                    return BlockEntityTypes.CATENARY_WITH_LONG_COUNTERWEIGHT_MIRROR.get();
                case CATENARY_SHORT_COUNTERWEIGHT_MIRROR:
                    return BlockEntityTypes.CATENARY_WITH_SHORT_COUNTERWEIGHT_MIRROR.get();
                default:
                    return BlockEntityTypes.CATENARY_WITH_LONG.get();
            }
        }
    }

    public enum CatenaryModel {
        CATENARY_LONG("catenary_long", true, false, false, false),
        CATENARY_LONG_TOP("catenary_long_top", true, true, false, false),
        CATENARY_LONG_COUNTERWEIGHT("catenary_long_counterweight", true, false, true, false),
        CATENARY_LONG_COUNTERWEIGHT_MIRROR("catenary_long_counterweight_mirror", true, false, true, true),
        CATENARY_SHORT("catenary_short", false, false, false, false),
        CATENARY_SHORT_TOP("catenary_short_top", false, true, false, false),
        CATENARY_SHORT_COUNTERWEIGHT("catenary_short_counterweight", false, false, true, false),
        CATENARY_SHORT_COUNTERWEIGHT_MIRROR("catenary_short_counterweight_mirror", false, false, true, true);

        private final String modelId;
        private final boolean isLong;
        private final boolean isTop;
        private final boolean hasCounterweight;
        private final boolean isMirror;

        CatenaryModel(String modelId, boolean isLong, boolean isTop, boolean hasCounterweight, boolean isMirror) {
            this.modelId = modelId;
            this.isLong = isLong;
            this.isTop = isTop;
            this.hasCounterweight = hasCounterweight;
            this.isMirror = isMirror;
        }

        public String getModelId() {
            return this.modelId;
        }

        public boolean isLong() {
            return this.isLong;
        }

        public boolean isTop() {
            return this.isTop;
        }

        public boolean isHasCounterweight() {
            return this.hasCounterweight;
        }

        public boolean isMirror() {
            return this.isMirror;
        }
    }
}