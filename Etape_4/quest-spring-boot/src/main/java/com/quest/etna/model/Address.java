package com.quest.etna.model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.Set;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(length = 100)
    private String street;

    @NotNull
    @Column(length = 30)
    private String postalCode;

    @NotNull
    @Column(length = 50)
    private String city;

    @NotNull
    @Column(length = 50)
    private String country;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updatedDate;
    
    public Address() {

    }

    public Address(int id, String street, String postalCode, String city, String country, User user, Date creationDate, Date updatedDate) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.user = user;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }   

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // TODO Check si c'est utile
    public User getUser() {
        return user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updateDate) {
        this.updatedDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getId() == address.getId() && getStreet().equals(address.getStreet()) && getPostalCode().equals(address.getPostalCode()) && getCity().equals(address.getCity()) && getCountry().equals(address.getCountry()) && Objects.equals(getUser(), address.getUser()) && Objects.equals(getCreationDate(), address.getCreationDate()) && Objects.equals(getUpdatedDate(), address.getUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStreet(), getPostalCode(), getCity(), getCountry(), getUser(), getCreationDate(), getUpdatedDate());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", user=" + user +
                ", creationDate=" + creationDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
