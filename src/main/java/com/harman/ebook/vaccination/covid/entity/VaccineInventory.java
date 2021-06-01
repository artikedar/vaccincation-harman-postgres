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

	  @Column(name="vacType")
	  private Short vacType;

	  @Column(name = "dateOfAvailability")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateOfAvailability;

	  @Column(name = "noOfDoses")
	  private Short noOfDoses;

	  @Column(name="location")
	  private Short location;
	  
	  

	public VaccineInventory() {
		super();
	}



	public VaccineInventory(Integer vacInvId, Short vacType, Date dateOfAvailability, Short noOfDoses, Short location) {
		super();
		this.vacInvId = vacInvId;
		this.vacType = vacType;
		this.dateOfAvailability = dateOfAvailability;
		this.noOfDoses = noOfDoses;
		this.location = location;
	}



	public Integer getVacInvId() {
		return vacInvId;
	}



	public void setVacInvId(Integer vacInvId) {
		this.vacInvId = vacInvId;
	}



	public Short getVacType() {
		return vacType;
	}



	public void setVacType(Short vacType) {
		this.vacType = vacType;
	}



	public Date getDateOfAvailability() {
		return dateOfAvailability;
	}



	public void setDateOfAvailability(Date dateOfAvailability) {
		this.dateOfAvailability = dateOfAvailability;
	}



	public Short getNoOfDoses() {
		return noOfDoses;
	}



	public void setNoOfDoses(Short noOfDoses) {
		this.noOfDoses = noOfDoses;
	}



	public Short getLocation() {
		return location;
	}



	public void setLocation(Short location) {
		this.location = location;
	}
	
	
	  
	  

}
