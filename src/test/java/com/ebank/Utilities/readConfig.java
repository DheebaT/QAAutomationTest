package com.ebank.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readConfig {
	
	Properties pro;
	
	public readConfig()
	{
		File src = new File("./Configfiles/config.properties");
		
		{
			try {
				FileInputStream fis = new FileInputStream(src);
				pro = new Properties();
				pro.load(fis);
				} catch (IOException e) {
					
					System.out.println("Exception is " +e.getMessage());
				}
			}
			
			
			
		}
		public String getApplicationURL()
	{
		String url = pro.getProperty("baseURL");
			return url;
		
	}
		public String getUsername()
		{
			String uname = pro.getProperty("username");
				return uname;
			
		}
		public String getPassword()
		{
			String pwd = pro.getProperty("password");
				return pwd;
			
		}
		public String getChromePath()
		{
			String chrome = pro.getProperty("chromepath");
				return chrome;
			
		}
		public String getFirefoxPath()
		{
			String ff = pro.getProperty("firefoxpath");
				return ff;
			
		}
		
	}


