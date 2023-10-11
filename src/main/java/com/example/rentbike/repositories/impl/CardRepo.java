package com.example.rentbike.repositories.impl;

import com.example.rentbike.models.transaction.Card;
import com.example.rentbike.utils.mappers.impl.CardMapper;

import java.util.Hashtable;

public class CardRepo extends GenericRepo<Card> {
    public Card getCard(Card card){
        Hashtable<String, Object> table = new Hashtable<>();
        table.put("owner", card.getOwner());
        table.put("cvvCode", card.getCvvCode());
        table.put("cardNumber", card.getCardNumber());
        table.put("expirationDate", card.getExpirationDate());
        return findOne("cards", table, new CardMapper());
    }

    public Card getCardById(Integer id){
        return findById("cards",id,new CardMapper());
    }

    public void updateAmount(Integer cardId, int amount){
        update("UPDATE interbank SET amount = amount + ? WHERE cardId = ?",amount,cardId);
    }




}
