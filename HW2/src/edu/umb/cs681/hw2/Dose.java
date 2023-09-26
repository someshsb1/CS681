package edu.umb.cs681.hw2;

import java.time.LocalDate;

public class Dose {
    private String vacProductName;
    private String lotNumber;
    private LocalDate date;
    private String vacSite;

    public Dose (String VacProductName, String lotNumber, LocalDate date, String vacSite) {
        this.vacProductName = VacProductName;
        this.lotNumber = lotNumber;
        this.date = date;
        this.vacSite = vacSite;
    }

    public String getVacProductName() {
        return vacProductName;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getVacSite() {
        return vacSite;
    }
}
