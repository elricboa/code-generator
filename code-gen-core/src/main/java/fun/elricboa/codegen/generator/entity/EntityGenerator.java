package fun.elricboa.codegen.generator.entity;

import fun.elricboa.codegen.Imports;
import fun.elricboa.codegen.Model;
import fun.elricboa.codegen.generator.Generator;
import fun.elricboa.codegen.generator.GeneratorContext;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elricboa on 2017/11/25.
 */
public class EntityGenerator implements Generator<Entity> {
    @Setter
    @Getter
    private String surffix = "entity";
    @Setter
    @Getter
    private String pkg;

    @Override
    public Entity generate(GeneratorContext generatorContext) {
        Entity entity = new Entity();
        Model model = generatorContext.getModel();
        Imports imports = Imports.create(model.getFields());
        entity.setImports(imports);
        entity.setTypeOriginName(pkg + "." + model.getName() + surffix);
        return entity;
    }
}
