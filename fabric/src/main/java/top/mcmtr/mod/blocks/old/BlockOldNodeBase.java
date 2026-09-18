package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.HolderBase;
import top.mcmtr.mod.blocks.BlockNodeBase;
import top.mcmtr.mod.packet.MSDPacketDeleteData;

import java.util.List;

import static org.mtr.mapping.mapper.DirectionHelper.FACING;

public abstract class BlockOldNodeBase extends BlockNodeBase {
    public static final BooleanProperty IS_CONNECTED = BooleanProperty.of("is_connected");

    public BlockOldNodeBase() {
        super();
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(IS_CONNECTED);
        properties.add(FACING);
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final Direction facing = ctx.getPlayerFacing();
        return getDefaultState2().with(new Property<>(FACING.data), facing.data);
    }

    @Override
    public void addTooltips(ItemStack stack, BlockView world, List<MutableText> tooltip, TooltipContext options) {
        tooltip.add(TextHelper.translatable("tooltip.msd.deprecated").formatted(TextFormatting.RED));
    }

    @Override
    public void onBreak2(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            MSDPacketDeleteData.sendDirectlyToServerCatenaryNodePosition(ServerWorld.cast(world), org.mtr.mod.Init.blockPosToPosition(pos));
        }
    }

    @Override
    public VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }
}
