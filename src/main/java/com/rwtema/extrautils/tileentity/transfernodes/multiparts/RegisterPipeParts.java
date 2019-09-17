// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.multiparts;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;
import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.tileentity.transfernodes.TileEntityFilterPipe;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class RegisterPipeParts implements MultiPartRegistry.IPartFactory, MultiPartRegistry.IPartConverter {
    public TMultiPart createPart(final String name, final boolean client) {
        if (name.equals("extrautils:transfer_pipe_filter")) {
            return new FilterPipePart();
        }
        if (name.equals("extrautils:transfer_pipe")) {
            return new PipePart();
        }
        return null;
    }

    public void init() {
        MultiPartRegistry.registerConverter(this);
        MultiPartRegistry.registerParts(this, new String[]{"extrautils:transfer_pipe", "extrautils:transfer_pipe_filter"});
    }

    public Iterable<Block> blockTypes() {
        final Set<Block> set = new HashSet<Block>();
        set.add(ExtraUtils.transferPipe);
        set.add(ExtraUtils.transferPipe2);
        return set;
    }

    public TMultiPart convert(final World world, final BlockCoord pos) {
        final Block id = world.getBlock(pos.x, pos.y, pos.z);
        int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
        if (id != ExtraUtils.transferPipe && id != ExtraUtils.transferPipe2) {
            return null;
        }
        if (id == ExtraUtils.transferPipe2) {
            meta += 16;
        }
        if (meta != 9) {
            return new PipePart(meta);
        }
        if (world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityFilterPipe) {
            final InventoryBasic t = ((TileEntityFilterPipe) world.getTileEntity(pos.x, pos.y, pos.z)).items;
            return new FilterPipePart(t);
        }
        return new FilterPipePart();
    }
}


