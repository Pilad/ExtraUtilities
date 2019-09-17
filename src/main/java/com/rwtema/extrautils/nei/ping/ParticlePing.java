// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei.ping;

import com.rwtema.extrautils.helper.XURandom;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ParticlePing extends EntityReddustFX {
    public static final Random RANDOM;
    public static final float r = 1.0f;
    public static final float g = 1.0f;
    public static final float b = 1.0f;

    static {
        RANDOM = XURandom.getInstance();
    }

    public ParticlePing(final World world, final int x, final int y, final int z) {
        super(world, x + randOffset(), y + randOffset(), z + randOffset(), 1.0f, 1.0f, 1.0f);
        this.noClip = true;
        this.particleMaxAge *= 10;
        this.motionX *= 0.1;
        this.motionY *= 0.1;
        this.motionZ *= 0.1;
        this.particleScale *= 2.0f;
    }

    public ParticlePing(final WorldClient theWorld, final ChunkPosition p) {
        this(theWorld, p.chunkPosX, p.chunkPosY, p.chunkPosZ);
    }

    public static double randOffset() {
        return 0.5 + (ParticlePing.RANDOM.nextDouble() - 0.5) * ParticlePing.RANDOM.nextDouble();
    }

    public void renderParticle(final Tessellator tessellator, final float partialTickTime, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
        super.renderParticle(tessellator, partialTickTime, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        tessellator.draw();
        GL11.glDisable(2929);
        tessellator.startDrawingQuads();
        super.renderParticle(tessellator, partialTickTime, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        tessellator.draw();
        GL11.glEnable(2929);
        tessellator.startDrawingQuads();
    }
}


