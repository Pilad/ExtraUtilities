// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.worldgen;

import com.rwtema.extrautils.ExtraUtils;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkDataEvent;

import java.util.Random;

public class WorldGenEnderLillies implements IWorldGenerator {
    public static String retrogen;

    static {
        WorldGenEnderLillies.retrogen = "XU:EnderLilyRetrogen";
    }

    public WorldGenEnderLillies() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isAirBlock(final Block id) {
        return id == Blocks.air || id == null;
    }

    public static void gen(final Random random, final Chunk chunk) {
        for (int i = 0; i < 32; ++i) {
            final int x = random.nextInt(16);
            final int y = 10 + random.nextInt(65);
            final int z = random.nextInt(16);
            if (chunk.getBlock(x, y, z) == Blocks.end_stone && isAirBlock(chunk.getBlock(x, y + 1, z))) {
                chunk.func_150807_a(x, y + 1, z, ExtraUtils.enderLily, 7);
                if (random.nextDouble() < 0.2) {
                    return;
                }
            }
        }
    }

    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 1) {
            gen(random, world.getChunkFromChunkCoords(chunkX, chunkZ));
        }
    }

    public void saveData(final ChunkDataEvent.Save event) {
        if (event.world.provider.dimensionId == 1) {
            event.getData().setInteger(WorldGenEnderLillies.retrogen, ExtraUtils.enderLilyRetrogenId);
        }
    }

    public void loadData(final ChunkDataEvent.Load event) {
        if (ExtraUtils.enderLilyRetrogenId > 0 && event.world.provider.dimensionId == 1 && event.world instanceof WorldServer && event.getData().getInteger(WorldGenEnderLillies.retrogen) != ExtraUtils.enderLilyRetrogenId) {
            final long worldSeed = event.world.getSeed();
            final Random random = new Random(worldSeed);
            final long xSeed = random.nextLong() >> 3;
            final long zSeed = random.nextLong() >> 3;
            final long chunkSeed = xSeed * event.getChunk().xPosition + zSeed * event.getChunk().zPosition ^ worldSeed;
            random.setSeed(chunkSeed);
            gen(random, event.getChunk());
        }
    }
}


