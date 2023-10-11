package com.example.rentbike.utils.mappers.impl;

import com.example.rentbike.models.transaction.TransactionInfo;
import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionInfoMapper implements IResultSetMapper<TransactionInfo> {
    @Override
    public TransactionInfo mapRow(ResultSet res) throws SQLException {
        TransactionInfo transactionInfo = new TransactionInfo();

        transactionInfo.setId(res.getInt("transactionInfoId"));
        transactionInfo.setTransactionContent(res.getString("transactionContent"));
        transactionInfo.setCommand(res.getString("command"));
        transactionInfo.setCard(new CardMapper().mapRow(res));
        transactionInfo.setOrder(new OrderMapper().mapRow(res));
        transactionInfo.setAmount(res.getInt("amount"));
        transactionInfo.setCreatedAt(res.getTimestamp("createdAt"));
        transactionInfo.setErrorCode(res.getString("errorCode"));

        return transactionInfo;
    }
}
