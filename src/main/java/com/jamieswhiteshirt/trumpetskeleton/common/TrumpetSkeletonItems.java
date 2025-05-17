package com.jamieswhiteshirt.trumpetskeleton.common;

import com.jamieswhiteshirt.trumpetskeleton.TrumpetSkeleton;
import com.jamieswhiteshirt.trumpetskeleton.common.item.ItemTrumpet;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@GameRegistry.ObjectHolder(TrumpetSkeleton.MODID)
public class TrumpetSkeletonItems {
    public static final Item TRUMPET = new ItemTrumpet().setUnlocalizedName("trumpetskeleton.trumpet").setCreativeTab(CreativeTabs.tabMisc);
}
