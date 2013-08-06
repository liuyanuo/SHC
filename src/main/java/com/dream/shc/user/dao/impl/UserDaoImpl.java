package com.dream.shc.user.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dream.shc.base.dao.impl.BaseDaoImpl;
import com.dream.shc.user.dao.UserDao;
import com.dream.shc.user.document.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public void save(User object) {
		getMongoTemplate().insert(object);
		
	}

	@Override
	public User get(String id) {
		return getMongoTemplate().findOne(new Query(Criteria.where("id").is(id)),
				User.class);
	}

	@Override
	public void delete(String id) {
		getMongoTemplate().remove(new Query(Criteria.where("id").is(id)), User.class);
		
	}

	@Override
	public void createCollection() {
		if (!getMongoTemplate().collectionExists(User.class)) {
			getMongoTemplate().createCollection(User.class);
		}
	}

	@Override
	public void dropCollection() {
		if (!getMongoTemplate().collectionExists(User.class)) {
			getMongoTemplate().dropCollection(User.class);
		}
	}



}
