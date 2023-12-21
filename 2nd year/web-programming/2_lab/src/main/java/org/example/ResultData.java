package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class ResultData {
    private final double x;
    private final BigDecimal y;
    private final double r;
    private final boolean isInside;
    private final double scriptExecutionTime;
    private final LocalDateTime localDateTime;
    private String formattedScriptExecutionTime;

    public ResultData(double x, BigDecimal y, double r, boolean isInside, double scriptExecutionTime, LocalDateTime localDateTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInside = isInside;
        this.scriptExecutionTime = scriptExecutionTime;
        this.localDateTime = localDateTime;
    }

    public double getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isInside() {
        return isInside;
    }

    public double getScriptExecutionTime() {
        return scriptExecutionTime;
    }

    public String getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy, hh:mm:ss a");
        return localDateTime.format(formatter);
    }

    public void setFormattedScriptExecutionTime(String formattedScriptExecutionTime) {
        this.formattedScriptExecutionTime = formattedScriptExecutionTime;
    }
}
