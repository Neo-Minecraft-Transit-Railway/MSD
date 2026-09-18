package top.mcmtr.core.legacy.data;

import org.mtr.core.data.Position;
import org.mtr.core.serializer.ReaderBase;
import org.mtr.core.tool.Utilities;
import org.mtr.legacy.data.DataFixer;
import top.mcmtr.core.legacy.generated.data.TransCatenaryNodeSchema;

import java.util.function.Consumer;

public final class LegacyTransCatenaryNode extends TransCatenaryNodeSchema {
    public LegacyTransCatenaryNode(ReaderBase readerBase) {
        super(readerBase);
        updateData(readerBase);
    }

    @Override
    public String getHexId() {
        return Utilities.numberToPaddedHexString(transCatenaryNodePos);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public Position getStartPosition() {
        return DataFixer.fromLong(transCatenaryNodePos);
    }

    public long getStartPositionLong() {
        return transCatenaryNodePos;
    }

    public void iterateConnections(Consumer<TransCatenaryNodeConnection> consumer) {
        transCatenaryConnections.forEach(consumer);
    }
}