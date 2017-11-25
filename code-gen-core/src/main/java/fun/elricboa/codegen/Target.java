package fun.elricboa.codegen;

import lombok.Data;

import java.util.List;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class Target {
    private String sql;
    private List<String> methods;
    private Project project;
}
