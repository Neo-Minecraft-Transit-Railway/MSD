package top.mcmtr.mod.packet;

import org.mtr.core.serializer.JsonReader;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.tool.PacketBufferReceiver;
import top.mcmtr.core.operation.MSDDataRequest;
import top.mcmtr.core.operation.MSDDataResponse;
import top.mcmtr.core.servlet.OperationType;
import top.mcmtr.mod.client.MSDMinecraftClientData;

import javax.annotation.Nonnull;

public final class MSDPacketRequestData extends MSDPacketRequestResponseBase {

    public MSDPacketRequestData(PacketBufferReceiver packetBufferReceiver) {
        super(packetBufferReceiver);
    }

    public MSDPacketRequestData(MSDDataRequest dataRequest) {
        super(Utilities.getJsonObjectFromData(dataRequest).toString());
    }

    public MSDPacketRequestData(String content) {
        super(content);
    }

    @Override
    protected void runServerInbound(ServerWorld serverWorld, JsonObject jsonObject) {
    }

    @Override
    protected void runClientInbound(JsonReader jsonReader) {
        new MSDDataResponse(jsonReader, MSDMinecraftClientData.getInstance()).write();
    }

    @Override
    protected MSDPacketRequestResponseBase getInstance(String content) {
        return new MSDPacketRequestData(content);
    }

    @Override
    protected SerializedDataBase getDataInstance(JsonReader jsonReader) {
        return new MSDDataRequest(jsonReader);
    }

    @Nonnull
    @Override
    protected String getKey() {
        return OperationType.GET_DATA;
    }

    @Override
    protected ResponseType responseType() {
        return ResponseType.PLAYER;
    }
}