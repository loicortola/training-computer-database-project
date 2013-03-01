package com.formation.project.computerDatabase.base;

import java.util.ArrayList;
import java.util.List;

public class Computers {
	private List<Computer> computers = null;
	private Integer computerCount = null;
	private TableSort tableSort = null;
	
	
	
	public Computers() {
		super();
	}
	
	
	public Computers(List<Computer> computers, Integer computerCount,
			TableSort tableSort) {
		super();
		this.computers = computers;
		this.computerCount = computerCount;
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
	public Integer getComputerCount() {
		return computerCount;
	}
	public void setComputerCount(Integer computerCount) {
		this.computerCount = computerCount;
	}
	public TableSort getTableSort() {
		return tableSort;
	}
	public void setTableSort(TableSort tableSort) {
		this.tableSort = tableSort;
	}
	
	
}
