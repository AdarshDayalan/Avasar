package com.example.realfblaapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendenceDatabase {
    String attendanceId;
    String attendanceTimeDate;
    String attendanceCheckNum;

    public AttendenceDatabase(String attendanceCheckNum, String attendanceId, String attendanceTimeDate) {
        this.attendanceId = attendanceId;
        this.attendanceTimeDate = attendanceTimeDate;
        this.attendanceCheckNum = attendanceCheckNum;
    }

    public String getAttendanceId() {
        return this.attendanceId;
    }

    public String getAttendanceTimeDate() {
        return this.attendanceTimeDate;
    }

    public String getAttendanceCheckNum() {
        return this.attendanceCheckNum;
    }
}
