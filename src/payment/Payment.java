package payment;

import money.Money;

public abstract class Payment {
    private Money amount;
    private String paymentId;
    private PaymentStatus status;

    public abstract void capture();

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment: " + amount + "; " + paymentId + "; " + status;
    }
}
