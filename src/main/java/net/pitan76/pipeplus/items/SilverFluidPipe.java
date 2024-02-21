package net.pitan76.pipeplus.items;

import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendBlockItem;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.pipeplus.blocks.Blocks;

public class SilverFluidPipe extends ExtendBlockItem {
    public SilverFluidPipe(CompatibleItemSettings settings) {
        super(Blocks.SILVER_FLUID_PIPE, settings);
    }

    @Override
    public void appendTooltip(ItemAppendTooltipEvent e) {
        e.tooltip.add(TextUtil.translatable("tooltip.pipeplus.auto_extract_pipe"));
    }
}
