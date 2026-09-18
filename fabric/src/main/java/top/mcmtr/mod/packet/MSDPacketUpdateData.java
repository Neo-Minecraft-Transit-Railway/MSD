package top.mcmtr.mod.packet;

import org.mtr.core.serializer.JsonReader;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.tool.PacketBufferReceiver;
import top.mcmtr.core.data.Catenary;
import top.mcmtr.core.data.RigidCatenary;
import top.mcmtr.core.operation.MSDUpdateDataRequest;
import top.mcmtr.core.operation.MSDUpdateDataResponse;
import top.mcmtr.core.servlet.OperationType;
import top.mcmtr.mod.client.MSDMinecraftClientData;

import javax.annotation.Nonnull;

public final class MSDPacketUpdateData extends MSDPacketRequestResponseBase {
    public MSDPacketUpdateData(PacketBufferReceiver packetBufferReceiver) {
        super(packetBufferReceiver);
    }

    public MSDPacketUpdateData(MSDUpdateDataRequest updateDataRequest) {
        super(Utilities.getJsonObjectFromData(updateDataRequest).toString());
    }

    public MSDPacketUpdateData(String content) {
        super(content);
    }

    @Override
    protected void runServerInbound(ServerWorld serverWorld, JsonObject jsonObject) {
    }

    @Override
    protected void runClientInbound(JsonReader jsonReader) {
        new MSDUpdateDataResponse(jsonReader, MSDMinecraftClientData.getInstance()).write();
    }

    @Override
    protected MSDPacketRequestResponseBase getInstance(String content) {
        return new MSDPacketUpdateData(content);
    }

    @Override
    protected SerializedDataBase getDataInstance(JsonReader jsonReader) {
        return new MSDUpdateDataRequest(jsonReader, new MSDMinecraftClientData());
    }

    @Nonnull
    @Override
    protected String getKey() {
        return OperationType.UPDATE_DATA;
    }

    @Override
    protected ResponseType responseType() {
        return ResponseType.ALL;
    }

    public static void sendDirectlyToServerCatenary(ServerWorld serverWorld, Catenary catenary) {
        new MSDPacketUpdateData(new MSDUpdateDataRequest(new MSDMinecraftClientData()).addCatenary(catenary)).runServerOutbound(serverWorld, null);
    }

    public static void sendDirectlyToServerRigidCatenary(ServerWorld serverWorld, RigidCatenary rigidCatenary) {
        new MSDPacketUpdateData(new MSDUpdateDataRequest(new MSDMinecraftClientData()).addRigidCatenary(rigidCatenary)).runServerOutbound(serverWorld, null);
    }
}