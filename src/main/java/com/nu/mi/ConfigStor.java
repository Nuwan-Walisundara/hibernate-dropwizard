package com.nu.mi;

public class ConfigStor {
	private  static MyConfig config;
	public static  void setConfig(MyConfig config2){
		config = config2;
	}
	
	public static MyConfig getConfig(){
		return config;
	}
}
