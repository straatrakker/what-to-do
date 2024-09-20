package straatrakker.leukedingetjes.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private Float price;

    public Card() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Card(String description) {
        this.description = description;
        this.price = (float) 0;
    }

    public Card(String description, Float price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean isFree() {
        return this.price <= 0;
    }

    public Expensiveness getExpensiveness() {
        if(this.price == 0) {
            return Expensiveness.FREE;
        }
        if (this.price <= 10) {
            return Expensiveness.LOW;
        }

        if (this.price <= 20) {
            return Expensiveness.MEDIUM;
        }

        return Expensiveness.HIGH;
    }
}
