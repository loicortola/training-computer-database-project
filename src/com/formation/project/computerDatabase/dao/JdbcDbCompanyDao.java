package com.formation.project.computerDatabase.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.formation.project.computerDatabase.base.Company;

public class JdbcDbCompanyDao implements CompanyDao {
	private JdbcConnectionFactory dbh = null;
	
	public JdbcDbCompanyDao() {
		dbh = new JdbcConnectionFactory();
	}
	
	public JdbcDbCompanyDao(JdbcConnectionFactory dbh) {
		this.dbh = dbh;
	}

	@Override
	public HashMap<Integer,Company> getCompanies(String name) {
		Connection conn 				= dbh.getConn();
		CallableStatement cs 			= null;
		ResultSet rs 					= null;
		HashMap<Integer,Company> companies 	= null;
		
		try {
				cs = conn.prepareCall("{CALL getCompanies(?)}");
				cs.setString("p_name", name);
				rs = cs.executeQuery();
				companies = new HashMap<Integer,Company>();

				while(rs.next())
				{
					companies.put(rs.getInt("id_company"),new Company(rs.getInt("id_company"),rs.getString("name")));					
				}
			} catch (SQLException e) {
				System.out.println("Error in getCompanies:" +e.getMessage());
			} finally {
				dbh.closeConn(conn);	
			}

		return companies;
	}

	@Override
	public void addCompany(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCompany(Integer companyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Company getCompany(Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
