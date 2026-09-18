package top.mcmtr.mod.screen;

import org.mtr.core.data.Position;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import top.mcmtr.core.data.Catenary;
import top.mcmtr.core.data.OffsetPosition;
import top.mcmtr.core.operation.MSDResetDataRequest;
import top.mcmtr.mod.InitClient;
import top.mcmtr.mod.blocks.BlockCatenaryWithModel;
import top.mcmtr.mod.client.MSDMinecraftClientData;
import top.mcmtr.mod.packet.MSDPacketResetData;
import top.mcmtr.mod.packet.MSDPacketUpdateModel;

import java.util.Objects;

public final class CatenaryWithModelScreen extends ScreenExtension implements IGui {
    private final TextFieldWidgetExtension textFieldTranslateX;
    private final TextFieldWidgetExtension textFieldTranslateY;
    private final TextFieldWidgetExtension textFieldTranslateZ;
    private final TextFieldWidgetExtension textFieldRotateY;

    private static final MutableText MODEL_TRANSLATION_TEXT = TextHelper.translatable("gui.mtr.model_translation");
    private static final MutableText MODEL_ROTATION_TEXT = TextHelper.translatable("gui.mtr.model_rotation");
    private static final MutableText X_TEXT = TextHelper.literal("X");
    private static final MutableText Y_TEXT = TextHelper.literal("Y");
    private static final MutableText Z_TEXT = TextHelper.literal("Z");
    private static final int MAX_NUMBER_TEXT_LENGTH = 10;

    private final BlockPos blockPos;
    private final BlockCatenaryWithModel.BlockCatenaryWithModelEntity blockEntity;
    private final boolean isConnected;
    private final int xStart;
    private final double offsetXDefault;
    private final double offsetYDefault;
    private final double offsetZDefault;
    private final double rotationYDefault;

    public CatenaryWithModelScreen(BlockPos blockPos, boolean isConnected) {
        ClientWorld world = MinecraftClient.getInstance().getWorldMapped();
        if (world != null && world.getBlockEntity(blockPos) != null) {
            this.blockPos = blockPos;
            this.isConnected = isConnected;
            this.blockEntity = (BlockCatenaryWithModel.BlockCatenaryWithModelEntity) Objects.requireNonNull(world.getBlockEntity(blockPos)).data;
            this.offsetXDefault = blockEntity.getOffsetPosition().getX();
            this.offsetYDefault = blockEntity.getOffsetPosition().getY();
            this.offsetZDefault = blockEntity.getOffsetPosition().getZ();
            this.rotationYDefault = blockEntity.getRotation().getY();
            this.textFieldTranslateX = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, MAX_NUMBER_TEXT_LENGTH, TextCase.DEFAULT, "[^\\d.-]", null);
            this.textFieldTranslateX.setChangedListener2(text -> updateClient());
            this.textFieldTranslateY = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, MAX_NUMBER_TEXT_LENGTH, TextCase.DEFAULT, "[^\\d.-]", null);
            this.textFieldTranslateY.setChangedListener2(text -> updateClient());
            this.textFieldTranslateZ = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, MAX_NUMBER_TEXT_LENGTH, TextCase.DEFAULT, "[^\\d.-]", null);
            this.textFieldTranslateZ.setChangedListener2(text -> updateClient());
            this.textFieldRotateY = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, MAX_NUMBER_TEXT_LENGTH, TextCase.DEFAULT, "[^\\d.]", null);
            this.textFieldRotateY.setChangedListener2(text -> updateClient());
            this.xStart = Math.max(GraphicsHolder.getTextWidth(X_TEXT), Math.max(GraphicsHolder.getTextWidth(Y_TEXT), GraphicsHolder.getTextWidth(Z_TEXT)));
        } else {
            this.blockPos = null;
            this.blockEntity = null;
            this.xStart = 0;
            this.textFieldTranslateX = null;
            this.textFieldTranslateY = null;
            this.textFieldTranslateZ = null;
            this.textFieldRotateY = null;
            this.offsetXDefault = 0;
            this.offsetYDefault = 0;
            this.offsetZDefault = 0;
            this.rotationYDefault = 0;
            this.isConnected = false;
        }
    }

    @Override
    protected void init2() {
        if (blockEntity != null) {
            IDrawing.setPositionAndWidth(textFieldTranslateX, SQUARE_SIZE + xStart + TEXT_PADDING, SQUARE_SIZE * 3 + TEXT_FIELD_PADDING / 2, SQUARE_SIZE * 4);
            IDrawing.setPositionAndWidth(textFieldTranslateY, SQUARE_SIZE + xStart + TEXT_PADDING, SQUARE_SIZE * 4 + TEXT_FIELD_PADDING * 3 / 2, SQUARE_SIZE * 4);
            IDrawing.setPositionAndWidth(textFieldTranslateZ, SQUARE_SIZE + xStart + TEXT_PADDING, SQUARE_SIZE * 5 + TEXT_FIELD_PADDING * 5 / 2, SQUARE_SIZE * 4);
            IDrawing.setPositionAndWidth(textFieldRotateY, width / 2 + xStart + TEXT_PADDING, SQUARE_SIZE * 3 + TEXT_FIELD_PADDING * 3 / 2, SQUARE_SIZE * 4);

            textFieldTranslateX.setText2(String.valueOf(offsetXDefault));
            textFieldTranslateY.setText2(String.valueOf(offsetYDefault));
            textFieldTranslateZ.setText2(String.valueOf(offsetZDefault));
            textFieldRotateY.setText2(String.valueOf(rotationYDefault));

            addChild(new ClickableWidget(textFieldTranslateX));
            addChild(new ClickableWidget(textFieldTranslateY));
            addChild(new ClickableWidget(textFieldTranslateZ));
            addChild(new ClickableWidget(textFieldRotateY));
        }
    }

    @Override
    public void tick2() {
        super.tick2();
        if (blockEntity != null) {
            textFieldTranslateX.tick2();
            textFieldTranslateY.tick2();
            textFieldTranslateZ.tick2();
            textFieldRotateY.tick2();
        }
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        if (blockEntity != null) {
            renderBackground(graphicsHolder);
            super.render(graphicsHolder, mouseX, mouseY, delta);
            graphicsHolder.drawText(MODEL_TRANSLATION_TEXT, SQUARE_SIZE, SQUARE_SIZE * 2 + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(X_TEXT, SQUARE_SIZE, SQUARE_SIZE * 3 + TEXT_FIELD_PADDING / 2 + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(Y_TEXT, SQUARE_SIZE, SQUARE_SIZE * 4 + TEXT_FIELD_PADDING * 3 / 2 + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(Z_TEXT, SQUARE_SIZE, SQUARE_SIZE * 5 + TEXT_FIELD_PADDING * 5 / 2 + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(MODEL_ROTATION_TEXT, width / 2, SQUARE_SIZE * 2 + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(Y_TEXT, width / 2, SQUARE_SIZE * 3 + TEXT_FIELD_PADDING / 2 + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
        }
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }

    @Override
    public void onClose2() {
        if (blockEntity != null) {
            super.onClose2();
            OffsetPosition offsetPosition = new OffsetPosition(parse(textFieldTranslateX.getText2(), -1.0, 1.0), parse(textFieldTranslateY.getText2(), -1.0, 1.0), parse(textFieldTranslateZ.getText2(), -1.0, 1.0));
            OffsetPosition rotationPosition = new OffsetPosition(0, parse(textFieldRotateY.getText2(), 0, 360.0), 0);
            final Position position = org.mtr.mod.Init.blockPosToPosition(blockPos);
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new MSDPacketUpdateModel(this.blockPos, offsetPosition, rotationPosition));
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new MSDPacketResetData(new MSDResetDataRequest(MSDMinecraftClientData.getInstance()).addCatenaryNodePosition(position).addOffsetPosition(offsetPosition)));
        }
    }

    private void updateClient() {
        OffsetPosition offsetPosition = new OffsetPosition(parse(textFieldTranslateX.getText2(), -1.0, 1.0), parse(textFieldTranslateY.getText2(), -1.0, 1.0), parse(textFieldTranslateZ.getText2(), -1.0, 1.0));
        OffsetPosition rotationPosition = new OffsetPosition(0, parse(textFieldRotateY.getText2(), 0, 360.0), 0);
        this.blockEntity.setOffsetPosition(offsetPosition, rotationPosition);
        if (isConnected) {
            MSDMinecraftClientData clientData = MSDMinecraftClientData.getInstance();
            final Position position = org.mtr.mod.Init.blockPosToPosition(blockPos);
            clientData.positionsToCatenary.get(position).forEach((endPosition, catenary) -> {
                clientData.catenaries.remove(catenary);
                clientData.catenaries.add(Catenary.copy(catenary,
                        position.equals(catenary.getPosition1()) ? offsetPosition : catenary.getOffsetPositionStart(),
                        position.equals(catenary.getPosition2()) ? offsetPosition : catenary.getOffsetPositionEnd()));
            });
            clientData.sync();
        }
    }

    private static double parse(String text, double mixValue, double maxValue) {
        try {
            double number = Double.parseDouble(text);
            return number < mixValue ? mixValue : Math.min(number, maxValue);
        } catch (Exception ignored) {
            return 0;
        }
    }
}