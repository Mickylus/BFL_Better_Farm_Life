package net.mickylus.bflife.screen.custom.registeritem;

import net.mickylus.bflife.screen.ModScreenHandlers;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class RegisterScreenHandler extends AbstractContainerMenu {

    private final Data data;

    private final int entityId;

    public RegisterScreenHandler(int syncId, Inventory playerInventory, Data data){
        super(ModScreenHandlers.REGISTER_SCREEN, syncId);
        this.data = data;
        this.entityId = data.entityId;
    }

    public Data getData() {
        return data;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        Entity entity = player.level().getEntity(entityId);
        if (entity == null) {
            return false;
        }
        return player.distanceToSqr(entity) < 64.0; // 8 Blocchi
    }

    public record Data(
            int entityId,
            int hunger,
            String mood,
            float production,
            boolean wild,
            String mother,
            String father
    ) {
        public static final StreamCodec<RegistryFriendlyByteBuf, RegisterScreenHandler.Data> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.INT,        Data::entityId,
                        ByteBufCodecs.INT,        Data::hunger,
                        ByteBufCodecs.STRING_UTF8, Data::mood,
                        ByteBufCodecs.FLOAT,      Data::production,
                        ByteBufCodecs.BOOL,       Data::wild,
                        ByteBufCodecs.STRING_UTF8, Data::mother,
                        ByteBufCodecs.STRING_UTF8, Data::father,
                        Data::new
                );
    }
}
