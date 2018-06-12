/**
 * Class Representing a Virtual Machine
 * 
 */

package mainPkg;

import java.util.Comparator;
import java.util.ArrayList;

public class VirtualMachine {
	
	// value for required number of processors for this VM
	private int rqProcessors;
	// value for required memory for this VM
	private int rqMemory;
	// name of VM
	private String name;
	// static counter for number of VMs created
	private static int counter = 0;
	// boolean to be toggled on/off when shuffling VMs. true when VM slated to be shuffled
	private boolean shuffle = false;
	// list of previously used numbers for VM names
	private static ArrayList<Integer> usedNumbers = new ArrayList<Integer>();
	// Current Server host
	private String host;
	
	// CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public VirtualMachine()
	{
		rqProcessors = 1;
		rqMemory = 1;
		
		incrCounter();
		
		name = "VM" + counter;
		host = "none";
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param proc required processors
	 * @param mem required memory
	 */
	public VirtualMachine(int proc, int mem, String sv)
	{
		rqProcessors = proc;
		rqMemory = mem;
		
		incrCounter();
		
		name = "VM" + counter;
		host = sv;
	}
	
	/**
	 * Copy Constructor
	 * 
	 * @param orig original VM to copy
	 */
	public VirtualMachine(VirtualMachine orig)
	{
		rqProcessors = orig.getProc();
		rqMemory = orig.getMem();
		
		name = orig.getName();
		shuffle = orig.getShuffle();
		host = orig.getHost();
	}
	
	// GETTERS AND SETTERS
	
	/**
	 * get required nb. of processors
	 * 
	 * @return # of req. processors
	 */
	public int getProc()
	{
		return rqProcessors;
	}
	
	/**
	 * set rq. processors for VM
	 * 
	 * @param nProc new rq. processors
	 */
	public void setProc(int nProc)
	{
		rqProcessors = nProc;
	}
	
	/**
	 * get required memory for VM
	 * 
	 * @return required memory
	 */
	public int getMem()
	{
		return rqMemory;
	}
	
	/**
	 * set required memory
	 */
	public void setMem(int nMem)
	{
		rqMemory = nMem;
	}
	
	/**
	 * get VM's name
	 * 
	 * @return name of VM
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * set VM's name
	 * 
	 * @param nName new name for VM
	 */
	public void setName(String nName)
	{
		name = nName;
	}
	
	/**
	 * get # of VMs generated counter
	 * 
	 * @return number of generated VMs counter
	 */
	public int getCount()
	{
		return counter;
	}
	
	/**
	 * set counter to specified value
	 * 
	 * @param nc new counter value
	 */
	public void setCount(int nc)
	{
		counter = nc;
	}
	
	/**
	 * return shuffle value
	 * 
	 * @return shuffle on or off
	 */
	public boolean getShuffle()
	{
		return shuffle;
	}
	
	/**
	 * set shuffle value
	 * 
	 * @param shf new shuffle value
	 */
	public void setShuffle(boolean shf)
	{
		shuffle = shf;
	}
	
	/**
	 * get list of used numbers for names
	 * 
	 * @return list of used numbers for names
	 */
	public ArrayList<Integer> getUsedList()
	{
		return usedNumbers;
	}
	
	/**
	 * set list of used numbers for VM names
	 * 
	 * @param newlist new list of used numbers
	 */
	public void setUsedList(ArrayList<Integer> newlist)
	{
		usedNumbers = newlist;
	}
	
	public String getHost()
	{
		return host;
	}
	
	public void setHost(String nh)
	{
		host = nh;
	}
	
	// OTHER
	
	/**
	 * Increment VM counter
	 */
	private static void incrCounter()
	{
		counter++;
	}
	
	/**
	 * toString override
	 */
	public String toString()
	{
		return ("VM name: " + getName() + ", requires: " 
	+ getProc() + " processors and " + getMem() + " memory. Shuffle is " + getShuffle());
				
	}
	
	/**
	 * remove number from used list
	 * 
	 * @param nb number to remove from used list
	 */
	public static void removeNb(int nb)
	{
		if (usedNumbers.contains(nb))
			usedNumbers.remove(nb);
	}
	
	public String vmOut()
	{
		String outp = getName() + "." + getHost() + "-(" + getProc() + "," + getMem() + ")";
		
		return outp;
	}
	
	/**
	 * ascending order
	 */
	public static Comparator<VirtualMachine> vmSizeComparator = new Comparator<VirtualMachine>() {
		
		public int compare(VirtualMachine vm1, VirtualMachine vm2)
		{
			int size1 = vm1.getProc() * vm1.getMem();
			
			int size2 = vm2.getProc() * vm2.getMem();
			
			double comp = size1 - size2;
			
			if (comp < 0)
				return -1;
			else if (comp == 0)
				return 0;
			else
				return 1;
		}
		
	};
	
	/**
	 * descending order
	 */
	public static Comparator<VirtualMachine> vmSize2Comparator = new Comparator<VirtualMachine>() {
		
		public int compare(VirtualMachine vm1, VirtualMachine vm2)
		{
			int size1 = vm1.getProc() * vm1.getMem();
			
			int size2 = vm2.getProc() * vm2.getMem();
			
			double comp = size2 - size1;
			
			if (comp < 0)
				return -1;
			else if (comp == 0)
				return 0;
			else
				return 1;
		}
		
	};
	
	public static Comparator<VirtualMachine> vmNameComparator = new Comparator<VirtualMachine>() {
		
		public int compare(VirtualMachine vm1, VirtualMachine vm2)
		{
			String num1 = vm1.getName().substring(2);
			
			String num2 = vm2.getName().substring(2);
			
			int c1 = Integer.parseInt(num1);
			
			int c2 = Integer.parseInt(num2);
			
			int comp = c1 - c2;
			
			if (comp < 0)
				return -1;
			else if (comp == 0)
				return 0;
			else
				return 1;
		}
		
	};
	
}
