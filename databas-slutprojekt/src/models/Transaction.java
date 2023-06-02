package models;

import java.util.Date;

public class Transaction {
    private final int id;
    private final int amount;
    private final int senderAccountId;
    private final int receiverAccountId;
    private final Date created;
    private String senderAccountName;
    private String receiverAccountName;
    public Transaction(int id, int amount, int senderAccountId, int receiverAccountId, Date created) {
        this.id = id;
        this.amount = amount;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.created = created;
    }
    public int getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public int getSenderAccountId() {
        return senderAccountId;
    }
    public int getReceiverAccountId() {
        return receiverAccountId;
    }
    public Date getCreated() {
        return created;
    }
    public String getSenderAccountName() {
        return senderAccountName;
    }
    public void setSenderAccountName(String senderAccountName) {
        this.senderAccountName = senderAccountName;
    }
    public String getReceiverAccountName() {
        return receiverAccountName;
    }
    public void setReceiverAccountName(String receiverAccountName) {
        this.receiverAccountName = receiverAccountName;
    }
    @Override
    public String toString() {
        return "Transaction ID: " + id +
                "\nAmount: " + amount +
                "\nSender Account ID: " + senderAccountId +
                "\nReceiver Account ID: " + receiverAccountId +
                "\nCreated: " + created;
    }
}