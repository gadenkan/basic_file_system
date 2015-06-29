package com.interview.filesystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileSystem {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		
		try{
			String line = reader.readLine();
			while(line != null){
				
				if(line.indexOf("create ") == 0){
					
				}else if(line.indexOf("mkdir ") == 0){
					
				}else if(line.indexOf("append ") != 0){
					
				}else if(line.indexOf("cp ") != 0){
					
				}else if(line.indexOf("rm ") != 0){
					
				}else if(line.indexOf("cat ") != 0){
					
				}
				
				line = reader.readLine();
			}
		}finally {
			reader.close();
		}
		
	}

}
