package com.formation.project.computerDatabase.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.formation.project.computerDatabase.exception.DaoConfigurationException;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

public enum DataSourceFactory {

    INSTANCE;

    private static final String FICHIER_PROPERTIES       = "com/formation/project/computerDatabase/dao/dao.properties";

    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "user";
    private static final String PROPERTY_MOT_DE_PASSE    = "password";
	
	private BoneCPDataSource ds	 				 	 = null;
	private ThreadLocal<Connection> threadLocalConn  = new ThreadLocal<Connection>();
	
	private DataSourceFactory() {
	
    	Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DaoConfigurationException( "Cannot find properties file " + FICHIER_PROPERTIES );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( FileNotFoundException e ) {
        	throw new DaoConfigurationException( "Cannot find properties file " + FICHIER_PROPERTIES , e);
        } catch ( IOException e ) {
            throw new DaoConfigurationException( "Cannot load properties file " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DaoConfigurationException( "Cannot find driver in classpath", e );
        }

        
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl( url );
        config.setUsername( nomUtilisateur );
        config.setPassword( motDePasse );
        config.setMinConnectionsPerPartition( 5 );
        config.setMaxConnectionsPerPartition( 10 );
        config.setPartitionCount( 2 );
        
        ds = new BoneCPDataSource( config );
    }	
        
    public Connection getConn() {
    	if(threadLocalConn.get() == null) {
    		//System.out.println("DSF: ds.getConn set in local Thread " + Thread.currentThread().getId());
	    	try {
				threadLocalConn.set(ds.getConnection());
			} catch (SQLException e) {
				System.err.println("Error in DataSourceFactory.getConnThread: " + e.getMessage());
			}
    	}
    	//System.out.println("DSF: getConn in Thread " + Thread.currentThread().getId());
		return threadLocalConn.get();
    }
	
	public void closeConn() {
		try {
			//System.out.println("DSF: Closing conn in Thread " + Thread.currentThread().getId());
			threadLocalConn.get().close();
			threadLocalConn.remove();
		} catch (SQLException e) {
			System.err.println("Error in DataSourceFactory.closeConn: " + e.getMessage());
		}
	}
}
