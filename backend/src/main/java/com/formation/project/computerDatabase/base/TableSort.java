package com.formation.project.computerDatabase.base;

public enum TableSort {
	
	
	NAME_ASC("name",true),NAME_DESC("name",false),
	INTRODUCED_ASC("introduced",true),INTRODUCED_DESC("introduced",false),
	DISCONTINUED_ASC("discontinued",true),DISCONTINUED_DESC("discontinued",false),
	COMPANY_ASC("company.name",true),COMPANY_DESC("company.name",false);
	
	private String sortString;
	private Boolean isAsc;
	
	private TableSort(String sortString,Boolean isAsc) {
		this.sortString = sortString;
		this.isAsc = isAsc;
	}
	
	public String getSortString() {
		return sortString;
	}
	
	public Boolean isAsc() {
		return isAsc;
	}
	
	public static TableSort fromInteger(int x) {
	        switch(x) {
	        case 0:
	            return NAME_ASC;
	        case 1:
	            return NAME_DESC;
	        case 2:
	            return INTRODUCED_ASC;
	        case 3:
	            return INTRODUCED_DESC;
	        case 4:
	            return DISCONTINUED_ASC;
	        case 5:
	            return DISCONTINUED_DESC;
	        case 6:
	            return COMPANY_ASC;
	        case 7:
	            return COMPANY_DESC;	        
	        }
	        return null;
	}
}
