package fun.elricboa.codegen;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Project project = new Project();
        project.setBasePackage(String.valueOf(model.get("basePackage")));
        project.setDaoPackage(project.getBasePackage() + ".dao");
        project.setEntityPackage(project.getBasePackage() + ".entity");
        project.setDaoRealizeTarget("dao-Realize-Target");
        Target target = new Target();
        target.setSql(model.get("sql").toString());
        List<String> methods = Lists.newArrayList();
        methods.add(model.get("methods").toString());
        target.setMethods(methods);
        target.setProject(project);

        return "index";
    }
}
