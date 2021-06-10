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

		@Column(name="manipalid")
		private String manipalid;

		@Column(name="cowinid")
		private String cowinid;

	  @Column(name="isRegistered")
	  private Boolean isRegistered;

	  @Column(name = "dateOfDoseI")
	  @Temporal(TemporalType.DATE)
	  private Date dateOfDoseI;

	  @Column(name = "vacType", nullable = false)
	  private Short vacType;

	  @Column(name = "empmasterid")
	  private Integer empMasterId;

	  @ManyToOne
	  @JoinColumn(name="empmasterid", referencedColumnName="empmasterid", updatable = false, insertable=false)
	  private EmployeeMaster employeeMaster;
	  
}
