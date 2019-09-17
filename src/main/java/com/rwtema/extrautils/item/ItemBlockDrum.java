// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.ICreativeTabSorting;
import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.tileentity.TileEntityDrum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.ItemFluidContainer;

import java.util.List;
import java.util.Locale;

public class ItemBlockDrum extends ItemBlockMetadata implements IFluidContainerItem, ICreativeTabSorting {
    public ItemFluidContainer slaveItem;
    protected int capacity;

    public ItemBlockDrum(final Block b) {
        super(b);
        this.capacity = 256000;
        this.slaveItem = new ItemFluidContainer(-1, this.capacity);
    }

    public void setCapacityFromMeta(final int meta) {
        this.slaveItem.setCapacity(TileEntityDrum.getCapacityFromMetadata(meta));
    }

    public ItemFluidContainer setCapacity(final int capacity) {
        return this.slaveItem.setCapacity(capacity);
    }

    public FluidStack getFluid(final ItemStack container) {
        this.setCapacityFromMeta(container.getItemDamage());
        return this.slaveItem.getFluid(container);
    }

    public int getCapacity(final ItemStack container) {
        this.setCapacityFromMeta(container.getItemDamage());
        return this.slaveItem.getCapacity(container);
    }

    public int fill(final ItemStack container, final FluidStack resource, final boolean doFill) {
        this.setCapacityFromMeta(container.getItemDamage());
        return this.slaveItem.fill(container, resource, doFill);
    }

    public FluidStack drain(final ItemStack container, final int maxDrain, final boolean doDrain) {
        this.setCapacityFromMeta(container.getItemDamage());
        final FluidStack t = this.slaveItem.drain(container, maxDrain, doDrain);
        if (this.slaveItem.getFluid(container) != null && this.slaveItem.getFluid(container).amount < 0) {
            container.setTagCompound(null);
            throw new RuntimeException("Fluid container has been drained into negative numbers. This is a Forge bug.");
        }
        return t;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack item, final EntityPlayer par2EntityPlayer, final List info, final boolean par4) {
        this.setCapacityFromMeta(item.getItemDamage());
        final FluidStack fluid = this.slaveItem.getFluid(item);
        if (fluid != null) {
            info.add(XUHelper.getFluidName(fluid) + ": " + String.format(Locale.ENGLISH, "%,d", fluid.amount) + " / " + String.format(Locale.ENGLISH, "%,d", this.slaveItem.getCapacity(null)));
        } else {
            info.add("Empty: 0 / " + String.format(Locale.ENGLISH, "%,d", this.slaveItem.getCapacity(null)));
        }
    }

    public String getItemStackDisplayName(final ItemStack item) {
        String s = super.getItemStackDisplayName(item);
        final FluidStack fluid = this.getFluid(item);
        if (fluid != null) {
            s = XUHelper.getFluidName(fluid) + " " + s;
        }
        return s.trim();
    }

    public String getSortingName(final ItemStack item) {
        return super.getItemStackDisplayName(item) + ":" + this.getItemStackDisplayName(item);
    }
}


