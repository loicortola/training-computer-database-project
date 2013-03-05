package com.formation.project.computerDatabase.base;

public enum TableSort {
	
	
	NAME_ASC("computer_name ASC"),NAME_DESC("computer_name DESC"),
	INTRODUCED_ASC("introduced ASC"),INTRODUCED_DESC("introduced DESC"),
	DISCONTINUED_ASC("discontinued ASC"),DISCONTINUED_DESC("discontinued DESC"),
	COMPANY_ASC("company_name ASC"),COMPANY_DESC("company_name DESC");
	
	private String sortString;
	
	private TableSort(String sortString) {
		this.sortString = sortString;
	}
	
	public String getSortString() {
		return sortString;
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
