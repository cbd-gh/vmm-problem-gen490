package mainPkg;

import java.util.ArrayList;

public class FinalConfig {
	
	// type of final config : FAIL, CONSOLIDATE, LOAD BALANCE
	private String type;
	// list of servers detailing final config
	private ArrayList<Server> svConfig;
	// check whether final config is incomplete 
	private boolean downgraded;
	
	// CONSTRUCTORS
	
	public FinalConfig()
	{
		type = "default";
		
		downgraded = false;
		
		svConfig = new ArrayList<Server>();
	}
	
	public FinalConfig(String tp, ArrayList<Server> svs)
	{
		type = tp;
		
		downgraded = false;
		
		// deep copy of list
		ArrayList<Server> clist = new ArrayList<Server>();
		
		Server temp;
		
		for (Server s : svs)
		{
			temp = new Server(s);
			
			clist.add(temp);
		}
		
		svConfig = clist;
	}
	
	public FinalConfig(FinalConfig orig)
	{
		type = orig.getType();
		
		downgraded = orig.getDowngraded();
		
		ArrayList<Server> olist = orig.getServers();
		
		ArrayList<Server> clist = new ArrayList<Server>();
		
		for (Server s : olist)
		{
			clist.add(new Server(s));
		}
		
		svConfig = clist;
	}
	
	// GETTERS / SETTERS
	
	/**
	 * get type of config
	 * 
	 * @return type of config
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Set type of Config
	 * 
	 * @param tp new type
	 */
	public void setType(String tp)
	{
		type = tp;
	}
	
	/**
	 * Return server list
	 * 
	 * @return server list
	 */
	public ArrayList<Server> getServers()
	{
		return svConfig;
	}
	
	/**
	 * set new server config list
	 * 
	 * @param svlist new list
	 */
	public void setServers(ArrayList<Server> svlist, boolean copy)
	{
		if (copy = true)
		{
			ArrayList<Server> clist = new ArrayList<Server>();
			
			Server temp;
			
			for (Server sv : svlist)
			{
				temp = new Server(sv);
				
				clist.add(temp);
			}
			
			svConfig = clist;
		}
		else
			svConfig = svlist;
	}
	
	/**
	 * check whether scenario was downgraded
	 * 
	 * @return whether scenario was downgraded
	 */
	public boolean getDowngraded()
	{
		return downgraded;
	}
	
	/**
	 * return scenario status
	 * 
	 * @param status downgraded or not
	 */
	public void setDowngraded(boolean status)
	{
		downgraded = status;
	}
	
	// OTHER
	
	
}
