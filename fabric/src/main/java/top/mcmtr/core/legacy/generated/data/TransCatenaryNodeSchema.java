package top.mcmtr.core.legacy.generated.data;

import org.mtr.core.serializer.ReaderBase;
import org.mtr.core.serializer.SerializedDataBaseWithId;
import org.mtr.core.serializer.WriterBase;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import top.mcmtr.core.legacy.data.TransCatenaryNodeConnection;

public abstract class TransCatenaryNodeSchema implements SerializedDataBaseWithId {
    protected long transCatenaryNodePos;
    protected final ObjectArrayList<TransCatenaryNodeConnection> transCatenaryConnections = new ObjectArrayList<>();
    private static final String KEY_TRANS_NODE_POS = "trans_catenary_node_pos";
    private static final String KEY_TRANS_CATENARY_CONNECTIONS = "trans_catenary_connections";

    public TransCatenaryNodeSchema() {
    }

    public TransCatenaryNodeSchema(ReaderBase readerBase) {
    }

    @Override
    public void updateData(ReaderBase readerBase) {
        readerBase.unpackLong(KEY_TRANS_NODE_POS, value -> transCatenaryNodePos = value);
        readerBase.iterateReaderArray(KEY_TRANS_CATENARY_CONNECTIONS, transCatenaryConnections::clear, readerBaseChild -> transCatenaryConnections.add(new TransCatenaryNodeConnection(readerBaseChild)));
    }

    @Override
    public void serializeData(WriterBase writerBase) {
        writerBase.writeLong(KEY_TRANS_NODE_POS, transCatenaryNodePos);
        writerBase.writeDataset(transCatenaryConnections, KEY_TRANS_CATENARY_CONNECTIONS);
    }
}