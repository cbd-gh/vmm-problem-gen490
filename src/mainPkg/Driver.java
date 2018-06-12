package mainPkg;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver extends JFrame{
	
	private JLabel titleLab;
	// path for input file 1
	private JTextField inPath1;
	// path for input file 2
	private JTextField inPath2;
	// path for output files
	private JTextField outPath;
	// button for input 1 selection
	private JButton inputB1;
	// button for input 2 selection
	private JButton inputB2;
	// button for output directory selection
	private JButton outputB;
	// button to generate outputs
	private JButton generateB;
	// file chooser object
	private JFileChooser fileSelect;
	
	private boolean inp1Set = false;
	private boolean inp2Set = false;
	private boolean outpSet = false;
	
	private int counter = 0;
	
	public Driver()
	{
		Container cp = getContentPane();
		
		cp.setLayout(new BorderLayout());
		
		JPanel panTop = new JPanel(new FlowLayout());
		
		titleLab = new JLabel("Choose Input files and Output path for Generator");
		
		panTop.add(titleLab);
		
		JPanel panMid = new JPanel(new GridLayout(3,2,2,2));
		
		inPath1 = new JTextField();
		inPath1.setEditable(false);
		panMid.add(inPath1);
		
		inputB1 = new JButton("Get Input File 1");
		panMid.add(inputB1);
		
		inPath2 = new JTextField();
		inPath2.setEditable(false);
		panMid.add(inPath2);
		
		inputB2 = new JButton("Get Input File 2");
		panMid.add(inputB2);
		
		outPath = new JTextField();
		outPath.setEditable(false);
		panMid.add(outPath);
		
		outputB = new JButton("Set Output Directory");
		panMid.add(outputB);
		
		JPanel panBot = new JPanel(new FlowLayout());
		
		JLabel statusLab = new JLabel("Select input files 1 and 2 and a location for the Output files");
		panBot.add(statusLab);
		
		generateB = new JButton("Generate");
		panBot.add(generateB);
		
		
		
		//LISTENERS
		
		inputB1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				fileSelect = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				
				int returnVal = fileSelect.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File selected = fileSelect.getSelectedFile();
					
					String filePath = selected.getPath();
					
					inPath1.setText(filePath);
					
					inp1Set = true;
				}
				
				if (inp1Set && inp2Set && outpSet)
				{
					statusLab.setText("Ready to Generate");
				}
			}
		});
		
		inputB2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				fileSelect = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				
				int returnVal = fileSelect.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File selected = fileSelect.getSelectedFile();
					
					String filePath = selected.getPath();
					
					inPath2.setText(filePath);
					
					inp2Set = true;
				}
				
				if (inp1Set && inp2Set && outpSet)
				{
					statusLab.setText("Ready to Generate");
				}
			}
		});
		
		outputB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				fileSelect = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				
				fileSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int returnVal = fileSelect.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File selected = fileSelect.getSelectedFile();
					
					String filePath = selected.getPath();
					
					outPath.setText(filePath);
					
					outpSet = true;
				}
				
				if (inp1Set && inp2Set && outpSet)
				{
					statusLab.setText("Ready to Generate");
				}
			}
		});
		
		generateB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				if (!inp1Set || !inp2Set || !outpSet)
				{
					statusLab.setText("ERROR: Options Missing");
					return;
				}
				
				statusLab.setText("Working...");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss-SSS");
				
				LocalDateTime now = LocalDateTime.now();
				
				String currDate = dtf.format(now);
				
				String file1path = inPath1.getText();
				
				String file2path = inPath2.getText();
				
				String outputP = outPath.getText();
				
				String nDir = "txt_Output_" + currDate;
				String nDir2 = "csv_Output_" + currDate;
				
				Path oPath = Paths.get(outputP, nDir);
				Path oPath2 = Paths.get(outputP, nDir2);
				
				File txtOutFile = new File(oPath.toString());
				File csvOutFile = new File(oPath2.toString());
				
				if (txtOutFile.mkdirs() && csvOutFile.mkdirs())
				{
					System.out.println("Reading Inputs...");
					
					KaryFatTree t1 = readInputs(file1path, file2path);
					
					System.out.println("Writing Results...");
					
					fileOutput(t1, txtOutFile.toString());
					
					fileOutputCSV(t1, csvOutFile.toString());
				}
				
				System.out.println("Completed.");
				
				counter++;
				
				statusLab.setText("Generation Complete (" + counter + ")");
			}
		});
		
		cp.add(panTop, BorderLayout.NORTH);
		cp.add(panMid, BorderLayout.CENTER);
		cp.add(panBot, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("VMM Generator");
		setSize(800,400);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Driver app = new Driver();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss-SSS");
		
		LocalDateTime now = LocalDateTime.now();
		
		String currDate = dtf.format(now);
		
		String nbSv = args[0];
		
		String file2path = args[1];
		
		String outputP = args[2];
		
		String nDir = "txt_Output_" + currDate;
		String nDir2 = "csv_Output_" + currDate;
		
		Path oPath = Paths.get(outputP, nDir);
		Path oPath2 = Paths.get(outputP, nDir2);
		
		File txtOutFile = new File(oPath.toString());
		File csvOutFile = new File(oPath2.toString());
		
		if (txtOutFile.mkdirs() && csvOutFile.mkdirs())
		{
			System.out.println("Reading Inputs...");
			
			KaryFatTree t1 = readInputs(nbSv, file2path);
			
			System.out.println("Writing Results...");
			
			fileOutput(t1, txtOutFile.toString());
			
			fileOutputCSV(t1, csvOutFile.toString());
		}
		
		System.out.println("Completed.");
	}

	
	public static KaryFatTree readInputs(String nbSvs, String file2)
	{
		Scanner reader = null;
		
		//Reading information from File # 1
		
		int kvalue = 2;
		
		int bwidth = 2;
		
		kvalue = Integer.parseInt(nbSvs);
		
		/*
		try
		{
			reader = new Scanner(new FileReader(file1));
			
			System.out.println("Reading from first file...");
			
			StringTokenizer strTok;
			
			String fline;
			
			while (reader.hasNextLine())
			{
				fline = reader.nextLine();
				
				String nextToken;
				
				// if line is not blank
				if (!fline.equals(""))
				{
					strTok = new StringTokenizer(fline);
					
					nextToken = strTok.nextToken();
					
					// if line is for number of servers
					if (nextToken.equals("NB.OFSERVERS:"))
					{
						//System.out.println("Getting number of servers...");
						
						nextToken = strTok.nextToken();
						
						kvalue = Integer.parseInt(nextToken);
						
						//System.out.println("K is " + kvalue);
					}
					else if (nextToken.equals("BANDWIDTH:"))
					{
						//System.out.println("Getting Bandwidth...");
						
						nextToken = strTok.nextToken();
						
						bwidth = Integer.parseInt(nextToken);
						
						//System.out.println("Bandwidth is " + bwidth);
					}
				}
				
			}
		}
		catch (IOException e)
		{
			System.out.println("Error reading " + file1);
		}
		finally
		{
			if (reader != null)
				reader.close();
		}
		*/
		
		// Reading File #2
		
		reader = null;
		
		ArrayList<Integer> serverCV = new ArrayList<Integer>();
		
		ArrayList<Integer> serverMV = new ArrayList<Integer>();
		
		ArrayList<Integer> vmCV = new ArrayList<Integer>();
		
		ArrayList<Integer> vmMV = new ArrayList<Integer>();
		
		boolean paired = false;
		
		ArrayList<String> loadClasses = new ArrayList<String>();
		
		ArrayList<String> scenarios = new ArrayList<String>();
		
		double shufflePct = 0;
		
		try
		{
			System.out.println("Reading from second file...");
			
			reader = new Scanner(new FileReader(file2));
			
			StringTokenizer strTok;
			
			String fline;
			
			while (reader.hasNextLine())
			{
				fline = reader.nextLine();
				
				String nextToken;
				
				if (!fline.equals(""))
				{
					strTok = new StringTokenizer(fline);
					
					nextToken = strTok.nextToken();
					
					if (nextToken.equals("SVCPU:"))
					{
						//System.out.println("Getting server CPU values...");
						
						// fill array for server cpu values
						while (strTok.hasMoreTokens())
						{
							nextToken = strTok.nextToken();
							
							serverCV.add(Integer.parseInt(nextToken));
						}
						
						//System.out.println("Server CPU values recorded.");
					}
					else if (nextToken.equals("SVMEM:"))
					{
						//System.out.println("Getting Server memory values...");
						// fill array for server memory values
						while (strTok.hasMoreTokens())
						{
							nextToken = strTok.nextToken();
							
							serverMV.add(Integer.parseInt(nextToken));
						}
						
						//System.out.println("Server memory values recorded.");
					}
					else if (nextToken.equals("VMCPU:"))
					{
						
						// fill array for vm cpu values
						while (strTok.hasMoreTokens())
						{
							nextToken = strTok.nextToken();
							
							vmCV.add(Integer.parseInt(nextToken));
						}
					}
					else if (nextToken.equals("VMMEM:"))
					{
						// fill array for vm memory values
						while (strTok.hasMoreTokens())
						{
							nextToken = strTok.nextToken();
							
							vmMV.add(Integer.parseInt(nextToken));
						}
					}
					else if (nextToken.equals("PAIRED:"))
					{
						nextToken = strTok.nextToken();
						
						paired = Boolean.parseBoolean(nextToken);
					}
					else if (nextToken.equals("VMLOAD:"))
					{
						while (strTok.hasMoreTokens())
						{
							nextToken = strTok.nextToken();
							
							loadClasses.add(nextToken);
						}
					}
					else if (nextToken.equals("SCENARIOS:"))
					{
						while (strTok.hasMoreTokens())
						{
							nextToken = strTok.nextToken();
							
							scenarios.add(nextToken);
						}
					}
					else if (nextToken.equals("SHUFFLE:"))
					{
						nextToken = strTok.nextToken();
						
						double tkVal = Double.parseDouble(nextToken);
						
						shufflePct = tkVal / 100;
					}
					else
					{
						
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Problem reading " + file2);
		}
		finally
		{
			if (reader != null)
				reader.close();
		}
		
		System.out.println("Reading files complete.");
		
		// CREATING TREE FROM READ PARAMETERS
		
		KaryFatTree k1 = new KaryFatTree(kvalue, "default");
		
		//System.out.println("Creating ValueRange object...");
		
		ValueRange vr = new ValueRange(paired);
		
		vr.setSVPrange(serverCV);
		vr.setSVMrange(serverMV);
		vr.setVMPrange(vmCV);
		vr.setVMMrange(vmMV);
		
		k1.setValueRange(vr);
		
		ArrayList<ServerGroup> svclasses = new ArrayList<ServerGroup>();
		ServerGroup temp;
		
		StringTokenizer loadTok;
		
		double proportion;
		double maxload;
		
		//System.out.println("Creating loadCLasses list...");
		
		int i = 1;
		
		for (String sg : loadClasses)
		{
			
			//System.out.println("Class " + i + ":");
			
			loadTok = new StringTokenizer(sg, "-", false);
			
			proportion = Double.parseDouble(loadTok.nextToken()) / 100;
			
			//System.out.println("proportion is: " + proportion);
			
			maxload = Double.parseDouble(loadTok.nextToken()) / 100;
			
			//System.out.println("maxload is: " + maxload);
			
			temp = new ServerGroup(proportion, maxload);
			
			svclasses.add(temp);
			
			i++;
		}
		
		k1.setSVclasses(svclasses);
		
		//System.out.println("Generating Tree layout...");
		
		k1.generateTree();
		
		//System.out.println("Number of servers: " + k1.getSVlist().size());
		
		//System.out.println("Populating Tree Servers...");
		
		k1.populateServers2();
		
		//System.out.println("Tree Generation Complete");
		
		//System.out.println("Shuffling at " + shufflePct + "%");
		
		//k1.shuffle2(shufflePct);
		
		//k1.printServerDetails();
		
		k1.restore();
		
		//System.out.println("Shuffle Complete");
		
		//System.out.println("Creating Scenarios...");
		
		StringTokenizer scenTok;
		
		String scToken;
		
		//System.out.println("Running Scenarios...");
		
		for (String sc : scenarios)
		{
			scenTok = new StringTokenizer(sc, "-");
			
			scToken = scenTok.nextToken();
			
			if (scToken.equals("LBAL"))
			{
				k1.shuffle2(shufflePct);
				k1.loadBalance2();
			}
			else if (scToken.equals("FAIL"))
			{
				scToken = scenTok.nextToken();
				
				int nbFail = Integer.parseInt(scToken);
				
				k1.shuffle2(shufflePct);
				k1.fail2(nbFail);
			}
			else if (scToken.equals("CONS"))
			{
				String mmin = scenTok.nextToken();
				
				String mmax = scenTok.nextToken();
				
				double minL = Double.parseDouble(mmin) / 100;
				
				double maxL = Double.parseDouble(mmax) / 100;
				
				k1.shuffle2(shufflePct);
				k1.consolidate2(minL, maxL);
			}
			else if (scToken.equals("DSHF"))
			{
				k1.dlShuffle();
			}
		}
		
		//System.out.println("Scenarios complete.");
		
		return k1;
	}
	
	public static boolean fileOutput(KaryFatTree tree, String outPath)
	{
		PrintWriter pout = null;
		
		// PRINTING LOG FILE
		
		Path logPath = Paths.get(outPath, "log.txt");
		
		try
		{
			pout = new PrintWriter(logPath.toString());
			
			pout.println("LIST OF SCENARIOS: \n");
			
			for (FinalConfig fc : tree.getFinalStates())
			{
				pout.print(fc.getType() + " ");
				
				if (fc.getDowngraded())
					pout.print("(DOWNGRADED)");
				
				pout.println();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error Reading file");
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (pout != null)
				pout.close();
		}
		
		// INITIAL STATE OUTPUT
		
		Path initOutPath = Paths.get(outPath,"init_state.txt");
		
		tree.restore();
		
		try
		{
			pout = new PrintWriter(initOutPath.toString());
			
			pout.println("NB.POD: " + tree.getNbPods());
			
			pout.println("NB.EDGEPERPOD: " + tree.getK());
			
			pout.println("NB.SERVERS: " + tree.getSVlist().size());
			
			pout.print("LIST.SV: ");
			
			for (Server sv : tree.getSVlist())
			{
				pout.print(sv.serverOutput() + " ");
			}
			
			pout.println();
			
			pout.println("NB.VM: " + tree.getVMnum());
			
			pout.print("LIST.VM: ");
			
			for (VirtualMachine vm : tree.getVMlist())
			{
				pout.print(vm.vmOut() + " ");
			}
			
			pout.println();
			
			pout.println("VM.PER.SERVER:");
			
			for (Server sv : tree.getSVlist())
			{
				pout.println(sv.serverVMOutput());
			}
			
			pout.println();
			
			pout.print("LIST.OCCUP: ");
			
			for (Server sv : tree.getSVlist())
			{
				pout.print(sv.occupOut() + " ");
			}
			
			pout.println();
		}
		catch (IOException e)
		{
			System.out.println("Error opening file");
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (pout != null)
				pout.close();
		}
		
		// PRINT OUTPUT FILES
		
		Path finalOutPath;
		
		String outName;
		
		StringTokenizer scenNames;
		
		String nTok;
		
		int scNum = 0;
		
		for (FinalConfig fs : tree.getFinalStates())
		{
			scenNames = new StringTokenizer(fs.getType(),"-");
			
			nTok = scenNames.nextToken();
			
			// FAIL scenario
			if (nTok.equals("FAIL"))
			{
				outName = "final_state_" + fs.getType() + "_" + scNum + ".txt";
				
				finalOutPath = Paths.get(outPath, outName);
				
				try
				{
					pout = new PrintWriter(finalOutPath.toString());
					
					pout.println("SCENARIO: " + fs.getType());
					
					if (fs.getDowngraded())
						pout.println("DOWNGRADED");
					
					pout.println("VM.PER.SERVER:");
					
					for (Server sv : fs.getServers())
					{
						pout.println(sv.serverVMOutput());
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return false;
				}
				finally
				{
					if (pout != null)
						pout.close();
				}
			}
			
			if (nTok.equals("CONS"))
			{
				outName = "final_state_" + fs.getType() + "_" + scNum + ".txt";
				
				finalOutPath = Paths.get(outPath, outName);
				
				try
				{
					pout = new PrintWriter(finalOutPath.toString());
					
					pout.println("SCENARIO: " + fs.getType());
					
					if (fs.getDowngraded())
						pout.println("DOWNGRADED");
					
					pout.println("VM.PER.SERVER:");
					
					for (Server sv : fs.getServers())
					{
						pout.println(sv.serverVMOutput());
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return false;
				}
				finally
				{
					if (pout != null)
						pout.close();
				}
			}
			
			if (nTok.equals("LBAL"))
			{
				outName = "final_state_" + fs.getType() + "_" + scNum + ".txt";
				
				finalOutPath = Paths.get(outPath, outName);
				
				try
				{
					pout = new PrintWriter(finalOutPath.toString());
					
					pout.println("SCENARIO: " + fs.getType());
					
					if (fs.getDowngraded())
						pout.println("DOWNGRADED");
					
					pout.println("VM.PER.SERVER:");
					
					for (Server sv : fs.getServers())
					{
						pout.println(sv.serverVMOutput());
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return false;
				}
				finally
				{
					if (pout != null)
						pout.close();
				}
			}
			
			if (nTok.equals("DSHF"))
			{
				outName = "final_state_" + fs.getType() + "_" + scNum + ".txt";
				
				finalOutPath = Paths.get(outPath, outName);
				
				try
				{
					pout = new PrintWriter(finalOutPath.toString());
					
					pout.println("SCENARIO: " + fs.getType());
					
					if (fs.getDowngraded())
						pout.println("DOWNGRADED");
					
					pout.println("VM.PER.SERVER:");
					
					for (Server sv : fs.getServers())
					{
						pout.println(sv.serverVMOutput());
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return false;
				}
				finally
				{
					if (pout != null)
						pout.close();
				}
			}
			
			scNum++;
		}
		
		return true;
	}
	
	/**
	 * Output CSV files describing the tree states
	 * 
	 * @param tree server configuration
	 * @param outPath path to write
	 */
	public static void fileOutputCSV(KaryFatTree tree, String outPath)
	{
		// INITIAL STATE CSV
		
		//Path initPathCSV = Paths.get(outPath, "init_state.csv");
		
		tree.restore();
		
		//csvMaker(tree.getSVlist(), tree.getVMlist(), initPathCSV.toString(), "Initial");
		
		// FINAL STATE CSV
		
		Path finalPathCSV;
		
		int fsNum = 0;
		
		for (FinalConfig fs : tree.getFinalStates())
		{
			String fname = "final_state_SV" + tree.getSVlist().size() + "_"+ fs.getType() + 
					"_" + fsNum + ".csv";
			
			finalPathCSV = Paths.get(outPath, fname);
			
			csvMaker2(tree.getSVlist(), fs.getServers(), finalPathCSV.toString());
			
			fsNum++;
		}
	}
	
	/**
	 * output in .csv form
	 * 
	 * @param svs
	 * @param vms
	 * @param outPath
	 * @param type
	 */
	public static void csvMaker(ArrayList<Server> svs, ArrayList<VirtualMachine> vms, String outPath, String type)
	{
		PrintWriter pout = null;
		
		try
		{
			pout = new PrintWriter(outPath);
			
			// first line
			pout.print(svs.size() + ",");
			pout.println(vms.size() + ",");
			
			//second line
			pout.print(type + ",");
			pout.print("VM,");
			
			for (int i = 1; i < vms.size(); i++)
			{
				pout.print(",");
			}
			
			pout.print("Usage,,Capacity,");
			pout.println();
			
			// third line
			pout.print("SERVER,");
			for (int i = 0; i < vms.size(); i++)
			{
				pout.print((i + 1) + ",");
			}
			pout.print("CPU,");
			pout.print("Memory,");
			pout.print("CPU,");
			pout.print("MEMORY,");
			pout.println();
			
			//table
			for (int i = 0; i < svs.size(); i++)
			{
				Server currServ = svs.get(i);
				
				ArrayList<Integer> held = new ArrayList<Integer>();
				
				// build list of all VMs held by current server
				for (VirtualMachine vm : currServ.getVMList())
				{
					String vmName = vm.getName();
					
					int vmNum = Integer.parseInt(vmName.substring(2));
					
					held.add(vmNum);
				}
				
				pout.print((i + 1) + ",");
				
				for (int j = 0; j < vms.size(); j++)
				{
					int curr = j + 1;
					
					if (held.contains(curr))
						pout.print("1,");
					else
						pout.print("0,");
				}
				
				pout.print(currServ.getUProc() + ",");
				pout.print(currServ.getUMem() + ",");
				pout.print(currServ.getTProc() + ",");
				pout.print(currServ.getTMem() + ",");
				
				pout.println();
			}
			
			// VM stats
			
			// CPU
			
			pout.print("CPU,");
			
			for (VirtualMachine vm : vms)
			{
				pout.print(vm.getProc() + ",");
			}
			
			pout.println();
			
			// MEMORY
			
			pout.print("MEMORY,");
			
			for (VirtualMachine vm : vms)
			{
				pout.print(vm.getMem() + ",");
			}
			
			pout.println();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (pout != null)
				pout.close();
		}
	}
	
	public static void csvMaker2(ArrayList<Server> svsInit, ArrayList<Server> svsFinal, String outpath)
	{
		PrintWriter pout = null;
		
		// Constructing VM lists for init and final states
		
		ArrayList<VirtualMachine> vmInit = new ArrayList<VirtualMachine>();
		
		ArrayList<VirtualMachine> vmFinal = new ArrayList<VirtualMachine>();
		
		// populate init vm list
		for (Server svi : svsInit)
		{
			if (svi.getNbVMs() != 0)
			{
				for (VirtualMachine vmi : svi.getVMList())
				{
					vmInit.add(vmi);
				}
			}
		}
		
		// populate final vm list
		for (Server svf : svsFinal)
		{
			if (svf.getNbVMs() != 0)
			{
				for (VirtualMachine vmi : svf.getVMList())
				{
					vmFinal.add(vmi);
				}
			}
		}
		
		// sorting list by VM number
		Collections.sort(vmInit, VirtualMachine.vmNameComparator);
		
		Collections.sort(vmFinal, VirtualMachine.vmNameComparator);
		
		try
		{
			pout = new PrintWriter(outpath);
			
			//topline
			
			pout.println(svsInit.size() + "," + vmInit.size() + ",");
			
			// printing VM table
			
			pout.println("VM,I,F,N,CPU,Memory,Links");
			
			for (int i = 0; i < vmInit.size(); i++)
			{
				VirtualMachine vmCurr = vmInit.get(i);
				
				VirtualMachine vmCurr2 = vmFinal.get(i);
				
				String currName = vmCurr.getName().substring(2);
				
				int vmNum = Integer.parseInt(currName);
				
				int vmI = Integer.parseInt(vmCurr.getHost().substring(2));
				
				int vmF = Integer.parseInt(vmCurr2.getHost().substring(2));
				
				int vmCPU = vmCurr.getProc();
				
				int vmMemory = vmCurr.getMem();
				
				String vmline = vmNum + "," + vmI +"," + vmF +"," + vmMemory + "," +
				vmCPU + "," + vmMemory + ",1,";
				
				pout.println(vmline);
			}
			
			//Buffer Line
			
			pout.println(",");
			
			//Printing Server Table
			
			pout.println("Server,Capacity-CPU,Init-CPU,Final-CPU,Capacity-MEM,Init-MEM,Final-MEM,");
			
			// sorting server lists
			Collections.sort(svsInit, Server.svNumComparator);
			
			Collections.sort(svsFinal, Server.svNumComparator);
			
			for (int i = 0; i < svsInit.size(); i++)
			{
				Server svCurr = svsInit.get(i);
				
				Server svCurr2 = svsFinal.get(i);
				
				int currNum = Integer.parseInt(svCurr.getName().substring(2));
				
				int cpuCap = svCurr.getTProc();
				int cpuInit = svCurr.getTProc() - svCurr.getUProc();
				int cpuFinal = svCurr2.getTProc() - svCurr2.getUProc();
				
				int memCap = svCurr.getTMem();
				int memInit = svCurr.getTMem() - svCurr.getUMem();	
				int memFinal = svCurr2.getTMem() - svCurr2.getUMem();
				
				String svLine = currNum + "," + cpuCap + "," + cpuInit + "," + cpuFinal + "," + 
				memCap + "," + memInit + "," + memFinal + ",";
				
				pout.println(svLine);
			}
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (pout != null)
				pout.close();
		}
	}
}
