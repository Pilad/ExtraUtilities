// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.modintegration;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.rwtema.extrautils.item.ItemAngelRing;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ItemBaubleAngelRing extends ItemAngelRing implements IBauble {
    @Override
    public BaubleType getBaubleType(final ItemStack itemstack) {
        return super.getBaubleType(null);
    }

    @Override
    public void onWornTick(final ItemStack itemstack, final EntityLivingBase player) {
        super.onUpdate(itemstack, player.worldObj, player, 0, false);
    }

    @Override
    public void onEquipped(final ItemStack itemstack, final EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(final ItemStack itemstack, final EntityLivingBase player) {
    }

    @Override
    public boolean canEquip(final ItemStack itemstack, final EntityLivingBase player) {
        return false;
    }

    @Override
    public boolean canUnequip(final ItemStack itemstack, final EntityLivingBase player) {
        return false;
    }
}


