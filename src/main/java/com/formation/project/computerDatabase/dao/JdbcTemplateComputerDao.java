package com.formation.project.computerDatabase.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.mapper.ComputerCountRowMapper;
import com.formation.project.computerDatabase.dao.mapper.ComputerRowMapper;

@Repository
public class JdbcTemplateComputerDao implements IComputerDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateComputerDao() {
		
	}
	
	@Override
	public Integer addComputer(Computer computer) {

		StringBuilder query	 	= new StringBuilder();
		
		query.append("INSERT INTO computer(name, introduced, discontinued, id_company) ");
		query.append("VALUES(?,?,?,?);");
		
		jdbcTemplate.update(query.toString(),new Object[] { 
							computer.getName(), 
							new Timestamp(computer.getIntroduced().getTime()),
							(computer.getDiscontinued() == null) ? null : new Timestamp(computer.getDiscontinued().getTime()),
							computer.getCompany().getId()
							});
		return jdbcTemplate.queryForInt( "SELECT last_insert_id()" );
	}

	@Override
	public void updateComputer(Computer computer) {
		StringBuilder query	 = new StringBuilder();
		
		query.append("UPDATE computer ");
		query.append("SET name = ?, introduced = ?, discontinued = ?, id_company = ? ");
		query.append("WHERE id_computer = ?;");
		
		jdbcTemplate.update(query.toString(),new Object[] { 
			computer.getName(), 
			new Timestamp(computer.getIntroduced().getTime()),
			(computer.getDiscontinued() == null) ? null : new Timestamp(computer.getDiscontinued().getTime()),
			computer.getCompany().getId(),
			computer.getId()
			});
	}

	@Override
	public void deleteComputer(Integer computerId) {
		StringBuilder query	 = new StringBuilder();
		
		query.append("UPDATE computer ");
		query.append("SET is_visible = 0 ");
		query.append("WHERE id_computer = ?;");
		jdbcTemplate.update(query.toString());
			
	}

	@Override
	public Computer getComputer(Integer computerId) {
		StringBuilder query	 = new StringBuilder();
		query.append("SELECT computer.id_computer as id_computer, computer.name as computer_name, computer.introduced, computer.discontinued, company.id_company as id_company, company.name as company_name ");
		query.append("FROM computer ");
		query.append("INNER JOIN company on computer.id_company = company.id_company ");
		query.append("WHERE id_computer = ?;");
		List<Computer> computerList = jdbcTemplate.query(query.toString(), new Object[] {computerId}, new ComputerRowMapper());
		if(computerList.size() == 1)
			return computerList.get(0);
		return null;
	}

	@Override
	public List<Computer> getComputers(Integer currentPage, Integer resultsPerPage, String sortBy, String name) {
		StringBuilder query					= new StringBuilder();
		
		query.append("SELECT computer.id_computer as id_computer, computer.name as computer_name, computer.introduced, computer.discontinued, company.id_company as id_company, company.name as company_name ");
		query.append("FROM computer ");
		query.append("INNER JOIN company ON computer.id_company = company.id_company ");
		query.append("WHERE (LOWER(computer.name) LIKE CONCAT('%',?,'%') OR ? = '') ");
		query.append("AND is_visible = 1 ");		
		query.append(" ORDER BY ");
		query.append(sortBy);
		query.append(" LIMIT ?,?;");
		
		return jdbcTemplate.query(query.toString(), 
						   new Object[] {name,name,(currentPage-1)*resultsPerPage,resultsPerPage}, 
						   new ComputerRowMapper());
	}

	@Override
	public Integer getComputerCount(String name) {
		StringBuilder query					= new StringBuilder();
		query.append("SELECT COUNT(*) AS count FROM computer ");
		query.append("WHERE (LOWER(name) LIKE LOWER(CONCAT('%',?,'%')) OR ? = '') ");
		query.append("AND is_visible = 1;");
		
		List<Integer> countList = jdbcTemplate.query(query.toString(), new Object[] { name, name }, new ComputerCountRowMapper());
		if(countList.size() == 1)
			return countList.get(0);
		return null;
	}

}
