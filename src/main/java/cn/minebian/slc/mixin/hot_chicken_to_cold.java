package cn.minebian.slc.mixin;

import cn.minebian.slc.item.Items;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class hot_chicken_to_cold {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        ItemEntity self = (ItemEntity)(Object)this;
        World world = self.getWorld();

        // 仅在服务端执行且物品未被标记为移除
        if (!world.isClient && !self.isRemoved()) {
            ItemStack stack = self.getStack();

            // 检查是否为物品A
            if (stack.isOf(Items.STLEVES_LAVA_CHICKEN_HOT)) {
                BlockPos pos = self.getBlockPos();
                FluidState fluidState = world.getFluidState(pos);

                if (fluidState.isIn(FluidTags.WATER)) {
                    // 播放音效（服务端会自动同步到客户端）
                    world.playSound(
                            null, // 无特定播放源
                            pos, // 使用方块位置
                            SoundEvents.BLOCK_LAVA_EXTINGUISH, // 音效类型
                            SoundCategory.BLOCKS, // 音效分类
                            1.0F, // 音量
                            (world.random.nextFloat() * 0.4F) + 0.8F // 随机音调
                    );
                }

                // 检测是否接触水
                if (fluidState.isIn(FluidTags.WATER)) {
                    // 创建新物品实体
                    ItemStack newStack = new ItemStack(
                            Items.STLEVES_LAVA_CHICKEN,
                            stack.getCount()
                           // stack.getNbt() // 保留原有NBT数据
                    );

                    ItemEntity newEntity = new ItemEntity(
                            world,
                            self.getX(),
                            self.getY(),
                            self.getZ(),
                            newStack
                    );

                    // 继承原有运动状态
                    newEntity.setVelocity(self.getVelocity());
                    newEntity.setPickupDelay(40); // 防止立即捡起

                    // 生成新实体并移除旧实体
                    world.spawnEntity(newEntity);
                    self.discard();
                }
            }
        }
    }
}