package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import top.mcmtr.mod.BlockEntityTypes;

public final class BlockOldShortCatenaryNodeStyle2 extends BlockOldNodeBase {
    public BlockOldShortCatenaryNodeStyle2() {
        super();
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockOldShortCatenaryNodeStyle2Entity(blockPos, blockState);
    }

    public static class BlockOldShortCatenaryNodeStyle2Entity extends BlockNodeBaseEntity {
        public BlockOldShortCatenaryNodeStyle2Entity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypes.SHORT_CATENARY_NODE_STYLE_2.get(), blockPos, blockState);
        }
    }
}
