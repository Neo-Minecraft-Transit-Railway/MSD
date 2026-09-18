package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mod.Items;
import top.mcmtr.mod.BlockEntityTypes;
import top.mcmtr.mod.Blocks;
import top.mcmtr.mod.blocks.BlockCatenaryNode;

public final class BlockOldTransCatenaryNode extends BlockOldNodeBase {
    public BlockOldTransCatenaryNode() {
        super();
    }

    @Override
    public BlockRenderType getRenderType2(BlockState state) {
        return state.get(new Property<>(IS_CONNECTED.data)) ? BlockRenderType.INVISIBLE : super.getRenderType2(state);
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (player.isHolding(Items.BRUSH.get())) {
                final boolean isConnected = state.get(new Property<>(IS_CONNECTED.data));
                world.breakBlock(pos, false);
                world.setBlockState(pos, Blocks.NEW_CATENARY_NODE.get().getDefaultState().with(new Property<>(BlockCatenaryNode.IS_CONNECTED.data), isConnected));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockOldTransCatenaryNodeEntity(blockPos, blockState);
    }

    public static class BlockOldTransCatenaryNodeEntity extends BlockEntityExtension {
        public BlockOldTransCatenaryNodeEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypes.TRANS_CATENARY_NODE.get(), blockPos, blockState);
        }
    }
}