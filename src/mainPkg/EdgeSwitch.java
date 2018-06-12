package mainPkg;

import java.util.ArrayList;
import java.util.Random;

public class EdgeSwitch extends Switch{
	
	// list of servers linked to this Edge Switch
	private ArrayList<Server> svList = new ArrayList<Server>();
	
	//CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public EdgeSwitch()
	{
		super();
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param nm name of switch
	 * @param ly layer of switch
	 */
	public EdgeSwitch(String nm, int ly, String loc)
	{
		super(nm, ly, loc);
	}
	
	// GETTERS AND SETTERS
	
	/**
	 * get server list from edge switch
	 * 
	 * @return list of linked servers
	 */
	public ArrayList<Server> getSVList()
	{
		return svList;
	}
	
	// OTHER
	
	/**
	 * Return a server from list based on index
	 * 
	 * @param index index of server in list to return
	 * @return selected server from list
	 */
	public Server getServer(int index)
	{
		return svList.get(index);
	}
	
	/**
	 * Add a server to the list
	 * 
	 * @param nSV new server to add
	 */
	public void addSV(Server nSV)
	{
		svList.add(nSV);
	}
	
	/**
	 * remove a server from the list of servers
	 * 
	 * @param index index of server from list to remove
	 * @return removed Server
	 */
	public Server removeSV(int index)
	{
		return svList.remove(index);
	}
	
	/**
	 * Generate a number of servers attached to the edge switch
	 * 
	 * @param amount number of servers to generate
	 * @return list of generated servers.
	 */
	public ArrayList<Server> generateServers(int amount)
	{
		Server temp;
		Random rng = new Random();
		
		int tProc;
		int tMem;
		
		int a;
		
		String loc;
		
		// create x servers with random specs for an edge switch
		for (int i = 0; i < amount; i++)
		{
			a = rng.nextInt(4) + 3;
			
			tProc = (int) Math.pow(2, a);
			
			a = rng.nextInt(5) + 5;
			
			tMem = (int) Math.pow(2, a);
			
			loc = getLocation() + "." + getName();
			
			temp = new Server(tProc, tMem, loc);
			
			addSV(temp);
		}
		
		return getSVList();
	}
	
	/**
	 * Create a given number of random servers with specs taken from list of values
	 * 
	 * @param amount number of servers to generate
	 * @param vr range of values for server capacity
	 * @return list of generated servers
	 */
	public ArrayList<Server> generateServers(int amount, ValueRange vr)
	{
		Random rng = new Random();
		
		int svPind;
		int svMind;
		
		int svProc;
		int svMem;
		String loc = getLocation() + "." + getName();
		
		Server temp;
		
		for (int i = 0; i < amount; i++)
		{
			if (vr.isPaired())
			{
				svPind = rng.nextInt(vr.getSVPsize());
				
				svProc = vr.getSVPvalue(svPind);
				svMem = vr.getSVMvalue(svPind);
				
				temp = new Server(svProc, svMem, loc);
				
				addSV(temp);
			}
			else
			{
				svPind = rng.nextInt(vr.getSVPsize());
				svMind = rng.nextInt(vr.getSVMsize());
				
				svProc = vr.getSVPvalue(svPind);
				svMem = vr.getSVMvalue(svMind);
				
				temp = new Server(svProc, svMem, loc);
				
				addSV(temp);
			}
		}
		
		return getSVList();
	}
}
