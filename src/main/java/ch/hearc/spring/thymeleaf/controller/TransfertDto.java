package ch.hearc.spring.thymeleaf.controller;

public class TransfertDto {

	private String noDebiteur;
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private int montant;
	
	public TransfertDto() {}

	public String getNoDebiteur() {
		return noDebiteur;
	}

	public void setNoDebiteur(String noDebiteur) {
		this.noDebiteur = noDebiteur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	
}
