package com.harman.ebook.vaccination.covid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "slot_info", schema = "covid")
@SequenceGenerator(name = "slotInfoSeq", sequenceName = "slot_info_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class SlotInfo extends BaseEntity {
	@Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slotInfoSeq")
	  @Column(name = "slotInfoId", nullable = false, updatable = false, insertable = false)
	  private Integer slotInfoId;

	  @Column(name="location")
	  private Short location;

	  @Column(name="slotNo")
	  private Short slotNo;

}
