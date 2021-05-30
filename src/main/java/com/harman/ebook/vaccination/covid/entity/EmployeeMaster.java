package com.harman.ebook.vaccination.covid.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.rest.core.annotation.RestResource;

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
@Table(name = "employee_master", schema = "covid")
@SequenceGenerator(name = "empmasterseq", sequenceName = "employee_master_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class EmployeeMaster extends BaseEntity {
	private static final long serialVersionUID = -384180042546432865L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empmasterseq")
	  @Column(name = "empmasterid", nullable = false, updatable = false, insertable = false)
	  private Integer empMasterId;

	  @Column(name = "empid", nullable = false)
	  private Integer employeeId;

	  @Column(name = "dateOfjoining")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateOfJoining;

	  @Column(name="grade")
	  private String grade;

	  @Column(name="workplacelocation")
	  private String workplaceLocation;
	  
	  @Column(name="employmenttype")
	  private Short employmenttype;

	public Integer getEmpMasterId() {
		return empMasterId;
	}

	public void setEmpMasterId(Integer empMasterId) {
		this.empMasterId = empMasterId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getWorkplaceLocation() {
		return workplaceLocation;
	}

	public void setWorkplaceLocation(String workplaceLocation) {
		this.workplaceLocation = workplaceLocation;
	}

	public Short getEmploymenttype() {
		return employmenttype;
	}

	public void setEmploymenttype(Short employmenttype) {
		this.employmenttype = employmenttype;
	}
	  
	  
	  
}
