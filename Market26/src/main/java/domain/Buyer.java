package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Buyer implements Serializable {
    
    @Id
    private String email;
    private String name;
    private String password;
    
    // Un comprador puede tener muchas ofertas aceptadas
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Sale> acceptedSales = new ArrayList<Sale>();

    public Buyer() {
        super();
    }

    public Buyer(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // --- Getters y Setters ---
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Sale> getAcceptedSales() { return acceptedSales; }
    public void setAcceptedSales(List<Sale> acceptedSales) { this.acceptedSales = acceptedSales; }
    
    // Método útil para añadir una oferta a la lista del comprador
    public void addAcceptedSale(Sale sale) {
        this.acceptedSales.add(sale);
    }
}