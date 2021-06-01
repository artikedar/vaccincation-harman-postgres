package com.harman.ebook.vaccination.covid.entity;

import java.io.Serializable;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = -4232695967772349049L;

	  @Column(name="createdby", nullable = false, length = 255, updatable = false)
	  @CreatedBy
	  private String createdby;

	  @Column(name = "createdon", nullable = false, updatable = false)
	  @CreatedDate
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date createdOn;

	  @Column(name = "modifiedby", length = 255)
	  @LastModifiedBy
	  private String modifiedby;

	  @Column(name = "modifiedon")
	  @Temporal(TemporalType.TIMESTAMP)
	  @LastModifiedDate
	  private Date modifiedOn;

	  @Column(name = "isactive", nullable = false, updatable = true, insertable = false)
	  private Boolean isactive;

}
