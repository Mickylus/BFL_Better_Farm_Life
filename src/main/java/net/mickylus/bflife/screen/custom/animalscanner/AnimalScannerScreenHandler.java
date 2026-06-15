package net.mickylus.bflife.screen.custom.animalscanner;

import net.mickylus.bflife.screen.ModScreenHandlers;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class AnimalScannerScreenHandler extends AbstractContainerMenu {

    private final Data data;

    // Costruttore lato SERVER — aperto da NetworkHooks.openScreen
    public AnimalScannerScreenHandler(int syncId, Inventory playerInventory, Data data) {
        super(ModScreenHandlers.ANIMAL_SCANNER, syncId);
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    /**
     * Dati trasmessi dal server al client all'apertura dello screen.
     * Aggiungi qui tutti i campi che ti servono per gli screen futuri.
     */
    public record Data(
            int entityId,
            int hunger,
            String mood,
            float production,
            boolean wild,
            String mother,
            String father
    ) {
        public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC =
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
