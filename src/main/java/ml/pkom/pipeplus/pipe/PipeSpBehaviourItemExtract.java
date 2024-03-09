package ml.pkom.pipeplus.pipe;

import alexiil.mc.lib.attributes.Simulation;
import alexiil.mc.lib.attributes.item.ItemExtractable;
import alexiil.mc.lib.attributes.item.ItemInsertable;
import alexiil.mc.lib.attributes.item.impl.EmptyItemExtractable;
import alexiil.mc.mod.pipes.pipe.PartSpPipe;
import alexiil.mc.mod.pipes.pipe.PipeSpBehaviourSided;
import alexiil.mc.mod.pipes.pipe.PipeSpFlowItem;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.Direction;

import java.util.logging.Logger;

public class PipeSpBehaviourItemExtract extends PipeSpBehaviourSided {
    private int needCooldown = 20;
    private int cooldown = needCooldown;

    private int pulses = 1;

    public PipeSpBehaviourItemExtract(PartSpPipe pipe, int time, int pulses) {
        super(pipe);
        this.needCooldown = time;
        this.pulses = pulses;
    }

    @Override
    public void tick() {
        super.tick();
        cooldown--;
        if (cooldown <= 0) {
            cooldown = needCooldown;
            if (!pipe.getPipeWorld().isClient) {
                Direction dir = currentDirection();
                if (dir != null) {
                    tryExtract(dir, pulses);
                }
            }
        }
    }

    @Override
    protected boolean canFaceDirection(Direction dir) {
        if (pipe.getNeighbourPipe(dir) != null) {
            return false;
        } else {
            return pipe.getItemExtractable(dir) != EmptyItemExtractable.NULL;
        }
    }

    public void tryExtract(Direction dir, int pulses) {
        ItemExtractable extractable = pipe.getItemExtractable(dir);

        NbtCompound fullTag =   pipe.holder.getPart().toTag();//.get("f");
        assert fullTag != null;
       NbtCompound contents = fullTag.getCompound("f");



        NbtList  itemlist = contents.getList("items",NbtElement.COMPOUND_TYPE);


       // Log.info(LogCategory.LOG, itemlist.size()+ " : " +contents.asString() );


        ItemStack stack = extractable.attemptAnyExtraction(pulses, Simulation.ACTION);

        if (!stack.isEmpty() & itemlist.size() < 32) {
            ((PipeSpFlowItem)pipe.getFlow()).insertItemsForce(stack, dir, null, 0.08D);
        }
    }
}
