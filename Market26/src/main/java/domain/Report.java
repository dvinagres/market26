package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Report implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String reason; // Motivo de la denuncia
    private String date; // Lo guardamos como String igual que hizo tu amigo en Review
    
    private String buyerName; // Nombre de quien denuncia
    
    @ManyToOne
    private Seller seller; // El vendedor que ha sido denunciado

    public Report() {
        super();
    }

    public Report(String reason, String date, String buyerName, Seller seller) {
        this.reason = reason;
        this.date = date;
        this.buyerName = buyerName;
        this.seller = seller;
    }

    // --- Getters y Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public Seller getSeller() { return seller; }
    public void setSeller(Seller seller) { this.seller = seller; }
    
    @Override
    public String toString() {
        return "[Buyer: " + buyerName + " | Date: " + date + " | Reason: " + reason + "]";
    }
}