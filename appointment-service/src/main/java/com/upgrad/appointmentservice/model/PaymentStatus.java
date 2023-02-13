package com.upgrad.appointmentservice.model;

public enum PaymentStatus {
    PENDING_PAYMENT("PendingPayment"), CONFIRMED("Confirmed");

    PaymentStatus(String pendingPayment) {
    }
}
