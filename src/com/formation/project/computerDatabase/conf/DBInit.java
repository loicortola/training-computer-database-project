package com.formation.project.computerDatabase.conf;

import java.io.File;  
import java.io.FileReader;  
import java.io.IOException;

import java.sql.SQLException;  

import javax.servlet.ServletContext;

import com.formation.project.computerDatabase.dao.JdbcDbHandler;


public final class DBInit {

	public static void initDatabase(ServletContext context) throws SQLException  
	{  
		JdbcDbHandler _dbHandler = new JdbcDbHandler();

		DBScriptRunner sr = new DBScriptRunner(_dbHandler.getConn(),false,false);


		try {			
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/initDBStructure.sql"))));
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/initStoredProcedures.sql"))));			
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/initDefaultEntries.sql"))));
		} catch (IOException e) {
			System.out.println("Error in conf.DBInit: " + e.getMessage());
		}


	}
}