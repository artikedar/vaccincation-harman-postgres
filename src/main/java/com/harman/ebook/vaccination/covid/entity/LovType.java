package com.harman.ebook.vaccination.covid.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@RestResource
@Table(name="LOV_TYPE", schema="covid" )
public class LovType implements Serializable {
	private static final long serialVersionUID = 1692148954698178273L;

	  @Id
	  @Column(name="lovtypeid", nullable=false, updatable = false, insertable=false)
	  private Short  lovtypeid;

	  @Column(name="name", length=255)
	  private String name;

	  @Column(name="displayname", length=255)
	  private String  displayname;

	  @Column(name="IsActive", nullable=false,updatable = false, insertable=false)
	  private Boolean isactive;
}
