package interfaces;

import modelo.Sistema;
import org.json.JSONObject;

public interface ItoJson_fromJson <T>{
    JSONObject toJson();
    T fromJson(JSONObject jsonObject);
    int getId();
}
