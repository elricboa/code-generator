package fun.elricboa.codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * @author elricboa on 2017/11/25.
 */
public class FreemarkerWrapper {

    private Configuration configuration;
    private static FreemarkerWrapper instance = new FreemarkerWrapper();

    public static FreemarkerWrapper getInstance() {
        return instance;
    }

    public Template getTemplate(String name) {
        try {
            return configuration.getTemplate(name);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
