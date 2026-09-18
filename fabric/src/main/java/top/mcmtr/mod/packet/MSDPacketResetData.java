package top.mcmtr.mod.packet;

import org.mtr.core.serializer.JsonReader;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.tool.PacketBufferReceiver;
import top.mcmtr.core.operation.MSDResetDataRequest;
import top.mcmtr.core.operation.MSDResetDataResponse;
import top.mcmtr.core.servlet.OperationType;
import top.mcmtr.mod.client.MSDMinecraftClientData;

import javax.annotation.Nonnull;

public final class MSDPacketResetData extends MSDPacketRequestResponseBase {
    public MSDPacketResetData(PacketBufferReceiver packetBufferReceiver) {
        super(packetBufferReceiver);
    }

    public MSDPacketResetData(MSDResetDataRequest resetDataRequest) {
        super(Utilities.getJsonObjectFromData(resetDataRequest).toString());
    }

    public MSDPacketResetData(String content) {
        super(content);
    }

    @Override
    protected void runServerInbound(ServerWorld serverWorld, JsonObject jsonObject) {
    }

    @Override
    protected void runClientInbound(JsonReader jsonReader) {
        new MSDResetDataResponse(jsonReader, MSDMinecraftClientData.getInstance()).write();
    }

    @Override
    protected MSDPacketRequestResponseBase getInstance(String content) {
        return new MSDPacketResetData(content);
    }

    @Override
    protected SerializedDataBase getDataInstance(JsonReader jsonReader) {
        return new MSDResetDataRequest(jsonReader, new MSDMinecraftClientData());
    }

    @Nonnull
    @Override
    protected String getKey() {
        return OperationType.RESET_DATA;
    }

    @Override
    protected ResponseType responseType() {
        return ResponseType.ALL;
    }
}