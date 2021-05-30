package com.harman.ebook.vaccination.covid.entity;

/*import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;*/
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author Bhagwat Rokade
 * @project HarmanVaccination
 * @created 5/30/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@RestResource
@Table(name="LOV", schema="covid" )
@SequenceGenerator(name="seqLovSeq", sequenceName = "LOV_SEQ", schema="covid", initialValue=1, allocationSize=1)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY,region="lov")
public class Lov implements Serializable {
	private static final long serialVersionUID = 8828225098455296763L;

	  @Id
	  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seqLovSeq")
	  @Column(name="lovid", nullable=false, updatable = false, insertable=false)
	  private Integer lovid;

	  @Column(name="lovsequence", length=255)
	  private String lovsequence;

	  @Column(name="valueid")
	  private Integer valueid;

	  @Column(name="value", length=255)
	  private String  value;

	  @Column(name="lovtypeid", nullable=false, updatable = false, insertable=false)
	  private Short lovtypeid;

	  @ManyToOne
	  @JoinColumn(name="lovtypeid", referencedColumnName="lovtypeid", updatable = false, insertable=false)
	  private LovType igcLovType;

	  @Column(name="isactive", nullable=false,updatable = false, insertable=false)
	  private Boolean isActive;
}
