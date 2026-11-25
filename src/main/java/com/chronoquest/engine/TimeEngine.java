/*
 * Code written/updated: 14-11-2026
 * ChronoQuest - Time Engine
 *
 * Stack DSA is used for temporal game-state rewind.
 */
package com.chronoquest.engine;

import com.chronoquest.model.GameSnapshot;
import com.chronoquest.model.Item;
import com.chronoquest.model.Player;
import com.chronoquest.model.Puzzle;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class TimeEngine {

    /*
     * Stack stores previous game states.
     *
     * TOP of stack = latest captured state
     */
    private final Stack<GameSnapshot> temporalStack =
            new Stack<>();

    /*
     * Temporary inventory used while creating
     * temporal snapshots.
     */
    private final Set<Item> activeInventory =
            new HashSet<>();

    /*
     * Capture the current game state.
     */
    public void captureState(
            Player player,
            Puzzle puzzle,
            boolean drawerOpen,
            boolean keyTaken,
            int timeRemaining,
            String objective,
            String action) {

        if (player == null || puzzle == null) {
            return;
        }

        activeInventory.clear();

        activeInventory.addAll(
                player.getInventory()
        );

        GameSnapshot snapshot =
                new GameSnapshot(
                        activeInventory,
                        drawerOpen,
                        keyTaken,
                        puzzle.isSolved(),
                        player.getScore(),
                        timeRemaining,
                        objective,
                        action
                );

        temporalStack.push(snapshot);
    }

    /*
     * Check whether rewind is possible.
     */
    public boolean canRewind() {

        return temporalStack.size() > 1;
    }

    /*
     * Rewind to the previous game state.
     */
    public GameSnapshot rewind() {

        if (canRewind()) {

            /*
             * Remove the current state.
             */
            temporalStack.pop();

            /*
             * Get the previous state.
             */
            GameSnapshot prior =
                    temporalStack.peek();

            /*
             * Restore inventory inside the
             * temporal engine.
             */
            activeInventory.clear();

            activeInventory.addAll(
                    prior.getInventory()
            );

            return prior;
        }

        return null;
    }

    /*
     * Add an item to the temporal inventory.
     */
    public void addItem(Item item) {

        if (item != null) {
            activeInventory.add(item);
        }
    }

    /*
     * Check whether an item exists
     * in the temporal inventory.
     */
    public boolean hasItem(String itemId) {

        if (itemId == null) {
            return false;
        }

        return activeInventory
                .stream()
                .anyMatch(
                        item ->
                                item.getId()
                                        .equalsIgnoreCase(itemId)
                );
    }

    /*
     * Return the current temporal inventory.
     *
     * Unmodifiable view prevents outside code
     * from directly changing the collection.
     */
    public Set<Item> getInventory() {

        return Collections.unmodifiableSet(
                activeInventory
        );
    }

    /*
     * Get number of saved temporal states.
     */
    public int getStateCount() {

        return temporalStack.size();
    }

    /*
     * Remove all temporal states and inventory.
     */
    public void reset() {

        temporalStack.clear();

        activeInventory.clear();
    }
}