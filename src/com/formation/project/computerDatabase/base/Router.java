package com.formation.project.computerDatabase.base;

import java.io.Serializable;


public class Router implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _url = "";
	private String _action = "";
	public Router(){};

	public void setUrl(String url)
	{
		this._url = url;
	}
	public String getUrl()
	{
		return this._url;
	}
	public void setAction(String action)
	{
		if(action != null)
			this._action = action;

	}
	public String getAction()
	{
		return this._action;
	}


}