import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.google.common.base.CaseFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.UPPER_CAMEL_CASE;

/**
 * TODO
 *
 * @author kane
 */
public class test {
    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategyBase(){
            @Override
            public String translate(String input) {
                if (input == null) return null;
                return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, input);
            }
        });
        User user = User.builder()
                .username("kane")
                .userAddress("shanghai")
                .build();
        String json  = mapper.writeValueAsString(user);
        System.out.println(json);


        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = com.alibaba.fastjson.PropertyNamingStrategy.PascalCase;
        json = JSON.toJSONString(user, config);
        System.out.println(json);



    }

    @Test
    public void test1(){
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, "userName"));
    }

    @Builder
//    @NoArgsConstructor
    @Data
    static class User {
        private String username;
        private String userAddress;
    }
}