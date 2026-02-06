package payment;

import money.Money;

class CardPayment extends Payment {
    private String last4;

    public CardPayment(Money amount, String paymentId, PaymentStatus status, String last4) {
        super(amount, paymentId, status);
        this.last4 = last4;
    }

    @Override
    void capture() {
        if (getStatus() == PaymentStatus.INITIATED){
            setStatus(PaymentStatus.CAPTURED);
        }
    }
}
