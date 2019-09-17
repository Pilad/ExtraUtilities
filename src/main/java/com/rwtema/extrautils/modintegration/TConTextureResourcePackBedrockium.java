// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.modintegration;

import com.google.common.base.Throwables;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TConTextureResourcePackBedrockium extends TConTextureResourcePackBase {
    static BufferedImage bedrockImage;

    static {
        TConTextureResourcePackBedrockium.bedrockImage = null;
    }

    public TConTextureResourcePackBedrockium(final String name) {
        super(name);
    }

    public static BufferedImage getBedrockImage() {
        if (TConTextureResourcePackBedrockium.bedrockImage == null) {
            final ResourceLocation bedrockLocation = new ResourceLocation("minecraft", "textures/blocks/bedrock.png");
            try {
                final DefaultResourcePack mcDefaultResourcePack = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), new String[]{"field_110450_ap", "mcDefaultResourcePack"});
                InputStream inputStream = mcDefaultResourcePack.getInputStream(bedrockLocation);
                final List<ResourcePackRepository.Entry> t = (List<ResourcePackRepository.Entry>) Minecraft.getMinecraft().getResourcePackRepository().getRepositoryEntries();
                for (final ResourcePackRepository.Entry entry : t) {
                    final IResourcePack resourcePack = entry.getResourcePack();
                    if (resourcePack.resourceExists(bedrockLocation)) {
                        inputStream = resourcePack.getInputStream(bedrockLocation);
                    }
                }
                TConTextureResourcePackBedrockium.bedrockImage = ImageIO.read(inputStream);
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        }
        return TConTextureResourcePackBedrockium.bedrockImage;
    }

    @Override
    public BufferedImage modifyImage(final BufferedImage image) {
        final BufferedImage bedrockImage = getBedrockImage();
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                final int c = image.getRGB(x, y);
                if (c == 0 || TConTextureResourcePackBedrockium.rgb.getAlpha(c) < 16) {
                    image.setRGB(x, y, 0);
                } else {
                    final float b = this.brightness(c) / 255.0f;
                    final int dx = x * bedrockImage.getWidth() / image.getWidth();
                    final int dy = y * bedrockImage.getHeight() / image.getHeight();
                    final int col = bedrockImage.getRGB(dx, dy);
                    image.setRGB(x, y, TConTextureResourcePackBedrockium.rgb.getAlpha(c) << 24 | (int) (TConTextureResourcePackBedrockium.rgb.getRed(col) * b) << 16 | (int) (TConTextureResourcePackBedrockium.rgb.getGreen(col) * b) << 8 | (int) (TConTextureResourcePackBedrockium.rgb.getBlue(col) * b));
                }
            }
        }
        return image;
    }

    @Override
    public void onResourceManagerReload(final IResourceManager p_110549_1_) {
        super.onResourceManagerReload(p_110549_1_);
        TConTextureResourcePackBedrockium.bedrockImage = null;
    }
}


