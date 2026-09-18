package top.mcmtr.mod.screen;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import top.mcmtr.mod.InitClient;
import top.mcmtr.mod.blocks.BlockCustomTextBase;
import top.mcmtr.mod.packet.MSDPacketUpdateCustomText;

public class CustomTextScreen extends ScreenExtension implements IGui {
    private final BlockPos blockPos;
    private final String[] messages;
    private final TextFieldWidgetExtension[] textFieldMessages;
    private final int maxArrivals;
    private final MutableText messageText = TextHelper.translatable("gui.msd.custom_text");
    private static final int MAX_MESSAGE_LENGTH = 2048;

    public CustomTextScreen(BlockPos blockPos, int maxArrivals) {
        this.blockPos = blockPos;
        this.maxArrivals = maxArrivals;
        this.messages = new String[maxArrivals];
        for (int i = 0; i < maxArrivals; i++) {
            messages[i] = "";
        }
        final ClientWorld clientWorld = MinecraftClient.getInstance().getWorldMapped();
        if (clientWorld != null) {
            final BlockEntity blockEntity = clientWorld.getBlockEntity(blockPos);
            if (blockEntity != null) {
                for (int i = 0; i < maxArrivals; i++) {
                    messages[i] = ((BlockCustomTextBase.BlockCustomTextEntity) blockEntity.data).getMessage(i);
                }
            }
        }
        this.textFieldMessages = new TextFieldWidgetExtension[maxArrivals];
        for (int i = 0; i < maxArrivals; i++) {
            textFieldMessages[i] = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, MAX_MESSAGE_LENGTH, TextCase.DEFAULT, null, "");
        }
    }

    @Override
    protected void init2() {
        super.init2();
        for (int i = 0; i < maxArrivals; i++) {
            int y = SQUARE_SIZE * (i + 2);
            final TextFieldWidgetExtension textFieldMessage = textFieldMessages[i];
            IDrawing.setPositionAndWidth(textFieldMessage, SQUARE_SIZE + TEXT_FIELD_PADDING / 2, y, width - SQUARE_SIZE);
            textFieldMessage.setText2(messages[i]);
            addChild(new ClickableWidget(textFieldMessage));
        }
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        renderBackground(graphicsHolder);
        graphicsHolder.drawText(messageText, SQUARE_SIZE, SQUARE_SIZE, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
        super.render(graphicsHolder, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }

    @Override
    public void onClose2() {
        for (int i = 0; i < maxArrivals; i++) {
            messages[i] = textFieldMessages[i].getText2();
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new MSDPacketUpdateCustomText(blockPos, messages));
        }
        super.onClose2();
    }
}