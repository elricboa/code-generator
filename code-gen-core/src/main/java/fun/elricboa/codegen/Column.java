package fun.elricboa.codegen;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
@AllArgsConstructor
public class Column {
    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isAutoIncrement;
    private String comment;
}
