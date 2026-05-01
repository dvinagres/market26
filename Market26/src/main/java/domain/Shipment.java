package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Shipment implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String deliveryType;
    private String address;     
    @OneToOne
    private Sale sale;           // A qué venta pertenece este envío
    
    @ManyToOne
    private Buyer buyer;         // Quién es el comprador que lo ha pedido

    public Shipment() {
        super();
    }

    public Shipment(String deliveryType, String address, Sale sale, Buyer buyer) {
        super();
        this.deliveryType = deliveryType;
        this.address = address;
        this.sale = sale;
        this.buyer = buyer;
    }

    // --- Getters y Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDeliveryType() { return deliveryType; }
    public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Sale getSale() { return sale; }
    public void setSale(Sale sale) { this.sale = sale; }

    public Buyer getBuyer() { return buyer; }
    public void setBuyer(Buyer buyer) { this.buyer = buyer; }
}