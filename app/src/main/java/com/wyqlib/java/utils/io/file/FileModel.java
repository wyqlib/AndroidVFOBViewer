package com.wyqlib.java.utils.io.file;

import java.io.Serializable;


public class FileModel implements Serializable{
	public boolean IsFile = false;

    public long Length = 0;

    public String FullPath = "";

    public String CreationTime = "";

    public String LastWriteTime = "";

    public String LastAccessTime = "";

    public String ParentDirectory = "";

    public String Name = "";

    public String Extension = "";
    public FileModel(){
    	
    }

	public FileModel(boolean isFile, long length, String fullPath, String creationTime, String lastWriteTime,
			String lastAccessTime, String parentDirectory, String name, String extension) {
		IsFile = isFile;
		Length = length;
		FullPath = fullPath;
		CreationTime = creationTime;
		LastWriteTime = lastWriteTime;
		LastAccessTime = lastAccessTime;
		ParentDirectory = parentDirectory;
		Name = name;
		Extension = extension;
	}
}
