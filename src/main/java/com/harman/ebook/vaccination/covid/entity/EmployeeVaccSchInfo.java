package com.harman.ebook.vaccination.covid.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.rest.core.annotation.RestResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@RestResource
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee_vacc_sch_info", schema = "covid")
@SequenceGenerator(name = "employeeVaccSchInfoSeq", sequenceName = "employee_vacc_sch_info_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class EmployeeVaccSchInfo extends BaseEntity{
	private static final long serialVersionUID = -384180042546432865L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeVaccSchInfoSeq")
	  @Column(name = "empVaccSchId", nullable = false, updatable = false, insertable = false)
	  private Integer empVaccSchId;

	  @Column(name = "empmasterid")
	  private Integer empMasterId;

	  @ManyToOne
	  @JoinColumn(name="empmasterid", referencedColumnName="empmasterid", updatable = false, insertable=false)
	  private EmployeeMaster employeeMaster;

	  @Column(name = "dateOfVaccination")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateOfVaccination;

	  @Column(name="slotAllocated")
	  private Short slotAllocated;

	  @Column(name = "bookingStatus")
	  private String bookingStatus;

	  @Column(name = "vaccineStatus")
	  private String vaccineStatus;

	  @Column(name="isBookingActive", nullable=false,updatable = false, insertable=false)
	  private Boolean isBookingActive;

	  @Column(name="doseLevel")
	  private Short doseLevel;

	  @Column(name="location")
	  private Short location;

	  @Column(name="vacType")
	  private Short vacType;
}
