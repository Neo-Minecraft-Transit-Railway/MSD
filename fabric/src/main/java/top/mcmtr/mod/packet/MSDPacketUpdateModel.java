package top.mcmtr.mod.packet;

import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;
import top.mcmtr.core.data.OffsetPosition;
import top.mcmtr.mod.blocks.BlockCatenaryWithModel;

public final class MSDPacketUpdateModel extends PacketHandler {
    private final BlockPos blockPos;
    private final OffsetPosition offsetPosition;
    private final OffsetPosition rotationPosition;

    public MSDPacketUpdateModel(BlockPos blockPos, OffsetPosition offsetPosition, OffsetPosition rotationPosition) {
        this.blockPos = blockPos;
        this.offsetPosition = offsetPosition;
        this.rotationPosition = rotationPosition;
    }

    public MSDPacketUpdateModel(PacketBufferReceiver puf) {
        this.offsetPosition = new OffsetPosition(0, 0, 0);
        this.rotationPosition = new OffsetPosition(0, 0, 0);
        this.blockPos = BlockPos.fromLong(puf.readLong());
        this.offsetPosition.setX(puf.readDouble());
        this.offsetPosition.setY(puf.readDouble());
        this.offsetPosition.setZ(puf.readDouble());
        this.rotationPosition.setX(puf.readDouble());
        this.rotationPosition.setY(puf.readDouble());
        this.rotationPosition.setZ(puf.readDouble());
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeDouble(offsetPosition.getX());
        packetBufferSender.writeDouble(offsetPosition.getY());
        packetBufferSender.writeDouble(offsetPosition.getZ());
        packetBufferSender.writeDouble(rotationPosition.getX());
        packetBufferSender.writeDouble(rotationPosition.getY());
        packetBufferSender.writeDouble(rotationPosition.getZ());
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        BlockEntity blockEntity = serverPlayerEntity.getEntityWorld().getBlockEntity(this.blockPos);
        if (blockEntity != null && blockEntity.data instanceof BlockCatenaryWithModel.BlockCatenaryWithModelEntity) {
            ((BlockCatenaryWithModel.BlockCatenaryWithModelEntity) blockEntity.data).setOffsetPosition(this.offsetPosition, this.rotationPosition);
        }
    }
}