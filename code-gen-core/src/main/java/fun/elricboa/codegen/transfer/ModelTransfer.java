package fun.elricboa.codegen.transfer;

import fun.elricboa.codegen.Model;
import fun.elricboa.codegen.Table;

/**
 * @author elricboa on 2017/11/26.
 */
public interface ModelTransfer {
    Model transfer(Table table);
}
