package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for orders and mission coordination.
 */
public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "Unknown" : from.getName();

        if (GuildHall.TOPIC_SCOUTING.equals(topic)) {
            System.out.println("[Captain " + getName() + "] Received scouting update from "
                    + sender + ": " + payload);
        } else if (GuildHall.TOPIC_SUPPLIES.equals(topic)) {
            System.out.println("[Captain " + getName() + "] Received supply update from "
                    + sender + ": " + payload);
        } else if (GuildHall.TOPIC_HEALING.equals(topic)) {
            System.out.println("[Captain " + getName() + "] Received healing update from "
                    + sender + ": " + payload);
        } else {
            System.out.println("[Captain " + getName() + "] Noted message from "
                    + sender + ": " + payload);
        }
    }
}