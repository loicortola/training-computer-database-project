package com.formation.project.computerDatabase.model;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.formation.project.computerDatabase.base.Computer;

@XmlRootElement(name = "computerList")
public class ComputerList {

	@XmlElement(name = "computer", required = true)
	private List<Computer> list;

	public ComputerList() {
	}

	public ComputerList(List<Computer> list) {
		this.list = list;
	}

	public List<Computer> getList() {
		return list;
	}
}
