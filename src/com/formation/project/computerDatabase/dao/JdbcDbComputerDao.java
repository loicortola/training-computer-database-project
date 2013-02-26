package com.formation.project.computerDatabase.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;

public class JdbcDbComputerDao implements IComputerDao {
	
	public JdbcDbComputerDao() {
	}

	@Override
	public void addComputer(Computer computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComputer(Computer computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComputer(Integer computerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Computer getComputer(Integer computerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Computer> getComputers(String name) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		ResultSet rs 						= null;
		ArrayList<Computer> computers 		= null;
		ICompanyDao companyDao				= DaoFactory.getCompanyDao();
		
		HashMap<Integer,Company> companies	= companyDao.getCompanies("");
		
		companyDao							= null;
		
		try {
				cs = conn.prepareCall("{CALL getComputers(?)}");
				cs.setString("p_name", name);
				rs = cs.executeQuery();
				computers = new ArrayList<Computer>();

				while(rs.next())
				{
					computers.add(new Computer(rs.getInt("id_computer"),rs.getString("name"),rs.getTimestamp("introduced"),rs.getTimestamp("discontinued"),companies.get(rs.getInt("id_company"))));
				}
			} catch (SQLException e) {
				System.out.println("Error in getComputers:" +e.getMessage());
			} finally {
				JdbcConnectionFactory.closeConn(conn);	
			}

		return computers;
	}

}
