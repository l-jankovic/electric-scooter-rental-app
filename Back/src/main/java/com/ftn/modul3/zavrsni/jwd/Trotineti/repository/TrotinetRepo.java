package com.ftn.modul3.zavrsni.jwd.Trotineti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;

@Repository
public interface TrotinetRepo extends JpaRepository<Trotinet, Long> {

	
	
	@Query("SELECT t FROM Trotinet t WHERE " +
	"(:adresaId IS NULL OR t.adresa.id = :adresaId) AND" +
	"(:batOd IS NULL OR :batDo IS NULL OR t.nivoBat BETWEEN :batOd AND :batDo)")
		Page<Trotinet> search(@Param("adresaId") Long adresaId,@Param("batOd") Integer batOd,@Param("batDo") Integer batDo,Pageable pageable);

	Page<Trotinet> findByNivoBatBetween(Integer batOd, Integer batDo,Pageable pageable);
	
	Page<Trotinet> findByAdresaIdAndNivoBatBetween( Long adresaId,Integer batOd, Integer batDo,Pageable pageable);
}
