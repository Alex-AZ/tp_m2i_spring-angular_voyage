package com.m2i.tpspringangular.voyage.services;

import java.util.Date;

public class DateService {

    private final Date startDate;
    private final Date endDate;

    public DateService(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinRange(Date testDate) {
        return testDate.getTime() >= startDate.getTime() && testDate.getTime() <= endDate.getTime();
    }
}
