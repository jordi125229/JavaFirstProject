package payment;

import money.Money;

public abstract class Payment {
    private Money amount;
    private String paymentId;
    private PaymentStatus status;

    public Payment(Money amount, String paymentId, PaymentStatus status) {
        this.amount = amount;
        this.paymentId = paymentId;
        this.status = status;
    }

    public abstract void capture();

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
