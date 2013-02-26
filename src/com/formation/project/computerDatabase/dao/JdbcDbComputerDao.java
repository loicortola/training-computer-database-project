package com.formation.project.computerDatabase.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.ComputerBuilder;

public class JdbcDbComputerDao implements IComputerDao {
	
	public JdbcDbComputerDao() {
	}

	@Override
	public void addComputer(Computer computer) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		
		try {
			cs = conn.prepareCall("{CALL addComputer(?,?,?,?)}");
			cs.setString("p_name", computer.getName());
			cs.setTimestamp("p_introduced", new Timestamp(computer.getIntroduced().getTime()));
			if(computer.getDiscontinued() == null)
				cs.setTimestamp("p_discontinued", null);
			else
				cs.setTimestamp("p_discontinued", new Timestamp(computer.getDiscontinued().getTime()));
			cs.setInt("p_id_company", computer.getCompany().getId());
			cs.executeQuery();
				
		} catch (SQLException e) {
			System.out.println("Error in addComputer:" +e.getMessage());
		} finally {
			JdbcConnectionFactory.closeConn(conn);	
		}
	}

	@Override
	public void updateComputer(Computer computer) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		
		try {
			cs = conn.prepareCall("{CALL updateComputer(?,?,?,?,?)}");
			cs.setInt("p_id_computer", computer.getId());
			cs.setString("p_name", computer.getName());
			cs.setTimestamp("p_introduced", new Timestamp(computer.getIntroduced().getTime()));
			if(computer.getDiscontinued() == null)
				cs.setTimestamp("p_discontinued", null);
			else
				cs.setTimestamp("p_discontinued", new Timestamp(computer.getDiscontinued().getTime()));
			cs.setInt("p_id_company", computer.getCompany().getId());
			cs.executeQuery();
				
		} catch (SQLException e) {
			System.out.println("Error in updateComputer:" +e.getMessage());
		} finally {
			JdbcConnectionFactory.closeConn(conn);	
		}
		
	}

	@Override
	public void deleteComputer(Integer computerId) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		
		try {
			cs = conn.prepareCall("{CALL deleteComputer(?)}");
			cs.setInt("p_id", computerId);
			cs.executeQuery();
				
		} catch (SQLException e) {
			System.out.println("Error in deleteComputer:" +e.getMessage());
		} finally {
			JdbcConnectionFactory.closeConn(conn);	
		}
	}

	@Override
	public Computer getComputer(Integer computerId) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		ResultSet rs 						= null;
		Computer computer			 		= null;
		ICompanyDao companyDao				= DaoFactory.getCompanyDao();
		
		HashMap<Integer,Company> companies	= companyDao.getCompanies("");
		
		try {
				cs = conn.prepareCall("{CALL getComputer(?)}");
				cs.setInt("p_id", computerId);
				rs = cs.executeQuery();
				
				while(rs.next())
				{
					computer = new ComputerBuilder()
									.id(rs.getInt("id_computer"))
									.name(rs.getString("name"))
									.introduced(rs.getTimestamp("introduced"))
									.discontinued(rs.getTimestamp("discontinued"))
									.company(companies.get(rs.getInt("id_company")))
									.build();
				}
			} catch (SQLException e) {
				System.out.println("Error in getComputer:" +e.getMessage());
			} finally {
				JdbcConnectionFactory.closeConn(conn);	
			}

		return computer;
	}

	@Override
	public ArrayList<Computer> getComputers(Integer currentPage, Integer resultsPerPage, String name) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		ResultSet rs 						= null;
		ArrayList<Computer> computers 		= null;
		ICompanyDao companyDao				= DaoFactory.getCompanyDao();
		
		HashMap<Integer,Company> companies	= companyDao.getCompanies("");
		
		try {
				cs = conn.prepareCall("{CALL getComputers(?,?,?)}");
				cs.setInt("p_offset",(currentPage-1)*resultsPerPage);
				cs.setInt("p_results_per_page",resultsPerPage);
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

	@Override
	public Integer getComputerCount(String name) {
		Connection conn 					= JdbcConnectionFactory.getConn();
		CallableStatement cs 				= null;
		ResultSet rs 						= null;
		Integer count						= null;
		try {
				cs = conn.prepareCall("{CALL getComputerCount(?)}");
				cs.setString("p_name", name);
				rs = cs.executeQuery();		

				while(rs.next())
				{
					count = rs.getInt("count");
				}
			} catch (SQLException e) {
				System.out.println("Error in getComputerCount:" +e.getMessage());
			} finally {
				JdbcConnectionFactory.closeConn(conn);	
			}

		return count;
	}

}
