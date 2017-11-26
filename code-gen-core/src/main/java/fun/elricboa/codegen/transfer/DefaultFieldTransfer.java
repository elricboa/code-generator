package fun.elricboa.codegen.transfer;

import fun.elricboa.codegen.Column;
import fun.elricboa.codegen.Field;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author elricboa on 2017/11/26.
 */
public class DefaultFieldTransfer implements FieldTransfer {

    private static Map<String, Class> typeTransMap = new HashMap<String, Class>() {
        {
            put("int", Integer.TYPE);
            put("tinyint", Integer.TYPE);
            put("smallint", Integer.TYPE);
            put("bigint", Long.TYPE);
            put("varchar", String.class);
            put("text", String.class);
            put("mediumtext", String.class);
            put("longtext", String.class);
            put("datetime", Date.class);
            put("timestamp", Date.class);
            put("decimal", BigDecimal.class);
            put("double", Double.class);
        }
    };

    @Override
    public Field transfer(Column column) {
        Class transferType = transferType(column.getType());
        if (transferType == null) {
            throw new IllegalArgumentException("unknown type" + column);
        }
        return new Field(StringUtils.uncapitalize(column.getName()), transferType.getName(), column);
    }

    private Class transferType(String type) {
        return typeTransMap.get(type);
    }


}
