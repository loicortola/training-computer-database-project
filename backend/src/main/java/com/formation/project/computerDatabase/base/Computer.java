package com.formation.project.computerDatabase.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="computer")
public class Computer {

    public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued,
			Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
		this.isVisible = true;
	}
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_computer")
	private Long id;
    
    @Column(nullable=false)
    private String name;
    
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(nullable=true)
    @Temporal(TemporalType.DATE)
    private LocalDate introduced;
    
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(nullable=true)
    @Temporal(TemporalType.DATE)
    private LocalDate discontinued;
    
    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;
    
    @Column(name="is_visible")
    private Boolean isVisible;
    
   

	public Computer() {}
    
    
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if(this.id == other.getId())
			return true;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		if(id != null)
			this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
    
	public Boolean isVisible() {
			return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
			this.isVisible = isVisible;
	}
}