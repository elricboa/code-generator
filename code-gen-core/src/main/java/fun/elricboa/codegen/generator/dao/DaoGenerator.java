package fun.elricboa.codegen.generator.dao;

import fun.elricboa.codegen.Model;
import fun.elricboa.codegen.Project;
import fun.elricboa.codegen.Table;
import fun.elricboa.codegen.domain.Target;
import fun.elricboa.codegen.generator.GeneratorContext;
import fun.elricboa.codegen.generator.entity.Entity;
import fun.elricboa.codegen.generator.entity.EntityGenerator;
import fun.elricboa.codegen.generator.parser.MysqlCreateTablePaser;
import fun.elricboa.codegen.transfer.DefaultModelTransfer;

/**
 * @author elricboa on 2017/11/25.
 */
public class DaoGenerator {

    public GeneratorContext generateGeneratorContext(Target target) {
        Project project = target.getProject();
        EntityGenerator entityGenerator = new EntityGenerator();
        entityGenerator.setSurffix("Entity");
        entityGenerator.setPkg(project.getEntityPackage());

        Table table = new MysqlCreateTablePaser().parse(target.getSql());

        DefaultModelTransfer defaultModelTransfer = new DefaultModelTransfer();
        Model model = defaultModelTransfer.transfer(table);

        GeneratorContext generatorContext = new GeneratorContext();
        Entity entity = entityGenerator.generate(generatorContext);
        generatorContext.setTable(table);
        generatorContext.setModel(model);
        generatorContext.setEntity(entity);
        return generatorContext;
    }
}
