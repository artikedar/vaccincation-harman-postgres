package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VaccineInventorySchedule {

    @JsonProperty("vacType")
    private Short vacType;

    @JsonProperty("location")
    private Short location;

    @JsonProperty("schedule")
    private List<Schedule> schedule;

    public VaccineInventorySchedule() {
    }

    public VaccineInventorySchedule(Short vacType, Short location, List<Schedule> schedule) {
        this.vacType = vacType;
        this.location = location;
        this.schedule = schedule;
    }

    public Short getVacType() {
        return vacType;
    }

    public void setVacType(Short vacType) {
        this.vacType = vacType;
    }

    public Short getLocation() {
        return location;
    }

    public void setLocation(Short location) {
        this.location = location;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
