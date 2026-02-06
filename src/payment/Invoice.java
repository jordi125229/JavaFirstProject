package payment;

import money.Money;
import user.User;

import java.time.LocalDateTime;

public class Invoice {
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private User buyer;
    private Money total;
    private String itemDescription;

    public Invoice(String invoiceNumber, LocalDateTime issueDate, User buyer, Money total, String itemDescription) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.buyer = buyer;
        this.total = total;
        this.itemDescription = itemDescription;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", buyer=" + buyer +
                ", total=" + total +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
