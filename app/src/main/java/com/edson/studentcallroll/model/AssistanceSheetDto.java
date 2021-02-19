package com.edson.studentcallroll.model;

import java.util.Date;

public class AssistanceSheetDto {

        private long assistanceSheetId;
        private String assistanceSheetStatus;
        private Date date;
        private String groupName;
        private long idGroup;
        private long idModule;
        private long idSemestre;
        private String module;
        private Date myAttendanceTime;
        private long semestre;
        private String statusOfMyAssistance;
        private String teacherName;

    public AssistanceSheetDto() {
    }

    public AssistanceSheetDto(long assistanceSheetId, String assistanceSheetStatus, Date date, String groupName, long idGroup, long idModule, long idSemestre, String module, Date myAttendanceTime, long semestre, String statusOfMyAssistance, String teacherName) {
        this.assistanceSheetId = assistanceSheetId;
        this.assistanceSheetStatus = assistanceSheetStatus;
        this.date = date;
        this.groupName = groupName;
        this.idGroup = idGroup;
        this.idModule = idModule;
        this.idSemestre = idSemestre;
        this.module = module;
        this.myAttendanceTime = myAttendanceTime;
        this.semestre = semestre;
        this.statusOfMyAssistance = statusOfMyAssistance;
        this.teacherName = teacherName;
    }

    public long getAssistanceSheetId() {
        return assistanceSheetId;
    }

    public void setAssistanceSheetId(long assistanceSheetId) {
        this.assistanceSheetId = assistanceSheetId;
    }

    public String getAssistanceSheetStatus() {
        return assistanceSheetStatus;
    }

    public void setAssistanceSheetStatus(String assistanceSheetStatus) {
        this.assistanceSheetStatus = assistanceSheetStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public long getIdModule() {
        return idModule;
    }

    public void setIdModule(long idModule) {
        this.idModule = idModule;
    }

    public long getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(long idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getMyAttendanceTime() {
        return myAttendanceTime;
    }

    public void setMyAttendanceTime(Date myAttendanceTime) {
        this.myAttendanceTime = myAttendanceTime;
    }

    public long getSemestre() {
        return semestre;
    }

    public void setSemestre(long semestre) {
        this.semestre = semestre;
    }

    public String getStatusOfMyAssistance() {
        return statusOfMyAssistance;
    }

    public void setStatusOfMyAssistance(String statusOfMyAssistance) {
        this.statusOfMyAssistance = statusOfMyAssistance;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
