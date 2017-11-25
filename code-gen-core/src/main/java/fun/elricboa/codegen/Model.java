package fun.elricboa.codegen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
@AllArgsConstructor
public class Model {
    private String name;
    private List<Field> fields;
//    public Field getPrimaryField() {
//        for (Field field : fields) {
//            field.
//        }
//    }
}
