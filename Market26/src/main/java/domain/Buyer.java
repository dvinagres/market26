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
    
    private List<Sale> wishlist = new ArrayList<Sale>();

    public Buyer() {
        super();
    }

    public Buyer(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Sale> getAcceptedSales() { return acceptedSales; }
    public void setAcceptedSales(List<Sale> acceptedSales) { this.acceptedSales = acceptedSales; }
    
    public void addAcceptedSale(Sale sale) {this.acceptedSales.add(sale);}
    
    public List<Sale> getWishlist() {return wishlist;}
    public void setWishlist(List<Sale> wishlist) {this.wishlist = wishlist;}
    public void addToWishlist(Sale sale) {
    	if(!this.wishlist.contains(sale)) {
    		this.wishlist.add(sale);
    	}
    }
}