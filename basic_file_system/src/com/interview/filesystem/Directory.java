package com.interview.filesystem;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Directory {

	private String name;
	private Directory parent;
	private HashMap<String, Directory> children = new LinkedHashMap<String, Directory>();
	private HashMap<String, File> files = new LinkedHashMap<String, File>();
	
	public Directory(String name, Directory parent){
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Directory getParent() {
		return parent;
	}
	public void setParent(Directory parent) {
		this.parent = parent;
	}

	

	public HashMap<String, Directory> getChildren() {
		return children;
	}

	public void setChildren(HashMap<String, Directory> children) {
		this.children = children;
	}

	public HashMap<String, File> getFiles() {
		return files;
	}

	public void setFiles(HashMap<String, File> files) {
		this.files = files;
	}

	public void addChildDirectory(Directory child) throws Exception{
		// add a child directory
		if(children.containsKey(child.getName())){
			throw new Exception("Directory "+child.getName()+" already exists");
		}else{
			child.setParent(this);
			children.put(child.getName(), child);
		}

	}
	
	public Directory getChildDirectory(String name) throws Exception{
		
		if(children.containsKey(name)){
			return children.get(name);
		}else{
			throw new Exception("Error: Directory does not exist: /"+name);
		}
	}
	
	public void addFile(File file) throws Exception{
		if(files.containsKey(file.getName())){
			throw new Exception("File "+file.getName()+" already exists");
		}else{
			files.put(file.getName(), file);
		}
	}
	
	public File getFile(String name) throws Exception{
		if(files.containsKey(name)){
			return files.get(name);
		}else{
			throw new Exception("Error: File does not exist: /"+name);
		}
	}
	
	public void removeFile(String name) throws Exception{
		if(files.containsKey(name)){
			files.remove(name);
		}else{
			throw new Exception("Error: File does not exist: /"+name);
		}
	}
}
