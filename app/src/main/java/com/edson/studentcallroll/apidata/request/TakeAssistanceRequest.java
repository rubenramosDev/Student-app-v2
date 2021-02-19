package com.edson.studentcallroll.apidata.request;

public class TakeAssistanceRequest {

    long assitanceSheetId;
    String studentNumber;

    public TakeAssistanceRequest(long assitanceSheetId, String studentNumber) {
        this.assitanceSheetId = assitanceSheetId;
        this.studentNumber = studentNumber;
    }

    public long getAssitanceSheetId() {
        return assitanceSheetId;
    }

    public void setAssitanceSheetId(long assitanceSheetId) {
        this.assitanceSheetId = assitanceSheetId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
