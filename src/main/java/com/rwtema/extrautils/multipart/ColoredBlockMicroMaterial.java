// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart;

import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MaterialRenderHelper;
import com.rwtema.extrautils.block.BlockColor;
import com.rwtema.extrautils.block.BlockColorData;
import com.rwtema.extrautils.helper.XUHelperClient;
import com.rwtema.extrautils.tileentity.TileEntityBlockColorData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class ColoredBlockMicroMaterial extends BlockMicroMaterial {
    public ColoredBlockMicroMaterial(final BlockColor block, final int meta) {
        super(block, meta);
    }

    @SideOnly(Side.CLIENT)
    public void renderMicroFace(final Vector3 pos, final int pass, final Cuboid6 bounds) {
        float[] col = BlockColor.initColor[this.meta()];
        if (XUHelperClient.clientPlayer().getEntityWorld() != null && pass != -1) {
            col = BlockColorData.getColorData(XUHelperClient.clientPlayer().getEntityWorld(), (int) pos.x, (int) pos.y, (int) pos.z, this.meta());
        } else {
            final Entity holder = XUHelperClient.clientPlayer();
            if (holder != null) {
                final TileEntity tiledata = holder.worldObj.getTileEntity(BlockColorData.dataBlockX((int) Math.floor(holder.posX)), BlockColorData.dataBlockY((int) holder.posY), BlockColorData.dataBlockZ((int) Math.floor(holder.posZ)));
                if (tiledata instanceof TileEntityBlockColorData) {
                    col = ((TileEntityBlockColorData) tiledata).palette[this.meta()];
                }
            }
        }
        final int c = (int) (col[0] * 255.0f) << 24 | (int) (col[1] * 255.0f) << 16 | (int) (col[2] * 255.0f) << 8 | 0xFF;
        MaterialRenderHelper.start(pos, pass, this.icont()).blockColour(c).lighting().render();
    }
}


