package fun.elricboa.codegen;

import lombok.Data;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class Field extends Type{
    private String name;
    private Column column;

    public Field(String name, String typeName, Column column) {
        super(typeName);
        this.name = name;
        this.column = column;
    }
}
