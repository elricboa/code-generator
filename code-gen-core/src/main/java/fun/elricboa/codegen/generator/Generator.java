package fun.elricboa.codegen.generator;

/**
 * @author elricboa on 2017/11/25.
 */
public interface Generator<T> {
    T generate(GeneratorContext generatorContext);
}
