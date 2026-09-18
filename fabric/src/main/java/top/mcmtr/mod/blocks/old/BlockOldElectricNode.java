package top.mcmtr.mod.blocks.old;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import top.mcmtr.mod.BlockEntityTypes;

public final class BlockOldElectricNode extends BlockOldNodeBase {
    public BlockOldElectricNode() {
        super();
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockOldElectricNodeEntity(blockPos, blockState);
    }

    public static class BlockOldElectricNodeEntity extends BlockNodeBaseEntity {
        public BlockOldElectricNodeEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypes.ELECTRIC_NODE.get(), blockPos, blockState);
        }
    }
}
