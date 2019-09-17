// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.fakeplayer;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

import java.io.File;

public class FakeWorld extends World {
    public FakeWorld() {
        super(FakeSave.instance, "", null, FakeWorldProvider.instance, null);
    }

    protected IChunkProvider createChunkProvider() {
        return null;
    }

    protected int func_152379_p() {
        return 0;
    }

    public Entity getEntityByID(final int var1) {
        return null;
    }

    public static class FakeSave implements ISaveHandler {
        public static FakeSave instance;

        static {
            FakeSave.instance = new FakeSave();
        }

        public WorldInfo loadWorldInfo() {
            return FakeWorldInfo.instance;
        }

        public void checkSessionLock() throws MinecraftException {
        }

        public IChunkLoader getChunkLoader(final WorldProvider var1) {
            return null;
        }

        public void saveWorldInfoWithPlayer(final WorldInfo var1, final NBTTagCompound var2) {
        }

        public void saveWorldInfo(final WorldInfo var1) {
        }

        public IPlayerFileData getSaveHandler() {
            return null;
        }

        public void flush() {
        }

        public File getWorldDirectory() {
            return null;
        }

        public File getMapFileFromName(final String var1) {
            return null;
        }

        public String getWorldDirectoryName() {
            return null;
        }
    }

    public static class FakeWorldInfo extends WorldInfo {
        public static FakeWorldInfo instance;

        static {
            FakeWorldInfo.instance = new FakeWorldInfo();
        }

        public boolean isInitialized() {
            return true;
        }

        public int getVanillaDimension() {
            return 0;
        }
    }

    public static class FakeWorldProvider extends WorldProvider {
        public static FakeWorldProvider instance;

        static {
            FakeWorldProvider.instance = new FakeWorldProvider();
        }

        public void calculateInitialWeather() {
        }

        public String getDimensionName() {
            return "FAKE";
        }

        protected void registerWorldChunkManager() {
        }
    }
}


