package com.souha.parfums.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.souha.parfums.entities.Marque;
import com.souha.parfums.entities.Parfum;

@RepositoryRestResource(path="rest")
public interface ParfumRepository extends JpaRepository<Parfum,Long> {
	List<Parfum> findByNomParfum(String nom);
	List<Parfum> findByNomParfumContains(String nom);
	
	/*@Query("select p from Parfum p where p.nomParfum like %?1 and p.prixParfum > ?2")
	List<Parfum> findByNomPrix (String nom, Double prix);*/
	
	@Query("select p from Parfum p where p.nomParfum like %:nom and p.prixParfum > :prix")
	List<Parfum> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);
	@Query("select p from Parfum p where p.marque =?1")
	List<Parfum> findByMarque (Marque marque);
	
	List<Parfum>findByMarqueIdMarque(Long id);
	
	List<Parfum> findByOrderByNomParfumAsc();
	
	@Query("select p from Parfum p order by p.nomParfum ASC, p.prixParfum DESC")
	List<Parfum> trierParfumsNomsPrix ();
	
}
