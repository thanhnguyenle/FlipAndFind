# Memory Card Game

## Overview
This project is a **Memory Card Game** developed in Java, following **OOP principles** and implementing **design patterns** such as **Adapter Pattern** and **Observer Pattern**. The game allows users to play memory-based card matching, tracking their scores, levels, and rankings. It also includes a graphical user interface (GUI) built with **Java Swing**, supporting animations and event handling.

## Features
- **Card Matching Gameplay**: Flip cards, find matching pairs, and win by uncovering all cards.
- **User Management**: Create, edit, and delete users with avatars and track their scores.
- **Game Levels & Ranks**: Progress through different levels with increasing difficulty.
- **File I/O for Persistence**: Store user data, scores, and rankings in text files.
- **Dynamic UI**: Interactive game screens with animations, effects, and sound.
- **Multiple Screens**: PlayGame, HighScore, Settings, About.
- **Event Handling**: Actions such as flipping cards, updating scores, handling time limits, and user interactions.
- **Audio Support**: Background music and sound effects for an immersive experience.

## Design Patterns Implemented
- **Adapter Pattern**: Used for handling different implementations of cards.
- **Observer Pattern**: Implemented in `ModelDataListCard` to notify observers when `ListCards` is updated.

## Class Structure
### **Model**
- **Card & ListCards**: Represents cards and manages card-related actions (shuffle, flip, hide, select, etc.).
- **User & ListUsers**: Manages user data, ranking, and score.
- **LevelData**: Handles game levels and difficulty settings.
- **File Handling**: Reads and writes user data, scores, and game progress.

### **View**
- **CardView & ListCardsView**: Renders cards visually and updates UI dynamically.
- **Dialog**: Displays win/lose messages.
- **Menu Screens**: Manages different game screens (PlayGame, HighScore, Settings, About).
- **UI Support Components**: Draws buttons, timers, and ranking panels.

### **Controller**
- **ActionFlipCard**: Handles card flipping and checking for matches.
- **ActionLevelUp**: Updates user scores and progresses to the next level.
- **ActionYouLose**: Manages losing conditions and restarting.
- **ActionScreen**: Handles screen transitions and animations.
- **ActionUserInform**: Processes user management actions (create, edit, delete users).
- **AudioHandler**: Manages background music and sound effects.


## Installation & Running
1. Clone the repository:
```sh
git clone git@github.com:thanhnguyenle/FlipAndFind.git
cd FlipAndFind
```
2. Build the Docker image:
```sh
docker build -t flip-and-find .
```
3. Run the container:
```sh
docker run -it flip-and-find
```