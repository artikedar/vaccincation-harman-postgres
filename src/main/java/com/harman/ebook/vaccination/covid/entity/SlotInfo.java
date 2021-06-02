package com.harman.ebook.vaccination.covid.entity;

import javax.persistence.*;

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

    @Column(name = "location")
    private Short location;

    @Column(name = "slotNo")
    private Short slotNo;

    @Column(name = "noOfDoses")
    private Short noOfDoses;

    @Column(name = "noOfBookedDoses")
    private Short noOfBookedDoses;

    @Column(name = "noOfAvailableDoses")
    private Short noOfAvailableDoses;

	@Column(name = "vacinvid")
	private Integer vacInvId;

	@ManyToOne
	@JoinColumn(name="vacInvId", referencedColumnName="vacInvId", updatable = false, insertable=false)
	private VaccineInventory vaccineInventory;

}
