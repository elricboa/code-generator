package fun.elricboa.codegen.transfer;

import fun.elricboa.codegen.Column;
import fun.elricboa.codegen.Field;

/**
 * @author elricboa on 2017/11/26.
 */
public interface FieldTransfer {
    Field transfer(Column column);
}
