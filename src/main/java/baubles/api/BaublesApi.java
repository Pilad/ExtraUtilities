// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package baubles.api;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

import java.lang.reflect.Method;

public class BaublesApi {
    static Method getBaubles;

    public static IInventory getBaubles(final EntityPlayer player) {
        IInventory ot = null;
        try {
            if (BaublesApi.getBaubles == null) {
                final Class fake = Class.forName("baubles.common.lib.PlayerHandler");
                BaublesApi.getBaubles = fake.getMethod("getPlayerBaubles", EntityPlayer.class);
            }
            ot = (IInventory) BaublesApi.getBaubles.invoke(null, player);
        } catch (Exception ex) {
            FMLLog.warning("[Baubles API] Could not invoke baubles.common.lib.PlayerHandler method getPlayerBaubles");
        }
        return ot;
    }
}


