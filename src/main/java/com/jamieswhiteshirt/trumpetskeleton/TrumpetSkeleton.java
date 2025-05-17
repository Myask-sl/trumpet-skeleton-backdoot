package com.jamieswhiteshirt.trumpetskeleton;

import com.jamieswhiteshirt.trumpetskeleton.common.CommonProxy;
import com.jamieswhiteshirt.trumpetskeleton.common.TrumpetSkeletonItems;
import com.jamieswhiteshirt.trumpetskeleton.common.entity.EntityTrumpetSkeleton;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(
    modid = Tags.MODID,
    version = TrumpetSkeleton.VERSION,
    acceptedMinecraftVersions = "[" + Tags.MINECRAFT_VERSION + "]",
    name = Tags.MODNAME
)
public class TrumpetSkeleton {
    public static final String MODID = Tags.MODID;
    public static final String VERSION = "1.7.10-0.1"; //"1.12-1.0.2.1";

    //public static final ResourceLocation ENTITIES_TRUMPET_SKELETON_LOOT_TABLE = new ResourceLocation(MODID, "entities/trumpet_skeleton");


    @Mod.Instance
    public static TrumpetSkeleton instance;
    @SidedProxy(
        clientSide = "com.jamieswhiteshirt.trumpetskeleton.client.ClientProxy",
        serverSide = "com.jamieswhiteshirt.trumpetskeleton.server.ServerProxy",
        modId = MODID
    )
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        registerItems();
        registerEntityEntries();
//        LootTableList.register(ENTITIES_TRUMPET_SKELETON_LOOT_TABLE);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
//        EntityParrot.registerMimicSound(EntityTrumpetSkeleton.class, TrumpetSkeletonSoundEvents.E_PARROT_IM_TRUMPET_SKELETON);
        //no parrots to register for...yet.
        setSpawnLocations();
    }

//    @SubscribeEvent
    public void registerItems() { //RegistryEvent.Register<Item> event) {
        GameRegistry.registerItem(TrumpetSkeletonItems.TRUMPET, "trumpet", MODID);
    }

//    @SubscribeEvent
    public void registerEntityEntries() {//RegistryEvent.Register<EntityEntry> event) {
        EntityRegistry.registerModEntity(
            EntityTrumpetSkeleton.class,
            "trumpetskeleton.TrumpetSkeleton",
            0,
            this,
            80,
            3,
            false
        );
        EntityList.addMapping(EntityTrumpetSkeleton.class,
            "trumpetskeleton.TrumpetSkeleton",
            EntityTrumpetSkeleton.SPAWN_ID,
            0xC1C1C1, 0xFCFC00);
    }

    public void setSpawnLocations() {
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
            if (biome == null) continue;
            for (BiomeGenBase.SpawnListEntry entry : new ArrayList<>(biome.getSpawnableList(EnumCreatureType.monster))) {
                if (entry.entityClass == EntitySkeleton.class) {
                    EntityRegistry.addSpawn(EntityTrumpetSkeleton.class, entry.itemWeight / 4, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.monster, biome);
                }
            }
        }
    }


/*    @SubscribeEvent
    public void onActiveItemUseTick(LivingEntityUseItemEvent.Tick event) {
        ItemStack stack = event.getItem();
        if (stack.getItem() == TrumpetSkeletonItems.TRUMPET) {
            if (event.getDuration() == stack.getMaxItemUseDuration() - 10) {
                EntityLivingBase user = event.getEntityLiving();
                World world = user.world;
                user.playSound(TrumpetSkeletonSoundEvents.ITEM_TRUMPET_USE, 1.0F, 0.9F + world.rand.nextFloat() * 0.2F);
                scare(world, user);
                stack.damageItem(1, user);
            } else if (event.getDuration() <= stack.getMaxItemUseDuration() - 15) {
                event.setCanceled(true);
            }
        }
    }*/

    @SubscribeEvent
    public void onPlaySoundAtEntity(PlaySoundAtEntityEvent event) {
        if (event.entity instanceof EntityPlayer player) {
            if (player.getItemInUse() != null &&
                player.getItemInUse().getItem() == TrumpetSkeletonItems.TRUMPET) {
                if ("random.eat".equals(event.name) || "random.burp".equals(event.name)) {
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void scare(World world, EntityLivingBase user) {
        if (!world.isRemote) {
            List<EntityLivingBase> spookedEntities = world.getEntitiesWithinAABB(EntityLivingBase.class, user.getBoundingBox().expand(10.0D, 10.0D, 10.0D));
            for (EntityLivingBase spookedEntity : spookedEntities) {
                if (spookedEntity == user) continue;
                double deltaX = spookedEntity.posX - user.posX + world.rand.nextDouble() - world.rand.nextDouble();
                double deltaZ = spookedEntity.posZ - user.posZ + world.rand.nextDouble() - world.rand.nextDouble();
                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                spookedEntity.velocityChanged = true;
                spookedEntity.addVelocity(0.5 * deltaX / distance, 5.0D / (10.0D + distance), 0.5 * deltaZ / distance);
                spookedEntity.setRevengeTarget(user);
            }
        }
    }
}
