package cn.minebian.slc.component.type;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FoodComponents {
    public static final FoodComponent STLEVES_LAVA_CHICKEN = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F)
            .build();
    public static final FoodComponent STLEVES_LAVA_CHICKEN_HOT = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(1.2F)
            .build();
}
