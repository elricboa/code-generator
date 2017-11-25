package fun.elricboa.codegen;

import lombok.Data;

import java.util.List;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class Table {
    private String name;
    private List<String> columns;
    private String comment;
}
