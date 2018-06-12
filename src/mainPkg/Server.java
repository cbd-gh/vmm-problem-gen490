/**
 * Class for Server
 * 
 */
package mainPkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class Server {
	
	// total number of Processors on Server
	private int totalProcessors;
	// total Memory on Server
	private int totalMemory;
	// number of processors currently in use
	private int usedProcessors;
	// amount of memory currently in use
	private int usedMemory;
	// name of Server
	private String name;
	// number for server
	private int number;
	// where the server is located (ex: edge switch, pod)
	private String location;
	// counter tracking number of Servers generated
	private static int counter = 0;
	// list of VMs currently on Server
	private ArrayList<VirtualMachine> vmList = new ArrayList<VirtualMachine>();
	// occupancy rate of server
	private double load;
	
	// CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public Server()
	{
		totalProcessors = 1;
		totalMemory = 1;
		usedProcessors = 0;
		usedMemory = 0;
		
		incrCounter();
		
		name = "SV" + counter;
		number = counter;
	}
	
	/**
	 * Parametrized Constructor
	 * 
	 * @param tproc total processors of server
	 * @param tmem total memory of server
	 */
	public Server(int tproc, int tmem, String loc)
	{
		totalProcessors = tproc;
		totalMemory = tmem;
		usedProcessors = 0;
		usedMemory = 0;
		location = loc;
		
		incrCounter();
		
		name = "SV" + counter;
		number = counter;
	}
	
	public Server(Server orig)
	{
		totalProcessors = orig.getTProc();
		totalMemory = orig.getTMem();
		usedProcessors = orig.getUProc();
		usedMemory = orig.getUMem();
		location = orig.getLocation();
		
		name = orig.getName();
		load = orig.getLoad();
		number = orig.getSVnum();
		
		ArrayList<VirtualMachine> clist = new ArrayList<VirtualMachine>();
		
		for (VirtualMachine ele: orig.getVMList())
		{
			VirtualMachine temp = new VirtualMachine(ele);
			
			clist.add(temp);
		}
		
		vmList = clist;
	}
	
	// GETTERS AND SETTERS
	
	/**
	 * get total number of processors
	 * 
	 * @return total processors for Server
	 */
	public int getTProc()
	{
		return totalProcessors;
	}
	
	/**
	 * set total # processors
	 */
	public void setTProc(int ntp)
	{
		totalProcessors = ntp;
	}
	
	/**
	 * get Server's total memory
	 * 
	 * @return server's total memory
	 */
	public int getTMem()
	{
		return totalMemory;
	}
	
	/**
	 * set total memory for Server
	 * 
	 * @param ntm total memory
	 */
	public void setTMem(int ntm)
	{
		totalMemory = ntm;
	}
	
	/**
	 * get Server's nb. of processors in use
	 * 
	 * @return used processors
	 */
	public int getUProc()
	{
		return usedProcessors;
	}
	
	/**
	 * set Server's nb. of used processors
	 * 
	 * @param uproc new nb. used processors
	 */
	public void setUProc(int uproc)
	{
		usedProcessors = uproc;
	}
	
	/**
	 * get server's used memory
	 * 
	 * @return server memory in use
	 */
	public int getUMem()
	{
		return usedMemory;
	}
	
	/**
	 * set server's used memory
	 * 
	 * @param num amount of memory in use
	 */
	public void setUMem(int num)
	{
		usedMemory = num;
	}
	
	/**
	 * get server's name
	 * 
	 * @return server's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * set Server's name
	 * 
	 * @param newNm new name
	 */
	public void setName(String newNm)
	{
		name = newNm;
	}
	
	/**
	 * get server's location
	 * 
	 * @return string for server's location
	 */
	public String getLocation()
	{
		return location;
	}
	
	/**
	 * set serve's location
	 * 
	 * @param newloc new location
	 */
	public void setLocation(String newloc)
	{
		location = newloc;
	}
	
	/**
	 * get list of VMs on server
	 * 
	 * @return list of VMs on server
	 */
	public ArrayList<VirtualMachine> getVMList()
	{
		return vmList;
	}
	
	/**
	 * return load of server
	 * 
	 * @return load
	 */
	public double getLoad()
	{
		return load;
	}
	
	/**
	 * set load for server
	 * 
	 * @param nl new load
	 */
	public void setLoad(double nl)
	{
		load = nl;
	}
	
	/**
	 * get server's number
	 * 
	 * @return number of server
	 */
	public int getSVnum()
	{
		return number;
	}
	
	/**
	 * set server's number
	 * 
	 * @param nnum new number for Server
	 */
	public void setSVnum(int nnum)
	{
		number = nnum;
	}
	
	// OTHER
	
	/**
	 * get number of VMs currently on Server
	 * 
	 * @return number of VMs in server
	 */
	public int getNbVMs()
	{
		return vmList.size();
	}
	
	/**
	 * increment counter for number of Servers
	 */
	private static void incrCounter()
	{
		counter++;
	}
	
	/**
	 * reset counter
	 */
	public static void resetCount()
	{
		counter = 0;
	}
	
	/**
	 * calculate server's load. Load is determined by the max between:
	 * - used processors / total processors and
	 * - used memory / total memory
	 * 
	 * @return current load of server
	 */
	public double calculateLoad()
	{
		double procLoad = (double) usedProcessors / totalProcessors;
		double memLoad = (double) usedMemory / totalMemory;
		
		double svLoad = Math.max(procLoad, memLoad);
		
		setLoad(svLoad);
		
		return svLoad;
	}
	
	/**
	 * get CPU load
	 * 
	 * @return
	 */
	public double getCPUload()
	{
		double result = (double) usedProcessors / totalProcessors;
		
		return result;
	}
	
	/**
	 * get Memory Load.
	 * 
	 * @return
	 */
	public double getMEMload()
	{
		double result = (double) usedMemory / totalMemory;
		
		return result;
	}
	
	/**
	 * Whether the current Server load clears a given threshold
	 * 
	 * @param thresh threshold to clear
	 * @param isMax is it a max threshold or min threshold
	 * @return result
	 */
	public boolean clearThresh(double thresh, boolean isMax)
	{
		double cpuLoad = getCPUload();
		double memLoad = getMEMload();
		
		boolean cleared;
		
		if (isMax)
		{
			cleared = (cpuLoad <= thresh) && (memLoad <= thresh);
			
			return cleared;
		}
		else
		{
			cleared = (cpuLoad >= thresh) && (memLoad >= thresh);
			
			return cleared;
		}
	}
	
	/**
	 * check if Server exceeds a load threshold after adding a VM
	 * 
	 * @param thresh threshold to check
	 * @param isMax max or min
	 * @param toAdd VM to add to system
	 * @return answer
	 */
	public boolean clearThreshAdd(double thresh, boolean isMax, VirtualMachine toAdd)
	{
		double cpuLoad = (double) (getUProc() + toAdd.getProc()) / getTProc();
		double memLoad = (double) (getUMem() + toAdd.getMem()) / getTMem();
		
		boolean cleared;
		
		if (isMax)
		{
			cleared = (cpuLoad <= thresh) && (memLoad <= thresh);
			
			return cleared;
		}
		else
		{
			cleared = (cpuLoad >= thresh) && (memLoad >= thresh);
			
			return cleared;
		}
	}
	
	/**
	 * check if load with VM added falls within a given range
	 * 
	 * @param minThresh minimum load
	 * @param maxThresh maximum load
	 * @param toAdd VM to add
	 * @return answer
	 */
	public boolean clearThreshAdd2(double minThresh, double maxThresh, VirtualMachine toAdd)
	{
		return (clearThreshAdd(minThresh, false, toAdd) && clearThreshAdd(maxThresh, true, toAdd));
	}
	
	/**
	 * return cpu load of Server with vm removed
	 * 
	 * @param vmInd
	 * @return
	 */
	public double cpuLoadRemove(int vmInd)
	{
		VirtualMachine vmRemoved = getVM(vmInd);
		
		// cpu load with vm removed
		double newLoad = (double) (getUProc() - vmRemoved.getProc()) / getTProc();
		
		return newLoad;
	}
	
	/**
	 * return memory load of Server with vm removed
	 * 
	 * @param vmInd
	 * @return
	 */
	public double memLoadRemove(int vmInd)
	{
		VirtualMachine vmRemoved = getVM(vmInd);
		
		// memory load with vm removed
		double newLoad = (double) (getUMem() - vmRemoved.getMem()) / getTMem();
		
		return newLoad;
	}
	
	/**
	 * Add a VM to the server
	 * 
	 * @param nVM new VM to add
	 */
	public void addVM(VirtualMachine nVM)
	{
		// add VM to list
		vmList.add(nVM);
		// add VM's memory and processor usage
		setUProc(getUProc() + nVM.getProc());
		setUMem(getUMem() + nVM.getMem());
		calculateLoad();
	}
	
	/**
	 * remove a VM from the server by index
	 * 
	 * @param index index of removed VM
	 * @return removed VM
	 */
	public VirtualMachine removeVM(int index)
	{
		VirtualMachine removed = vmList.remove(index);
		
		setUProc(getUProc() - removed.getProc());
		setUMem(getUMem() - removed.getMem());
		calculateLoad();
		
		return removed;
	}
	
	/**
	 * Return VM from list according to index
	 * 
	 * @param index index of VM to return
	 * @return selected VM
	 */
	public VirtualMachine getVM(int index)
	{
		return vmList.get(index);
	}
	
	/**
	 * return a VM from the Server by name, if it is present
	 * 
	 * @param nm name of VM to return
	 * @return VM matching name, null otherwise
	 */
	public VirtualMachine getVM(String nm)
	{
		for (VirtualMachine vm : vmList)
		{
			String vmName = vm.getName();
			
			if (vmName.equals(nm))
				return vm;
		}
		
		return null;
	}
	
	/**
	 * return index of VM in Server by name
	 * 
	 * @param nm
	 * @return
	 */
	public int getVMindex(String nm)
	{
		int numVM = getVMList().size();
		
		for (int i = 0; i < numVM; i++)
		{
			if (getVM(i).getName().equals(nm))
				return i;
		}
		
		return -1;
	}
	
	/**
	 * Return the index of a random VM from server
	 * 
	 * @return index of random VM from server
	 */
	public int randomVM()
	{
		Random rng = new Random();
		
		int ind;
		
		if (vmList.size() == 0)
			ind = -1;
		else
			ind = rng.nextInt(vmList.size());
		
		return ind;
	}
	
	/**
	 * clear list of used VMs
	 */
	public void clearVMList()
	{
		// clear VM list
		vmList.clear();
		
		// reset usage
		setUProc(0);
		setUMem(0);
		setLoad(0);
	}
	
	/**
	 * populate Server with VMs until target load is reached
	 * 
	 * @param targetLoad minimum load to stop VM generation
	 * @return list of VMs in server
	 */
	public ArrayList<VirtualMachine> populate(double targetLoad)
	{
		Random rng = new Random();
		
		int vmProc;
		int vmMem;
		
		int np;
		int nm;
		
		VirtualMachine nvm;
		
		while (getLoad() < targetLoad)
		{
			// generate a new VM
			// ranges are arbitrary for the moment
			// processors: 1-6, memory: [2,4,8,16,32]
			np = rng.nextInt(6) + 1;
			nm = rng.nextInt(5) + 1;
			
			vmProc = np;
			vmMem = (int) Math.pow(2, nm);
			
			// check if new VM is too big to add to Server
			boolean pOver = (vmProc + getUProc()) < getTProc();
			boolean mOver = (vmMem + getUMem()) < getTMem();
			
			if (pOver && mOver)
			{
				nvm = new VirtualMachine(vmProc, vmMem, getName());
				
				addVM(nvm);
			}
			
		}
		
		return vmList;
	}
	
	/**
	 * populate server with random virtual machines
	 * 
	 * @param minLoad minimum load for server
	 * @param maxLoad maximum load for server
	 * @param vr range of values for vm generation
	 * @return list of VMs on server
	 */
	public ArrayList<VirtualMachine> populate(double minLoad, double maxLoad, ValueRange vr)
	{
		Random rng = new Random();
		
		int vmProc;
		int vmMem;
		
		int pInd;
		int mInd;
		
		VirtualMachine nvm;
		
		// add or remove a VM if load is not in specified range
		while (getLoad() <= minLoad)
		{
			if (maxLoad == 0)
				break;
			
			if (vr.isPaired())
			{
				pInd = rng.nextInt(vr.getVMPsize());
				
				vmProc = vr.getVMPvalue(pInd);
				vmMem = vr.getVMMvalue(pInd);
			}
			else
			{
				pInd = rng.nextInt(vr.getVMPsize());
				mInd = rng.nextInt(vr.getVMMsize());
				
				vmProc = vr.getVMPvalue(pInd);
				vmMem = vr.getVMMvalue(mInd);
			}
			
			nvm = new VirtualMachine(vmProc, vmMem, getName());
			
			if (loadCheck(maxLoad, nvm))
			{
				addVM(nvm);
			}
			
		}
		
		return vmList;
	}
	
	/**
	 * improvement on previous populate method
	 * 
	 * @param minLoad minimum load
	 * @param maxLoad maximum load
	 * @param vr value range object
	 * @return list of VMs in server
	 */
	public ArrayList<VirtualMachine> populate2(double minLoad, double maxLoad, ValueRange vr)
	{
		ArrayList<Integer> validVM = new ArrayList<Integer>();
		
		while (getLoad() <= minLoad)
		{
			if (maxLoad == 0)
				break;
			
			validVM.clear();
			
			if (vr.isPaired())
			{
				VirtualMachine dummy;
				
				int dCPU;
				int dMEM;
				
				// get list of valid VMs
				for (int i = 0; i < vr.getVMPrange().size(); i++)
				{
					dCPU = vr.getVMPvalue(i);
					dMEM = vr.getVMMvalue(i);
					// creating a dummy VM with given specs
					dummy = new VirtualMachine(dCPU, dMEM, "dummy");
					// if it can move in, add index to list of valid
					if (loadCheck(maxLoad, dummy))
						validVM.add(i);
				}
				
				// if there are no types of VMs that can be added
				if (validVM.isEmpty())
					break;
				else
				{
					// get a random VM out of whats available
					Collections.shuffle(validVM);
					
					int selected = validVM.get(0);
					
					dCPU = vr.getVMPvalue(selected);
					dMEM = vr.getVMMvalue(selected);
					
					// new virtual machine to add
					VirtualMachine newVM = new VirtualMachine(dCPU,dMEM,getName());
					
					if (loadCheck(maxLoad, newVM))
						addVM(newVM);
				}
				
			}
		}
		
		return vmList;
	}
	
	/**
	 * Print server details
	 */
	public void print()
	{
		System.out.println("Server name : " + getName());
		System.out.println("Location: " + getLocation());
		System.out.println("Total Processors: " + getTProc() + " Total Memory " + getTMem());
		System.out.println("Usage: proc: " + getUProc() + " mem: " + getUMem() + " load: " + getLoad());
		
		if (getNbVMs() == 0)
			System.out.println("No VMs on Server");
		else
		{
			System.out.println("List of VMs on Server:");
			
			for (int i = 0; i < getNbVMs(); i++)
			{
				System.out.println(getVM(i).toString());
			}
		}
		
	}
	
	/**
	 * Determine if there is space for a given VM within the server
	 * 
	 * @param nvm VM to be added
	 * @return whether VM fits on server or not
	 */
	public boolean canMoveIn(VirtualMachine nvm)
	{
		int svTP = getTProc();
		int svTM = getTMem();
		int svUP = getUProc();
		int svUM = getUMem();
		
		int vmP = nvm.getProc();
		int vmM = nvm.getMem();
		
		boolean procSpace = (svUP + vmP) <= svTP;
		boolean memSpace = (svUM + vmM) <= svTM;
		
		if (procSpace && memSpace)
			return true;
		else
			return false;
	}
	
	/**
	 * check if server plus given vm exceeds given target vm load
	 * 
	 * @param targetLoad target load to check
	 * @param nvm vm to add
	 * @return true is exceeded, false otherwise
	 */
	public boolean loadCheck(double targetLoad, VirtualMachine nvm)
	{
		int svTP = getTProc();
		int svTM = getTMem();
		int svUP = getUProc();
		int svUM = getUMem();
		
		int vmP = nvm.getProc();
		int vmM = nvm.getMem();
		
		double procUsage = (double) (svUP + vmP) / svTP;
		double memUsage = (double) (svUM + vmM) / svTM;
		
		double nload = Math.max(procUsage, memUsage);
		
		if (nload > targetLoad)
			return false;
		else
			return true;
	}
	
	/**
	 * output format for writing vm list of servers to file
	 * 
	 * @return output list
	 */
	public String serverVMOutput()
	{
		String outp = "LIST.VM." + getName() + ":";
		
		String vmo;
		
		if (getNbVMs() == 0)
		{
			return outp;
		}
		
		for (VirtualMachine vm : vmList)
		{
			vmo = " " + vm.getName() + "." + getName() + "-(" + vm.getProc() + "," + vm.getMem() + ")";
			
			outp += vmo;
		}
		
		return outp;
	}
	
	/**
	 * server details for output
	 * 
	 * @return string representing server
	 */
	public String serverOutput()
	{
		String outp = getName() + "." + getLocation() + "-(" + getTProc() + "," + getTMem() + ")";
		
		return outp;
	}
	
	/**
	 * print server occupation
	 *  
	 * @return
	 */
	public String occupOut()
	{
		String outp = getName();
		
		double load1 = ((double) getUProc()) / getTProc();
		
		double load2 = ((double) getUMem()) / getTMem();
		
		String l1 = Integer.toString(getUProc()) + "/" + Integer.toString(getTProc());
		
		String l2 = Integer.toString(getUMem()) + "/" + Integer.toString(getTMem());
		
		outp += "-(" + l1 + "," + l2;
		
		if (load1 > load2)
			outp += "," + l1 + ")";
		else
			outp += "," + l2 + ")";
		
		return outp;
	}
	
	/**
	 * 
	 * 
	 * @param cpuVal average cpu value of system
	 * @param memVal average memory value of system
	 * @return total gap between cpu and mem values and system averages
	 */
	public double totalGap(double cpuVal, double memVal)
	{
		double cpuGap = Math.abs(getCPUload() - cpuVal);
		double memGap = Math.abs(getMEMload() - memVal);
		
		double result = cpuGap + memGap;
		
		return result;
	}
	
	/**
	 * calculate average gap of server plus new vm
	 * 
	 * @param cpuVal avg cpu value
	 * @param memVal avg mem value
	 * @param vmAdd vm to add
	 * @return result
	 */
	public double totalGapPlus(double cpuVal, double memVal, VirtualMachine vmAdd)
	{
		double cpuLoadPlus = (double) (getUProc() + vmAdd.getProc()) / getTProc();
		double cpuGap = Math.abs(cpuLoadPlus - cpuVal);
		
		double memLoadPlus = (double) (getUMem() + vmAdd.getMem()) / getTMem();
		double memGap = Math.abs(memLoadPlus - memVal);
		
		double result = cpuGap + memGap;
		
		return result;
	}
	
	/**
	 * calculate total gap minus a vm on system
	 * 
	 * @param cpuVal average cpu load value
	 * @param memVal average memory load value
	 * @param vmRem removed vm
	 * @return total gap
	 */
	public double totalGapMinus(double cpuVal, double memVal, int vmRem)
	{
		VirtualMachine vmr = getVM(vmRem);
		
		double cpuLoadPlus = (double) (getUProc() - vmr.getProc()) / getTProc();
		double cpuGap = Math.abs(cpuLoadPlus - cpuVal);
		
		double memLoadPlus = (double) (getUMem() - vmr.getMem()) / getTMem();
		double memGap = Math.abs(memLoadPlus - memVal);
		
		double result = cpuGap + memGap;
		
		return result;
	}
	
	/**
	 * Comparator object to sort servers by load
	 * source for idea: https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
	 */
	public static Comparator<Server> svLoadComparator = new Comparator<Server>() {
		
		public int compare(Server s1, Server s2) {
			
			double load1 = s1.getLoad();
			double load2 = s2.getLoad();
			
			// ascending order
			double comp = load1 - load2;
			
			if (comp < 0)
				return -1;
			else if (comp == 0)
				return 0;
			else
				return 1;
		}
		
	};
	
	/**
	 * compare servers by average of both loads (descending order)
	 */
	public static Comparator<Server> svAvgLoadComparator = new Comparator<Server>() {
		
		public int compare(Server s1, Server s2) {
			
			double cpu1 = s1.getCPUload();
			double mem1 = s1.getMEMload();
			double avg1 = (cpu1 + mem1) / 2;
			
			double cpu2 = s2.getCPUload();
			double mem2 = s2.getMEMload();
			double avg2 = (cpu2 + mem2) / 2;
			
			// descending order
			double comp = avg2 - avg1;
			
			if (comp < 0)
				return -1;
			else if (comp == 0)
				return 0;
			else
				return 1;
		}
	};
	
	/**
	 * Compare Servers by number/name
	 */
	public static Comparator<Server> svNumComparator = new Comparator<Server>() {
		
		public int compare(Server s1, Server s2) {
			
			int num1 = s1.getSVnum();
			int num2 = s2.getSVnum();
			
			// ascending order
			int comp = num1 - num2;
			
			if (comp < 0)
				return -1;
			else if (comp == 0)
				return 0;
			else
				return 1;
		}
		
	};
	
	
}
