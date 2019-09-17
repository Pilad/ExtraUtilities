// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.multiparts;

import com.rwtema.extrautils.ExtraUtilsMod;
import com.rwtema.extrautils.tileentity.transfernodes.pipes.IFilterPipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

public class FilterPipePart extends PipePart implements IFilterPipe {
    public InventoryBasic items;

    public FilterPipePart(final int meta) {
        super(9);
        this.items = new InventoryBasic("Sorting Pipe", true, 6);
    }

    public FilterPipePart(final InventoryBasic items) {
        super(9);
        this.items = new InventoryBasic("Sorting Pipe", true, 6);
        this.items = items;
    }

    public FilterPipePart() {
        super(9);
        this.items = new InventoryBasic("Sorting Pipe", true, 6);
    }

    public void onRemoved() {
        if (!this.getWorld().isRemote) {
            final List<ItemStack> drops = new ArrayList<ItemStack>();
            for (int i = 0; i < this.items.getSizeInventory(); ++i) {
                if (this.items.getStackInSlot(i) != null) {
                    drops.add(this.items.getStackInSlot(i));
                }
            }
            this.tile().dropItems(drops);
        }
    }

    @Override
    public String getType() {
        return "extrautils:transfer_pipe_filter";
    }

    @Override
    public boolean activate(final EntityPlayer player, final MovingObjectPosition part, final ItemStack item) {
        player.openGui(ExtraUtilsMod.instance, 0, this.getWorld(), this.x(), this.y(), this.z());
        return true;
    }

    public void load(final NBTTagCompound tags) {
        super.load(tags);
        if (tags.hasKey("items")) {
            final NBTTagCompound item_tags = tags.getCompoundTag("items");
            for (int i = 0; i < this.items.getSizeInventory(); ++i) {
                if (item_tags.hasKey("item_" + i)) {
                    this.items.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(item_tags.getCompoundTag("item_" + i)));
                }
            }
        }
    }

    public void save(final NBTTagCompound par1NBTTagCompound) {
        super.save(par1NBTTagCompound);
        final NBTTagCompound item_tags = new NBTTagCompound();
        for (int i = 0; i < this.items.getSizeInventory(); ++i) {
            if (this.items.getStackInSlot(i) != null) {
                final NBTTagCompound item = new NBTTagCompound();
                this.items.getStackInSlot(i).writeToNBT(item);
                item_tags.setTag("item_" + i, item);
            }
        }
        par1NBTTagCompound.setTag("items", item_tags);
    }

    @Override
    public IInventory getFilterInventory(final IBlockAccess world, final int x, final int y, final int z) {
        return this.items;
    }
}


