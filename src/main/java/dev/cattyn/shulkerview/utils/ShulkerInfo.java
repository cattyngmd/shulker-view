package dev.cattyn.shulkerview.utils;

import dev.cattyn.shulkerview.config.ShulkerViewConfig;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.cattyn.shulkerview.utils.Utils.getColor;

public record ShulkerInfo(ItemStack shulker, boolean compact, int color, int slot, List<ItemStack> stacks) {

    public static ShulkerInfo create(ShulkerViewConfig config, ItemStack stack, int slot) {
        ShulkerBoxBlock block = getBlock(stack);
        if (block == null) return null;

        List<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY);
        ContainerComponent component = stack.getComponents().get(DataComponentTypes.CONTAINER);

        boolean compact = config.isCompact();

        if (component != null) {
            Item unstackable = null;

            List<ItemStack> list = component.stream().toList();
            for (int i = 0; i < list.size(); i++) {
                ItemStack item = list.get(i);
                items.set(i, item);
                if (item.getMaxCount() == 1) {
                    if (unstackable != null && !item.getItem().equals(unstackable)) compact = false;
                    unstackable = item.getItem();
                }
            }

        }

        if (compact)
            shrinkToCompact(items);

        return new ShulkerInfo(stack, compact, getColor(block), slot, items);
    }

    private static void shrinkToCompact(List<ItemStack> items) {
        Map<Item, Integer> map = new HashMap<>();
        for (ItemStack item : items) {
            if (item.isEmpty()) continue;

            map.merge(item.getItem(), item.getCount(), Integer::sum);
        }
        items.clear();
        int k = 0;
        for (Map.Entry<Item, Integer> entry : map.entrySet()) {
            items.set(k++, new ItemStack(entry.getKey(), entry.getValue()));
        }
    }

    private static ShulkerBoxBlock getBlock(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem b && b.getBlock() instanceof ShulkerBoxBlock shulker)
            return shulker;
        return null;
    }

}