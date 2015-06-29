package com.interview.filesystem;

public class File {

	private String name;
	private String contents = null;
	private Directory directory;
	private int updates;

	public File(String name){
		this.name = name;
		this.updates = 0;
	}
	
	public File(String name, File otherFile){
		this.name = name;
		this.updates = 0;
		this.contents = otherFile.getContents();
	}
	
	public int getUpdates() {
		return updates;
	}

	public void setUpdates(int updates) {
		this.updates = updates;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}
	
	public void appendContent(String str){
		if(contents != null){
			contents = contents + "\n" + str;
		}else{
			contents = str;
		}
		
		this.updates++;
	}

	
}
