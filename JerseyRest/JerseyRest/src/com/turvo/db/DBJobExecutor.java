package com.turvo.db;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DBJobExecutor {
	ExecutorService threadPool;
	DBJobExecutor(){
		threadPool = Executors.newFixedThreadPool(10); // size of max active DB connections
	}
	
	public <T> Future<T> submit(Callable<T> dbJob){
		return this.threadPool.submit(dbJob);
	}
}
