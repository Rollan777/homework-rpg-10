package com.narxoz.rpg.quest;

/**
 * Custom iterator protocol for traversing quests.
 */
public interface QuestIterator {

    boolean hasNext();

    Quest next();
}
