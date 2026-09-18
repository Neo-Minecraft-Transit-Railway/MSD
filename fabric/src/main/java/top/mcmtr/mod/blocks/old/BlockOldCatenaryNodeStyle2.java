package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import top.mcmtr.mod.BlockEntityTypes;

public final class BlockOldCatenaryNodeStyle2 extends BlockOldNodeBase {
    public BlockOldCatenaryNodeStyle2() {
        super();
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockOldCatenaryNodeStyle2Entity(blockPos, blockState);
    }

    public static class BlockOldCatenaryNodeStyle2Entity extends BlockNodeBaseEntity {
        public BlockOldCatenaryNodeStyle2Entity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypes.CATENARY_NODE_STYLE_2.get(), blockPos, blockState);
        }
    }
}