package top.mcmtr.mod.render;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.Init;
import org.mtr.mod.InitClient;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.CustomResourceLoader;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderRails;
import org.mtr.mod.render.StoredMatrixTransformations;
import top.mcmtr.mod.blocks.BlockCatenaryWithModel;

public final class RenderCatenaryModel<T extends BlockCatenaryWithModel.BlockCatenaryWithModelEntity> extends BlockEntityRenderer<T> {
    private final BlockCatenaryWithModel.CatenaryModel model;

    public RenderCatenaryModel(Argument argument, BlockCatenaryWithModel.CatenaryModel model) {
        super(argument);
        this.model = model;
    }

    @Override
    public void render(T blockEntity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
        final World world = blockEntity.getWorld2();
        if (world == null) {
            return;
        }

        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        final ClientPlayerEntity clientPlayerEntity = minecraftClient.getPlayerMapped();
        if (clientPlayerEntity == null) {
            return;
        }

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(0.5 + blockEntity.getPos2().getX(), blockEntity.getPos2().getY(), 0.5 + blockEntity.getPos2().getZ());

        if (RenderRails.isHoldingRailRelated(clientPlayerEntity) && minecraftClient.getCurrentScreenMapped() == null) {
            MainRenderer.scheduleRender(new Identifier(Init.MOD_ID_NTE, "textures/item/eye_candy.png"), false, QueuedRenderLayer.INTERIOR, (graphicsHolderNew, offset) -> {
                storedMatrixTransformations.transform(graphicsHolderNew, offset);
                graphicsHolderNew.translate(0, 0.5, 0);
                InitClient.transformToFacePlayer(graphicsHolderNew, blockEntity.getPos2().getX() + 0.5, blockEntity.getPos2().getY() + 0.5, blockEntity.getPos2().getZ() + 0.5);
                IDrawing.drawTexture(graphicsHolderNew, -0.5F, -0.5F, 1, 1, Direction.UP, GraphicsHolder.getDefaultLight());
                graphicsHolderNew.pop();
            });
        }

        final BlockPos blockPos = blockEntity.getPos2();
        final Direction facing = IBlock.getStatePropertySafe(world, blockPos, BlockCatenaryWithModel.FACING);
        final String modelId = this.model.getModelId();
        if (modelId != null) {
            CustomResourceLoader.getObjectById(modelId, objectResource -> {
                final StoredMatrixTransformations storedMatrixTransformationsNew = storedMatrixTransformations.copy();
                storedMatrixTransformationsNew.add(graphicsHolderNew -> {
                    graphicsHolderNew.translate(blockEntity.getOffsetPosition().getX(), blockEntity.getOffsetPosition().getY(), blockEntity.getOffsetPosition().getZ());
                    graphicsHolderNew.rotateYDegrees(180 - facing.asRotation());
                    graphicsHolderNew.rotateXDegrees((float) blockEntity.getRotation().getX() + 180);
                    graphicsHolderNew.rotateYDegrees((float) blockEntity.getRotation().getY());
                    graphicsHolderNew.rotateZDegrees((float) blockEntity.getRotation().getZ());
                });
                objectResource.render(storedMatrixTransformationsNew, light);
            });
        }
    }

    @Override
    public boolean isInRenderDistance(BlockCatenaryWithModel.BlockCatenaryWithModelEntity blockEntity, Vector3d position) {
        return true;
    }
}
