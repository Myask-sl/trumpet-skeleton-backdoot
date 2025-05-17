package com.jamieswhiteshirt.trumpetskeleton.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.jamieswhiteshirt.trumpetskeleton.TrumpetSkeleton;

public class ItemTrumpet extends Item {
    public ItemTrumpet() {
        maxStackSize = 1;
        setMaxDamage(200);
        setTextureName("trumpet");
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 55;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        if (player.getItemInUseDuration() == stack.getMaxItemUseDuration() - 10) { //move out of event
            player.playSound(TrumpetSkeletonSoundEvents.ITEM_TRUMPET_USE, 1.0F, 0.9F + worldIn.rand.nextFloat() * 0.2F);
            TrumpetSkeleton.scare(worldIn, player);
            stack.damageItem(1, player);
        }
        return super.onItemRightClick(stack, worldIn, player);
    }


}
