package com.tchemso.dao;

import java.util.List;

public interface GenererDao_interface<E> {
	public E sauvegarde(E entity);

	public E misajour(E entity);

	public List<E> tousLister(E entity);

	public E trouverParId(Long id);

	public E supprimer(Long id);

	public List<E> tousLister(String mot, String ordre);
}
