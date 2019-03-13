package ch.hearc.spring.thymeleaf.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transfert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="id_debiteur", nullable=false)
    private CompteBancaire debiteur;
	
	@ManyToOne
    @JoinColumn(name="id_crediteur", nullable=false)
    private CompteBancaire crediteur;
	
	private int montant;
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompteBancaire getDebiteur() {
		return debiteur;
	}

	public void setDebiteur(CompteBancaire debiteur) {
		this.debiteur = debiteur;
	}

	public CompteBancaire getCrediteur() {
		return crediteur;
	}

	public void setCrediteur(CompteBancaire crediteur) {
		this.crediteur = crediteur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Transfert(CompteBancaire debiteur, CompteBancaire crediteur, int montant) {
		super();
		this.debiteur = debiteur;
		this.crediteur = crediteur;
		this.montant = montant;
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Transfert() {
		
	}

	@Override
	public String toString() {
		return "Transfert [id=" + id + ", debiteur=" + debiteur + ", crediteur=" + crediteur + ", montant=" + montant
				+ "]";
	}
	
	
}
