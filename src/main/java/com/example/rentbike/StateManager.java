package com.example.rentbike;

import com.example.rentbike.models.bike.Bike;
import com.example.rentbike.models.order.Order;
import com.example.rentbike.models.station.Station;
import com.example.rentbike.models.transaction.Card;


public class StateManager {
    private static StateManager stateManager;
    private Order order;
    private Station selectedStation;
    private Bike selectedBike;
    private Card card;

    public static StateManager getInstance() {
        if (stateManager == null) {
            stateManager = new StateManager();
        }
        Card card = Card.builder().
                owner("Hung").
                expirationDate("1125").
                cardNumber("29072002").
                build();

        stateManager.setCard(card);
        return stateManager;
    }

    public Order getOrder() {
        return stateManager.order;
    }

    public void setOrder(Order order) {
        stateManager.order = order;
    }

    public Station getSelectedStation() {
        return stateManager.selectedStation;
    }

    public void setSelectedStation(Station selectedStation) {
        this.selectedStation = selectedStation;
    }

    public Bike getSelectedBike() {
        return selectedBike;
    }

    public void setSelectedBike(Bike selectedBike) {
        this.selectedBike = selectedBike;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
