package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@JsonPropertyOrder({
        "personId",
        "empMasterId",
        "empVaccAppId",
        "fullName",
        "slotNo",
        "slotName",
        "locationName",
        "dateOfVaccination"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonCsvVO implements Serializable {

    @JsonProperty("personId")
    private Integer personId;

    @JsonProperty("empMasterId")
    private Integer empMasterId;

    @JsonProperty("empVaccAppId")
    private Integer empVaccAppId;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("slotNo")
    private Short slotNo;

    @JsonProperty("slotName")
    private String slotName;

    @JsonProperty("locationName")
    private String locationName;

    @JsonProperty("dateOfVaccination")
    private String dateOfVaccination;

    @Override
    public String toString() {
        return  personId + ","
                 + empMasterId + ","
                + empVaccAppId + ","
                 + fullName + ","
                + slotNo + ","+
                 slotName +  ","+
                 locationName +  ","
                + dateOfVaccination;

    }
}
