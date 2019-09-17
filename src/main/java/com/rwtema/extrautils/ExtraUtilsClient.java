// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils;

import com.rwtema.extrautils.block.BlockColor;
import com.rwtema.extrautils.block.render.*;
import com.rwtema.extrautils.command.*;
import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.item.*;
import com.rwtema.extrautils.multipart.FakeRenderBlocksMultiPart;
import com.rwtema.extrautils.multipart.microblock.RenderItemMicroblock;
import com.rwtema.extrautils.network.NetworkHandler;
import com.rwtema.extrautils.network.PacketHandler;
import com.rwtema.extrautils.network.PacketHandlerClient;
import com.rwtema.extrautils.network.packets.PacketUseItemAlt;
import com.rwtema.extrautils.particle.ParticleHelperClient;
import com.rwtema.extrautils.texture.LiquidColorRegistry;
import com.rwtema.extrautils.tileentity.RenderTileEntitySpike;
import com.rwtema.extrautils.tileentity.TileEntityEnchantedSpike;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

@SideOnly(Side.CLIENT)
public class ExtraUtilsClient extends ExtraUtilsProxy {
    public static final RenderItemBlockColor renderItemBlockColor;
    public static final RenderItemUnstable renderItemUnstable;
    public static final RenderItemSpikeSword renderItemSpikeSword;
    public static final RenderItemMultiTransparency renderItemMultiTransparency;
    public static final RenderItemBlockDrum renderItemDrum;
    public static final RenderBlockSpike renderBlockSpike;

    static {
        renderItemBlockColor = new RenderItemBlockColor();
        renderItemUnstable = new RenderItemUnstable();
        renderItemSpikeSword = new RenderItemSpikeSword();
        renderItemMultiTransparency = new RenderItemMultiTransparency();
        renderItemDrum = new RenderItemBlockDrum();
        renderBlockSpike = new RenderBlockSpike();
    }

    public ExtraUtilsClient() {
        ExtraUtilsClient.INSTANCE = this;
    }

    @Override
    public void registerClientCommands() {
        if (XUHelper.deObf || XUHelper.isTema(Minecraft.getMinecraft().getSession().func_148256_e())) {
            ClientCommandHandler.instance.registerCommand(new CommandDumpNEIInfo());
            ClientCommandHandler.instance.registerCommand(new CommandDumpNEIInfo2());
            ClientCommandHandler.instance.registerCommand(new CommandDumpTextureSheet());
        }
        ClientCommandHandler.instance.registerCommand(CommandTPSTimer.INSTANCE);
    }

    @Override
    public EntityPlayer getPlayerFromNetHandler(final INetHandler handler) {
        final EntityPlayer player = super.getPlayerFromNetHandler(handler);
        if (player == null) {
            return this.getClientPlayer();
        }
        return player;
    }

    @Override
    public void postInit() {
    }

    @Override
    public void registerEventHandler() {
        MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
        FMLCommonHandler.instance().bus().register(new EventHandlerClient());
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new LiquidColorRegistry());
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new ParticleHelperClient());
        ExtraUtils.handlesClientMethods = true;
        if (Loader.isModLoaded("ForgeMultipart")) {
            RenderBlockConnectedTextures.fakeRender = new FakeRenderBlocksMultiPart();
        }
        ClientCommandHandler.instance.registerCommand(new CommandHologram());
        ClientCommandHandler.instance.registerCommand(new CommandUUID());
        KeyHandler.INSTANCE.register();
        super.registerEventHandler();
    }

    @Override
    public void throwLoadingError(final String cause, final String... message) {
        throw new CustomErrorWGui(cause, message);
    }

    @Override
    public boolean isClientSideAvailable() {
        return true;
    }

    @Override
    public void newServerStart() {
        super.newServerStart();
    }

    @Override
    public void registerRenderInformation() {
        ExtraUtilsClient.colorBlockID = RenderingRegistry.getNextAvailableRenderId();
        ExtraUtilsClient.fullBrightBlockID = RenderingRegistry.getNextAvailableRenderId();
        ExtraUtilsClient.multiBlockID = RenderingRegistry.getNextAvailableRenderId();
        ExtraUtilsClient.spikeBlockID = RenderingRegistry.getNextAvailableRenderId();
        ExtraUtilsClient.drumRendererID = RenderingRegistry.getNextAvailableRenderId();
        ExtraUtilsClient.connectedTextureID = RenderingRegistry.getNextAvailableRenderId();
        ExtraUtilsClient.connectedTextureEtheralID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockColor());
        RenderingRegistry.registerBlockHandler(new RenderBlockFullBright());
        RenderingRegistry.registerBlockHandler(new RenderBlockMultiBlock());
        RenderingRegistry.registerBlockHandler(ExtraUtilsClient.renderBlockSpike);
        for (final Item item : ItemBlockSpike.itemHashSet) {
            MinecraftForgeClient.registerItemRenderer(item, ExtraUtilsClient.renderItemSpikeSword);
        }
        RenderingRegistry.registerBlockHandler(new RenderBlockDrum());
        RenderingRegistry.registerBlockHandler(new RenderBlockConnectedTextures());
        RenderingRegistry.registerBlockHandler(new RenderBlockConnectedTexturesEthereal());
        if (ExtraUtils.spikeGoldEnabled) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnchantedSpike.class, new RenderTileEntitySpike());
        }
        if (ExtraUtils.colorBlockDataEnabled) {
            for (final BlockColor b : ExtraUtils.colorblocks) {
                MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(b), ExtraUtilsClient.renderItemBlockColor);
            }
        }
        if (ExtraUtils.unstableIngot != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.unstableIngot, ExtraUtilsClient.renderItemUnstable);
        }
        if (ExtraUtils.erosionShovel != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.erosionShovel, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.destructionPickaxe != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.destructionPickaxe, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.buildersWand != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.buildersWand, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.ethericSword != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.ethericSword, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.healingAxe != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.healingAxe, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.creativeBuildersWand != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.creativeBuildersWand, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.precisionShears != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.precisionShears, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.temporalHoe != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.temporalHoe, ExtraUtilsClient.renderItemMultiTransparency);
        }
        if (ExtraUtils.drum != null) {
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ExtraUtils.drum), ExtraUtilsClient.renderItemDrum);
        }
        if (ExtraUtils.microBlocks != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.microBlocks, new RenderItemMicroblock());
        }
        if (ExtraUtils.lawSwordEnabled) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.lawSword, new RenderItemLawSword());
        }
        if (ExtraUtils.glove != null) {
            MinecraftForgeClient.registerItemRenderer(ExtraUtils.glove, RenderItemGlove.INSTANCE);
        }
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public PacketHandler getNewPacketHandler() {
        return new PacketHandlerClient();
    }

    @Override
    public void exectuteClientCode(final IClientCode clientCode) {
        clientCode.exectuteClientCode();
    }

    @Override
    public void sendUsePacket(final PlayerInteractEvent event) {
        if (event.world.isRemote) {
            final Vec3 hitVec = Minecraft.getMinecraft().objectMouseOver.hitVec;
            final float f = (float) hitVec.xCoord - event.x;
            final float f2 = (float) hitVec.yCoord - event.y;
            final float f3 = (float) hitVec.zCoord - event.z;
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(event.x, event.y, event.z, event.face, event.entityPlayer.inventory.getCurrentItem(), f, f2, f3));
        }
    }

    @Override
    public void sendUsePacket(final int x, final int y, final int z, final int face, final ItemStack item, final float hitX, final float hitY, final float hitZ) {
        if (this.isAltSneaking()) {
            this.sendAltUsePacket(x, y, z, face, item, hitX, hitY, hitZ);
        } else {
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(x, y, z, face, item, hitX, hitY, hitZ));
        }
    }

    @Override
    public void sendAltUsePacket(final int x, final int y, final int z, final int face, final ItemStack item, final float hitX, final float hitY, final float hitZ) {
        NetworkHandler.sendPacketToServer(new PacketUseItemAlt(x, y, z, face, item, hitX, hitY, hitZ));
    }

    @Override
    public void sendAltUsePacket(final ItemStack item) {
        this.sendAltUsePacket(-1, -1, -1, 255, item, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public boolean isAltSneaking() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            return KeyHandler.getIsKeyPressed(Minecraft.getMinecraft().gameSettings.keyBindSprint);
        }
        return super.isAltSneaking();
    }

    @Override
    public <F, T> T apply(final ISidedFunction<F, T> func, final F input) {
        return func.applyClient(input);
    }
}


