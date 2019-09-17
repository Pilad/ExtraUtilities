// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.multiparts;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.tileentity.transfernodes.TileEntityRetrievalNodeLiquid;
import net.minecraft.block.Block;

public class TransferNodePartLiquidRemote extends TransferNodePartLiquid {
    public TransferNodePartLiquidRemote() {
        super(new TileEntityRetrievalNodeLiquid());
    }

    public TransferNodePartLiquidRemote(final int meta, final TileEntityRetrievalNodeLiquid node) {
        super(meta, node);
    }

    @Override
    public String getType() {
        return "extrautils:transfer_node_liquid_remote";
    }

    @Override
    public Block getBlock() {
        return ExtraUtils.transferNodeRemote;
    }
}


