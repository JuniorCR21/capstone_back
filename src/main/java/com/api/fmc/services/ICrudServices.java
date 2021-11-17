package com.api.fmc.services;

import java.util.List;

public interface ICrudServices <T,ID>{

	public List<T> findAll();
	public T findById(ID id);
	public T save (T body);
	public void deleteById(ID id);
}
