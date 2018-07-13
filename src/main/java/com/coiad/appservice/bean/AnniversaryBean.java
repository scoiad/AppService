package com.coiad.appservice.bean;

import com.coiad.appservice.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AnniversaryBean {
    private int id;
    private String anniversaryName;
    private Date anniversaryDate;
    private Date hundredDate;
    private boolean hundredFlag;
    private boolean monthFlag;
    private boolean yearFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnniversaryName() {
        return anniversaryName;
    }

    public void setAnniversaryName(String anniversaryName) {
        this.anniversaryName = anniversaryName;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getHundredDate() {
        return hundredDate;
    }

    public void setHundredDate(Date hundredDate) {
        this.hundredDate = hundredDate;
    }

    public boolean isMonthFlag() {
        return monthFlag;
    }

    public void setMonthFlag(boolean monthFlag) {
        this.monthFlag = monthFlag;
    }

    public boolean isYearFlag() {
        return yearFlag;
    }

    public void setYearFlag(boolean yearFlag) {
        this.yearFlag = yearFlag;
    }

    public boolean isHundredFlag() {
        return hundredFlag;
    }

    public void setHundredFlag(boolean hundredFlag) {
        this.hundredFlag = hundredFlag;
    }
}
