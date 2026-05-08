# FAQ — Homework 10

## Iterator Questions

**Can I use `java.util.Iterator`?**

No. This homework intentionally uses the custom `QuestIterator` interface so the pattern is visible and easy to grade.

**Why is there no public `getQuests()` method?**

Because exposing the list couples clients to the aggregate's internal structure. The whole point of Iterator is that clients ask for a traversal object instead.

**Can `QuestLog.snapshot()` be public?**

No. It is package-private so only the iterator classes in `com.narxoz.rpg.quest` can use it.

**Should `next()` throw an exception when there is no next quest?**

You may choose to throw `NoSuchElementException`, or you may handle it another clear way. The important part is that normal client code uses `hasNext()` before `next()`.

**How should `PriorityQuestIterator` decide priority order?**

The provided enum order is `LOW`, `NORMAL`, `HIGH`, `URGENT`. A threshold of `HIGH` should include `HIGH` and `URGENT`.

**Does `PriorityQuestIterator` also sort quests by priority?**

Not required. It only needs to filter by threshold unless you clearly document and demonstrate a different behavior.

## Mediator Questions

**Can the `Captain` store a `Scout` field?**

No. That is direct colleague coupling and breaks the Mediator pattern.

**Can one colleague call another colleague's `receive(...)` method?**

No. Colleagues send outbound messages through `getMediator().dispatch(...)`.

**Can `GuildHall` know about concrete colleague types?**

It may use type checks sparingly for registration policy, but the better design is topic-based registration behavior. The key rule is that cross-colleague communication is centralized in the mediator.

**What topics should I use?**

Use clear strings such as `orders`, `supplies`, `scouting`, `healing`, `urgent`, or `rewards`. Keep them consistent between registration and dispatch.

**Should the sender receive its own message?**

Either choice is acceptable if it is intentional and consistent. Most demos skip notifying the sender to make routed communication easier to read.

**Where should message output happen?**

Usually in each colleague's `receive(...)` method. That proves colleagues still own their own behavior while the mediator owns routing.

## Independence

**Do Iterator and Mediator need to depend on each other?**

No. `QuestLog` and its iterators should not know about `GuildHall`. Guild members should not know how quests are stored.

**How do the patterns interact at runtime?**

`Main` or `CouncilEngine` walks quests through `QuestIterator`, then sends planning messages through `GuildMediator`.

**Can I put quest traversal inside `GuildHall`?**

Avoid that. It mixes responsibilities and makes the patterns harder to grade.

## Engine + Demo

**What should `CouncilEngine.runCouncil(...)` return?**

Return a `CouncilRunResult` with meaningful counts for quests traversed, messages routed, and members notified.

**How can I count members notified?**

You can have `GuildHall` expose a read-only count from its last dispatch, return internal statistics through a method you add, or track expected counts in the engine if your design makes that reliable.

**Does `Main.java` need a long story?**

No. Keep the output clear. Show the party, quest traversal, mediator messages, and final result.

**How many iterators must the demo use?**

At least two in the main demo. All three provided iterators must be implemented.

## General

**Can I add new classes?**

Yes. Part 4 requires adding `RewardSortedQuestIterator` and `Loremaster`.

**Can I rename packages?**

No. Keep package names under `com.narxoz.rpg`.

**Can I use Maven or Gradle?**

No. The grading setup expects plain Java 17 with `javac`.

**What should I submit?**

Submit source files, the docs, and two UML diagrams. Do not submit compiled `.class` files or the `out/` directory.
