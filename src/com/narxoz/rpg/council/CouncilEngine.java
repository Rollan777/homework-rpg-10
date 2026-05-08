package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.guild.GuildMember;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        if (party == null || questLog == null || hall == null) {
            return new CouncilRunResult(0, 0, 0);
        }

        int questsTraversed = 0;

        System.out.println();
        System.out.println("=== War Council Started ===");

        System.out.println();
        System.out.println("Party members:");
        for (Hero hero : party) {
            System.out.println("- " + hero);
        }

        GuildMember councilLeader = new Captain("Council Leader", hall);

        System.out.println();
        System.out.println("--- Iterator 1: Arrival Order ---");

        QuestIterator orderedIterator = questLog.ordered();

        while (orderedIterator.hasNext()) {
            Quest quest = orderedIterator.next();
            questsTraversed++;

            System.out.println("Planning quest: " + quest);

            hall.dispatch(
                    GuildHall.TOPIC_COMMAND,
                    councilLeader,
                    "Prepare plan for quest: " + quest.getTitle()
            );

            if (quest.isUrgent()) {
                hall.dispatch(
                        GuildHall.TOPIC_SCOUTING,
                        councilLeader,
                        "Urgent scouting required for: " + quest.getTitle()
                );
            }

            if (quest.getRewardGold() >= 100) {
                hall.dispatch(
                        GuildHall.TOPIC_SUPPLIES,
                        councilLeader,
                        "High reward quest. Prepare advanced supplies for: " + quest.getTitle()
                );
            }
        }

        System.out.println();
        System.out.println("--- Iterator 2: High Priority and Above ---");

        QuestIterator priorityIterator = questLog.priorityAtLeast(QuestPriority.HIGH);

        while (priorityIterator.hasNext()) {
            Quest quest = priorityIterator.next();
            questsTraversed++;

            System.out.println("Reviewing dangerous quest: " + quest);

            hall.dispatch(
                    GuildHall.TOPIC_HEALING,
                    councilLeader,
                    "Prepare healing potions for dangerous quest: " + quest.getTitle()
            );
        }

        int messagesRouted = 0;
        int membersNotified = 0;

        if (hall instanceof GuildHall) {
            GuildHall guildHall = (GuildHall) hall;
            messagesRouted = guildHall.getMessagesRouted();
            membersNotified = guildHall.getMembersNotified();
        }

        System.out.println();
        System.out.println("=== War Council Finished ===");

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}