package top.mcmtr.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.HolderBase;
import top.mcmtr.mod.items.ItemHold;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockChangeModelBase extends BlockExtension implements DirectionHelper {
    public static final IntegerProperty TYPE = IntegerProperty.of("type", 0, 5);
    private final int count;

    public BlockChangeModelBase(BlockSettings blockSettings, int maxModelNum) {
        super(blockSettings);
        this.count = maxModelNum;
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(FACING);
        properties.add(TYPE);
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final Direction facing = ctx.getPlayerFacing();
        final CompoundTag compoundTag = ctx.getStack().getOrCreateTag();
        final int index = compoundTag.contains(ItemHold.TAG_HOLD) ? compoundTag.getInt(ItemHold.TAG_HOLD) : 0;
        return getDefaultState2().with(new Property<>(FACING.data), facing.data).with(new Property<>(TYPE.data), index);
    }

    @Override
    public void addTooltips(ItemStack stack, @Nullable BlockView world, List<MutableText> tooltip, TooltipContext options) {
        tooltip.add(TextHelper.translatable("tooltip.msd.model_count", count).formatted(TextFormatting.GOLD));
    }

    public int getCount() {
        return count;
    }
}