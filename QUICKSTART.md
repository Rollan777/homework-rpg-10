# Quick Start — Homework 10

## Prerequisites

- Java 17 or newer
- A terminal
- No Maven or Gradle required

Check Java:

```bash
java -version
javac -version
```

---

## Project Tree

```text
homework-rpg-10/
├── README.md
├── ASSIGNMENT.md
├── QUICKSTART.md
├── STUDENT_CHECKLIST.md
├── FAQ.md
├── .gitignore
└── src/com/narxoz/rpg/
    ├── Main.java
    ├── combatant/
    ├── quest/
    ├── guild/
    └── council/
```

---

## Compile and Run on macOS or Linux

From the `homework-rpg-10` directory:

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.narxoz.rpg.Main
```

Expected scaffold output:

```text
=== Homework 10 Demo: Iterator + Mediator ===
```

---

## Compile and Run on Windows PowerShell

From the `homework-rpg-10` directory:

```powershell
New-Item -ItemType Directory -Force out
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out com.narxoz.rpg.Main
```

---

## IntelliJ IDEA

1. Open the `homework-rpg-10` folder.
2. Mark `src` as the Sources Root if IntelliJ does not do it automatically.
3. Set the project SDK to Java 17.
4. Open `src/com/narxoz/rpg/Main.java`.
5. Run `Main.main`.

---

## VS Code

1. Install the Extension Pack for Java.
2. Open the `homework-rpg-10` folder.
3. Make sure VS Code detects Java 17.
4. Open `Main.java`.
5. Use the Run button above `main`, or compile from the terminal commands above.

---

## Troubleshooting

**`javac` is not found**

Install a JDK, not only a JRE, and reopen the terminal.

**Package errors**

Run commands from the `homework-rpg-10` root. Do not compile from inside `src/com/narxoz/rpg`.

**Old output appears**

Delete `out/` and compile again:

```bash
rm -rf out
mkdir -p out
javac -d out $(find src -name "*.java")
```

**Main class not found**

Use the fully qualified class name:

```bash
java -cp out com.narxoz.rpg.Main
```

---

## Recommended Implementation Order

1. Read `QuestLog`, `QuestIterator`, and the three iterator classes.
2. Implement `OrderedQuestIterator`.
3. Implement `ReverseQuestIterator`.
4. Implement `PriorityQuestIterator`.
5. Implement `GuildHall.register(...)` and `GuildHall.dispatch(...)`.
6. Implement each guild member's outbound method and `receive(...)`.
7. Build `CouncilEngine.runCouncil(...)`.
8. Fill in `Main.java`, then add the Part 4 open/closed extensions.
