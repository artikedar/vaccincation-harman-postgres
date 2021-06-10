package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonRegisterDTO {
	 @JsonProperty("personId")
    private Integer personId;

    @JsonProperty("cowinid")
    private String cowinid;

    @JsonProperty("manipalid")
    private String manipalid;

    @JsonProperty("dateOfDoseI")
    private Date dateOfDoseI;
 }

