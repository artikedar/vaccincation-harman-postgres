package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {

    @JsonProperty("date")
    private String date;

    @JsonProperty("noOfDoses")
    private Short noOfDoses;

    public Schedule() {
    }

    public Schedule(String date, Short noOfDoses) {
        this.date = date;
        this.noOfDoses = noOfDoses;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Short getNoOfDoses() {
        return noOfDoses;
    }

    public void setNoOfDoses(Short noOfDoses) {
        this.noOfDoses = noOfDoses;
    }
}
