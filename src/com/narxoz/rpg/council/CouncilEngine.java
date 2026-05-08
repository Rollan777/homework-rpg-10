package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.QuestLog;
import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        // TODO: walk questLog with at least 2 different iterators,
        //       dispatch coordinating messages through hall for each quest,
        //       and return counters (questsTraversed, messagesRouted, membersNotified).
        return new CouncilRunResult(0, 0, 0);
    }
}
