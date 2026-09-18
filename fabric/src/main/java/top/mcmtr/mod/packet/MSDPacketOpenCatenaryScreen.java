package top.mcmtr.mod.packet;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class MSDPacketOpenCatenaryScreen extends PacketHandler {
    private final BlockPos blockPos;
    private final boolean isConnected;

    public MSDPacketOpenCatenaryScreen(PacketBufferReceiver packetBufferReceiver) {
        this.blockPos = BlockPos.fromLong(packetBufferReceiver.readLong());
        this.isConnected = packetBufferReceiver.readBoolean();
    }

    public MSDPacketOpenCatenaryScreen(BlockPos blockPos, boolean isConnected) {
        this.blockPos = blockPos;
        this.isConnected = isConnected;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeBoolean(isConnected);
    }

    @Override
    public void runClient() {
        MSDClientPacketHelper.openCatenaryScreen(isConnected, blockPos);
    }
}