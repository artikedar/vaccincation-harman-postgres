package com.harman.ebook.vaccination.covid.entity;

import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@RestResource
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee_vacc_appointment_info", schema = "covid")
@SequenceGenerator(name = "employeeVaccAppointmentInfoSeq", sequenceName = "employee_vacc_appointment_info_seq", schema = "covid", initialValue = 1, allocationSize = 1)
public class EmployeeVaccAppointmentInfo extends BaseEntity {
    private static final long serialVersionUID = -384180042546432865L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeVaccAppointmentInfoSeq")
    @Column(name = "empVaccAppId", nullable = false, updatable = false, insertable = false)
    private Integer empVaccAppId;

    @Column(name = "personId")
    private Integer personId;

    @Column(name = "dateOfVaccination")
    @Temporal(TemporalType.DATE)
    private Date dateOfVaccination;

    @Column(name = "status")
    private Short status;

    @Column(name = "isBookingActive", nullable = false, updatable = false, insertable = false)
    private Boolean isBookingActive;

    @Column(name = "doseLevel")
    private Short doseLevel;

    @Column(name = "location")
    private Short location;

    @Column(name = "vacType")
    private Short vacType;

    @Column(name = "slotNo")
    private Short slotNo;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "personId", updatable = false, insertable = false)
    private Person person;
}
