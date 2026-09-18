package top.mcmtr.core.legacy.generated.data;

import org.mtr.core.serializer.ReaderBase;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.serializer.WriterBase;

public abstract class TransCatenaryConnectionSchema implements SerializedDataBase {
    protected long transCatenaryNodePos;
    protected double transXStart;
    protected double transYStart;
    protected double transZStart;
    protected double transXEnd;
    protected double transYEnd;
    protected double transZEnd;
    protected String transCatenaryType;
    private static final String KEY_TRANS_NODE_POS = "trans_catenary_node_pos";
    private static final String KEY_TRANS_X_START = "t_c_x_start";
    private static final String KEY_TRANS_Y_START = "t_c_y_start";
    private static final String KEY_TRANS_Z_START = "t_c_z_start";
    private static final String KEY_TRANS_X_END = "t_c_x_end";
    private static final String KEY_TRANS_Y_END = "t_c_y_end";
    private static final String KEY_TRANS_Z_END = "t_c_z_end";
    private static final String KEY_TRANS_CATENARY_TYPE = "t_catenary_type";

    public TransCatenaryConnectionSchema() {
    }

    public TransCatenaryConnectionSchema(ReaderBase readerBase) {
    }

    @Override
    public void updateData(ReaderBase readerBase) {
        readerBase.unpackLong(KEY_TRANS_NODE_POS, value -> transCatenaryNodePos = value);
        readerBase.unpackDouble(KEY_TRANS_X_START, value -> transXStart = value);
        readerBase.unpackDouble(KEY_TRANS_Y_START, value -> transYStart = value);
        readerBase.unpackDouble(KEY_TRANS_Z_START, value -> transZStart = value);
        readerBase.unpackDouble(KEY_TRANS_X_END, value -> transXEnd = value);
        readerBase.unpackDouble(KEY_TRANS_Y_END, value -> transYEnd = value);
        readerBase.unpackDouble(KEY_TRANS_Z_END, value -> transZEnd = value);
        readerBase.unpackString(KEY_TRANS_CATENARY_TYPE, value -> transCatenaryType = value);
    }

    @Override
    public void serializeData(WriterBase writerBase) {
        writerBase.writeLong(KEY_TRANS_NODE_POS, transCatenaryNodePos);
        writerBase.writeDouble(KEY_TRANS_X_START, transXStart);
        writerBase.writeDouble(KEY_TRANS_Y_START, transYStart);
        writerBase.writeDouble(KEY_TRANS_Z_START, transZStart);
        writerBase.writeDouble(KEY_TRANS_X_END, transXEnd);
        writerBase.writeDouble(KEY_TRANS_Y_END, transYEnd);
        writerBase.writeDouble(KEY_TRANS_Z_END, transZEnd);
        writerBase.writeString(KEY_TRANS_CATENARY_TYPE, transCatenaryType);
    }

    public double getTransXStart() {
        return transXStart;
    }

    public double getTransYStart() {
        return transYStart;
    }

    public double getTransZStart() {
        return transZStart;
    }

    public double getTransXEnd() {
        return transXEnd;
    }

    public double getTransYEnd() {
        return transYEnd;
    }

    public double getTransZEnd() {
        return transZEnd;
    }
}