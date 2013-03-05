package com.formation.project.computerDatabase.conf;

import java.io.File;  
import java.io.FileReader;  
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;  

import javax.servlet.ServletContext;

public final class DBInit {

	public static void initDatabase(ServletContext context, Connection conn) throws SQLException  
	{  
		
		DBScriptRunner sr = new DBScriptRunner(conn,false,false);


		try {			
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/initDBStructure.sql"))));	
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/initDefaultEntries.sql"))));
			conn.commit();
		} catch (IOException e) {
			System.out.println("Error in conf.DBInit: " + e.getMessage());
		} finally {
			conn.close();
		}
	}
}