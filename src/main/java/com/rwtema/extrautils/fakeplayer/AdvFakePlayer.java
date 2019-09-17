// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.fakeplayer;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class AdvFakePlayer extends FakePlayer {
    private static AdvFakePlayer playerDefault;

    static {
        AdvFakePlayer.playerDefault = null;
    }

    String id;

    public AdvFakePlayer(final WorldServer world, final GameProfile name) {
        super(world, name);
        this.id = "41C82C87-7AfB-4024-BA57-13D2C99CAE77";
    }

    public String getPlayerIP() {
        return "fake.player.user.name=" + this.getGameProfile().getName();
    }
}


