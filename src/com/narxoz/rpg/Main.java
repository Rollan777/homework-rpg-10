package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        List<Hero> party = new ArrayList<>();
        party.add(new Hero("Arman the Knight", 120, 20, 12));
        party.add(new Hero("Dana the Mage", 80, 50, 25, 5, 40));
        party.add(new Hero("Rolan the Ranger", 95, 18, 8));

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear the Goblin Road", QuestPriority.NORMAL, 60, false));
        questLog.add(new Quest("Rescue the Lost Merchant", QuestPriority.HIGH, 120, true));
        questLog.add(new Quest("Collect Moon Herbs", QuestPriority.LOW, 30, false));
        questLog.add(new Quest("Defend the Northern Gate", QuestPriority.URGENT, 200, true));
        questLog.add(new Quest("Investigate the Old Crypt", QuestPriority.HIGH, 150, false));

        GuildHall hall = new GuildHall();

        Quartermaster quartermaster = new Quartermaster("Borin", hall);
        Scout scout = new Scout("Lyra", hall);
        Healer healer = new Healer("Mira", hall);
        Captain captain = new Captain("Cedric", hall);

        System.out.println();
        System.out.println("--- Manual Iterator Demo: Ordered ---");
        QuestIterator orderedIterator = questLog.ordered();

        while (orderedIterator.hasNext()) {
            System.out.println(orderedIterator.next());
        }

        System.out.println();
        System.out.println("--- Manual Iterator Demo: Reverse ---");
        QuestIterator reverseIterator = questLog.reverse();

        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }

        System.out.println();
        System.out.println("--- Manual Mediator Demo ---");

        captain.issueOrder(
                GuildHall.TOPIC_COMMAND,
                "All officers prepare for the next campaign."
        );

        scout.reportRoute(
                GuildHall.TOPIC_SCOUTING,
                "Eastern road is safe, but the northern gate has enemy movement."
        );

        quartermaster.requestSupplies(
                GuildHall.TOPIC_SUPPLIES,
                "Advanced armor and rope are ready for dangerous quests."
        );

        healer.prepareAid(
                GuildHall.TOPIC_HEALING,
                "Healing potions and bandages are prepared."
        );

        System.out.println();
        System.out.println("--- Council Engine Demo ---");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println();
        System.out.println("Final result:");
        System.out.println(result);
    }
}