package net.mickylus.bflife.networking;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record AnimalDataPayload(int hunger, String mood, float production, boolean wild) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<AnimalDataPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "animal_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AnimalDataPayload> CODEC = StreamCodec.composite(
            net.minecraft.network.codec.ByteBufCodecs.INT, AnimalDataPayload::hunger,
            net.minecraft.network.codec.ByteBufCodecs.STRING_UTF8, AnimalDataPayload::mood,
            net.minecraft.network.codec.ByteBufCodecs.FLOAT, AnimalDataPayload::production,
            net.minecraft.network.codec.ByteBufCodecs.BOOL, AnimalDataPayload::wild,
            AnimalDataPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}