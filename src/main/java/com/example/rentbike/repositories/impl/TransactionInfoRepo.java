package com.example.rentbike.repositories.impl;

import com.example.rentbike.models.transaction.TransactionInfo;

public class TransactionInfoRepo extends GenericRepo<TransactionInfo> {
    public void createNewTransaction(TransactionInfo transactionInfo) {
        insert("INSERT INTO TransactionInfo(cardID, invoiceID, transactionContent, amount,errorCode,command) values (?,?,?,?,?,?)", transactionInfo.getCard().getId(), transactionInfo.getInvoice().getId(), transactionInfo.getTransactionContent(), transactionInfo.getAmount(), transactionInfo.getErrorCode(), transactionInfo.getCommand());
    }

}
