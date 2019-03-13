package ch.hearc.spring.thymeleaf.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CompteBancaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private String identifiant;
	/**
	public Set<Transfert> getCredits() {
		return credits;
	}

	public void setCredits(Set<Transfert> credits) {
		this.credits = credits;
	}

	public Set<Transfert> getDebits() {
		return debits;
	}

	public void setDebits(Set<Transfert> debits) {
		this.debits = debits;
	}*/
/**
	@OneToMany(mappedBy="crediteur")
    private Set<Transfert> credits;
	
	@OneToMany(mappedBy="debiteur")
    private Set<Transfert> debits;
	*/
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	private int solde;

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public int getSoldeInitial() {
		return soldeInitial;
	}

	public void setSoldeInitial(int soldeInitial) {
		this.soldeInitial = soldeInitial;
	}

	private int soldeInitial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	


	public CompteBancaire(String userName, int soldeInitial, String identifiant) {
		super();
		this.userName = userName;
		this.soldeInitial = soldeInitial;
		this.solde = soldeInitial;
		this.identifiant = identifiant;
	}

	CompteBancaire() {
		
	}

	@Override
	public String toString() {
		return "CompteBancaire [id=" + id + ", userName=" + userName + ", identifiant=" + identifiant +", montant=" + soldeInitial + "]";
	}
	
	
	
}
