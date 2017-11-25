package fun.elricboa.codegen;

import lombok.Data;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class Type {

    private String typeOriginName;

    public Type() {
    }

    public Type(String typeOriginName) {
        this.typeOriginName = typeOriginName;
    }

    public String getTypeFullName() {
        if (typeOriginName.contains(".")) {
            return typeOriginName;
        } else {
            return null;
        }
    }
}
