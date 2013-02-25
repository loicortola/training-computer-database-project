package com.formation.project.computerDatabase.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Computer {

    public Computer(Integer id, String name, Date introduced, Date discontinued,
			Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Integer id;
    
    public String name;
    
    public Date introduced;
    
    public Date discontinued;
    
    public Company company;
    
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



	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}
	
	public String getFormatedIntroduced() {
		DateFormat df = new SimpleDateFormat("d MMMM yyyy");
		if(introduced != null)
			return df.format(introduced);
		return "N/A";
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}
	
	public String getFormatedDiscontinued() {
		DateFormat df = new SimpleDateFormat("d MMMM yyyy");
		if(discontinued != null)
			return df.format(discontinued);
		return "N/A";
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
    
    /**
     * Generic query helper for entity Computer with id Long
     */
//    public static Finder<Long,Computer> find = new Finder<Long,Computer>(Long.class, Computer.class); 
//    
//    /**
//     * Return a page of computer
//     *
//     * @param page Page to display
//     * @param pageSize Number of computers per page
//     * @param sortBy Computer property used for sorting
//     * @param order Sort order (either or asc or desc)
//     * @param filter Filter applied on the name column
//     */
//    public static Page<Computer> page(int page, int pageSize, String sortBy, String order, String filter) {
//        return 
//            find.where()
//                .ilike("name", "%" + filter + "%")
//                .orderBy(sortBy + " " + order)
//                .fetch("company")
//                .findPagingList(pageSize)
//                .getPage(page);
//    }
    
}