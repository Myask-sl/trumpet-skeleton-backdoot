package com.jamieswhiteshirt.trumpetskeleton.common.entity;

import com.jamieswhiteshirt.trumpetskeleton.TrumpetSkeleton;
import com.jamieswhiteshirt.trumpetskeleton.common.TrumpetSkeletonItems;
import com.jamieswhiteshirt.trumpetskeleton.common.TrumpetSkeletonSoundEvents;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

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
        if (SWINGING_ARMS != null) {
            boolean isSwingingArms = this.dataManager.get(SWINGING_ARMS);
            if (isSwingingArms) {
                TrumpetSkeleton.scare(world, this);
            }
        }
    }
}
