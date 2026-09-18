package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import top.mcmtr.mod.BlockEntityTypes;

public final class BlockOldCatenaryNode extends BlockOldNodeBase {
    public BlockOldCatenaryNode() {
        super();
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockOldCatenaryNodeEntity(blockPos, blockState);
    }

    public static class BlockOldCatenaryNodeEntity extends BlockNodeBaseEntity {
        public BlockOldCatenaryNodeEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypes.CATENARY_NODE.get(), blockPos, blockState);
        }
    }
}
