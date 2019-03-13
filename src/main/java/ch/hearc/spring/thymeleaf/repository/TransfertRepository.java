package ch.hearc.spring.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.spring.thymeleaf.model.Transfert;

@Repository
public interface TransfertRepository extends CrudRepository<Transfert, Long>{

	@Query("select t from Transfert t where t.crediteur.id = ?1 or t.debiteur = ?1 order by t.date desc")
	List<Transfert> findByDebiteurIdEqualsOrCrediteurIdEquals(Long id);
}
