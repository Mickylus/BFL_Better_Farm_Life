package net.mickylus.bflife.networking;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record AnimalDataPayload(int hunger, String mood, float production, boolean wild, int baseMultiplier, boolean tracked , String father, String mother, int entityID) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<AnimalDataPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "animal_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AnimalDataPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, AnimalDataPayload::hunger,
            ByteBufCodecs.STRING_UTF8, AnimalDataPayload::mood,
            ByteBufCodecs.FLOAT, AnimalDataPayload::production,
            ByteBufCodecs.BOOL, AnimalDataPayload::wild,
            ByteBufCodecs.INT, AnimalDataPayload::baseMultiplier,
            ByteBufCodecs.BOOL, AnimalDataPayload::tracked,
            ByteBufCodecs.STRING_UTF8, AnimalDataPayload::father,
            ByteBufCodecs.STRING_UTF8, AnimalDataPayload::mother,
            ByteBufCodecs.INT, AnimalDataPayload::entityID,
            AnimalDataPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}