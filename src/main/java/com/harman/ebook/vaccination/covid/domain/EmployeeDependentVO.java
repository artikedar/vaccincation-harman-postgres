package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDependentVO {

    @JsonProperty("empMasterId")
    private Integer empMasterId;

    @JsonProperty("dependents")
    private List<DependentVO> dependents;
}
