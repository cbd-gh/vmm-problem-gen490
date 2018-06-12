package mainPkg;

import java.util.ArrayList;

public class Switch {
	
	// name of Switch
	private String name;
	// layer on which switch rests
	private int layer;
	// list switches to which this one is connected
	private ArrayList<Switch> links; 
	// location info for switch (i.e. which pod is it in)
	private String location;
	
	// CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public Switch()
	{
		name = "default";
		layer = 0;
		location = "none";
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param nm name of switch
	 */
	public Switch(String nm, int ly, String loc)
	{
		name = nm;
		layer = ly;
		location = loc;
	}
	
	// GETTERS AND SETTERS
	
	/**
	 * get switch's name
	 * 
	 * @return name of switch
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * set name of switch
	 * 
	 * @param nm name of switch
	 */
	public void setName(String nm)
	{
		name = nm;
	}
	
	/**
	 * get layer of switch
	 * 
	 * @return layer
	 */
	public int getLayer()
	{
		return layer;
	}
	
	/**
	 * set layer of switch
	 * 
	 * @param nl
	 */
	public void setLayer(int nl)
	{
		layer = nl;
	}
	
	/**
	 * get switch's location info
	 * 
	 * @return
	 */
	public String getLocation()
	{
		return location;
	}
	
	/**
	 * set switch location info to new location
	 * 
	 * @param newloc new switch location
	 */
	public void setLocation(String newloc)
	{
		location = newloc;
	}
	
	/**
	 * return list of links
	 * 
	 * @return list of switches this is linked to
	 */
	public ArrayList<Switch> getLinks()
	{
		return links;
	}

	// OTHER
	
	/**
	 * add a new link to this switch 
	 * 
	 * @param nlink new linked switch
	 */
	public void addLink(Switch nlink)
	{
		links.add(nlink);
	}
	
	/**
	 * remove and return a linked switch by index
	 * 
	 * @param index index of removed switch in link list
	 * @return removed linked switch
	 */
	public Switch removeLink(int index)
	{
		return links.remove(index);
	}
	
	/**
	 * Return a linked switch by index
	 * 
	 * @param index index of returned switch in list
	 * @return requested linked switch
	 */
	public Switch getLink(int index)
	{
		return links.get(index);
	}
	
	
}
