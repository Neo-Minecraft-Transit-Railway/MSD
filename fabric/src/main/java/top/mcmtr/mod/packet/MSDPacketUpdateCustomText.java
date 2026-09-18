package top.mcmtr.mod.packet;

import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;
import top.mcmtr.mod.blocks.BlockCustomTextBase;

public class MSDPacketUpdateCustomText extends PacketHandler {
    private static final int MAX_MESSAGES = 64;
    private final BlockPos blockPos;
    private final String[] messages;

    public MSDPacketUpdateCustomText(PacketBufferReceiver packetBufferReceiver) {
        this.blockPos = BlockPos.fromLong(packetBufferReceiver.readLong());
        final int length = packetBufferReceiver.readInt();
        if (length < 0 || length > MAX_MESSAGES) {
            throw new IllegalArgumentException("Invalid custom text line count: " + length);
        }
        this.messages = new String[length];
        for (int i = 0; i < length; i++) {
            messages[i] = packetBufferReceiver.readString();
        }
    }

    public MSDPacketUpdateCustomText(BlockPos blockPos, String[] messages) {
        if (messages.length > MAX_MESSAGES) {
            throw new IllegalArgumentException("Too many custom text lines: " + messages.length);
        }
        this.blockPos = blockPos;
        this.messages = messages.clone();
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeInt(messages.length);
        for (String message : messages) {
            packetBufferSender.writeString(message == null ? "" : message);
        }
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof BlockCustomTextBase.BlockCustomTextEntity) {
            ((BlockCustomTextBase.BlockCustomTextEntity) entity.data).setMessages(messages);
        }
    }
}
