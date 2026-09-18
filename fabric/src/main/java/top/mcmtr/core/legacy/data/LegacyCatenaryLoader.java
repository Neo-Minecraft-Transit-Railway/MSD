package top.mcmtr.core.legacy.data;

import org.mtr.core.data.Position;
import org.mtr.core.simulation.FileLoader;
import org.mtr.core.tool.Angle;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArraySet;
import top.mcmtr.core.MSDMain;
import top.mcmtr.core.data.Catenary;
import top.mcmtr.core.data.CatenaryType;
import top.mcmtr.core.data.OffsetPosition;
import top.mcmtr.core.data.RigidCatenary;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.UUID;

public final class LegacyCatenaryLoader {
    private static final String KEY_CATENARIES = "catenaries";
    private static final String KEY_RIGID_CATENARIES = "rigid_catenaries";
    private static final String KEY_TRANS_CATENARIES = "trans_catenaries";

    public static void loadTransCatenary(Path savePath, ObjectArraySet<Catenary> catenaries) {
        final ObjectArraySet<LegacyTransCatenaryNode> legacyTransCatenaryNodes = new ObjectArraySet<>();
        new FileLoader<>(legacyTransCatenaryNodes, LegacyTransCatenaryNode::new, savePath, KEY_TRANS_CATENARIES);

        final Object2ObjectOpenHashMap<UUID, TransCatenaryNodeConnection.CatenaryType> transCatenaryCache = new Object2ObjectOpenHashMap<>();
        legacyTransCatenaryNodes.forEach(legacyTransCatenaryNode -> {
            final Position startPosition = legacyTransCatenaryNode.getStartPosition();
            final long startPositionLong = legacyTransCatenaryNode.getStartPositionLong();
            legacyTransCatenaryNode.iterateConnections(transCatenaryNodeConnection -> {
                final TransCatenaryNodeConnection.CatenaryType catenaryType = transCatenaryNodeConnection.getCatenaryType();
                final Position endPosition = transCatenaryNodeConnection.getEndPoint();
                final long endPositionLong = transCatenaryNodeConnection.getEndPointLong();
                final UUID uuid = getUuid(startPositionLong, endPositionLong);
                final TransCatenaryNodeConnection.CatenaryType oldCatenaryType = transCatenaryCache.get(uuid);
                if (oldCatenaryType != null) {
                    final Catenary catenary;
                    final OffsetPosition startOffsetPosition = new OffsetPosition(transCatenaryNodeConnection.getTransXStart() - startPosition.getX(), transCatenaryNodeConnection.getTransYStart() - startPosition.getY(), transCatenaryNodeConnection.getTransZStart() - startPosition.getZ());
                    final OffsetPosition endOffsetPosition = new OffsetPosition(transCatenaryNodeConnection.getTransXEnd() - endPosition.getX(), transCatenaryNodeConnection.getTransYEnd() - endPosition.getY(), transCatenaryNodeConnection.getTransZEnd() - endPosition.getZ());
                    if (Objects.requireNonNull(catenaryType) == TransCatenaryNodeConnection.CatenaryType.TRANS_CATENARY) {
                        catenary = new Catenary(startPosition, endPosition, startOffsetPosition, endOffsetPosition, CatenaryType.CATENARY);
                    } else {
                        catenary = new Catenary(startPosition, endPosition, startOffsetPosition, endOffsetPosition, CatenaryType.ELECTRIC);
                    }
                    catenaries.add(catenary);
                } else {
                    transCatenaryCache.put(uuid, catenaryType);
                }
            });
        });
        Path transCatenarySavePath = savePath.resolve(KEY_TRANS_CATENARIES);
        if (Files.exists(transCatenarySavePath)) {
            try {
                Files.walkFileTree(transCatenarySavePath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                Files.deleteIfExists(transCatenarySavePath);
            } catch (IOException e) {
                MSDMain.MSD_CORE_LOG.error("TransCatenary files delete fail,ignored");
            }
        }
    }

    public static void loadCatenary(Path savePath, ObjectArraySet<Catenary> catenaries) {
        final ObjectArraySet<LegacyCatenaryNode> legacyCatenaryNodes = new ObjectArraySet<>();
        new FileLoader<>(legacyCatenaryNodes, LegacyCatenaryNode::new, savePath, KEY_CATENARIES);

        final Object2ObjectOpenHashMap<UUID, CatenaryNodeConnection.CatenaryType> catenaryCache = new Object2ObjectOpenHashMap<>();
        legacyCatenaryNodes.forEach(legacyCatenaryNode -> {
            final Position startPosition = legacyCatenaryNode.getStartPosition();
            final long startPositionLong = legacyCatenaryNode.getStartPositionLong();
            legacyCatenaryNode.iterateConnections(catenaryNodeConnection -> {
                final CatenaryNodeConnection.CatenaryType catenaryType = catenaryNodeConnection.getCatenaryType();
                final Position endPosition = catenaryNodeConnection.getEndPoint();
                final long endPositionLong = catenaryNodeConnection.getEndPointLong();
                final UUID uuid = getUuid(startPositionLong, endPositionLong);
                final CatenaryNodeConnection.CatenaryType oldCatenaryType = catenaryCache.get(uuid);
                if (oldCatenaryType != null) {
                    final Catenary catenary;
                    switch (catenaryType) {
                        case CATENARY:
                            catenary = new Catenary(startPosition, endPosition, new OffsetPosition(0, 0, 0), new OffsetPosition(0, 0, 0), CatenaryType.CATENARY);
                            break;
                        case ELECTRIC:
                            catenary = new Catenary(startPosition, endPosition, new OffsetPosition(0, 0, 0), new OffsetPosition(0, 0, 0), CatenaryType.ELECTRIC);
                            break;
                        default:
                            catenary = new Catenary(startPosition, endPosition, new OffsetPosition(0, 0, 0), new OffsetPosition(0, 0, 0), CatenaryType.RIGID_SOFT_CATENARY);
                            break;
                    }
                    catenaries.add(catenary);
                } else {
                    catenaryCache.put(uuid, catenaryType);
                }
            });
        });
    }

    public static void loadRigidCatenary(Path savePath, ObjectArraySet<RigidCatenary> rigidCatenaries) {
        final ObjectArraySet<LegacyRigidCatenaryNode> legacyRigidCatenaryNodes = new ObjectArraySet<>();
        new FileLoader<>(legacyRigidCatenaryNodes, LegacyRigidCatenaryNode::new, savePath, KEY_RIGID_CATENARIES);

        final Object2ObjectOpenHashMap<UUID, RigidCatenaryNodeConnection> rigidCatenaryCache = new Object2ObjectOpenHashMap<>();
        legacyRigidCatenaryNodes.forEach(legacyRigidCatenaryNode -> {
            final Position startPosition = legacyRigidCatenaryNode.getStartPosition();
            final long startPositionLong = legacyRigidCatenaryNode.getStartPositionLong();
            legacyRigidCatenaryNode.iterateConnections(rigidCatenaryNodeConnection -> {
                final Position endPosition = rigidCatenaryNodeConnection.getEndPosition();
                final long endPositionLong = rigidCatenaryNodeConnection.getEndPositionLong();
                final Angle startAngle = rigidCatenaryNodeConnection.getStartAngle();
                final Angle endAngle = rigidCatenaryNodeConnection.getEndAngle();
                final UUID uuid = getUuid(startPositionLong, endPositionLong);
                final RigidCatenaryNodeConnection oldRigidCatenaryConnection = rigidCatenaryCache.get(uuid);
                if (oldRigidCatenaryConnection != null) {
                    final RigidCatenary rigidCatenary = new RigidCatenary(startPosition, startAngle, endPosition, endAngle, RigidCatenary.Shape.QUADRATIC, 0);
                    rigidCatenaries.add(rigidCatenary);
                } else {
                    rigidCatenaryCache.put(uuid, rigidCatenaryNodeConnection);
                }
            });
        });
    }

    private static UUID getUuid(long value1, long value2) {
        return value1 > value2 ? new UUID(value1, value2) : new UUID(value2, value1);
    }
}