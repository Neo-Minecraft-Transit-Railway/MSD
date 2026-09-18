package top.mcmtr.core.servlet;

import org.mtr.core.serializer.JsonReader;
import org.mtr.core.serializer.SerializedDataBase;
import org.mtr.core.tool.Utilities;
import top.mcmtr.core.operation.MSDDataRequest;
import top.mcmtr.core.operation.MSDDeleteDataRequest;
import top.mcmtr.core.operation.MSDResetDataRequest;
import top.mcmtr.core.operation.MSDUpdateDataRequest;
import top.mcmtr.core.simulation.MSDSimulator;

public final class MSDOperationProcessor {
    public static SerializedDataBase process(String key, SerializedDataBase data, MSDSimulator simulator) {
        final JsonReader jsonReader = new JsonReader(Utilities.getJsonObjectFromData(data));

        switch (key) {
            case OperationType.GET_DATA:
                return new MSDDataRequest(jsonReader).getData(simulator);
            case OperationType.UPDATE_DATA:
                return new MSDUpdateDataRequest(jsonReader, simulator).update();
            case OperationType.DELETE_DATA:
                return new MSDDeleteDataRequest(jsonReader).delete(simulator);
            case OperationType.RESET_DATA:
                return new MSDResetDataRequest(jsonReader, simulator).reset();
            default:
                return null;
        }
    }
}