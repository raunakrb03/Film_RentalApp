package com.capgemini.film_rental.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_customer"))
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_staff"))
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "rental_id",
            foreignKey = @ForeignKey(name = "fk_payment_rental"))
    private Rental rental;

    @Column(name = "amount", nullable = false, precision = 5, scale = 2)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    // Constructors
    public Payment() {}

    // Getters and Setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }

    public Rental getRental() { return rental; }
    public void setRental(Rental rental) { this.rental = rental; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
}