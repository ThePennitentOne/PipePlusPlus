package ml.pkom.pipeplus.items;

import ml.pkom.mcpitanlibarch.api.event.item.ItemAppendTooltipEvent;
import ml.pkom.mcpitanlibarch.api.item.CompatibleItemSettings;
import ml.pkom.mcpitanlibarch.api.item.ExtendItem;
import ml.pkom.mcpitanlibarch.api.util.ItemUtil;
import ml.pkom.mcpitanlibarch.api.util.TextUtil;
import ml.pkom.pipeplus.PipePlus;
import ml.pkom.pipeplus.blocks.Blocks;
import net.minecraft.item.Item;

public class CopperFluidPipe extends ExtendItem {
    public static CompatibleItemSettings itemSettings = new CompatibleItemSettings().
            addGroup(PipePlus.PIPEPLUS_GROUP,
                    PipePlus.id("copper_fluid_pipe"));

    @Override
    public void appendTooltip(ItemAppendTooltipEvent event) {
        event.tooltip.add(TextUtil.translatable("tip.pipeplus.auto_extract_fluid_pipe"));
    }

    public CopperFluidPipe(CompatibleItemSettings settings) {
        super(settings);
    }

    public static CompatibleItemSettings getSettings() {
        return itemSettings;
    }

    public static Item newItem() {
        return ItemUtil.ofBlock(Blocks.COPPER_FLUID_PIPE, getSettings());
    }
}