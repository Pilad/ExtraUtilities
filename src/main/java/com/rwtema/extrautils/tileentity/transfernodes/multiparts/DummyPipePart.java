// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.multiparts;

import codechicken.lib.vec.Cuboid6;
import codechicken.microblock.ISidedHollowConnect;
import codechicken.multipart.JCuboidPart;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;
import com.rwtema.extrautils.tileentity.transfernodes.pipes.IPipe;

import java.util.Arrays;

public class DummyPipePart extends JCuboidPart implements JNormalOcclusion, ISidedHollowConnect {
    public int dir;
    public float h;

    public DummyPipePart(final int dir, final float h) {
        this.dir = dir;
        this.h = h;
    }

    public boolean occlusionTest(final TMultiPart npart) {
        return npart instanceof IPipe || NormalOcclusionTest.apply(this, npart);
    }

    public Iterable<Cuboid6> getOcclusionBoxes() {
        return Arrays.asList(this.getBounds());
    }

    public Cuboid6 getBounds() {
        switch (this.dir) {
            case 0: {
                return new Cuboid6(0.375, 0.0, 0.375, 0.625, this.h, 0.625);
            }
            case 1: {
                return new Cuboid6(0.375, 1.0f - this.h, 0.375, 0.625, 1.0, 0.625);
            }
            case 2: {
                return new Cuboid6(0.375, 0.375, 0.0, 0.625, 0.625, this.h);
            }
            case 3: {
                return new Cuboid6(0.375, 0.375, 1.0f - this.h, 0.625, 0.625, 1.0);
            }
            case 4: {
                return new Cuboid6(0.0, 0.375, 0.375, this.h, 0.625, 0.625);
            }
            case 5: {
                return new Cuboid6(1.0f - this.h, 0.375, 0.375, 1.0, 0.625, 0.625);
            }
            default: {
                return new Cuboid6(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            }
        }
    }

    public String getType() {
        return "dummyPipe";
    }

    public int getHollowSize(final int side) {
        return 2;
    }
}


