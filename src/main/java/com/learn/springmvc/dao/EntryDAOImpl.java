package com.learn.springmvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.learn.springmvc.entity.EntryEntity;

@Transactional
@Component
public class EntryDAOImpl implements EntryDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void insertEntry(EntryEntity entry)
	{
		this.hibernateTemplate.saveOrUpdate(entry);
	}
	
	public List<EntryEntity> getAllEntries()
	{
		List<EntryEntity> entries=this.hibernateTemplate.loadAll(EntryEntity.class);
		return entries;
	}
	
	public EntryEntity getEntry(int nid)
	{
		EntryEntity entry=this.hibernateTemplate.get(EntryEntity.class,nid);
		return entry;
	} 
	
	public void deleteEntry(int nid) {
		EntryEntity entry=this.hibernateTemplate.load(EntryEntity.class,nid);
		this.hibernateTemplate.delete(entry);
	}
	
}
