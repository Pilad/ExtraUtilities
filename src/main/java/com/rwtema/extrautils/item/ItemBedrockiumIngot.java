// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBedrockiumIngot extends Item {
    public ItemBedrockiumIngot() {
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setTextureName("extrautils:bedrockiumIngot");
        this.setUnlocalizedName("extrautils:bedrockiumIngot");
    }

    public void onUpdate(final ItemStack itemStack, final World world, final Entity entity, final int i, final boolean b) {
        super.onUpdate(itemStack, world, entity, i, b);
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 3));
        }
    }

    public int getEntityLifespan(final ItemStack itemStack, final World world) {
        return 2147473647;
    }
}


