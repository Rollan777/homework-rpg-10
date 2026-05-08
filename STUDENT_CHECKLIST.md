# Student Checklist — Homework 10

## Phase 1 — Setup

- [ ] I can compile the scaffold with `javac`.
- [ ] I can run `com.narxoz.rpg.Main`.
- [ ] I understand which files contain TODOs.

## Phase 2 — Quest Model

- [ ] I understand `Quest` fields and getters.
- [ ] I created at least 5 quests in the demo.
- [ ] My quests use mixed priorities.

## Phase 3 — Iterator Contract

- [ ] `QuestLog` does not expose a public `getQuests()`.
- [ ] Clients use `QuestIterator`, not `List<Quest>`.
- [ ] Iterator classes use snapshots instead of the live internal list.

## Phase 4 — Concrete Iterators

- [ ] `OrderedQuestIterator.hasNext()` works.
- [ ] `OrderedQuestIterator.next()` works.
- [ ] `ReverseQuestIterator.hasNext()` works.
- [ ] `ReverseQuestIterator.next()` works.
- [ ] `PriorityQuestIterator.hasNext()` works.
- [ ] `PriorityQuestIterator.next()` works.

## Phase 5 — Mediator Contract

- [ ] Concrete guild members do not store references to other concrete members.
- [ ] Concrete guild members use `getMediator().dispatch(...)` for outbound messages.
- [ ] No concrete guild member directly calls another member's `receive(...)`.

## Phase 6 — Guild Hall

- [ ] `GuildHall.register(...)` assigns members to useful topics.
- [ ] `GuildHall.dispatch(...)` finds subscribers by topic.
- [ ] The sender is handled intentionally, either skipped or clearly included.
- [ ] Dispatch behavior is visible in console output.

## Phase 7 — Colleagues

- [ ] `Quartermaster.receive(...)` has meaningful behavior.
- [ ] `Scout.receive(...)` has meaningful behavior.
- [ ] `Healer.receive(...)` has meaningful behavior.
- [ ] `Captain.receive(...)` has meaningful behavior.
- [ ] Each colleague has one outbound convenience method.

## Phase 8 — Engine and Demo

- [ ] `CouncilEngine` uses at least 2 iterators.
- [ ] `CouncilEngine` dispatches messages through `GuildMediator`.
- [ ] `CouncilRunResult` reports useful counters.
- [ ] `Main.java` prints a readable start-to-finish demo.

## Phase 9 — Final Submission

- [ ] I added `RewardSortedQuestIterator` for Part 4.
- [ ] I added `Loremaster` for Part 4.
- [ ] I included 2 UML diagrams.
- [ ] I removed compiled `.class` files.
- [ ] I zipped the complete source and docs.
