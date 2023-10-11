package com.example.rentbike.utils.mappers.impl;

import com.example.rentbike.models.transaction.Card;
import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements IResultSetMapper<Card> {
    @Override
    public Card mapRow(ResultSet res) throws SQLException {
        Card card = new Card();
        card.setId(res.getInt("cardId"));
        card.setCardNumber(res.getString("cardNumber"));
        card.setOwner(res.getString("owner"));
        card.setCvvCode(res.getString("cvvCode"));
        card.setExpirationDate(res.getString("expirationDate"));
        card.setAmount(res.getInt("amount"));
        return card;
    }
}
