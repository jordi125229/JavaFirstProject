package payment;

import money.Money;

public class CardPayment extends Payment {
    private String last4;

    public CardPayment(String last4) {
        this.last4 = last4;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    @Override
    public void capture() {
        setStatus(PaymentStatus.CAPTURED);
    }

    @Override
    public String toString() {
        return "Payment: " + getStatus();
    }
}
