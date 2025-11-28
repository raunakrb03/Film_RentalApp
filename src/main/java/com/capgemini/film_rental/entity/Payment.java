package com.capgemini.film_rental.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id",columnDefinition = "SMALLINT UNSIGNED")
   // @JsonIgnore
    private int paymentId;

    
    //Multiple payment -> 1 customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    private Customer customer;
    
    
    //Multiple payment -> 1 staff
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false,columnDefinition = "TINYINT UNSIGNED")
    private Staff staff;

    
    //Multiple payment -> 1 rental
    @ManyToOne
    @JoinColumn(name = "rental_id",columnDefinition = "INT")
    private Rental rental;

    @Column(name = "amount", nullable = false,precision = 5,scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime paymentDate;

    @Column(name = "last_update", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, customer, lastUpdate, paymentDate, paymentId, rental, staff);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(customer, other.customer)
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(paymentDate, other.paymentDate)
				&& Objects.equals(paymentId, other.paymentId) && Objects.equals(rental, other.rental)
				&& Objects.equals(staff, other.staff);
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", customer=" + customer + ", staff=" + staff + ", rental=" + rental
				+ ", amount=" + amount + ", paymentDate=" + paymentDate + ", lastUpdate=" + lastUpdate + "]";
	}

	public Payment(int paymentId, Customer customer, Staff staff, Rental rental, BigDecimal amount,
			LocalDateTime paymentDate, LocalDateTime lastUpdate) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
		this.staff = staff;
		this.rental = rental;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.lastUpdate = lastUpdate;
	}

    public Payment() {
		// TODO Auto-generated constructor stub
	}
    
    
}