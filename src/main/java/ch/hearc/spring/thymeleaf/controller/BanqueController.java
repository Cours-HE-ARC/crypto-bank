package ch.hearc.spring.thymeleaf.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hearc.spring.thymeleaf.model.CompteBancaire;
import ch.hearc.spring.thymeleaf.model.Transfert;
import ch.hearc.spring.thymeleaf.model.TransfertDomaineService;
import ch.hearc.spring.thymeleaf.repository.CompteBancaireRepository;
import ch.hearc.spring.thymeleaf.repository.TransfertRepository;
import ch.hearc.spring.thymeleaf.security.SecurityUtil;



@Controller
public class BanqueController {

	@Autowired
	CompteBancaireRepository compteBancaireRepository;
	
	@Autowired
	TransfertRepository transfertRepository;
	
		
		
		@GetMapping("/comptes")
		public String comptes(Map<String, Object> model) {
			
			model.put("page", "Administration des comptes bancaires");
			model.put("comptes", compteBancaireRepository.findAll());
			return "admin-comptes";
		}
		
		@PostMapping(value = "/transfert")
	    public String transfer(@Valid @ModelAttribute TransfertDto transfert, BindingResult errors, Model model) {
	       
			TransfertDomaineService transfertSevice = new TransfertDomaineService(compteBancaireRepository,transfertRepository);
			transfertSevice.transfertMontant(transfert.getNoDebiteur(), transfert.getMontant(), transfert.getDescription());
			
			
			System.out.println(
	    		   String.format("Transfer to %s", transfert.getNoDebiteur())
			);
	       
	       return ((errors.hasErrors()) ? "saisie_transfert" : "redirect:compte");
	       
	    }
		
		@GetMapping("/compte")
		public String comptesperso(Map<String, Object> model) {
			
			model.put("page", "Compte bancaire");
			
			String loggedUserName = SecurityUtil.getLoggedUserName();
			
			CompteBancaire compteBancaire = compteBancaireRepository.findByUserName(loggedUserName);
			List<Transfert> transferts = transfertRepository.findByDebiteurIdEqualsOrCrediteurIdEquals(compteBancaire.getId());
			
			model.put("transactions",transferts );
			model.put("compte", compteBancaire);
			return "compte";
		}
		
		@GetMapping("/saisie_transfert")
		public String transfert(Map<String, Object> model) {
			
			model.put("page", "Saisir transfert");
			
			String loggedUserName = SecurityUtil.getLoggedUserName();
			
			CompteBancaire compteBancaire = compteBancaireRepository.findByUserName(loggedUserName);
			
			model.put("cpt_debit", compteBancaire);
			model.put("comptes_dispo",compteBancaireRepository.findCompteWitoutLoggedId(compteBancaire.getId()) );
			model.put("transfert", new TransfertDto());
			return "saisie_transfert";
		}
		
		
		
}


