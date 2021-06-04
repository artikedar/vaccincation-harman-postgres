package com.harman.ebook.vaccination.covid.entity;


import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 5/29/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@RestResource
@EqualsAndHashCode(callSuper = true)
@Table(name = "vaccine_inventory", schema = "covid")
@SequenceGenerator(name = "vaccineInventorySeq", sequenceName = "vaccine_inventory_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class VaccineInventory extends BaseEntity {

    private static final long serialVersionUID = -384180042546432865L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccineInventorySeq")
    @Column(name = "vacInvId", nullable = false, updatable = false, insertable = false)
    private Integer vacInvId;

    @Column(name = "vacType")
    private Short vacType;

    @Column(name = "dateofavailability")
    @Temporal(TemporalType.DATE)
    private Date dateOfAvailability;

    @Column(name = "totalNoOfDoses")
    private Short totalNoOfDoses;

    @Column(name = "noOfBookedDoses")
    private Short noOfBookedDoses;

    @Column(name = "noOfAvailableDoses")
    private Short noOfAvailableDoses;

    @Column(name = "location")
    private Short location;

}
