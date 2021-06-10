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
	  @Temporal(TemporalType.DATE)
	  private Date dateOfJoining;

	  @Column(name="employmenttype")
	  private Short employmenttype;

		@Column(name="fullname")
		private String fullname;
}
