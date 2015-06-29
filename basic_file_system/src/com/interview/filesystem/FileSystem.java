package com.interview.filesystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Currency;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FileSystem {
	
	static Directory currentDirectory = new Directory("", null);

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		
		try{
			String line = reader.readLine();
			while(line != null){
				
				System.out.println(line);
				if(line.indexOf("create ") == 0){
					boolean result = createFile(line.substring(7));
					if(!result){
						System.out.println("Error creating file. Command "+line);
					}
				}else if(line.indexOf("mkdir ") == 0){
					boolean result = createDirectory(line.substring(6));
					if(!result){
						System.out.println("Error creating directory. Command "+line);
					}
				}else if(line.indexOf("append ") == 0){
					boolean result = appendToFile(line.substring(7));
					if(!result){
						System.out.println("Error appending string to file. Command "+line);
					}
				}else if(line.indexOf("cp ") == 0){
					boolean result = copyFile(line.substring(3));
					if(!result){
						System.out.println("Error copying file. Command "+line);
					}
				}else if(line.indexOf("rm ") == 0){
					boolean result = removeFile(line.substring(3));
					if(!result){
						System.out.println("Error removing file. Command "+line);
					}
				}else if(line.indexOf("cat ") == 0){
					boolean result = concatenate(line.substring(4));
					if(!result){
						System.out.println("Error reading file. Command "+line);
					}
				}
				
				line = reader.readLine();
			}
			
			System.out.println("\n");
			displayDirectoryContents();
		}finally {
			reader.close();
		}
		
	}
	
	private static void displayDirectoryContents() {

		printFilesInDirectory(currentDirectory);
		
		Map<String, Directory> directories = currentDirectory.getChildren();
		Iterator it = directories.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Directory> directory = (Map.Entry<String, Directory>) it.next();
			System.out.println("/"+ directory.getKey());
			printFilesInDirectory(directory.getValue());
		}
		
	}
	
	private static void printFilesInDirectory(Directory d){
		Map<String, File> files = d.getFiles();
		Iterator it = files.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, File> file = (Map.Entry<String, File>) it.next();
			System.out.println("/"+ file.getKey() + " (" + file.getValue().getUpdates()+") " + file.getValue().getContents() );
		}
	}

	private static boolean concatenate(String command) {

		String[] strArr = command.split("/");
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr);
			
			File f = d.getFile(strArr[strArr.length-1]);
			System.out.println(f.getContents());
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static boolean removeFile(String command) {

		String[] strArr = command.split("/");
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr);
			
			d.removeFile(strArr[strArr.length-1]);
			//System.out.println("Removed file "+strArr[strArr.length-1]+" to directory "+d.getName());
			return true;
		}catch(Exception e){
			System.out.println("Error "+e.getMessage());
			return false;
		}
	}

	private static boolean copyFile(String command) {

		
		String[] strArr0 = command.split(" ");
		
		String[] strArr1 = strArr0[0].split("/");
		String[] strArr2 = strArr0[1].split("/");
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr1);
			
			File f = d.getFile(strArr1[strArr1.length-1]);
			
			
			Directory d2 = findDirectory(strArr2);
			File f2 = new File(strArr2[strArr2.length-1], f);
			d2.addFile(f2);
			f2.setDirectory(d2);
			//System.out.println("Copied of file "+f.getName()+ " to "+f2.getName());
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static boolean appendToFile(String command) {

		
		String[] strArr0 = command.split(" ");
		String[] strArr1 = strArr0[1].split("/");
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr1);
			
			File f = d.getFile(strArr1[strArr1.length-1]);
			
			f.appendContent(strArr0[0]);
			//System.out.println("Contents of file "+f.getName()+ " : "+f.getContents());
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean createFile(String command){
		String[] strArr = command.split("/");
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr);
			
			File f = new File(strArr[strArr.length-1]);
			d.addFile(f);
			//System.out.println("Added file "+f.getName()+" to directory "+d.getName());
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private static Directory findDirectory(String[] strArr) {
		
		Directory currDir = currentDirectory;
		for(int i=1; i<strArr.length-1;i++){
			try{
				currDir = currDir.getChildDirectory(strArr[i]);
			}catch(Exception e){
				System.out.println(e.getMessage());
				throw e;
			}
		}
		return currDir;
	}

	public static boolean createDirectory(String command){
		String[] strArr = command.split("/");
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr);
			
			Directory newDir = new Directory(strArr[strArr.length-1], d);
			d.addChildDirectory(newDir);
			//System.out.println("Added new directory "+newDir.getName()+" to directory "+d.getName());
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

}
