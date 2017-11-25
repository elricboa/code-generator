package fun.elricboa.codegen;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import fun.elricboa.codegen.generator.GeneratorContext;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author elricboa on 2017/11/25.
 */
public class FreeMarkerRender implements Renderer {

    private Template template;

    public FreeMarkerRender(Object template) {
    }

    @Override
    public String render(GeneratorContext generatorContext) {
        StringWriter stringWriter = new StringWriter();
        try {
            template.process(generatorContext, stringWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
