/*
 * Code written/updated: 13-11-2026
 * ChronoQuest - Game State Manager
 *
 * Responsible for creating and describing 
 * snapshots of the current game state.
 */

package com.chronoquest.engine;

import com.chronoquest.model.GameSnapshot;
import com.chronoquest.model.Item;
import com.chronoquest.model.Player;
import com.chronoquest.model.Puzzle;

import java.util.Set;


public class GameStateManager {

    /*
     * Create a snapshot of the current game.
     */
    public GameSnapshot createSnapshot(
            Player player,
            Puzzle puzzle,
            boolean drawerOpen,
            boolean keyTaken,
            String currentObjective,
            String actionDescription,
            int timeRemaining) {

        Set<Item> inventory =
                player.getInventory();

        return new GameSnapshot(
                inventory,
                drawerOpen,
                keyTaken,
                puzzle.isSolved(),
                player.getScore(),
                timeRemaining,
                currentObjective,
                actionDescription
        );
    }

    /*
     * Generate a readable summary
     * of a saved game state.
     */
    public String getSnapshotSummary(
            GameSnapshot snapshot) {

        return "Score: "
                + snapshot.getScore()
                + "\nTime Remaining: "
                + snapshot.getTimeRemaining()
                + "\nDrawer Open: "
                + snapshot.isDrawerOpen()
                + "\nKey Taken: "
                + snapshot.isKeyTaken()
                + "\nTimeline Solved: "
                + snapshot.isTimelineSolved()
                + "\nObjective: "
                + snapshot.getCurrentObjective()
                + "\nLast Action: "
                + snapshot.getActionDescription();
    }
}