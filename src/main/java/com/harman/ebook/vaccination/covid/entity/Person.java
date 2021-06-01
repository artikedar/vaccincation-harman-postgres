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
@Getter @Setter
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
	  
	  

	public Person() {
		super();
	}

	public Person(Integer personId, String fullName, Short personAge, Short gender, Boolean isDoseI, Date dateOfDoseI,
			Boolean isDoseII, Date dateOfDoseII, Short vacType, String emailId, String country, Date dateOfBirth,
			String grade, Short location, Integer empMasterId, EmployeeMaster employeeMaster) {
		super();
		this.personId = personId;
		this.fullName = fullName;
		this.personAge = personAge;
		this.gender = gender;
		this.isDoseI = isDoseI;
		this.dateOfDoseI = dateOfDoseI;
		this.isDoseII = isDoseII;
		this.dateOfDoseII = dateOfDoseII;
		this.vacType = vacType;
		this.emailId = emailId;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
		this.grade = grade;
		this.location = location;
		this.empMasterId = empMasterId;
		this.employeeMaster = employeeMaster;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Short getPersonAge() {
		return personAge;
	}

	public void setPersonAge(Short personAge) {
		this.personAge = personAge;
	}

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public Boolean getIsDoseI() {
		return isDoseI;
	}

	public void setIsDoseI(Boolean isDoseI) {
		this.isDoseI = isDoseI;
	}

	public Date getDateOfDoseI() {
		return dateOfDoseI;
	}

	public void setDateOfDoseI(Date dateOfDoseI) {
		this.dateOfDoseI = dateOfDoseI;
	}

	public Boolean getIsDoseII() {
		return isDoseII;
	}

	public void setIsDoseII(Boolean isDoseII) {
		this.isDoseII = isDoseII;
	}

	public Date getDateOfDoseII() {
		return dateOfDoseII;
	}

	public void setDateOfDoseII(Date dateOfDoseII) {
		this.dateOfDoseII = dateOfDoseII;
	}

	public Short getVacType() {
		return vacType;
	}

	public void setVacType(Short vacType) {
		this.vacType = vacType;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Short getLocation() {
		return location;
	}

	public void setLocation(Short location) {
		this.location = location;
	}

	public Integer getEmpMasterId() {
		return empMasterId;
	}

	public void setEmpMasterId(Integer empMasterId) {
		this.empMasterId = empMasterId;
	}

	public EmployeeMaster getEmployeeMaster() {
		return employeeMaster;
	}

	public void setEmployeeMaster(EmployeeMaster employeeMaster) {
		this.employeeMaster = employeeMaster;
	}
	  
	  
}
