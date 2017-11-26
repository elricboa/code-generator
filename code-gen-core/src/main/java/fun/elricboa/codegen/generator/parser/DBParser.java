package fun.elricboa.codegen.generator.parser;

import fun.elricboa.codegen.Table;

/**
 * @author elricboa on 2017/11/26.
 */
public interface DBParser {
    Table parse(String schema);
}
