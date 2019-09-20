package com.tchemso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.MvtStock;

import ch.qos.logback.core.db.dialect.MsSQLDialect;

public interface MvtRepository extends JpaRepository<MvtStock, Long> {
	@Query("select sum(m.quantite) from MvtStock m where m.article.idArticle=:x and m.typeMvt=1")
	public List<MvtStock> nbArticle(@Param("x") Long id);
	
	@Query("select m from MvtStock m where m.article.idArticle=:x and  m.typeMvt=3")
	 public MvtStock mvtByAtricle(@Param("x") Long id);//elle va permettre de prendre un movement par rapport à l'id de l'article
}
