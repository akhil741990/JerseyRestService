package com.turvo.rest;

import com.turvo.db.Database;
import com.turvo.db.MySqlDatabase;

public class ApplicationContext {
	private static Database db;
	static{
		db = MySqlDatabase.getInstance();
		db.init();
	}
	
	public static Database getDatabase(){
		return db;
	}
	
}
