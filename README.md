# Notepad in Java

A lightweight desktop notepad/text editor built with Java Swing and AWT, inspired by the classic Windows Notepad.

## Features

- Create, open, edit, and save plain text files
- Standard text editing — cut, copy, paste, undo/redo
- Clean and minimal GUI built with Java Swing
- Familiar menu-driven interface

## Requirements

- Java JDK 8 or higher
- An IDE such as IntelliJ IDEA (recommended) or any Java-compatible IDE

## Getting Started

### Clone the repository

```bash
git clone https://github.com/gustavogandrade/notepad-in-java.git
cd notepad-in-java
```

### Run with IntelliJ IDEA

1. Open IntelliJ IDEA and select **File > Open**
2. Navigate to the cloned folder and open it
3. Locate the main class inside `src/`
4. Click **Run** or press `Shift + F10`

### Run from the command line

```bash
cd src
javac *.java
java Main
```

> Adjust the class name if the entry point differs from `Main`.

## Project Structure

```
notepad-in-java/
├── src/          # Java source files
├── .idea/        # IntelliJ IDEA project settings
├── GPad.iml      # IntelliJ module file
└── .gitignore
```

## Tech Stack

- **Java** — core language
- **Swing / AWT** — GUI framework

## License

This project is open source. Feel free to fork and build on it.
