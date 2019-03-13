package ch.hearc.spring.thymeleaf;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.hearc.spring.thymeleaf.model.CompteBancaire;
import ch.hearc.spring.thymeleaf.model.Transfert;
import ch.hearc.spring.thymeleaf.model.TransfertDomaineService;
import ch.hearc.spring.thymeleaf.repository.CompteBancaireRepository;
import ch.hearc.spring.thymeleaf.repository.TransfertRepository;



@SpringBootApplication
public class CrpytoBankApplication {

	@Autowired
	CompteBancaireRepository compteBancaireRepository;
	
	@Autowired
	TransfertRepository transfertRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrpytoBankApplication.class, args);
	}
	
	@PostConstruct
	public void initCompteBancaire() {
		
		
		CompteBancaire cpt1 = new CompteBancaire("seb", 2000, "SCE-1234");
		compteBancaireRepository.save(cpt1);
		System.out.println("Compte1 créé: " + cpt1);
		
		CompteBancaire cpt2 = new CompteBancaire("tim",1500, "TIM-123");
		compteBancaireRepository.save(cpt2);
		System.out.println("Compte2 créé: " + cpt2);
		
		CompteBancaire cpt3 = new CompteBancaire("tom",20, "TOM-123");
		compteBancaireRepository.save(cpt3);
		System.out.println("Compte2 créé: " + cpt3);
		
		
		Transfert t = new Transfert(cpt1,cpt2,1000);
		t.setDescription("Init data");
		t =  transfertRepository.save(t);
		cpt1.setSolde(1000);
		compteBancaireRepository.save(cpt1);
		cpt2.setSolde(2500);
		compteBancaireRepository.save(cpt2);
		
		t = new Transfert(cpt1,cpt3,100);
		t.setDescription("Init data");
		t =  transfertRepository.save(t);
		cpt1.setSolde(900);
		compteBancaireRepository.save(cpt1);
		cpt3.setSolde(120);
		compteBancaireRepository.save(cpt3);
		
		t = new Transfert(cpt1,cpt3,100);
		t.setDescription("Init data");
		t =  transfertRepository.save(t);
		cpt1.setSolde(800);
		compteBancaireRepository.save(cpt1);
		cpt3.setSolde(220);
		compteBancaireRepository.save(cpt3);
		
		t = new Transfert(cpt3,cpt1,100);
		t.setDescription("Init data");
		t =  transfertRepository.save(t);
		cpt1.setSolde(900);
		compteBancaireRepository.save(cpt1);
		cpt3.setSolde(120);
		compteBancaireRepository.save(cpt3);
		
		
		
	}
	
}
