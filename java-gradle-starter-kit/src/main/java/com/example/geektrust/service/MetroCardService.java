package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;

import java.util.HashMap;
import java.util.Map;

public class MetroCardService {
    private final Map<String, MetroCard> cards = new HashMap<>();

    public void addBalance(String cardId, int amount){
        MetroCard card = cards.get(cardId);
        if(card == null){
            card = new MetroCard(cardId, amount);
            cards.put(cardId, card);
        }
        else{
            card.addBalance(amount);
        }
    }

    public MetroCard getCard(String cardId){
        return cards.get(cardId);
    }

    public boolean hasSufficientBalance(MetroCard card, int fare){
        return card.getBalance() >= fare;
    }

    public int autoRecharge(MetroCard card, int requireAmount){
        int rechargeAmount = requireAmount;
        int serviceFee = (int) Math.ceil(rechargeAmount * 0.02);

        int total = rechargeAmount * serviceFee;
        card.addBalance(total);

        return serviceFee;
    }

    public void deductFee(MetroCard card, int amount){
        card.deductBalance(amount);
    }
}
