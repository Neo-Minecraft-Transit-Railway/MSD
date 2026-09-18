package top.mcmtr.mod.items;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockItemExtension;
import org.mtr.mapping.mapper.ItemStackNbtHelper;

public class ItemHold extends BlockItemExtension {
    public static final String TAG_HOLD = "hold_num";

    private final int max_count;

    public ItemHold(Block block, ItemSettings itemSettings, int max_count) {
        super(block, itemSettings);
        this.max_count = max_count;
    }

    @Override
    public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);
            CompoundTag tag = ItemStackNbtHelper.getTag(stack);
            int current = Math.floorMod(tag.getInt(TAG_HOLD), max_count);
            tag.putInt(TAG_HOLD, (current + 1) % max_count);
            ItemStackNbtHelper.saveTag(stack, tag);
        }
    }
}
