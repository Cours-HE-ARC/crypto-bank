package ch.hearc.spring.thymeleaf.model;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ch.hearc.spring.thymeleaf.repository.CompteBancaireRepository;
import ch.hearc.spring.thymeleaf.repository.TransfertRepository;


public class TransfertDomaineService {

	
	CompteBancaireRepository compteBancaireRepository;
	
	
	TransfertRepository transfertRepository;
	
	public TransfertDomaineService(CompteBancaireRepository compteBancaireRepository,TransfertRepository transfertRepository ) {
		this.transfertRepository = transfertRepository;
		this.compteBancaireRepository = compteBancaireRepository;
		
	}

	private CompteBancaire findCompteUserLogged() {
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		
		return compteBancaireRepository.findByUserName(aut.getName());
	}

	private CompteBancaire findCompteByNoCompte(String noCompte) {
		System.out.println("Find by no compte  :" + noCompte);
		return compteBancaireRepository.findByIdentifiant(noCompte);
	}

	@Transactional
	public boolean transfertMontant(String noCompte, int transfert, String desc) {
		CompteBancaire crediteur = findCompteByNoCompte(noCompte);
		CompteBancaire debiteur = findCompteUserLogged();
		
		System.out.println("Compte crediteur  :" + debiteur.getIdentifiant());
		System.out.println("Compte debiteur  :" + crediteur.getIdentifiant());
		
		if(debiteur.getSolde() - transfert < 0) {
			throw new RuntimeException("Not sufficient found on debiteur account: " + debiteur.getSolde());
		}
		Transfert t = new Transfert(debiteur,crediteur,transfert);
		t.setDescription(desc);
		t =  transfertRepository.save(t);
		System.out.println("Transfert  :" + t);
		
		
		if(t.getId() != null) {
			int crediTeurSolde = crediteur.getSolde() + transfert;
			crediteur.setSolde(crediTeurSolde);
			compteBancaireRepository.save(crediteur);
			
			int debiTeurSolde = debiteur.getSolde() - transfert;
			debiteur.setSolde(debiTeurSolde);
			compteBancaireRepository.save(debiteur);
			return true;
		}else {
			return false;
		}
	}
}
