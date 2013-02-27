package com.formation.project.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.formation.project.computerDatabase.base.Company;

public class JdbcDbCompanyDao implements ICompanyDao {
	
	private DaoFactory daoFactory = null;
	
	public JdbcDbCompanyDao() {
	}
	
	public JdbcDbCompanyDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public HashMap<Integer,Company> getCompanies(String name) {
		Connection conn 				= daoFactory.getConn();
		PreparedStatement ps 			= null;
		ResultSet rs 					= null;
		HashMap<Integer,Company> companies 	= null;
		
		try {
				ps = conn.prepareStatement("SELECT * FROM company WHERE ? = (name OR '');");
				ps.setString(1, name);
				rs = ps.executeQuery();
				companies = new HashMap<Integer,Company>();

				while(rs.next())
				{
					companies.put(rs.getInt("id_company"),new Company(rs.getInt("id_company"),rs.getString("name")));					
				}
			} catch (SQLException e) {
				System.out.println("Error in getCompanies:" +e.getMessage());
			} finally {
				DaoFactory.closeConn(conn);	
			}

		return companies;
	}

	@Override
	public Company getCompany(Integer companyId) {
		Connection conn 				= daoFactory.getConn();
		PreparedStatement ps 			= null;
		ResultSet rs 					= null;
		Company company					= null;
		try {
				ps = conn.prepareCall("SELECT * FROM company WHERE id_company = ?;");
				ps.setInt(1, companyId);
				rs = ps.executeQuery();

				while(rs.next())
				{
					company = new Company(rs.getInt("id_company"),rs.getString("name"));					
				}
			} catch (SQLException e) {
				System.out.println("Error in getCompany:" +e.getMessage());
			} finally {
				DaoFactory.closeConn(conn);	
			}

		return company;
	}

}
