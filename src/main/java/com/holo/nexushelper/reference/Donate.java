package com.holo.nexushelper.reference;

public enum Donate 
{
    DIVINE("DIVINE"),
    MINIST("MINIST"),
    TITAN("TITAN"),
    LEGEND("LEGEND"),
    HERO("HERO"),
    MASTER("MASTER"),
    PREMIUM("PREMIUM"),
    VIP("VIP");

    Donate(String name) 
    {
        this.name = name;	
    }

    private final String name;
    
    

    public String getName() 
    {
        return this.name;
    }
    public static boolean isDIVINE = false;
	public static boolean isMINIST = false;
	public static boolean isTITAN = false;
	public static boolean isLEGEND = false;
	public static boolean isHERO = false;
	public static boolean isMASTER = false;
	public static boolean isPREMIUM = false;
	public static boolean isVIP = false;
	
	public static int priceDivine = 40000000;
	public static int priceMinist = 40000000;
	public static int priceTitan = 40000000;
	public static int priceLegend = 40000000;
	public static int priceHero = 40000000;
	public static int priceMaster = 40000000;
	public static int pricePremium = 40000000;
	public static int priceVip = 40000000;
}
