package fun.elricboa.codegen.generator.parser;

import fun.elricboa.codegen.Column;
import fun.elricboa.codegen.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author elricboa on 2017/11/26.
 */
public class MysqlCreateTablePaser implements DBParser {

    private Pattern tableNamePattern = Pattern.compile("CREATE\\s+TABLE\\s+([`\\w]+)\\s*\\((.*)\\)([^)]+)",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private Pattern pattern4PK = Pattern.compile("(.*)PRIMARY KEY \\(`(\\w+)`\\)",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private Pattern pattern4Fields = Pattern.compile("\\s*([`\\w]+)\\s+(\\w+)([^,]*),",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private Pattern pattern4Comment = Pattern.compile("COMMENT\\s+'(.*?)'",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private Pattern pattern4TableComment = Pattern.compile("COMMENT\\s*=\\s*'(.*?)'",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private Pattern patternIsAutoIncrement = Pattern.compile(".*AUTO_INCREMENT.*",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);


    @Override
    public Table parse(String schema) {
        Matcher matcher = tableNamePattern.matcher(schema);
        if (matcher.find()) {
            Table table = new Table();
            String tableName = matcher.group(1).replace("`", "");
            table.setName(tableName);
            String fields = matcher.group(2);
            Matcher matcher4Comment = tableNamePattern.matcher(matcher.group(3));
            String comment = null;
            if (matcher4Comment.find()) {
                comment = matcher4Comment.group(1);
            }
            List<Column> columns = parseColunms(fields);
            table.setColumns(columns);
            table.setComment(StringUtils.removeEnd(comment, "表"));
            return table;
        }
        return null;
    }

    private List<Column> parseColunms(String fieldsText) {
        Matcher matcher = pattern4PK.matcher(fieldsText);
        String nameOfPK = "";
        if (matcher.find()) {
            nameOfPK = matcher.group(2);
            fieldsText = matcher.group(1);
        }
        fieldsText = fieldsText.replaceAll("\\(\\d+,\\d+\\)", "");
        matcher = pattern4Fields.matcher(fieldsText);
        List<Column> fieldList = new ArrayList<Column>();
        while (matcher.find()) {
            String name = matcher.group(1).replace("`", "");
            String type = matcher.group(2).toLowerCase();
            Matcher matcher4Comment = pattern4Comment.matcher(matcher.group(3));
            String comment = null;
            if (matcher4Comment.find()) {
                comment = matcher4Comment.group(1);
            }

            fieldList.add(new Column(name, type, name.equals(nameOfPK), patternIsAutoIncrement.matcher(matcher.group(3)).matches(), comment));
        }
        return fieldList;
    }
}
