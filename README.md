# Homework 10 — The Adventurers' Guild: Iterator + Mediator

## Overview

In this assignment you will implement two behavioral design patterns inside an RPG guild hall preparing for its next campaign:

- **Iterator** traverses the guild's quest log without exposing its internal collection
- **Mediator** routes messages among guild officers without direct colleague-to-colleague references

The two patterns are intentionally independent. Iterator handles *how quests are traversed*; Mediator handles *how guild members coordinate*. They interact in the demo when the war council walks quests and routes planning messages through the guild hall.

---

## Patterns Covered

| Pattern | Role in this system |
|---------|---------------------|
| **Iterator** | Walks the `QuestLog` in arrival order, reverse order, and filtered priority order without leaking the aggregate's internal `List`. New traversal orders are added by creating new iterator classes. |
| **Mediator** | Coordinates `Quartermaster`, `Scout`, `Healer`, and `Captain` through `GuildHall`. Concrete colleagues never store direct references to each other. |

---

## What Is Provided

| File | Description |
|------|-------------|
| `src/com/narxoz/rpg/quest/Quest.java` | Immutable quest data class |
| `src/com/narxoz/rpg/quest/QuestPriority.java` | Quest priority enum |
| `src/com/narxoz/rpg/quest/QuestIterator.java` | Custom iterator interface |
| `src/com/narxoz/rpg/quest/QuestLog.java` | Quest aggregate that hides its internal list |
| `src/com/narxoz/rpg/quest/OrderedQuestIterator.java` | Arrival-order iterator skeleton |
| `src/com/narxoz/rpg/quest/ReverseQuestIterator.java` | Reverse-arrival iterator skeleton |
| `src/com/narxoz/rpg/quest/PriorityQuestIterator.java` | Priority-filtered iterator skeleton |
| `src/com/narxoz/rpg/guild/GuildMediator.java` | Mediator interface |
| `src/com/narxoz/rpg/guild/GuildHall.java` | Topic-based mediator skeleton |
| `src/com/narxoz/rpg/guild/GuildMember.java` | Abstract colleague base class |
| `src/com/narxoz/rpg/guild/Quartermaster.java` | Concrete colleague skeleton |
| `src/com/narxoz/rpg/guild/Scout.java` | Concrete colleague skeleton |
| `src/com/narxoz/rpg/guild/Healer.java` | Concrete colleague skeleton |
| `src/com/narxoz/rpg/guild/Captain.java` | Concrete colleague skeleton |
| `src/com/narxoz/rpg/combatant/Hero.java` | Trimmed hero class for the council demo |
| `src/com/narxoz/rpg/council/CouncilRunResult.java` | Council run summary data class |
| `src/com/narxoz/rpg/council/CouncilEngine.java` | Council orchestration shell |
| `src/com/narxoz/rpg/Main.java` | Entry point skeleton |

Everything else — iterator logic, mediator routing, colleague reactions, and the full council demo flow — is yours to build.

---

## Quick Start

```bash
# Compile
javac -d out $(find src -name "*.java")

# Run
java -cp out com.narxoz.rpg.Main
```

See `QUICKSTART.md` for IDE setup and detailed instructions.

---

## Read Next

- `ASSIGNMENT.md` — full requirements, anti-pattern penalties, and grading rubric
- `STUDENT_CHECKLIST.md` — phase-by-phase progress tracker
- `FAQ.md` — answers to common questions about Iterator and Mediator

---

## Connection to Previous Homeworks

| Homework | Patterns | Theme |
|----------|----------|-------|
| `homework-rpg-8` | State + Template Method | The Haunted Tower |
| `homework-rpg-9` | Visitor + Memento | Chronomancer's Vault |
| `homework-rpg-10` | Iterator + Mediator | The Adventurers' Guild |

The series keeps mixing pattern pairs, but each assignment still asks you to implement the patterns independently and demonstrate them clearly in `Main.java`.
