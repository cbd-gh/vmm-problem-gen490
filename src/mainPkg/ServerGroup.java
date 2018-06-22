package mainPkg;

import java.util.Comparator;

public class ServerGroup {
	
	// % of total servers
	private double proportion;
	// maximum load of Server
	private double maxLoad;

	// CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public ServerGroup()
	{
		proportion = 0;
		
		maxLoad = 0;
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param prop proportion of total servers
	 * @param ml max load of class
	 */
	public ServerGroup(double prop, double ml)
	{
		proportion = prop;
		
		maxLoad = ml;
	}
	
	// GETTERS / SETTERS
	
	/**
	 * get proportion of server occupied
	 * 
	 * @return proportion of server occupied
	 */
	public double getProp()
	{
		return proportion;
	}
	
	/**
	 * set proportion
	 * 
	 * @param np new proportion
	 */
	public void setProp(double np)
	{
		proportion = np;
	}
	
	/**
	 * return max load of class
	 * 
	 * @return max load for group
	 */
	public double getMLoad()
	{
		return maxLoad;
	}
	
	/**
	 * set max load of class
	 * 
	 * @param nml new max load of group
	 */
	public void setMLoad(double nml)
	{
		maxLoad = nml;
	}
	
	/**
	 * String Representation of class
	 * 
	 * @return
	 */
	public String classStr()
	{
		String str = proportion + "-" + maxLoad;
		
		return str;
	}
	
	/**
	 * Comparator for maxload of server groups
	 */
	public static Comparator<ServerGroup> sgLoadComparator = new Comparator<ServerGroup>() {
		
		public int compare(ServerGroup sg1, ServerGroup sg2)
		{
			double load1 = sg1.getMLoad();
			
			double load2 = sg2.getMLoad();
			
			double result = load1 - load2;
			
			if (result < 0)
				return -1;
			else if (result == 0)
				return 0;
			else
				return 1;
		}
	};
}
