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
    private Boolean atHome;
    private Boolean outdoors;

    public Card() {
        this.atHome = false;
    }

    public Card(String description) {
        this.description = description;
        this.price = (float) 0;
        this.atHome = false;
    }

    public Card(String description, Float price) {
        this.description = description;
        this.price = price;
        this.atHome = false;
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

    public void setAtHome(Boolean atHome) {
        this.atHome = atHome;
    }

    public void setOutdoors(Boolean outdoors) {
        this.outdoors = outdoors;
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

    public Boolean getAtHome() {
        return atHome;
    }

    public Boolean getOutdoors() {
        return outdoors;
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
