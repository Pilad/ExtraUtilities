// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.ExtraUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemDestructionPickaxe extends ItemPickaxe implements IItemMultiTransparency {
    private IIcon[] icons;

    public ItemDestructionPickaxe() {
        super(Item.ToolMaterial.EMERALD);
        this.setUnlocalizedName("extrautils:destructionpickaxe");
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setMaxDamage(Item.ToolMaterial.EMERALD.getMaxUses() * 4);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack, final int pass) {
        return false;
    }

    public float func_150893_a(final ItemStack par1ItemStack, final Block par2Block) {
        float t = super.func_150893_a(par1ItemStack, par2Block);
        t = Math.max(t, Items.diamond_pickaxe.func_150893_a(par1ItemStack, par2Block));
        return t;
    }

    public boolean onBlockDestroyed(final ItemStack par1ItemStack, final World par2World, final Block par3, final int par4, final int par5, final int par6, final EntityLivingBase par7EntityLivingBase) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister par1IIconRegister) {
        this.icons = new IIcon[2];
        final IIcon[] icons = this.icons;
        final int n = 0;
        final IIcon registerIcon = par1IIconRegister.registerIcon(this.getUnlocalizedName().substring(5));
        icons[n] = registerIcon;
        this.itemIcon = registerIcon;
        this.icons[1] = par1IIconRegister.registerIcon(this.getUnlocalizedName().substring(5) + "1");
    }

    public int numIcons(final ItemStack item) {
        return 2;
    }

    public IIcon getIconForTransparentRender(final ItemStack item, final int pass) {
        return this.icons[pass];
    }

    public float getIconTransparency(final ItemStack item, final int pass) {
        if (pass == 1) {
            return 0.5f;
        }
        return 1.0f;
    }
}


