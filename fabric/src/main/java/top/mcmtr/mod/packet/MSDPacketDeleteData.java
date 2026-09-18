package top.mcmtr.mod.packet;

import org.mtr.core.data.Position;
import org.mtr.core.serializer.JsonReader;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mod.Init;
import top.mcmtr.core.operation.MSDDeleteDataRequest;
import top.mcmtr.core.operation.MSDDeleteDataResponse;
import top.mcmtr.core.servlet.OperationType;
import top.mcmtr.mod.blocks.BlockNodeBase;
import top.mcmtr.mod.client.MSDMinecraftClientData;

import javax.annotation.Nonnull;

public final class MSDPacketDeleteData extends MSDPacketRequestResponseBase {
    public MSDPacketDeleteData(PacketBufferReceiver packetBufferReceiver) {
        super(packetBufferReceiver);
    }

    public MSDPacketDeleteData(MSDDeleteDataRequest deleteDataRequest) {
        super(Utilities.getJsonObjectFromData(deleteDataRequest).toString());
    }

    public MSDPacketDeleteData(String content) {
        super(content);
    }

    @Override
    protected void runServerInbound(ServerWorld serverWorld, JsonObject jsonObject) {
        new MSDDeleteDataResponse(new JsonReader(jsonObject)).iterateCatenaryNodePosition(catenaryNodePosition -> BlockNodeBase.resetCatenaryNode(serverWorld, Init.positionToBlockPos(catenaryNodePosition)));
    }

    @Override
    protected void runClientInbound(JsonReader jsonReader) {
        final MSDDeleteDataResponse deleteDataResponse = new MSDDeleteDataResponse(jsonReader);
        deleteDataResponse.write(MSDMinecraftClientData.getInstance());
    }

    @Override
    protected MSDPacketRequestResponseBase getInstance(String content) {
        return new MSDPacketDeleteData(content);
    }

    @Override
    protected SerializedDataBase getDataInstance(JsonReader jsonReader) {
        return new MSDDeleteDataRequest(jsonReader);
    }

    @Nonnull
    @Override
    protected String getKey() {
        return OperationType.DELETE_DATA;
    }

    @Override
    protected ResponseType responseType() {
        return ResponseType.ALL;
    }

    public static void sendDirectlyToServerCatenaryNodePosition(ServerWorld serverWorld, Position catenaryNodePosition) {
        new MSDPacketDeleteData(new MSDDeleteDataRequest().addCatenaryNodePosition(catenaryNodePosition)).runServerOutbound(serverWorld, null);
    }

    public static void sendDirectlyToServerCatenaryId(ServerWorld serverWorld, String catenaryId) {
        new MSDPacketDeleteData(new MSDDeleteDataRequest().addCatenaryId(catenaryId)).runServerOutbound(serverWorld, null);
    }

    public static void sendDirectlyToServerRigidCatenaryId(ServerWorld serverWorld, String rigidCatenaryId) {
        new MSDPacketDeleteData(new MSDDeleteDataRequest().addRigidCatenaryId(rigidCatenaryId)).runServerOutbound(serverWorld, null);
    }
}