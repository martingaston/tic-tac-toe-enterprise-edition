import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

class Encode {
    static String from(JsonOutgoing jsonOutgoing) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        return mapper.writeValueAsString(jsonOutgoing);
    }
}

