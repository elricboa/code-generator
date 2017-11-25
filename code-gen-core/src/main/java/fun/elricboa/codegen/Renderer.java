package fun.elricboa.codegen;

import fun.elricboa.codegen.generator.GeneratorContext;

/**
 * @author elricboa on 2017/11/25.
 */
public interface Renderer {
    String render(GeneratorContext generatorContext);
}
