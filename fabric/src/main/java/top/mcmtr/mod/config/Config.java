package top.mcmtr.mod.config;

import org.mtr.core.tool.Utilities;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.mapping.holder.MinecraftClient;
import top.mcmtr.mod.Init;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class Config {
    private static int rigidCatenarySegmentLength = 1;
    private static int yuuniPIDSMaxViewDistance = 128;
    private static int yamanoteRailwaySignMaxViewDistance = 128;
    private static int customTextSignMaxViewDistance = 128;
    private static int electricCurvatureScale = 100;
    private static final String SEGMENT_LENGTH = "rigid_catenary_segment_length";
    private static final String PIDS_MAX_VIEW_DISTANCE = "yuuni_pids_max_view_distance";
    private static final String RAILWAY_SIGN_MAX_VIEW_DISTANCE = "railway_sign_max_view_distance";
    private static final String TEXT_SIGN_MAX_VIEW_DISTANCE = "custom_text_sign_max_view_distance";
    private static final String ELECTRIC_CURVATURE_SCALE = "electric_curvature_scale";
    private static final Path CONFIG_PATH = MinecraftClient.getInstance().getRunDirectoryMapped().toPath().resolve("config").resolve("msd.json");
    public static final int MAX_VIEW_DISTANCE = 256;
    public static final int MAX_ELECTRIC_CURVATURE_SCALE = 800;

    public static void setRigidCatenarySegmentLength(int rigidCatenarySegmentLength) {
        Config.rigidCatenarySegmentLength = rigidCatenarySegmentLength;
        writeToFile();
    }

    public static void setYuuniPIDSMaxViewDistance(int yuuniPIDSMaxViewDistance) {
        Config.yuuniPIDSMaxViewDistance = yuuniPIDSMaxViewDistance;
        writeToFile();
    }

    public static void setYamanoteRailwaySignMaxViewDistance(int yamanoteRailwaySignMaxViewDistance) {
        Config.yamanoteRailwaySignMaxViewDistance = yamanoteRailwaySignMaxViewDistance;
        writeToFile();
    }

    public static void setCustomTextSignMaxViewDistance(int customTextSignMaxViewDistance) {
        Config.customTextSignMaxViewDistance = customTextSignMaxViewDistance;
        writeToFile();
    }

    public static void setElectricCurvatureScale(int electricCurvatureScale) {
        Config.electricCurvatureScale = electricCurvatureScale;
        writeToFile();
    }

    public static int getRigidCatenarySegmentLength() {
        return rigidCatenarySegmentLength;
    }

    public static int getYuuniPIDSMaxViewDistance() {
        return yuuniPIDSMaxViewDistance;
    }

    public static int getYamanoteRailwaySignMaxViewDistance() {
        return yamanoteRailwaySignMaxViewDistance;
    }

    public static int getCustomTextSignMaxViewDistance() {
        return customTextSignMaxViewDistance;
    }

    public static int getElectricCurvatureScale() {
        return electricCurvatureScale;
    }

    public static void refreshProperties() {
        Init.MSD_LOGGER.info("Refreshed MSD config");
        try {
            final JsonObject jsonConfig = Utilities.parseJson(String.join("", Files.readAllLines(CONFIG_PATH))).getAsJsonObject();
            try {
                rigidCatenarySegmentLength = jsonConfig.get(SEGMENT_LENGTH).getAsInt();
                if (rigidCatenarySegmentLength < 1 || rigidCatenarySegmentLength > 4) {
                    Init.MSD_LOGGER.error("error! Rigid Catenary Segment Length must be >= 1 & <= 4");
                    rigidCatenarySegmentLength = 1;
                }
            } catch (Exception ignored) {
            }
            try {
                yuuniPIDSMaxViewDistance = jsonConfig.get(PIDS_MAX_VIEW_DISTANCE).getAsInt();
            } catch (Exception ignored) {
            }
            try {
                yamanoteRailwaySignMaxViewDistance = jsonConfig.get(RAILWAY_SIGN_MAX_VIEW_DISTANCE).getAsInt();
            } catch (Exception ignored) {
            }
            try {
                customTextSignMaxViewDistance = jsonConfig.get(TEXT_SIGN_MAX_VIEW_DISTANCE).getAsInt();
            } catch (Exception ignored) {
            }
            try {
                electricCurvatureScale = jsonConfig.get(ELECTRIC_CURVATURE_SCALE).getAsInt();
            } catch (Exception ignored) {
            }
        } catch (Exception e) {
            writeToFile();
            refreshProperties();
        }
    }

    private static void writeToFile() {
        Init.MSD_LOGGER.info("Wrote MSD config to file");
        final JsonObject jsonConfig = new JsonObject();
        jsonConfig.addProperty(SEGMENT_LENGTH, rigidCatenarySegmentLength);
        jsonConfig.addProperty(PIDS_MAX_VIEW_DISTANCE, yuuniPIDSMaxViewDistance);
        jsonConfig.addProperty(RAILWAY_SIGN_MAX_VIEW_DISTANCE, yamanoteRailwaySignMaxViewDistance);
        jsonConfig.addProperty(TEXT_SIGN_MAX_VIEW_DISTANCE, customTextSignMaxViewDistance);
        jsonConfig.addProperty(ELECTRIC_CURVATURE_SCALE, electricCurvatureScale);
        try {
            if (!Files.exists(CONFIG_PATH.getParent())) {
                Files.createDirectories(CONFIG_PATH.getParent());
            }
            Files.write(CONFIG_PATH, Collections.singleton(Utilities.prettyPrint(jsonConfig)));
        } catch (IOException e) {
            Init.logException(e, "Configuration file write exception");
        }
    }
}