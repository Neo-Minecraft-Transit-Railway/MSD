package top.mcmtr.core.legacy.data;

import org.mtr.core.data.Position;
import org.mtr.core.serializer.ReaderBase;
import org.mtr.core.tool.EnumHelper;
import org.mtr.legacy.data.DataFixer;
import top.mcmtr.core.legacy.generated.data.TransCatenaryConnectionSchema;

public final class TransCatenaryNodeConnection extends TransCatenaryConnectionSchema {
    public TransCatenaryNodeConnection(ReaderBase readerBase) {
        super(readerBase);
        updateData(readerBase);
    }

    public Position getEndPoint() {
        return DataFixer.fromLong(transCatenaryNodePos);
    }

    public long getEndPointLong() {
        return transCatenaryNodePos;
    }

    public CatenaryType getCatenaryType() {
        return EnumHelper.valueOf(CatenaryType.CATENARY, transCatenaryType);
    }

    public enum CatenaryType {
        CATENARY(),
        ELECTRIC(),
        RIGID_CATENARY(),
        RIGID_SOFT_CATENARY(),
        TRANS_CATENARY(),
        TRANS_ELECTRIC();

        CatenaryType() {
        }
    }
}