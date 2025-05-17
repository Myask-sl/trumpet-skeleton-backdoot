package com.jamieswhiteshirt.trumpetskeleton.common.entity;

import com.jamieswhiteshirt.trumpetskeleton.TrumpetSkeleton;
import com.jamieswhiteshirt.trumpetskeleton.common.TrumpetSkeletonItems;
import com.jamieswhiteshirt.trumpetskeleton.common.TrumpetSkeletonSoundEvents;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class EntityTrumpetSkeleton extends EntitySkeleton {
    public static final int SPAWN_ID = 254;
 /*   private static final DataParameter<Boolean> SWINGING_ARMS;

    static {
        Field swingingArmsField = ReflectionHelper.findField(AbstractSkeleton.class, "field_184728_b", "SWINGING_ARMS");
        DataParameter<Boolean> swingingArms = null;
        try {
            swingingArms = (DataParameter<Boolean>) swingingArmsField.get(null);
        } catch (IllegalAccessException | ClassCastException e) {
        	TrumpetSkeleton.logger.error("Could not access SWINGING_ARMS data field", e);
        }
        SWINGING_ARMS = swingingArms;
    }*/

    public EntityTrumpetSkeleton(World worldIn) {

        super(worldIn);
        }

    @Override
    protected String getLivingSound() {
        return TrumpetSkeletonSoundEvents.ENTITY_TRUMPET_SKELETON_AMBIENT;
    }

    @Override
    protected void addRandomArmor() {
        super.addRandomArmor();
        setCurrentItemOrArmor(0, new ItemStack(TrumpetSkeletonItems.TRUMPET));
    }

    @Override
    public void playLivingSound() {
        super.playLivingSound();
        if (isSwingInProgress) {
            TrumpetSkeleton.scare(this.worldObj, this);
        }
    }
}
