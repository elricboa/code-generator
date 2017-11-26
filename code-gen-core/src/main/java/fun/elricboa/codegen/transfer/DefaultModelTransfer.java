package fun.elricboa.codegen.transfer;

import fun.elricboa.codegen.Column;
import fun.elricboa.codegen.Field;
import fun.elricboa.codegen.Model;
import fun.elricboa.codegen.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author elricboa on 2017/11/26.
 */
public class DefaultModelTransfer implements ModelTransfer {

    @Setter
    @Getter
    private FieldTransfer fieldTransfer;

    @Override
    public Model transfer(Table table) {
        String tableName = table.getName();
        List<Field> fieldList = Lists.newArrayList();
        for (Column column : table.getColumns()) {
            Field field = fieldTransfer.transfer(column);
            if (field != null) {
                fieldList.add(field);
            }
        }
        tableName = StringUtils.capitalize(tableName);
        return new Model(tableName, fieldList);
    }
}
