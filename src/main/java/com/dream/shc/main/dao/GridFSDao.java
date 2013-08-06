package com.dream.shc.main.dao;

import java.io.File;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

public interface GridFSDao {
	public String savePhoto(File file);
	public String savePhoto(File file,String newFileName);
	public String savePhoto(InputStream is);
	public String savePhoto(InputStream is,String fileName);
	public String savePhoto(MultipartFile photoFile);
	public GridFSDBFile getPhotoByName(String fileName);
	public GridFSDBFile getPhotoById(String id);
}
