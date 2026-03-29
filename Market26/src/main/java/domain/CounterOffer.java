package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class CounterOffer implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private float offeredPrice;
	
	// El estado puede ser: "Pendiente", "Aceptada" o "Rechazada"
	private String status; 
	
	@ManyToOne
	private Buyer buyer;
	
	@ManyToOne
	private Sale sale;

	public CounterOffer() {
		super();
	}

	public CounterOffer(float offeredPrice, Buyer buyer, Sale sale) {
		super();
		this.offeredPrice = offeredPrice;
		this.buyer = buyer;
		this.sale = sale;
		this.status = "Pendiente"; // Toda contraoferta nace pendiente
	}

	// --- Getters y Setters ---
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public float getOfferedPrice() { return offeredPrice; }
	public void setOfferedPrice(float offeredPrice) { this.offeredPrice = offeredPrice; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public Buyer getBuyer() { return buyer; }
	public void setBuyer(Buyer buyer) { this.buyer = buyer; }

	public Sale getSale() { return sale; }
	public void setSale(Sale sale) { this.sale = sale; }
}