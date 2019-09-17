// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.multiparts;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;
import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.tileentity.transfernodes.*;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class RegisterTransferNodeParts implements MultiPartRegistry.IPartFactory, MultiPartRegistry.IPartConverter {
    public void init() {
        MultiPartRegistry.registerConverter(this);
        MultiPartRegistry.registerParts(this, new String[]{"extrautils:transfer_node_inv", "extrautils:transfer_node_liquid", "extrautils:transfer_node_energy", "extrautils:transfer_node_inv_remote", "extrautils:transfer_node_liquid_remote", "extrautils:transfer_node_energy_hyper"});
    }

    public Iterable<Block> blockTypes() {
        final Set<Block> s = new HashSet<Block>();
        s.add(ExtraUtils.transferNode);
        s.add(ExtraUtils.transferNodeRemote);
        return s;
    }

    public TMultiPart convert(final World world, final BlockCoord pos) {
        final Block id = world.getBlock(pos.x, pos.y, pos.z);
        final int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
        if (id == ExtraUtils.transferNode) {
            if (meta < 6) {
                if (world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityTransferNodeInventory) {
                    return new TransferNodePartInventory(meta, (TileEntityTransferNodeInventory) world.getTileEntity(pos.x, pos.y, pos.z));
                }
            } else if (meta < 12) {
                if (world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityTransferNodeLiquid) {
                    return new TransferNodePartLiquid(meta, (TileEntityTransferNodeLiquid) world.getTileEntity(pos.x, pos.y, pos.z));
                }
            } else if (meta == 12) {
                if (world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityTransferNodeEnergy) {
                    return new TransferNodePartEnergy(meta, (TileEntityTransferNodeEnergy) world.getTileEntity(pos.x, pos.y, pos.z));
                }
            } else if (meta == 13 && world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityTransferNodeHyperEnergy) {
                return new TransferNodePartHyperEnergy(meta, (TileEntityTransferNodeHyperEnergy) world.getTileEntity(pos.x, pos.y, pos.z));
            }
        }
        if (id == ExtraUtils.transferNodeRemote) {
            if (meta < 6) {
                if (world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityRetrievalNodeInventory) {
                    return new TransferNodePartInventoryRemote(meta, (TileEntityRetrievalNodeInventory) world.getTileEntity(pos.x, pos.y, pos.z));
                }
            } else if (meta < 12) {
                if (world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityRetrievalNodeLiquid) {
                    return new TransferNodePartLiquidRemote(meta, (TileEntityRetrievalNodeLiquid) world.getTileEntity(pos.x, pos.y, pos.z));
                }
            } else if (meta == 12 && world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityTransferNodeEnergy) {
                return new TransferNodePartEnergy(meta, (TileEntityTransferNodeEnergy) world.getTileEntity(pos.x, pos.y, pos.z));
            }
        }
        return null;
    }

    public TMultiPart createPart(final String name, final boolean client) {
        if (name.equals("extrautils:transfer_node_inv")) {
            return new TransferNodePartInventory();
        }
        if (name.equals("extrautils:transfer_node_liquid")) {
            return new TransferNodePartLiquid();
        }
        if (name.equals("extrautils:transfer_node_energy")) {
            return new TransferNodePartEnergy();
        }
        if (name.equals("extrautils:transfer_node_inv_remote")) {
            return new TransferNodePartInventoryRemote();
        }
        if (name.equals("extrautils:transfer_node_liquid_remote")) {
            return new TransferNodePartLiquidRemote();
        }
        if (name.equals("extrautils:transfer_node_energy_hyper")) {
            return new TransferNodePartHyperEnergy();
        }
        return null;
    }
}


