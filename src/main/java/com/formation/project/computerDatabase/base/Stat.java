package com.formation.project.computerDatabase.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stats")
public class Stat {

    public Stat(Long id, String statType) {
		super();
		this.id = id;
		this.statType = statType;
	}
    @Id
   	@GeneratedValue
   	@Column(name="id_computer")
	private Long id;
    
    @Column(name="stat_type",nullable=false)
    private String statType;
    
    public Stat() {}    

	public Long getId() {
		return id;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}