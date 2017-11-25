package fun.elricboa.codegen.generator;

import com.google.common.collect.Maps;
import fun.elricboa.codegen.*;
import fun.elricboa.codegen.generator.entity.Entity;
import lombok.Data;

import java.util.Map;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class GeneratorContext {
    private Model model;
    private Table table;
    private Entity entity;
    private SqlMap sqlMap;
    private Project project;
    private Map<String, Object> map = Maps.newHashMap();
}
