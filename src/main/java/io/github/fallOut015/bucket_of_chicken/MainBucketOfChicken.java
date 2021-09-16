package io.github.fallOut015.bucket_of_chicken;

import io.github.fallOut015.bucket_of_chicken.world.item.ItemsBucketOfChicken;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MainBucketOfChicken.MODID)
public class MainBucketOfChicken {
    public static final String MODID = "bucket_of_chicken";

    public MainBucketOfChicken() {
        ItemsBucketOfChicken.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onEntityInteract(final PlayerInteractEvent.EntityInteract entityInteractEvent) {
            boolean isChicken = entityInteractEvent.getTarget().getType().equals(EntityType.CHICKEN);
            boolean isHoldingBucket = entityInteractEvent.getPlayer().getItemInHand(entityInteractEvent.getHand()).is(Items.BUCKET);

            if(isChicken && isHoldingBucket) {
                entityInteractEvent.getPlayer().setItemInHand(entityInteractEvent.getHand(), new ItemStack(ItemsBucketOfChicken.BUCKET_OF_CHICKEN.get()));
                entityInteractEvent.getTarget().discard();
            }
        }
    }
}