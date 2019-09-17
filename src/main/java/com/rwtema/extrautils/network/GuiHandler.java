// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.dynamicgui.DynamicGui;
import com.rwtema.extrautils.dynamicgui.IDynamicContainerProvider;
import com.rwtema.extrautils.gui.*;
import com.rwtema.extrautils.tileentity.TileEntityFilingCabinet;
import com.rwtema.extrautils.tileentity.TileEntityTradingPost;
import com.rwtema.extrautils.tileentity.TileEntityTrashCan;
import com.rwtema.extrautils.tileentity.enderconstructor.DynamicContainerEnderConstructor;
import com.rwtema.extrautils.tileentity.enderconstructor.DynamicGuiEnderConstructor;
import com.rwtema.extrautils.tileentity.enderconstructor.TileEnderConstructor;
import com.rwtema.extrautils.tileentity.generators.DynamicContainerGenerator;
import com.rwtema.extrautils.tileentity.generators.TileEntityGenerator;
import com.rwtema.extrautils.tileentity.transfernodes.nodebuffer.INode;
import com.rwtema.extrautils.tileentity.transfernodes.pipes.IFilterPipe;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    public static final int TILE_ENTITY = 0;
    public static final int PLAYER_INVENTORY = 1;

    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        if (ID == 0) {
            final TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof INode) {
                return new ContainerTransferNode(player.inventory, ((INode) tile).getNode());
            }
            if (tile instanceof TileEntityTrashCan) {
                return new ContainerTrashCan(player.inventory, (TileEntityTrashCan) tile);
            }
            if (tile instanceof IFilterPipe) {
                return new ContainerFilterPipe(player.inventory, ((IFilterPipe) tile).getFilterInventory(world, x, y, z));
            }
            if (tile instanceof TileEntityFilingCabinet) {
                return new ContainerFilingCabinet(player.inventory, (TileEntityFilingCabinet) tile, false);
            }
            if (tile instanceof TileEntityGenerator) {
                return new DynamicContainerGenerator(player.inventory, (TileEntityGenerator) tile);
            }
            if (tile instanceof TileEnderConstructor) {
                return new DynamicContainerEnderConstructor(player.inventory, (TileEnderConstructor) tile);
            }
            if (tile instanceof IDynamicContainerProvider) {
                return ((IDynamicContainerProvider) tile).getContainer(player);
            }
        } else if (ID == 1) {
            if (x >= player.inventory.mainInventory.length) {
                return null;
            }
            final ItemStack item = player.inventory.getStackInSlot(x);
            if (item != null) {
                if (ExtraUtils.nodeUpgrade != null && item.getItem() == ExtraUtils.nodeUpgrade && item.getItemDamage() == 1) {
                    return new ContainerFilter(player, x);
                }
                if (ExtraUtils.goldenBag != null && item.getItem() == ExtraUtils.goldenBag) {
                    return new ContainerHoldingBag(player, x);
                }
            }
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity tile = world.getTileEntity(x, y, z);
        if (ID == 0) {
            if (tile instanceof TileEntityTradingPost) {
                final TileEntityTradingPost trade = (TileEntityTradingPost) tile;
                return new GuiTradingPost(player, trade.ids, trade.data, trade);
            }
            if (tile instanceof INode) {
                return new GuiTransferNode(player.inventory, ((INode) tile).getNode());
            }
            if (tile instanceof TileEntityTrashCan) {
                return new GuiTrashCan(player.inventory, (TileEntityTrashCan) tile);
            }
            if (tile instanceof IFilterPipe) {
                return new GuiFilterPipe(player.inventory, ((IFilterPipe) tile).getFilterInventory(world, x, y, z));
            }
            if (tile instanceof TileEntityFilingCabinet) {
                return new GuiFilingCabinet(player.inventory, (TileEntityFilingCabinet) tile);
            }
            if (tile instanceof TileEntityGenerator) {
                return new DynamicGui(new DynamicContainerGenerator(player.inventory, (TileEntityGenerator) tile));
            }
            if (tile instanceof TileEnderConstructor) {
                return new DynamicGuiEnderConstructor(new DynamicContainerEnderConstructor(player.inventory, (TileEnderConstructor) tile));
            }
            if (tile instanceof IDynamicContainerProvider) {
                return new DynamicGui(((IDynamicContainerProvider) tile).getContainer(player));
            }
        } else if (ID == 1) {
            if (x >= player.inventory.mainInventory.length) {
                return null;
            }
            final ItemStack item = player.inventory.getStackInSlot(x);
            if (item != null) {
                if (ExtraUtils.nodeUpgrade != null && item.getItem() == ExtraUtils.nodeUpgrade && item.getItemDamage() == 1) {
                    return new GuiFilter(player, x);
                }
                if (ExtraUtils.goldenBag != null && item.getItem() == ExtraUtils.goldenBag) {
                    return new GuiHoldingBag(player, x);
                }
            }
        }
        return null;
    }
}


