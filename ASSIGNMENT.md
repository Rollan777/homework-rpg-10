# Homework 10 — The Adventurers' Guild: Iterator + Mediator

## 1. Background Story

After surviving the Chronomancer's Vault in Homework 9, the heroes return to the Adventurers' Guild. The guild master calls a war council to plan the next campaign. On the table lies a thick **Quest Log** filled with monster hunts, cursed ruins, escort missions, and emergency contracts.

The council has two coordination problems:

- The quest log must be reviewed in different orders without revealing how it stores quests internally.
- The guild officers must coordinate supplies, scouting, healing, and orders without tightly coupling themselves to each other.

This assignment forces two behavioral patterns to appear for real:

- **Iterator** for traversing the `QuestLog`
- **Mediator** for routing messages through the `GuildHall`

The patterns interact at runtime, but they remain structurally independent. The quest log does not know about the guild hall, and guild members do not know how quests are stored.

---

## 2. Learning Objectives

By completing this assignment, you will:

- Apply the **Iterator** pattern using a custom iterator interface
- Keep aggregate storage private and expose traversal through iterator objects
- Apply the **Mediator** pattern to decouple concrete colleagues
- Route all cross-colleague communication through one mediator boundary
- Prove open/closed design by adding a new iterator and a new colleague without rewriting the core system

---

## 3. What Is Provided

The following files are given to you in skeleton form.

| File | Purpose |
|------|---------|
| `quest/Quest.java` | Immutable quest entry |
| `quest/QuestPriority.java` | Priority enum: `LOW`, `NORMAL`, `HIGH`, `URGENT` |
| `quest/QuestIterator.java` | Custom iterator protocol |
| `quest/QuestLog.java` | Aggregate that owns the hidden quest list |
| `quest/OrderedQuestIterator.java` | Iterator skeleton for arrival order |
| `quest/ReverseQuestIterator.java` | Iterator skeleton for reverse arrival order |
| `quest/PriorityQuestIterator.java` | Iterator skeleton for priority filtering |
| `guild/GuildMediator.java` | Mediator API |
| `guild/GuildHall.java` | Topic-based mediator skeleton |
| `guild/GuildMember.java` | Abstract colleague base class |
| `guild/Quartermaster.java` | Supplies colleague skeleton |
| `guild/Scout.java` | Reconnaissance colleague skeleton |
| `guild/Healer.java` | Recovery colleague skeleton |
| `guild/Captain.java` | Command colleague skeleton |
| `combatant/Hero.java` | Simple hero model for the demo |
| `council/CouncilRunResult.java` | Immutable result summary |
| `council/CouncilEngine.java` | Orchestration shell |
| `Main.java` | Entry point skeleton |

You may fill in TODOs in these scaffold files, but do not rename packages or weaken the pattern boundaries.

---

## 4. What You Must Build

You are building **The Adventurers' Guild: War Council** demo with:

- A `QuestLog` that supports at least three traversal styles through `QuestIterator`
- A `GuildHall` mediator that registers colleagues and dispatches topic-based messages
- Concrete guild officers that never hold references to other concrete officers
- A console demo that clearly shows quests being traversed and guild messages being routed

---

## 5. Part 1: Iterator Pattern

### The Interface

`QuestIterator` is a custom interface. Do not replace it with `java.util.Iterator`.

```java
public interface QuestIterator {
    boolean hasNext();
    Quest next();
}
```

### Aggregate Contract

| Rule | Why it matters |
|------|----------------|
| `QuestLog` must not expose `getQuests()` publicly | Clients must not depend on the internal collection |
| `QuestLog` provides `ordered()`, `reverse()`, and `priorityAtLeast(...)` | The aggregate creates traversal objects |
| Iterator classes use `QuestLog.snapshot()` | Iterators get a stable view without leaking the list |
| New traversal orders are new iterator classes | This proves open/closed behavior |

### Concrete Iterators

You must complete at least these 3 iterator implementations:

- `OrderedQuestIterator` — visits quests in arrival order
- `ReverseQuestIterator` — visits quests from newest to oldest
- `PriorityQuestIterator` — visits quests whose priority is at least a threshold

Each concrete iterator should own:

- A local snapshot list
- A cursor position
- `hasNext()` logic
- `next()` logic that returns the current quest and advances the cursor

Use the iterator protocol in `Main.java` and `CouncilEngine`. The demo should visibly show at least two different traversal orders.

### Anti-pattern Penalty Box — Iterator

> **Anti-pattern penalty box:** Adding a public `getQuests()` or exposing the internal `List<Quest>` makes the Iterator section score **0**.

> **Anti-pattern penalty box:** Returning `quests.iterator()` from `QuestLog` instead of using the provided custom iterator classes makes the Iterator section score **0**.

> **Anti-pattern penalty box:** Traversing by reaching into `QuestLog` internals from `Main`, `CouncilEngine`, or another package makes the Iterator section score **0**.

---

## 6. Part 2: Mediator Pattern

### Mediator Boundary

`Quartermaster`, `Scout`, `Healer`, and `Captain` are colleagues. They may know their own name and their `GuildMediator`, but they must not store fields like `private Scout scout` or `private Healer healer`. Any cross-colleague communication must go through `GuildHall.dispatch(...)`.

### The Mediator API

```java
public interface GuildMediator {
    void register(GuildMember member);
    void dispatch(String topic, GuildMember from, String payload);
}
```

### Colleague Responsibilities

Each concrete colleague must:

- Keep the constructor shape `(String name, GuildMediator mediator)`
- Register through the `GuildMember` base constructor
- Implement `receive(String topic, GuildMember from, String payload)`
- Provide one outbound convenience method that calls `getMediator().dispatch(...)`
- Print or record behavior that makes routed communication visible in the demo

`GuildHall` should use topic-based dispatch with `Map<String, List<GuildMember>>`. You may decide which topics each role subscribes to, but the behavior must be clear and testable.

### Anti-pattern Penalty Box — Mediator

> **Anti-pattern penalty box:** Any concrete colleague storing a direct reference to another concrete colleague makes the Mediator section score **0**.

> **Anti-pattern penalty box:** Calling another colleague's `receive(...)` directly from a concrete colleague makes the Mediator section score **0**.

> **Anti-pattern penalty box:** Implementing `GuildHall` as a pass-through that simply forwards to one hard-coded object makes the Mediator section score **0**.

> **Anti-pattern penalty box:** Putting all colleague-specific behavior inside `GuildHall` while colleagues do nothing meaningful makes the Mediator section score **0**.

---

## 7. Part 3: CouncilEngine + Demo

Your `Main.java` and `CouncilEngine` should produce a readable war council run.

1. Create at least **2 heroes** with different stats.
2. Build a `QuestLog` with at least **5 quests** of mixed priority.
3. Register at least **4 guild members**: `Quartermaster`, `Scout`, `Healer`, and `Captain`.
4. Iterate the quest log with at least **2 different `QuestIterator` implementations**.
5. Dispatch coordinating messages through the mediator while planning quests.
6. Show colleague output that proves messages were routed by topic.
7. Run `CouncilEngine.runCouncil(...)`.
8. Print a final `CouncilRunResult`.

> **Critical requirement:** The output must make the pattern boundaries visible. The grader should be able to tell where traversal happens and where mediated communication happens.

---

## 8. Part 4: Open/Closed Proof

After the required demo works, add both extensions below.

**Iterator extension:** Add `RewardSortedQuestIterator`.

- It should traverse quests by reward amount.
- It must use a snapshot from `QuestLog`.
- It must not require exposing the internal list.
- It must not require rewriting existing iterator classes.

**Mediator extension:** Add `Loremaster`.

- It should extend `GuildMember`.
- It should subscribe to or react to lore, curse, or history topics.
- Existing concrete colleagues must not gain direct references to it.
- Communication must still flow through `GuildHall`.

This is your open/closed proof: new behavior arrives by adding new classes and small registration/routing changes, not by breaking the existing boundaries.

---

## 9. Deliverables

Submit a ZIP file containing:

- All `.java` source files
- `README.md`, `ASSIGNMENT.md`, `QUICKSTART.md`, `STUDENT_CHECKLIST.md`, `FAQ.md`
- 2 UML diagrams
- No compiled `.class` files

The two diagrams are:

- **Diagram 1:** Iterator pattern — `QuestLog`, `QuestIterator`, and all concrete quest iterators
- **Diagram 2:** Mediator pattern — `GuildMediator`, `GuildHall`, `GuildMember`, and all concrete guild members

---

## 10. Grading

| Area | Points | What the grader checks |
|------|--------|------------------------|
| **Iterator** | 6 | Custom interface used correctly; aggregate does not leak internals; at least 3 concrete iterators work; traversal differences are visible; no direct list traversal by clients; open/closed proof with reward-sorted traversal |
| **Mediator** | 6 | Colleagues communicate only through `GuildMediator`; `GuildHall` routes by topic; colleagues have meaningful `receive` behavior; no direct colleague references; open/closed proof with `Loremaster` |
| **Engine + Demo** | 3 | `CouncilEngine` and `Main.java` produce a clear guild council run; at least two iterators are demonstrated; mediated messages are visible; final `CouncilRunResult` is printed |

Total: **15 points**
