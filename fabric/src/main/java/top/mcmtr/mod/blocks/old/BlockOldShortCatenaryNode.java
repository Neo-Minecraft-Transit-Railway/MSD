package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import top.mcmtr.mod.BlockEntityTypes;

public final class BlockOldShortCatenaryNode extends BlockOldNodeBase {
    public BlockOldShortCatenaryNode() {
        super();
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockOldShortCatenaryNodeEntity(blockPos, blockState);
    }

    public static class BlockOldShortCatenaryNodeEntity extends BlockNodeBaseEntity {
        public BlockOldShortCatenaryNodeEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypes.SHORT_CATENARY_NODE.get(), blockPos, blockState);
        }
    }
}
