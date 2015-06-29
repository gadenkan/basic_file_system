package com.interview.filesystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Currency;

public class FileSystem {
	
	static Directory currentDirectory = new Directory("", null);

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		
		try{
			String line = reader.readLine();
			while(line != null){
				
				if(line.indexOf("create ") == 0){
					boolean result = createFile(line.substring(6));
					if(result){
						System.out.println(line);
					}else{
						System.out.println("Error creating file. Command "+line);
					}
				}else if(line.indexOf("mkdir ") == 0){
					boolean result = createDirectory(line.substring(5));
					if(result){
						System.out.println(line);
					}else{
						System.out.println("Error creating directory. Command "+line);
					}
				}else if(line.indexOf("append ") == 0){
					boolean result = appendToFile(line.substring(6));
					if(result){
						System.out.println(line);
					}else{
						System.out.println("Error appending string to file. Command "+line);
					}
				}else if(line.indexOf("cp ") == 0){
					boolean result = copyFile(line.substring(2));
					if(result){
						System.out.println(line);
					}else{
						System.out.println("Error copying file. Command "+line);
					}
				}else if(line.indexOf("rm ") == 0){
					boolean result = removeFile(line.substring(2));
					if(result){
						System.out.println(line);
					}else{
						System.out.println("Error removing file. Command "+line);
					}
				}else if(line.indexOf("cat ") == 0){
					boolean result = concatenate(line.substring(3));
					if(result){
						System.out.println(line);
					}else{
						System.out.println("Error reading file. Command "+line);
					}
				}
				
				line = reader.readLine();
			}
		}finally {
			reader.close();
		}
		
	}
	
	private static boolean concatenate(String command) {

		System.out.println(command);
		return false;
	}

	private static boolean removeFile(String command) {

		System.out.println(command);
		return false;
	}

	private static boolean copyFile(String command) {

		System.out.println(command);
		return false;
	}

	private static boolean appendToFile(String command) {

		System.out.println(command);
		
		String[] strArr0 = command.split(" ");
		String[] strArr1 = strArr0[1].split("/");
		System.out.println(strArr0.length);
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr1);
			
			File f = new File(strArr1[strArr1.length-1]);
			d.addFile(f);
			System.out.println("Added file "+f.getName()+" to directory "+d.getName());
			
			System.out.println("Adding content to file "+f.getName());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static boolean createFile(String command){
		System.out.println(command);
		String[] strArr = command.split("/");
		System.out.println(strArr.length);
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr);
			
			File f = new File(strArr[strArr.length-1]);
			d.addFile(f);
			System.out.println("Added file "+f.getName()+" to directory "+d.getName());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private static Directory findDirectory(String[] strArr) {
		
		Directory currDir = currentDirectory;
		for(int i=1; i<strArr.length-1;i++){
			try{
				currDir = currDir.getChildDirectory(strArr[i]);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		}
		return currDir;
	}

	public static boolean createDirectory(String command){
		System.out.println(command);
		String[] strArr = command.split("/");
		System.out.println(strArr.length);
			
		// find directory
		// when the correct directory is reached
		// add file to the correct directory
		// break after adding
		try{
			Directory d = findDirectory(strArr);
			
			Directory newDir = new Directory(strArr[strArr.length-1], d);
			d.addChildDirectory(newDir);
			System.out.println("Added new directory "+newDir.getName()+" to directory "+d.getName());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
