package mainPkg;

import java.util.ArrayList;

public class KaryPod {
	
	// pod number
	private int number;
	// k-value for tree
	private int k;
	// number of aggregate switches in pod
	private int nbAggSwitches;
	// number of edge switches in pod
	private int nbEdgeSwitches;
	// list of aggregate switches in pod
	private ArrayList<Switch> aggSwitches = new ArrayList<Switch>();
	// list of edge switches in pod
	private ArrayList<EdgeSwitch> edgeSwitches = new ArrayList<EdgeSwitch>();
	
	// CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public KaryPod()
	{
		k = 2;
		number = 0;
		
		// k switches in each pod, split between 2 levels (in 3-level topology)
		nbAggSwitches = k;
		nbEdgeSwitches = k;
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param nk k-value for fat tree
	 */
	public KaryPod(int nk, int num)
	{
		k = nk;
		number = num;
		
		nbAggSwitches = k;
		nbEdgeSwitches = k;
	}
	
	// GETTERS AND SETTERS
	
	/**
	 * get k-value
	 * 
	 * @return k-value
	 */
	public int getK()
	{
		return k;
	}
	
	/**
	 * set new k-value
	 * 
	 * @param nk new k
	 */
	public void setK(int nk)
	{
		k = nk;
	}
	
	/**
	 * return number of pod
	 * 
	 * @return pod's number
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * Set number of pod
	 * 
	 * @param num new number of pod
	 */
	public void setNumber(int num)
	{
		number =  num;
	}
	
	/**
	 * return number of aggregate switches in pod
	 * 
	 * @return number of aggregate switches
	 */
	public int getNbAgg()
	{
		return nbAggSwitches;
	}
	
	/**
	 * set number of aggregate switches in pod
	 * 
	 * @param nagg new number of agg. switches
	 */
	public void setNbAgg(int nagg)
	{
		nbAggSwitches = nagg;
	}
	
	/**
	 * get number of edge switches in pod
	 * 
	 * @return nb. of edge switches
	 */
	public int getNbEdge()
	{
		return nbEdgeSwitches; 
	}
	
	/**
	 * set number of edge switches in pod
	 * 
	 * @param nedge new number of edge switches
	 */
	public void setNbEdge(int nedge)
	{
		nbEdgeSwitches = nedge;
	}
	
	/**
	 * return list of aggregate switches
	 * 
	 * @return aggregate switch list
	 */
	public ArrayList<Switch> getASlist()
	{
		return aggSwitches;
	}
	
	/**
	 * add an aggregate switch to list
	 * 
	 * @param nagg new aggregate switch
	 */
	public void addAgg(Switch nagg)
	{
		aggSwitches.add(nagg);
	}
	
	/**
	 * return an aggregate switch according to index
	 * 
	 * @param index index of agg. switch to return
	 * @return selected agg. switch
	 */
	public Switch getAgg(int index)
	{
		return aggSwitches.get(index);
	}
	
	/**
	 * return list of edge switches in pod
	 * 
	 * @return pod's edge switch list
	 */
	public ArrayList<EdgeSwitch> getESList()
	{
		return edgeSwitches;
	}
	
	/**
	 * Add an edge switch to the pod's list
	 * 
	 * @param es new edge switch to add
	 */
	public void addEdge(EdgeSwitch es)
	{
		edgeSwitches.add(es);
	}
	
	/**
	 * return an edge switch according to index
	 * 
	 * @param index index of edge switch to return
	 * @return selected edge switch
	 */
	public EdgeSwitch getEdge(int index)
	{
		return edgeSwitches.get(index);
	}
	
	// OTHER
	
	/**
	 * create a number of aggregate switches for the pod according to k
	 */
	public void createAggSwitches()
	{
		int nb = k;
		
		Switch temp;
		
		for (int i = 0; i < nb; i++)
		{
			temp = new Switch("A" + (i + 1), 2, "P" + number);
			
			addAgg(temp);
		}
	}
	
	/**
	 * Create Edge Switches for Pod
	 */
	public void createEdgeSwitches()
	{
		int nb = k;
		
		EdgeSwitch temp;
		
		for (int i = 0; i < nb; i++)
		{
			temp = new EdgeSwitch("E" + (i + 1), 3, "P" + number);
			
			addEdge(temp);
		}
	}
	
	/**
	 * Create aggregate and edge switches for this pod
	 */
	public void createSwitches()
	{
		createAggSwitches();
		
		createEdgeSwitches();
	}
	
	/**
	 * add servers to Pod ( (k/2)^2 servers per pod )
	 * 
	 * @return
	 */
	public ArrayList<Server> generateServers()
	{
		int perEdge = k;
		
		ArrayList<Server> svfullList = new ArrayList<Server>();
		
		ArrayList<Server> temp;
		
		for (EdgeSwitch es : edgeSwitches)
		{
			temp = es.generateServers(perEdge);
			
			svfullList.addAll(temp);
		}
		
		return svfullList;
	}
	
	public ArrayList<Server> generateServers(ValueRange vr)
	{
		int perEdge = k;

		ArrayList<Server> svfullList = new ArrayList<Server>();
		
		ArrayList<Server> temp;
		
		for (EdgeSwitch es : edgeSwitches)
		{
			temp = es.generateServers(perEdge, vr);
			
			svfullList.addAll(temp);
		}
		
		return svfullList;
		
	}
}
