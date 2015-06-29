package com.interview.filesystem;

public class Directory {

	private String name;
	private Directory parent;
	private Directory[] children;
	
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
	public Directory[] getChildren() {
		return children;
	}
	public void setChildren(Directory[] children) {
		this.children = children;
	}
}
