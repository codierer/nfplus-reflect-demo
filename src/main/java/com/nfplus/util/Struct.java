package com.nfplus.util;

/**
 * @author zac
 * @description: 中间实体类，用户数据库结构封装
 * @data 2020 2020/3/9 17:19
 */
public class Struct {

    private String className;
    private String columnNameForVar;
    private String columnName;
    private String comment;
    private String type;
    private String keyName;
    private String defaultName;



    public Struct(Build build){
        this.className = build.className;
        this.columnName = build.columnName;
        this.columnNameForVar = build.columnNameForVar;
        this.comment = build.comment;
        this.type = build.type;
        this.keyName = build.keyName;
        this.defaultName = build.defaultName;
    }
    public static class Build{
        private String className;
        private String columnNameForVar;
        private String columnName;
        private String comment;
        private String type;
        private String keyName;
        private String defaultName;

        public Build className(String classN){
            this.className = classN;
            return this;
        }


        public Build columnName(String col){
            this.columnName = col;
            return this;
        }

        public Build columnNameForVar(String col){
            this.columnNameForVar = col;
            return this;
        }

        public Build comment(String c){
            this.comment = c;
            return this;
        }

        public Build type(String t){
            this.type = t;
            return this;
        }

        public Build keyName(String k){
            this.keyName = k;
            return this;
        }

        public Build defaultName(String defName){
            this.defaultName = defName;
            return this;
        }

        public Struct buildParams(){
            return new Struct(this);
        }

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnNameForVar() {
        return columnNameForVar;
    }

    public void setColumnNameForVar(String columnNameForVar) {
        this.columnNameForVar = columnNameForVar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
