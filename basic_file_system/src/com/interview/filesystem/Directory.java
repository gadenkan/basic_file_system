package com.interview.filesystem;

import java.util.HashMap;
import java.util.Map;

public class Directory {

	private String name;
	private Directory parent;
	private HashMap<String, Directory> children;
	private HashMap<String, File> files;
	
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

	public void setChild(Directory child){
		// add a child directory
		child.setParent(this);
		children.put(child.getName(), child);
	}
	
	public Directory getChildDirectory(Directory name){
		
		if(children.containsKey(name)){
			return children.get(name);
		}else{
			throw new Error("Does not contain directory "+name);
		}
	}
}
