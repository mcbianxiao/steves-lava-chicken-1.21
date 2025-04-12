package cn.minebian.slc.item;

import cn.minebian.slc.Steveslavachicken;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Itemgroups {
    public static final ItemGroup CHICKEN_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(Steveslavachicken.MOD_ID, "chicken_group"),
            ItemGroup.create(null, -1).displayName(Text.translatable("itemGroup.chicken_group"))
                    .icon(() -> new ItemStack(Items.STLEVES_LAVA_CHICKEN)).entries((displayContext, entries) -> {
                        entries.add(Items.STLEVES_LAVA_CHICKEN);
                        entries.add(Items.STLEVES_LAVA_CHICKEN_HOT);
                    }).build());
    public static void registerModItemGroups() {
        Steveslavachicken.LOGGER.info("Registering Item Groups");
    }
}
