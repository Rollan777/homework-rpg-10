package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for wounds, potions, and recovery plans.
 */
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "Unknown" : from.getName();

        if (GuildHall.TOPIC_COMMAND.equals(topic)) {
            System.out.println("[Healer " + getName() + "] Preparing healing support after order from "
                    + sender + ": " + payload);
        } else if (GuildHall.TOPIC_HEALING.equals(topic)) {
            System.out.println("[Healer " + getName() + "] Checking medical plan from "
                    + sender + ": " + payload);
        } else {
            System.out.println("[Healer " + getName() + "] Noted message from "
                    + sender + ": " + payload);
        }
    }
}