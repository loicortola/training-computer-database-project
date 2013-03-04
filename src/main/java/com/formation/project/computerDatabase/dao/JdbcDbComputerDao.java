package com.formation.project.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.ComputerBuilder;

@Repository
public class JdbcDbComputerDao implements IComputerDao {
	
	public JdbcDbComputerDao() {
		
	}
	
	@Override
	public Integer addComputer(Computer computer) {
		StringBuilder query	 	= new StringBuilder();
		PreparedStatement ps 	= null;
		Connection conn 		= DataSourceFactory.INSTANCE.getConn();
		
		try {
			query.append("INSERT INTO computer(name, introduced, discontinued, id_company) ");
			query.append("VALUES(?,?,?,?);");
			ps = conn.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, computer.getName());
			ps.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
			if(computer.getDiscontinued() == null)
				ps.setTimestamp(3, null);
			else
				ps.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
			ps.setInt(4, computer.getCompany().getId());			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {
				return rs.getInt(1);
			}
			rs.close();	
			ps.close();
			
		} catch (SQLException e) {
			System.err.println("Error in addComputer:" +e.getMessage());
		} finally {
			
		}
		return 0;
	}

	@Override
	public void updateComputer(Computer computer) {
		StringBuilder query	 = new StringBuilder();
		PreparedStatement ps = null;
		Connection conn = DataSourceFactory.INSTANCE.getConn();
		
		try {
			query.append("UPDATE computer ");
			query.append("SET name = ?, introduced = ?, discontinued = ?, id_company = ? ");
			query.append("WHERE id_computer = ?;");
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, computer.getName());
			ps.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
			if(computer.getDiscontinued() == null)
				ps.setTimestamp(3, null);
			else
				ps.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
			ps.setInt(4, computer.getCompany().getId());
			ps.setInt(5, computer.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error in updateComputer:" +e.getMessage());
		}
	}

	@Override
	public void deleteComputer(Integer computerId) {
		StringBuilder query	 = new StringBuilder();
		PreparedStatement ps = null;
		
		Connection conn = DataSourceFactory.INSTANCE.getConn();
		
		try {
			query.append("UPDATE computer ");
			query.append("SET is_visible = 0 ");
			query.append("WHERE id_computer = ?;");
			ps = conn.prepareStatement(query.toString());
			ps.setInt(1, computerId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error in deleteComputer:" +e.getMessage());
		}
	}

	@Override
	public Computer getComputer(Integer computerId) {
		Connection conn 					= DataSourceFactory.INSTANCE.getConn();
		PreparedStatement ps 				= null;
		ResultSet rs 						= null;
		Computer computer			 		= null;
		
		try {
				ps = conn.prepareStatement("SELECT computer.*, company.* FROM computer INNER JOIN company on computer.id_company = company.id_company WHERE id_computer = ?;");
				ps.setInt(1, computerId);
				rs = ps.executeQuery();				
				while(rs.next())
				{
					computer = new ComputerBuilder()
									.id(rs.getInt("id_computer"))
									.name(rs.getString("computer.name"))
									.introduced(rs.getTimestamp("introduced"))
									.discontinued(rs.getTimestamp("discontinued"))
									.company(new Company(rs.getInt("company.id_company"),rs.getString("company.name")))
									.build();
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Error in getComputer:" +e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Error in getComputer:" +e.getMessage());
			}

		return computer;
	}

	@Override
	public List<Computer> getComputers(Integer currentPage, Integer resultsPerPage, String sortBy, String name) {
		StringBuilder query					= new StringBuilder();
		Connection conn 					= DataSourceFactory.INSTANCE.getConn();
		PreparedStatement ps 				= null;
		ResultSet rs 						= null;
		ArrayList<Computer> computers 		= null;
		
		query.append("SELECT computer.id_computer, computer.name as name, computer.introduced, computer.discontinued, company.id_company, company.name as company FROM computer ");
		query.append("INNER JOIN company ON computer.id_company = company.id_company ");
		query.append("WHERE (LOWER(computer.name) LIKE CONCAT('%',?,'%') OR ? = '') ");
		query.append("AND is_visible = 1 ");		
		query.append(" ORDER BY ");
		query.append(sortBy);
		query.append(" LIMIT ?,?;");
		try {
				ps = conn.prepareStatement(query.toString());
				ps.setString(1, name);
				ps.setString(2, name);
				ps.setInt(3,(currentPage-1)*resultsPerPage);
				ps.setInt(4,resultsPerPage);
				
				
				rs = ps.executeQuery();
				computers = new ArrayList<Computer>();

				while(rs.next())
				{
					computers.add(new Computer(rs.getInt("computer.id_computer"),rs.getString("computer.name"),rs.getTimestamp("computer.introduced"),rs.getTimestamp("computer.discontinued"),new Company(rs.getInt("company.id_company"),rs.getString("company"))));
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Error in getComputers:" +e.getMessage());
			}

		return computers;
	}

	@Override
	public Integer getComputerCount(String name) {
		StringBuilder query					= new StringBuilder();
		Connection conn 					= DataSourceFactory.INSTANCE.getConn();
		PreparedStatement ps 				= null;
		ResultSet rs 						= null;
		Integer count						= null;
		try {
				query.append("SELECT COUNT(*) AS count FROM computer ");
				query.append("WHERE (LOWER(name) LIKE LOWER(CONCAT('%',?,'%')) OR ? = '') ");
				query.append("AND is_visible = 1;");
				ps = conn.prepareStatement(query.toString());
				ps.setString(1, name);
				ps.setString(2, name);
				rs = ps.executeQuery();
				while(rs.next())
				{
					count = rs.getInt("count");
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Error in getComputerCount:" +e.getMessage());
			}

		return count;
	}

	@Override
	public Integer getLastInsertId() {
		
		Connection conn 					= DataSourceFactory.INSTANCE.getConn();
		PreparedStatement ps 				= null;
		ResultSet rs 						= null;
		Integer lastInsertId				= null;
		try {
				ps = conn.prepareStatement("SELECT LAST_INSERT_ID() AS id;");
				rs = ps.executeQuery();
				while(rs.next())
				{
					lastInsertId = rs.getInt("id");
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Error in getLastInsertId:" +e.getMessage());
			}

		return lastInsertId;
	}

}
