package com.formation.project.computerDatabase.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stats")
public class Stat {

	public Stat() {}
	
    public Stat(Long idComputer, String statType) {
		super();
		this.idComputer = idComputer;
		this.statType = statType;
	}
    
   	@Id
   	@GeneratedValue
	@Column(name="id_stats")
	private Long id;
    
   	@Column(name="id_computer")
	private Long idComputer;
    
    @Column(name="stat_type",nullable=false)
    private String statType;   

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
	public Long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(Long idComputer) {
		this.idComputer = idComputer;
	}
	
	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

    
}