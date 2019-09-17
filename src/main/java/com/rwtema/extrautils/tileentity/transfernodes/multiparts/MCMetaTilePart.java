// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.multiparts;

import codechicken.lib.vec.Vector3;
import codechicken.multipart.minecraft.McMetaPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;

public abstract class MCMetaTilePart extends McMetaPart {
    public MCMetaTilePart(final int meta) {
        super(meta);
    }

    public MCMetaTilePart() {
    }

    public abstract TileEntity getBlockTile();

    @SideOnly(Side.CLIENT)
    public boolean renderStatic(final Vector3 pos, final int pass) {
        if (pass == 0) {
            new RenderBlocks(new TilePartMetaAccess(this, this.getBlockTile())).renderBlockByRenderType(this.getBlock(), this.x(), this.y(), this.z());
            return true;
        }
        return false;
    }
}


