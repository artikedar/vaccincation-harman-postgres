package com.harman.ebook.vaccination.covid.entity;

import java.io.Serializable;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "person", schema = "covid")
@SequenceGenerator(name = "personseq", sequenceName = "person_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class Person extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -384180042546432865L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personseq")
	  @Column(name = "personId", nullable = false, updatable = false, insertable = false)
	  private Integer personId;

	  @Column(name="fullName")
	  private String fullName;

	  @Column(name="personAge")
	  private Short personAge;

	  @Column(name="gender")
	  private Short gender;

	  @Column(name="isDoseI", nullable=false,updatable = false, insertable=false)
	  private Boolean  isDoseI;

	  @Column(name = "dateOfDoseI")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateOfDoseI;

	  @Column(name="isDoseII", nullable=false,updatable = false, insertable=false)
	  private Boolean isDoseII;

	  @Column(name = "dateOfDoseII")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateOfDoseII;

	  @Column(name = "vacType", nullable = false)
	  private Short vacType;

	  @Column(name="emailId")
	  private String emailId;

	  @Column(name="country")
	  private String country;

	  @Column(name = "dateOfBirth")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateOfBirth;

	  @Column(name = "grade")
	  private String grade;

	  @Column(name="location")
	  private Short location;

	  @Column(name = "empmasterid")
	  private Integer empMasterId;

	  @ManyToOne
	  @JoinColumn(name="empmasterid", referencedColumnName="empmasterid", updatable = false, insertable=false)
	  private EmployeeMaster employeeMaster;
	  

}
