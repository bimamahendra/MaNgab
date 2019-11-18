package com.stiki.mangab.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SubjectModel extends RealmObject {

    @PrimaryKey
    String subjectId;
    String subjectName;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
