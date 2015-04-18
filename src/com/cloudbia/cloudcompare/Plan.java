package com.cloudbia.cloudcompare;

public class Plan {
	public static final String PROVIDER= "provider";
	public static final String PLAN_NAME= "planName";
	public static final String CPU= "cpu";
	public static final String MEMORY= "memory";
	public static final String HDD= "hdd";
	public static final String PRICE= "price";
	public static final String OS= "os";
	public static final String SQLITE_TABLE= "data";
	
	public static final double PLAN_GOOGLE_OS_PRICE_LINUX = 0.06;
	public static final double PLAN_GOOGLE_OS_PRICE_WINDOWS = 0.04;
	public static final double PLAN_GOOGLE_HDD_PRICE = 0.04;
	public static final double PLAN_AMAZON_HDD_PRICE = 0.12;
	
	
	
	public String serviceProvider;
	public String planName;
	public String os;
	public Integer hdd;
	public Double memory;
	public Integer cpu;
	public Double price;
	public Integer duration;
	
    public Plan(String serviceProvider, String planName, String os,int cpu, double memory, int hdd, double price, int duration) {
        super();
    	this.serviceProvider = serviceProvider;
        this.planName = planName;
        this.memory = memory;
        this.hdd = hdd;
        this.cpu = cpu;
        this.price = price;
        this.os = os;
        this.duration = duration;
    }
}
