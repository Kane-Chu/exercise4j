package kane.exercise.commons.test.base;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.ImmutableSet;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author kane
 */
@Slf4j
public class JacksonTest {

    private static final String FILTER_ID = "test filter";
    private static final Set<String> FILTER_FIELD = ImmutableSet.of("filter1", "filter2");
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
        simpleFilterProvider.addFilter(FILTER_ID, SimpleBeanPropertyFilter.serializeAllExcept(FILTER_FIELD));

        mapper.addMixIn(Object.class, PropertyFilterMixIn.class);
        mapper.setFilterProvider(simpleFilterProvider);

    }

    @Test
    public void test() throws Exception {
        User user = new User();
        log.info(mapper.writeValueAsString(user));
        String jsonString = "{\"userName\":null,\"password\":null,\"sex\":null,\"address\":null,\"phone\":null,\"email\":null,\"filter1\":null,\"filter2\":null}";
        Map<String, Object> stringObjectMap = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });
        log.info("{}", stringObjectMap);
    }

    @Data
    static class User {
        private String userName;
        private String password;
        private Integer sex;
        private String address;
        private String phone;
        private String email;
        private String filter1;
        private String filter2;
    }

    @JsonFilter(FILTER_ID)
    static class PropertyFilterMixIn {
    }

}