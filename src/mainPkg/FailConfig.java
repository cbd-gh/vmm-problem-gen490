package mainPkg;

import java.util.ArrayList;

public class FailConfig extends FinalConfig{
	
	// list of servers that have been selected to be emptied
	private ArrayList<Server> emptied = new ArrayList<Server>();
	// number of servers designated to be emptied
	private int nbEmpty;
	
	// CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public FailConfig()
	{
		super();
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param tp type of final config
	 * @param svs list of servers
	 */
	public FailConfig(ArrayList<Server> svs, int nbemp)
	{
		super("FAIL", svs);
		
		nbEmpty = nbemp;
	}

	// GETTERS / SETTERS
	
	/**
	 * get list of empty servers
	 * 
	 * @return list of emptied servers
	 */
	public ArrayList<Server> getEmpty()
	{
		return emptied;
	}
	
	/**
	 * set list of empty servers
	 * 
	 * @param eml empty server list
	 * @param copy make a deep copy or not of list
	 */
	public void setEmpty(ArrayList<Server> eml, boolean copy)
	{
		if (copy)
		{
			ArrayList<Server> clist = new ArrayList<Server>();
			
			Server temp;
			
			for (Server sv: eml)
			{
				temp = new Server(sv);
				
				clist.add(temp);
			}
			
			emptied = clist;
		}
		else
			emptied = eml;
	}
	
	/**
	 * Return number of emptied servers
	 * 
	 * @return number of emptied servers
	 */
	public int getNbEmp()
	{
		return nbEmpty;
	}
	
	/**
	 * set number of emptied servers
	 * 
	 * @param emp new nb. of emptied servers
	 */
	public void setNbEmpty(int emp)
	{
		nbEmpty = emp;
	}
}
