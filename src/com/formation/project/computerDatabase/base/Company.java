package com.formation.project.computerDatabase.base;


public class Company {

    public Company(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer id;
    
    public String name;
    
    public Company() {}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
//    public static Model.Finder<Long,Company> find = new Model.Finder<Long,Company>(Long.class, Company.class);
//
//    public static Map<String,String> options() {
//        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
//        for(Company c: Company.find.orderBy("name").findList()) {
//            options.put(c.id.toString(), c.name);
//        }
//        return options;
//    }

}