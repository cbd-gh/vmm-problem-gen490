package mainPkg;

import java.util.ArrayList;

public class ValueRange {

	// range of values for vm processor usage
	private ArrayList<Integer> vmProcRange = new ArrayList<Integer>();
	// range of values for vm memory usage
	private ArrayList<Integer> vmMemRange = new ArrayList<Integer>();
	// range of values for server max processors
	private ArrayList<Integer> svProcRange = new ArrayList<Integer>();
	// range of values for server max memory
	private ArrayList<Integer> svMemRange = new ArrayList<Integer>();
	// checks whether processor and memory requirements are paired or not
	private boolean paired;
	
	// CONSTRUCTORS
	
	/**
	 * default constructor
	 */
	public ValueRange()
	{
		paired = false;
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param pr paired value
	 */
	public ValueRange(boolean pr)
	{
		paired = pr;
	}
	
	// GETTERS / SETTERS
	
	/**
	 * get list of processor usage values for VMs
	 * 
	 * @return list of proc usage values
	 */
	public ArrayList<Integer> getVMPrange()
	{
		return vmProcRange;
	}
	
	/**
	 * set list of processor usage values for VMs
	 * 
	 * @param nrange new range of values
	 */
	public void setVMPrange(ArrayList<Integer> nrange)
	{
		vmProcRange = nrange;
	}
	
	/**
	 * get value from list of processor values for VMs by index
	 * 
	 * @param ind index of element to return
	 * @return desired element
	 */
	public int getVMPvalue(int ind)
	{
		return vmProcRange.get(ind);
	}
	
	/**
	 * add a value to range of processor values for VM
	 * 
	 * @param nvalue new value to add
	 */
	public void addVMPvalue(int nvalue)
	{
		vmProcRange.add(nvalue);
	}
	
	/**
	 * get range of memory values for VMs
	 * 
	 * @return range of memory values for VMs
	 */
	public ArrayList<Integer> getVMMrange()
	{
		return vmMemRange;
	}
	
	/**
	 * set range of values for VM memory
	 * 
	 * @param nrange new range of values
	 */
	public void setVMMrange(ArrayList<Integer> nrange)
	{
		vmMemRange = nrange;
	}
	
	/**
	 * get value by index for VM memory from range
	 * 
	 * @param ind index of value to return
	 * @return desired value
	 */
	public int getVMMvalue(int ind)
	{
		return vmMemRange.get(ind);
	}
	
	/**
	 * add new value to vm memory range
	 * 
	 * @param nvalue new value to add
	 */
	public void addVMMvalue(int nvalue)
	{
		vmMemRange.add(nvalue);
	}
	
	/**
	 * get range of server processor capacities range
	 * 
	 * @return list of server processor capabilities range
	 */
	public ArrayList<Integer> getSVPrange()
	{
		return svProcRange;
	}
	
	/**
	 * set range of server processor values
	 * 
	 * @param nrange new range of values
	 */
	public void setSVPrange(ArrayList<Integer> nrange)
	{
		svProcRange = nrange;
	}
	
	/**
	 * return a value from server processor range according to index
	 * 
	 * @param ind index of value to return
	 * @return desired element
	 */
	public int getSVPvalue(int ind)
	{
		return svProcRange.get(ind);
	}
	
	/**
	 * add a value to server proc range
	 * 
	 * @param nvalue new value to add to server proc range
	 */
	public void addSVPvalue(int nvalue)
	{
		svProcRange.add(nvalue);
	}
	
	/**
	 * return list of memory ranges for server
	 * 
	 * @return list of server memory range
	 */
	public ArrayList<Integer> getSVMrange()
	{
		return svMemRange;
	}
	
	/**
	 * set new range for server memory values
	 * 
	 * @param nrange new range of values
	 */
	public void setSVMrange(ArrayList<Integer> nrange)
	{
		svMemRange = nrange;
	}
	
	/**
	 * return value from memory range by index
	 * 
	 * @param ind index of element to return
	 * @return desired element
	 */
	public int getSVMvalue(int ind)
	{
		return svMemRange.get(ind);
	}
	
	/**
	 * add new value to server memory range
	 * 
	 * @param nvalue new value to add
	 */
	public void addSVMvalue(int nvalue)
	{
		svMemRange.add(nvalue);
	}
	
	/**
	 * return pairing option
	 * 
	 * @return pairing option
	 */
	public boolean isPaired()
	{
		return paired;
	}
	
	/**
	 * set pairing option of range
	 * 
	 * @param np new pairing option
	 */
	public void setPairing(boolean np)
	{
		paired = np;
	}
	
	// OTHER
	
	/**
	 * return number of vm processor range elements
	 * 
	 * @return nb. of vm processor options
	 */
	public int getVMPsize()
	{
		return vmProcRange.size();
	}
	
	/**
	 * return number of vm memory range elements
	 * 
	 * @return nb. of vm memory options
	 */
	public int getVMMsize()
	{
		return vmMemRange.size();
	}
	
	/**
	 * return number of server processor range options
	 * 
	 * @return nb. of server processor options
	 */
	public int getSVPsize()
	{
		return svProcRange.size();
	}
	
	/**
	 * return number of server memory range options
	 * 
	 * @return nb. of server memory options
	 */
	public int getSVMsize()
	{
		return svMemRange.size();
	}
}
