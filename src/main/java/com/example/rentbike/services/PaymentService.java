package com.example.rentbike.services;


import com.example.rentbike.common.exception.InvalidCardException;
import com.example.rentbike.common.exception.PaymentException;
import com.example.rentbike.models.order.Order;
import com.example.rentbike.models.transaction.Card;
import com.example.rentbike.models.transaction.TransactionInfo;
import com.example.rentbike.models.transaction.TransactionMessage;
import com.example.rentbike.repositories.impl.CardRepo;
import com.example.rentbike.repositories.impl.OrderRepo;
import com.example.rentbike.repositories.impl.TransactionInfoRepo;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class PaymentService extends BaseService {


    private final TransactionInfoRepo transactionInfoRepo;
    private final CardRepo cardRepo;

    private final OrderRepo orderRepo;

    public PaymentService() {
        transactionInfoRepo = new TransactionInfoRepo();
        cardRepo = new CardRepo();
        orderRepo = new OrderRepo();
    }

    public boolean validateName(String name) {
        try {
            name = name.trim();
            return ((!name.equals("")) && (name.matches("^[ A-Za-z0-9]+$")));
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean validateNumberField(String number) {

        try {
            number = number.trim();
            Integer.parseInt(number);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean validateExpirationDate(String date) throws InvalidCardException {
        try {
            date = date.trim();
            String regex = "^[0-9]{4}$";
            return date.matches(regex);
        } catch (Exception e) {
            throw new InvalidCardException("Invalid expiration date");
        }
    }

    public boolean validateCardCode(String number) {
        try {
            number = number.trim();
            return ((!number.equals("")) && (number.matches("^[_0-9A-Za-z]*$")));
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public void validateCard(String cardNumber, String holderName, String securityCode, String expirationDate) {
        if (!validateExpirationDate(expirationDate)) throw new InvalidCardException("Invalid expirationDate");
        if (!this.validateName(holderName))
            throw new InvalidCardException("Invalid Owner Name");
        else if (!this.validateNumberField(securityCode))
            throw new InvalidCardException("Wrong format cvvCode");
        else if (!this.validateCardCode(cardNumber))
            throw new InvalidCardException("Wrong format code number");
    }

    public void setAmountOrder(Order order, int amount) {
        order.setTotalUpToNow(amount);
    }


    public void submitToPay(Order order, Card card) {

        if (order.getEnd() == null) {
            TransactionMessage transactionMessage = payOrder(card, order.getDeposit());
            checkErrorCode(transactionMessage);
        } else {
            TransactionMessage transactionMessage = refund(card, order.getDeposit());
            checkErrorCode(transactionMessage);
            TransactionMessage transactionMessage1 = payOrder(card, order.getTotalUpToNow());
            checkErrorCode(transactionMessage1);
        }
    }

    public void checkErrorCode(TransactionMessage transactionMessage) {
        if (!transactionMessage.getErrorCode().equals("0")) {
            throw new PaymentException(transactionMessage.getContent());
        }
    }

    public TransactionMessage refund(Card card, int amount) {
        TransactionMessage transactionMessage = new TransactionMessage();
        Card foundCard = cardRepo.getCard(card);
        if (foundCard != null && foundCard.getId() != null) {
            cardRepo.updateAmount(foundCard.getId(), amount);
            transactionMessage.setErrorCode("0");
            transactionMessage.setContent("Refund successfully !!");
        } else {
            transactionMessage.setContent("Refund failed !! Not found cardId");
            transactionMessage.setErrorCode("1");
            foundCard = new Card();
        }
        createTransitionInfo(foundCard, transactionMessage, amount, "refund");
        return transactionMessage;

    }

    private TransactionInfo createTransitionInfo(Card card, TransactionMessage transactionMessage, Integer amount, String command) {
        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setCard(card);
        transactionInfo.setErrorCode(transactionMessage.getErrorCode());
        transactionInfo.setTransactionContent(transactionMessage.getContent());
        transactionInfo.setAmount(amount);
        transactionInfo.setCommand(command);
        transactionInfo.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        transactionInfoRepo.createNewTransaction(transactionInfo);
        return transactionInfo;
    }

    public TransactionMessage payOrder(Card card, int amount) {
        TransactionMessage transactionMessage = new TransactionMessage();
        Card foundCard = cardRepo.getCard(card);
        if (foundCard != null && foundCard.getId() != null) {
            if (foundCard.getAmount() < amount) {
                transactionMessage.setErrorCode("2");
                transactionMessage.setContent("Pay failed !! Not enough money to pay");
            } else {
                cardRepo.updateAmount(foundCard.getId(), -amount);
                transactionMessage.setErrorCode("0");
                transactionMessage.setContent("You have paid the order succesffully!");
            }
        } else {
            transactionMessage.setErrorCode("1");
            transactionMessage.setContent("Pay failed !! Not found cardId");
            foundCard = new Card();
        }
        createTransitionInfo(foundCard, transactionMessage, amount, "pay");
        return transactionMessage;
    }

    public void processPayRequest(Order order, Card card) {
        validateCard(card.getCardNumber(), card.getOwner(), card.getCvvCode(), card.getExpirationDate());
        submitToPay(order, card);
        orderRepo.createNewOrder(order);
    }
}
