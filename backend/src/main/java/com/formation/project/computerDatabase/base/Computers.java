package com.formation.project.computerDatabase.base;

import java.util.ArrayList;
import java.util.List;

public class Computers {
	private List<Computer> computers = null;
	private Long computerCount  	 = null;
	private Long pageCount	    	 = null;
	private Integer currentPage		 = null;
	private Integer numberOfElements = null;
	private TableSort tableSort 	 = null;
	
	
	
	public Computers() {
		super();
	}
	
	
	public Computers(List<Computer> computers, Long computerCount,
			TableSort tableSort) {
		super();
		this.computers = computers;
		this.computerCount = computerCount;
		this.tableSort = tableSort;
	}
	
	public Computers(List<Computer> computers, Integer computerCount,
			TableSort tableSort) {
		super();
		this.computers = computers;
		this.computerCount = computerCount.longValue();
		this.tableSort = tableSort;
	}

	public Integer size() {
		return computers.size();
	}
	
	public List<Computer> list() {
		return computers;
	}

	public List<Computer> getComputers() {
		return computers;
	}
	public void setComputers(ArrayList<Computer> computers) {
		this.computers = computers;
	}
	public Long getComputerCount() {
		return computerCount;
	}
	public void setComputerCount(Long computerCount) {
		this.computerCount = computerCount;
	}
	public TableSort getTableSort() {
		return tableSort;
	}
	public void setTableSort(TableSort tableSort) {
		this.tableSort = tableSort;
	}
	public Long getPageCount() {
		return pageCount;
	}	
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}	
	public Integer getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
