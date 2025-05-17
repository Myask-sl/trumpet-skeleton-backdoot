package com.jamieswhiteshirt.trumpetskeleton.client.renderer.entity;

import com.jamieswhiteshirt.trumpetskeleton.client.model.ModelTrumpetSkeleton;
import com.jamieswhiteshirt.trumpetskeleton.common.entity.EntityTrumpetSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderTrumpetSkeleton extends RenderBiped {
    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public RenderTrumpetSkeleton() {
        super(new ModelTrumpetSkeleton(), 0.5F);
    }
    public RenderTrumpetSkeleton(RenderManager renderManager) {
        super(new ModelTrumpetSkeleton(), 0.5F);
        this.renderManager = renderManager;
 //TODO:       this.addLayer(new LayerHeldItem(this));
//TODO:        this.addLayer(new LayerBipedArmor(this)
    }

    public void transformHeldFull3DItemLayer()
    {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return SKELETON_TEXTURES;
    }
}
