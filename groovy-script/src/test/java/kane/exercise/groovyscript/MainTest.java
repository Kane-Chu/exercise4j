package kane.exercise.groovyscript;


import java.util.HashMap;
import java.util.Map;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MainTest {

    @Test
    public void testSimple() {
        String scriptContent = "String paramValue = params.get(\"paramKey\");" +
                "        return service.process(paramValue);";
        Class<Script> clazz = new GroovyClassLoader().parseClass(scriptContent);

        Script script;
        try {
            script = clazz.newInstance();
        } catch (Exception ex) {
            log.error("error", ex);
            return;
        }

        Map<String, String> params = new HashMap<>(16);

        params.put("paramKey", "I am result!");

        Binding binding = new Binding();
        binding.setVariable("params", params);
        binding.setVariable("service", new SimpleService());

        script.setBinding(binding);

        Object scriptResult = script.run();

        log.info("script execute result [{}]", scriptResult);

    }

}