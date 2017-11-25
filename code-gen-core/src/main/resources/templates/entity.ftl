package ${entity.pkg};

import lombok.Data;
<#if entity.imports.imports?has_content>
    <#list entity.imports.imports as imp>
import ${imp};
    </#list>
</#if>

/**
*
* Create by elricboa http://www.github.com/elricboa/code-generator/<#if table.comment?exists>
*
* ${table.comment}</#if>
*
* @author elricboa
*/
@Data
public class ${entity.typeName} {

<#list model.fields as field>
    <#if field.column.comment?exists>
    /**
    * ${field.column.comment}
    */
    </#if>
    private ${field.typeName} ${field.name};

</#list>
}