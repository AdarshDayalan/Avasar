package com.example.realfblaapp;

public class AttendenceDatabase {
    String attendanceId;
    String attendanceTimeDate;
    String attendanceCheckNum;

    public AttendenceDatabase(String attendanceCheckNum, String attendanceId, String attendanceTimeDate) {
        this.attendanceId =attendanceId;
        this.attendanceTimeDate = attendanceTimeDate;
        this.attendanceCheckNum = attendanceCheckNum;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public String getAttendanceTimeDate() {
        return attendanceTimeDate;
    }

    public String getAttendanceCheckNum() {
        return attendanceCheckNum;
    }
}
