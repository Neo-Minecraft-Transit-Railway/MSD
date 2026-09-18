package top.mcmtr.mod.packet;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class MSDPacketOpenCatenaryWithModelScreen extends PacketHandler {
    private final BlockPos blockPos;
    private final boolean isConnected;

    public MSDPacketOpenCatenaryWithModelScreen(BlockPos blockPos, boolean isConnected) {
        this.blockPos = blockPos;
        this.isConnected = isConnected;
    }

    public MSDPacketOpenCatenaryWithModelScreen(PacketBufferReceiver puf) {
        this.blockPos = BlockPos.fromLong(puf.readLong());
        this.isConnected = puf.readBoolean();
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(this.blockPos.asLong());
        packetBufferSender.writeBoolean(this.isConnected);
    }

    @Override
    public void runClient() {
        MSDClientPacketHelper.openCatenaryWithModelScreen(this.blockPos, this.isConnected);
    }
}