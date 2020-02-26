import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.beanutils.BeanUtils;
import org.assertj.core.util.Lists;

/**
 * @author kane
 */
public class ConverterUtil {

    public static void main(String[] args) throws Exception {
        String type = "PObject1";
        List<Map<String, String>> datas = Lists.newArrayList(
                ImmutableMap.of("field1", "value1", "field2", "value2"),
                ImmutableMap.of("field1", "value11", "field2", "value22")
        );
        List dataList = Lists.newArrayList();
        Class<?> pObject1 = Class.forName(type);
        for(Map<String, String> data: datas){
            Object o = pObject1.newInstance();
            BeanUtils.populate(o, data);
            dataList.add(o);
        }
        ABigObject aBigObject = new ABigObject();
        Field[] declaredFields = ABigObject.class.getDeclaredFields();
        for (Field field : declaredFields) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Class parameterizedTypeClass = (Class) parameterizedType.getActualTypeArguments()[0];
                if (parameterizedTypeClass.getName().equals(type)) {
                    field.set(aBigObject, dataList);
                }
            }
        }
        System.out.println(aBigObject);

        PObject1 p1 = PObject1.builder().field1("value1").field2("value2").build();


    }

}