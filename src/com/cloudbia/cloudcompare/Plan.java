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
	
	
	public String serviceProvider;
	public String planName;
	public String os;
	public double hdd;
	public float memory;
	public int cpu;
	public double price;
	public double duration;
	
    public Plan(String serviceProvider, String planName, String os, float memory, int cpu, double price, double duration) {
        super();
    	this.serviceProvider = serviceProvider;
        this.planName = planName;
        this.memory = memory;
        this.cpu = cpu;
        this.price = price;
        this.duration = duration;
    }
}
