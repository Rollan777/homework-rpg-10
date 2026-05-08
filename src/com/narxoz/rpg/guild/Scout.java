package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for route reports and reconnaissance.
 */
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "Unknown" : from.getName();

        if (GuildHall.TOPIC_COMMAND.equals(topic)) {
            System.out.println("[Scout " + getName() + "] Scouting route after order from "
                    + sender + ": " + payload);
        } else if (GuildHall.TOPIC_SCOUTING.equals(topic)) {
            System.out.println("[Scout " + getName() + "] Reviewing scouting report from "
                    + sender + ": " + payload);
        } else {
            System.out.println("[Scout " + getName() + "] Noted message from "
                    + sender + ": " + payload);
        }
    }
}