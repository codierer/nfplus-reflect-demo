package com.nfplus.m;

import java.util.Date;

//表名转换为类名
public class ${table.col0.className} {


//列名转换为成员变量，以及相应get set方法

<#list table?values as col>
    //${col.comment}
    private ${col.type} ${col.columnNameForVar};
</#list>


<#list table?values as col>
    public ${col.type} get${col.columnName}(){
        return ${col.columnNameForVar};
    }
    public void set${col.columnName} (${col.type} ${col.columnNameForVar}){
        this.${col.columnNameForVar} = ${col.columnNameForVar};
    }
</#list>


}