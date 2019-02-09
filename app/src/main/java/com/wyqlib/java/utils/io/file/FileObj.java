package com.wyqlib.java.utils.io.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.wyqlib.java.utils.base.array.ArrayUtils;
import com.wyqlib.java.utils.base.string.StringUtils;


public class FileObj implements Serializable {
	public FileModel[] Files;

	public FileModel[] Directories;

	public String Root = "";

	public FileObj() {

	}

	public FileObj(FileModel[] files, FileModel[] directories, String root) {
		Files = files;
		Directories = directories;
		Root = root;
	}

	public FileModel[] getDirectoryModels(String dirPath) {
		List<FileModel> lfs = new ArrayList<FileModel>();

		dirPath = ctrim(dirPath);

		for (FileModel fm : Directories) {
			if (ctrim(fm.ParentDirectory).equals(dirPath)) {
				lfs.add(fm);
			}
		}

		return lfs.toArray(new FileModel[lfs.size()]);
	}

	public String ctrim(String s) {
		return StringUtils.trim(s, new char[] { ' ', '/', '\\' }).replace("/", "\\");
	}

	public FileModel[] getFileModels(String dirPath) {
		List<FileModel> lfs = new ArrayList<FileModel>();

		dirPath = ctrim(dirPath);

		for (FileModel fm : Files) {
			if (ctrim(fm.ParentDirectory).equals(dirPath)) {
				lfs.add(fm);
			}
		}

		return lfs.toArray(new FileModel[lfs.size()]);
	}

	public FileModel[] getDirFileModels(String dirPath) {
		return ArrayUtils.concat(getDirectoryModels(dirPath), getFileModels(dirPath));
	}

	public FileModel getFile(String fullPath) {
		fullPath = ctrim(fullPath);

		for (FileModel fm : Files) {
			if (ctrim(fm.FullPath).equals(fullPath)) {
				return fm;
			}
		}
		return null;
	}

	public FileModel getDirectory(String fullPath) {
		fullPath = ctrim(fullPath);

		for (FileModel fm : Directories) {
			if (ctrim(fm.FullPath).equals(fullPath)) {
				return fm;
			}
		}
		return null;
	}

	public FileModel[] searchDirectories(String pattern) {
		List<FileModel> lfs = new ArrayList<FileModel>();

		for (FileModel fm : Directories) {
			if (fm.Name.contains(pattern)) {
				lfs.add(fm);
			}
		}
		return lfs.toArray(new FileModel[lfs.size()]);
	}

	public FileModel[] searchFiles(String pattern) {
		List<FileModel> lfs = new ArrayList<FileModel>();

		for (FileModel fm : Files) {
			if (fm.Name.contains(pattern)) {
				lfs.add(fm);
			}
		}
		return lfs.toArray(new FileModel[lfs.size()]);
	}

	public FileModel[] SearchDirFiles(String pattern) {
		return ArrayUtils.concat(searchDirectories(pattern), searchFiles(pattern));
	}
}
