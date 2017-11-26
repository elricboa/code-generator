package fun.elricboa.codegen;

import fun.elricboa.codegen.domain.Target;
import fun.elricboa.codegen.generator.GeneratorContext;
import fun.elricboa.codegen.generator.dao.DaoGenerator;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author elricboa on 2017/11/25.
 */

@Controller
@EnableAutoConfiguration
public class GenSqlController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @GetMapping("/index")
    public String index(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/generate")
    public String generate(Map<String, Object> model,
                           @RequestParam(value = "basePackage") String basePackage,
                           @RequestParam(value = "sql") String sql,
                           @RequestParam(value = "methods") String methods) {
        Project project = new Project();
        project.setBasePackage(basePackage);
        project.setDaoPackage(project.getBasePackage() + ".dao");
        project.setEntityPackage(project.getBasePackage() + ".entity");
        project.setDaoRealizeTarget("dao-Realize-Target");
        Target target = new Target();
        target.setSql(sql);
        String[] method = methods.split(",");
        target.setMethods(Arrays.asList(method));
        target.setProject(project);
        DaoGenerator daoGenerator = new DaoGenerator();
        try {
            GeneratorContext generatorContext = daoGenerator.generateGeneratorContext(target);
            String entity = new FreeMarkerRender(FreemarkerWrapper.getInstance().getTemplate("templates/entity.ftl")).render(generatorContext);
            model.put("entity", entity);
        } catch (Exception e) {
            return "error";
        }
        return "generate";
    }
}
