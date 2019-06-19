package JavaBonusHW;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.cli.*;

public class BonusDemo {

	String inputPath;
	boolean allList;
	boolean sortedList;
	boolean size;
	boolean time;
	boolean help; 

	void run(String[] args) {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}
		}
		
//		try {
//			if(args[0].equals("ls")==false)
//				throw new Exception();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.exit(0);
//		}
	
		if (inputPath.isEmpty()) {
			System.out.println("No input path");
			System.exit(0);
		}
		
		File fileName = new File(inputPath);
		File[] fileList = fileName.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		
		for(File getFileList: fileList) {
			
		  if(getFileList.isFile()) {
		    String tempFileName = getFileList.getName();
		    fileNames.add(tempFileName);
		   
		  }
		}
		
		if (allList) {
			System.out.println("[f] option");
			System.out.println("This is list of all files in the path\n");
			System.out.println(" ");

			for (String toPrint : fileNames) {
				System.out.println(toPrint);
			}
			

		} 
		
		if (sortedList) {
			System.out.println("[a] option");
			System.out.println("This is list of sorted files in the path\n");
		
			Collections.sort(fileNames);

	        for (String toCheck : fileNames) {
	        	System.out.println(toCheck);
	        }


		} 
		
		if (size) {
			System.out.println("[s] option");
			System.out.println("This is size of files in the path\n");
			
			ArrayList<String> fileSizes = new ArrayList<String>();
			
			for (File fileSize : fileList) {
				long size = fileSize.length();
				String temp = new String(Long.toString(size));
				fileSizes.add(temp);
			}
			
			System.out.println("/File size/");
			for(String size : fileSizes) {
				System.out.println(size);
			}

			
		}
		
		if (time) {
			System.out.println("[t] option");
			System.out.println("This is the time of files in the path\n");
			
			for(File file : fileList) {
				String date = new SimpleDateFormat("\tMMM/dd/hh:mm") .format( 
						new Date(file.lastModified()) );

					System.out.println(file.getName()+""+date);				
			}
			
		} 
	
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			inputPath = cmd.getOptionValue("i");
			allList = cmd.hasOption("f");
			sortedList = cmd.hasOption("a");
			time = cmd.hasOption("t");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(
				Option.builder("i")
				.longOpt("InpuPath")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("f")
				.longOpt("fileName")
				.desc("Display list of all files in the directory")
				.argName("listOption")
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("a")
				.longOpt("Sortedlist")
				.desc("Display list of sorted files in the directory")
				.argName("sortedListOption")
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("t")
				.longOpt("time")
				.desc("Display list of the time in the directory")
				.argName("timeOption")
				.build());

		// add options by using OptionBuilder
		options.addOption(
				Option.builder("s")
				.longOpt("size")
				.desc("Display the size of files")
				.argName("sizeOption")
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h")
				.longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}

	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "JavaBonusHW";
		String footer = "";
		formatter.printHelp("Java_CLI_BonusHW", header, options, footer, true);
	}

}