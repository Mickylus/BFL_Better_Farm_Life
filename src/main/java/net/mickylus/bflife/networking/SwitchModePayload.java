package net.mickylus.bflife.networking;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SwitchModePayload() implements CustomPacketPayload {

    public static final Type<SwitchModePayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "switch_mode"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchModePayload> CODEC =
            StreamCodec.unit(new SwitchModePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}