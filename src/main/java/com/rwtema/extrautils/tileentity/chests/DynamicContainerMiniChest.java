// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.chests;

import com.rwtema.extrautils.dynamicgui.DynamicContainer;
import com.rwtema.extrautils.dynamicgui.WidgetDescPacket;
import com.rwtema.extrautils.dynamicgui.WidgetSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

public class DynamicContainerMiniChest extends DynamicContainer {
    private final TileMiniChest inv;

    public DynamicContainerMiniChest(final IInventory player, final TileMiniChest inv) {
        this.inv = inv;
        final int midPoint = 76;
        this.widgets.add(new WidgetSlot(inv, 0, midPoint, 19));
        this.widgets.add(new WidgetDescPacket() {
            @Override
            public NBTTagCompound getDescriptionPacket(final boolean changesOnly) {
                if (changesOnly || !DynamicContainerMiniChest.this.inv.hasCustomInventoryName()) {
                    return null;
                }
                final NBTTagCompound tag = new NBTTagCompound();
                tag.setString("Name", DynamicContainerMiniChest.this.inv.getInventoryName());
                return tag;
            }

            @Override
            public void handleDescriptionPacket(final NBTTagCompound packet) {
                final String name = packet.getString("Name");
                if (name.length() > 0) {
                    DynamicContainerMiniChest.this.inv.func_145976_a(name);
                }
            }
        });
        this.addTitle(inv.getInventoryName(), !inv.hasCustomInventoryName());
        this.cropAndAddPlayerSlots(player);
        this.validate();
    }

    public boolean canInteractWith(final EntityPlayer player) {
        return this.inv.isUseableByPlayer(player);
    }
}


