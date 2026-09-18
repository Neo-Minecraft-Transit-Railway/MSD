package top.mcmtr.mod.screen;

import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import org.mtr.mod.screen.WidgetShorterSlider;
import top.mcmtr.mod.Init;
import top.mcmtr.mod.config.Config;

public class ConfigScreen extends ScreenExtension implements IGui {
    private final WidgetShorterSlider sliderRigidCatenarySegmentLength;
    private final WidgetShorterSlider sliderPIDSViewDistance;
    private final WidgetShorterSlider sliderRailwaySignViewDistance;
    private final WidgetShorterSlider sliderCustomTextViewDistance;
    private final WidgetShorterSlider sliderElectricCurvatureScale;
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = TEXT_HEIGHT + TEXT_PADDING;

    public ConfigScreen() {
        super();
        this.sliderRigidCatenarySegmentLength = new WidgetShorterSlider(0, 0, 4, num -> String.format("%d", Math.max(num, 1)), null);
        this.sliderPIDSViewDistance = new WidgetShorterSlider(0, 0, Config.MAX_VIEW_DISTANCE, num -> String.format("%d", Math.max(num, 4)), null);
        this.sliderRailwaySignViewDistance = new WidgetShorterSlider(0, 0, Config.MAX_VIEW_DISTANCE, num -> String.format("%d", Math.max(num, 4)), null);
        this.sliderCustomTextViewDistance = new WidgetShorterSlider(0, 0, Config.MAX_VIEW_DISTANCE, num -> String.format("%d", Math.max(num, 4)), null);
        this.sliderElectricCurvatureScale = new WidgetShorterSlider(0, 0, Config.MAX_ELECTRIC_CURVATURE_SCALE, num -> String.format("%d", Math.max(num, 10)), null);
    }

    @Override
    protected void init2() {
        super.init2();
        Config.refreshProperties();
        int i = 1;
        IDrawing.setPositionAndWidth(sliderRigidCatenarySegmentLength, width - (SQUARE_SIZE * 10) - BUTTON_WIDTH, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE, BUTTON_WIDTH - TEXT_PADDING - GraphicsHolder.getTextWidth("256") + (SQUARE_SIZE * 9));
        IDrawing.setPositionAndWidth(sliderPIDSViewDistance, width - (SQUARE_SIZE * 10) - BUTTON_WIDTH, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE, BUTTON_WIDTH - TEXT_PADDING - GraphicsHolder.getTextWidth("256") + (SQUARE_SIZE * 9));
        IDrawing.setPositionAndWidth(sliderRailwaySignViewDistance, width - (SQUARE_SIZE * 10) - BUTTON_WIDTH, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE, BUTTON_WIDTH - TEXT_PADDING - GraphicsHolder.getTextWidth("256") + (SQUARE_SIZE * 9));
        IDrawing.setPositionAndWidth(sliderCustomTextViewDistance, width - (SQUARE_SIZE * 10) - BUTTON_WIDTH, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE, BUTTON_WIDTH - TEXT_PADDING - GraphicsHolder.getTextWidth("256") + (SQUARE_SIZE * 9));
        IDrawing.setPositionAndWidth(sliderElectricCurvatureScale, width - (SQUARE_SIZE * 10) - BUTTON_WIDTH, (SQUARE_SIZE + TEXT_FIELD_PADDING) * i + SQUARE_SIZE, BUTTON_WIDTH - TEXT_PADDING - GraphicsHolder.getTextWidth("256") + (SQUARE_SIZE * 9));
        this.sliderRigidCatenarySegmentLength.setHeight(BUTTON_HEIGHT);
        this.sliderPIDSViewDistance.setHeight(BUTTON_HEIGHT);
        this.sliderRailwaySignViewDistance.setHeight(BUTTON_HEIGHT);
        this.sliderCustomTextViewDistance.setHeight(BUTTON_HEIGHT);
        this.sliderElectricCurvatureScale.setHeight(BUTTON_HEIGHT);
        this.sliderRigidCatenarySegmentLength.setValue(Config.getRigidCatenarySegmentLength());
        this.sliderPIDSViewDistance.setValue(Config.getYuuniPIDSMaxViewDistance());
        this.sliderRailwaySignViewDistance.setValue(Config.getYamanoteRailwaySignMaxViewDistance());
        this.sliderCustomTextViewDistance.setValue(Config.getCustomTextSignMaxViewDistance());
        this.sliderElectricCurvatureScale.setValue(Config.getElectricCurvatureScale());
        addChild(new ClickableWidget(sliderRigidCatenarySegmentLength));
        addChild(new ClickableWidget(sliderPIDSViewDistance));
        addChild(new ClickableWidget(sliderRailwaySignViewDistance));
        addChild(new ClickableWidget(sliderCustomTextViewDistance));
        addChild(new ClickableWidget(sliderElectricCurvatureScale));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        try {
            renderBackground(graphicsHolder);
            graphicsHolder.drawCenteredText(TextHelper.translatable("gui.msd.options"), width / 2, TEXT_PADDING, ARGB_WHITE);
            int i = 1;
            graphicsHolder.drawText(TextHelper.translatable("options.msd.rigid_catenary_segment_length"), SQUARE_SIZE, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(TextHelper.translatable("options.msd.pids_view_distance"), SQUARE_SIZE, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(TextHelper.translatable("options.msd.railway_sign_view_distance"), SQUARE_SIZE, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(TextHelper.translatable("options.msd.custom_text_view_distance"), SQUARE_SIZE, (SQUARE_SIZE + TEXT_FIELD_PADDING) * (i++) + SQUARE_SIZE + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            graphicsHolder.drawText(TextHelper.translatable("options.msd.electric_curvature_scale"), SQUARE_SIZE, (SQUARE_SIZE + TEXT_FIELD_PADDING) * i + SQUARE_SIZE + TEXT_PADDING, ARGB_WHITE, false, GraphicsHolder.getDefaultLight());
            super.render(graphicsHolder, mouseX, mouseY, delta);
        } catch (Exception e) {
            Init.logException(e, "Config Screen Render Exception");
        }
    }

    @Override
    public void onClose2() {
        super.onClose2();
        Config.setRigidCatenarySegmentLength(Math.max(sliderRigidCatenarySegmentLength.getIntValue(), 1));
        Config.setYuuniPIDSMaxViewDistance(Math.max(sliderPIDSViewDistance.getIntValue(), 4));
        Config.setYamanoteRailwaySignMaxViewDistance(Math.max(sliderRailwaySignViewDistance.getIntValue(), 4));
        Config.setCustomTextSignMaxViewDistance(Math.max(sliderCustomTextViewDistance.getIntValue(), 4));
        Config.setElectricCurvatureScale(Math.max(sliderElectricCurvatureScale.getIntValue(), 10));
        Config.refreshProperties();
    }
}