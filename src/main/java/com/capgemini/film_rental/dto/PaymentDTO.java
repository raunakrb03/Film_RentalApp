
package com.capgemini.film_rental.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {
    private int paymentId;
    private Integer customerId;
    private Integer staffId;
    private Integer rentalId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int v) {
        this.paymentId = v;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer v) {
        this.customerId = v;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer v) {
        this.staffId = v;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer v) {
        this.rentalId = v;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal v) {
        this.amount = v;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime v) {
        this.paymentDate = v;
    }
}
