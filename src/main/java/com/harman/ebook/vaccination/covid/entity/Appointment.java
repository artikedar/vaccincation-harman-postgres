package com.harman.ebook.vaccination.covid.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "appointment", schema = "covid")
@SequenceGenerator(name = "appointmentseq", sequenceName = "appointment_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class Appointment extends BaseEntity {
	private static final long serialVersionUID = -384180042546432865L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentseq")
	@Column(name = "appointmentid", nullable = false, updatable = false, insertable = false)
	private Integer appointmentId;

	@Column(name = "empmasterid")
	private Integer empMasterId;

	@Column(name = "personid")
	@OneToMany(mappedBy="appointment")
	private List<Person> person;
	
	@Column(name="location")
	private Short location;
	
	@Column(name="bookingdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookingDate;
	
	@Column(name="slotno")
	private Short slotNo;
	
	@Column(name="appointmentstatus")
	private Short appointmentStatus;
	
}
