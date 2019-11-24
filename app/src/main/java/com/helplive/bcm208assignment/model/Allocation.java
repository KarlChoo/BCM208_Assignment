package com.helplive.bcm208assignment.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Allocation {
    private LocalDate fromDate;
    private LocalDate endDate;
    private int duration;   // in 12 or 18 months


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Allocation(Application application,
                      int duration, LocalDate fromDate) {
        setDuration(duration);
        setFromDate(fromDate);
        setEndDate(fromDate.plusMonths(duration).minusDays(1));
    }

    /**
     * @return the fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String toString() {
        return "";
    }

}