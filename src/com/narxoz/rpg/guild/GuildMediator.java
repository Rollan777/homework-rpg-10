package com.narxoz.rpg.guild;

/**
 * Central communication contract for guild colleagues.
 */
public interface GuildMediator {

    void register(GuildMember member);

    void dispatch(String topic, GuildMember from, String payload);
}
