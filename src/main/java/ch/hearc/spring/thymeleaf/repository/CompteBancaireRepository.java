package ch.hearc.spring.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.spring.thymeleaf.model.CompteBancaire;

@Repository
public interface CompteBancaireRepository extends CrudRepository<CompteBancaire, Long>{

	public CompteBancaire findByIdentifiant(String identifiant);
	
	public CompteBancaire findByUserName(String identifiant);
	
	@Query("select c from CompteBancaire c where c.id != ?1")
	public List<CompteBancaire> findCompteWitoutLoggedId(Long id);
	
}
