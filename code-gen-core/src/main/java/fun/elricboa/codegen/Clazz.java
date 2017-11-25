package fun.elricboa.codegen;

import lombok.Data;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class Clazz extends Type{
    private Imports imports = new Imports();
}
