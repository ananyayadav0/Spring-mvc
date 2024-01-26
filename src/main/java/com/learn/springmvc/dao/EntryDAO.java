package com.learn.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.springmvc.entity.EntryEntity;

@Component
public interface EntryDAO {

	void insertEntry(EntryEntity entry);
	List<EntryEntity> getAllEntries();
	EntryEntity getEntry(int nid);
	void deleteEntry(int nid);
}
