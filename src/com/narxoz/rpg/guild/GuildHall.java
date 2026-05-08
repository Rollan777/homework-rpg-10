package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {

    public static final String TOPIC_SCOUTING = "scouting";
    public static final String TOPIC_SUPPLIES = "supplies";
    public static final String TOPIC_HEALING = "healing";
    public static final String TOPIC_COMMAND = "command";

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    private int messagesRouted;
    private int membersNotified;

    @Override
    public void register(GuildMember member) {
        if (member == null) {
            return;
        }

        if (member instanceof Scout) {
            addSubscriber(TOPIC_COMMAND, member);
            addSubscriber(TOPIC_SCOUTING, member);
        } else if (member instanceof Quartermaster) {
            addSubscriber(TOPIC_COMMAND, member);
            addSubscriber(TOPIC_SUPPLIES, member);
        } else if (member instanceof Healer) {
            addSubscriber(TOPIC_COMMAND, member);
            addSubscriber(TOPIC_HEALING, member);
        } else if (member instanceof Captain) {
            addSubscriber(TOPIC_SCOUTING, member);
            addSubscriber(TOPIC_SUPPLIES, member);
            addSubscriber(TOPIC_HEALING, member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        messagesRouted++;

        String senderName = from == null ? "Unknown" : from.getName();

        System.out.println();
        System.out.println("[GuildHall] Topic: " + topic);
        System.out.println("[GuildHall] From: " + senderName);
        System.out.println("[GuildHall] Message: " + payload);

        List<GuildMember> subscribers = subscribersFor(topic);

        if (subscribers.isEmpty()) {
            System.out.println("[GuildHall] No subscribers for this topic.");
            return;
        }

        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic, from, payload);
                membersNotified++;
            }
        }
    }

    public int getMessagesRouted() {
        return messagesRouted;
    }

    public int getMembersNotified() {
        return membersNotified;
    }

    protected void addSubscriber(String topic, GuildMember member) {
        List<GuildMember> subscribers = membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>());

        if (!subscribers.contains(member)) {
            subscribers.add(member);
        }
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}