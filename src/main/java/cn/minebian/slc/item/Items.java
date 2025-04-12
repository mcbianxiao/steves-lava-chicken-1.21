package cn.minebian.slc.item;

import cn.minebian.slc.component.type.FoodComponents;
import cn.minebian.slc.Steveslavachicken;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;


public class Items {
    public static final net.minecraft.item.Item STLEVES_LAVA_CHICKEN = registerItems("steves_lava_chicken", new net.minecraft.item.Item(new net.minecraft.item.Item.Settings().food(FoodComponents.STLEVES_LAVA_CHICKEN)));
    public static final net.minecraft.item.Item STLEVES_LAVA_CHICKEN_HOT = registerItems("steves_lava_chicken_hot", new net.minecraft.item.Item(new net.minecraft.item.Item.Settings().food(FoodComponents.STLEVES_LAVA_CHICKEN_HOT)));

    private static Item registerItems(String name, Item item) {
        // 由原版整合的方法
//        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(ReTutorial.MOD_ID, name)), item);
        // 采用register的另一个方法
        return Registry.register(Registries.ITEM, Identifier.of(Steveslavachicken.MOD_ID, name), item);
    }
    // 初始化方法
    public static void registerModItems() {
        Steveslavachicken.LOGGER.info("Registering Items");
    }

    //  事件监听器
    public static class Hot_Chicken_Events {
        public static void register() {
            // 使用通用物品使用事件
            UseItemCallback.EVENT.register((player, world, hand) -> {
                ItemStack stack = player.getStackInHand(hand);

                // 检测是否为苹果
                if (stack.getItem() == Items.STLEVES_LAVA_CHICKEN_HOT && !world.isClient()) {
                    // 设置着火时间（10秒）
                    player.setFireTicks(20 * 10);

                    // 播放音效（修正参数）
                    world.playSound(
                            null, // 无特定播放源
                            player.getX(), // 使用实体坐标
                            player.getY(),
                            player.getZ(),
                            SoundEvents.BLOCK_LAVA_EXTINGUISH,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F + (world.random.nextFloat() - 0.5F) * 0.2F
                    );
                }
                return TypedActionResult.pass(stack);
            });
        }
    }

}
