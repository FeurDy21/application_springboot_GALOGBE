package com.tchemso.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tchemso.dao.MvtRepository;
import com.tchemso.entities.MvtStock;

@Service
@Transactional
public class impl_MvtStock implements I_MvtStock {
	@Autowired
	MvtRepository mv;

	@Override
	public MvtStock AjouterEnStock(MvtStock mvtStock) {
		// TODO Auto-generated method stub
		if (mvtStock.getTypeMvt() == 0) {
			throw new RuntimeException("Veillez cocher l'operation à faire");
		}
		return mv.save(mvtStock);
	}
	
	 public MvtStock mvtByAtricle(Long id) {
		 MvtStock mvtStock=mv.mvtByAtricle(id);
		 if(mvtStock==null) {
			 throw new RuntimeException("l/'article n/'a pas connu de mouvement en stock");
		 }
		 return mvtStock;
	 }
}
