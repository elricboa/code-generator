package fun.elricboa.codegen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author elricboa on 2017/11/25.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Imports {
    private Set<String> imports = new LinkedHashSet<>();

    public final static String PERFFIX = "java.lang";

    public static Imports create(List<? extends Type> types) {
        Set<String> mergeImportSet = new LinkedHashSet<>();
        for (Type type : types) {
            if (needImport(type.getTypeOriginName())) {
                mergeImportSet.add(type.getTypeFullName());
            }
        }
        return new Imports(mergeImportSet);
    }

    private static boolean needImport(String imports) {
        if (imports == null) {
            return false;
        }
        if (imports.startsWith(PERFFIX)) {
            return false;
        }
        return true;
    }
}
