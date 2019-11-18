package com.stiki.mangab.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ClassModel extends RealmObject {

    @PrimaryKey
    String classId;
    String className;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
