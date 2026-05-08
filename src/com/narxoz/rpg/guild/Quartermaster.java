package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for gear, supplies, and rewards.
 */
public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "Unknown" : from.getName();

        if (GuildHall.TOPIC_COMMAND.equals(topic)) {
            System.out.println("[Quartermaster " + getName() + "] Preparing equipment after order from "
                    + sender + ": " + payload);
        } else if (GuildHall.TOPIC_SUPPLIES.equals(topic)) {
            System.out.println("[Quartermaster " + getName() + "] Checking supplies request from "
                    + sender + ": " + payload);
        } else {
            System.out.println("[Quartermaster " + getName() + "] Noted message from "
                    + sender + ": " + payload);
        }
    }
}