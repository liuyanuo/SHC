package com.dream.shc.main.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.dream.shc.main.dao.GridFSDao;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
@Repository("gridFSDao")
public class GridFSDaoImpl implements GridFSDao {
	@Autowired
	protected Logger logger;
	
	private MongoTemplate mongoTemplateFS;
	public MongoTemplate getMongoTemplateFS() {
		return mongoTemplateFS;
	}
	@Resource(name="mongoTemplateFS")
	public void setMongoTemplateFS(MongoTemplate mongoTemplateFS) {
		this.mongoTemplateFS = mongoTemplateFS;
	}

	@Override
	public String savePhoto(File file, String newFileName) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		GridFSInputFile gfsFile;
		try {
			gfsFile = gfsPhoto.createFile(file);
			gfsFile.setFilename(newFileName);
			gfsFile.save();
			return gfsFile.getId().toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public String savePhoto(File file) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		GridFSInputFile gfsFile;
		try {
			gfsFile = gfsPhoto.createFile(file);
			gfsFile.save();
			return gfsFile.getId().toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String savePhoto(InputStream is) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		GridFSInputFile gfsFile;
		gfsFile = gfsPhoto.createFile(is);
		gfsFile.save();
		return gfsFile.getId().toString();
	}

	@Override
	public String savePhoto(InputStream is, String fileName) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		GridFSInputFile gfsFile;
		gfsFile = gfsPhoto.createFile(is);
		gfsFile.setFilename(fileName);
		gfsFile.save();
		return gfsFile.getId().toString();
	}
	@Override
	public String savePhoto(MultipartFile photoFile) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		GridFSInputFile gfsFile;
		try {
			gfsFile = gfsPhoto.createFile(photoFile.getInputStream());
			gfsFile.setFilename(photoFile.getName());
			gfsFile.setContentType(photoFile.getContentType());
			gfsFile.save();
			return gfsFile.getId().toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public GridFSDBFile getPhotoByName(String fileName) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		return gfsPhoto.findOne(fileName);
	}

	@Override
	public GridFSDBFile getPhotoById(String id) {
		GridFS gfsPhoto = new GridFS(mongoTemplateFS.getDb(), "photo");
		return gfsPhoto.findOne(new ObjectId(id));
	}


}
