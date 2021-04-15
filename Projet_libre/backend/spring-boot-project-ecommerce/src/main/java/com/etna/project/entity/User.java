package com.etna.project.entity;


import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updatedDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Customer> customers;

    public User() {

    }

    public User(int id, String username, String password, UserRole role, Date creationDate, Date updateDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.creationDate = creationDate;
        this.updatedDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

    public Set<Customer> getCustomers() { return customers; }

    public void setCustomers(Set<Customer> customers) { this.customers = customers; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", creationDate=" + creationDate +
                ", updateDate=" + updatedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getRole() == user.getRole() && Objects.equals(getCreationDate(), user.getCreationDate()) && Objects.equals(getUpdatedDate(), user.getUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getRole(), getCreationDate(), getUpdatedDate(), getCustomers());
    }

    public void add(Customer customer) {

        if (customer != null) {

            if (customers == null) {
                customers = new HashSet<>();
            }

            customers.add(customer);
            customer.setUser(this);
        }
    }
}
