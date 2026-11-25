/*
 * Code written/updated: 15-11-2026
 * ChronoQuest - Save Game Manager
 *
 * Handles saving and loading the current game progress using Java File Handling
 * and safely handles file-related exceptions.
 */

package com.chronoquest.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SaveGameManager {

    /*
     * Save file is created in the project
     * working directory.
     */
    private static final String SAVE_FILE =
            "chronoquest_save.txt";

    /*
     * Save the current game state.
     */
    public boolean saveGame(
            int score,
            int timeRemaining,
            boolean keyFound,
            boolean drawerOpened,
            boolean timelineSolved,
            boolean chronoCoreActivated,
            boolean chronoCoreSolved,
            boolean timelineRestored,
            String currentObjective) {

        try {

            File file =
                    new File(SAVE_FILE);

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(file)
                    );

            writer.write(
                    "SCORE="
                            + score
            );
            writer.newLine();

            writer.write(
                    "TIME="
                            + timeRemaining
            );
            writer.newLine();

            writer.write(
                    "KEY_FOUND="
                            + keyFound
            );
            writer.newLine();

            writer.write(
                    "DRAWER_OPENED="
                            + drawerOpened
            );
            writer.newLine();

            writer.write(
                    "TIMELINE_SOLVED="
                            + timelineSolved
            );
            writer.newLine();

            writer.write(
                    "CHRONO_CORE_ACTIVATED="
                            + chronoCoreActivated
            );
            writer.newLine();

            writer.write(
                    "CHRONO_CORE_SOLVED="
                            + chronoCoreSolved
            );
            writer.newLine();

            writer.write(
                    "TIMELINE_RESTORED="
                            + timelineRestored
            );
            writer.newLine();

            writer.write(
                    "OBJECTIVE="
                            + currentObjective
            );
            writer.newLine();

            writer.close();

            return true;

        } catch (IOException e) {

            System.out.println(
                    "Save error: "
                            + e.getMessage()
            );

            return false;
        }
    }

    /*
     * Check whether a saved game file exists.
     */
    public boolean saveFileExists() {

        File file =
                new File(SAVE_FILE);

        return file.exists();
    }

    /*
     * Load the saved game data.
     *
     * Array positions:
     *
     * 0 = Score
     * 1 = Time
     * 2 = Key Found
     * 3 = Drawer Opened
     * 4 = Timeline Solved
     * 5 = Chrono Core Activated
     * 6 = Chrono Core Solved
     * 7 = Timeline Restored
     * 8 = Current Objective
     */
    public String[] loadGame() {

        if (!saveFileExists()) {

            return null;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    SAVE_FILE
                            )
                    );

            String[] data =
                    new String[9];

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("SCORE=")) {

                    data[0] =
                            line.substring(6);

                } else if (line.startsWith("TIME=")) {

                    data[1] =
                            line.substring(5);

                } else if (line.startsWith("KEY_FOUND=")) {

                    data[2] =
                            line.substring(10);

                } else if (line.startsWith("DRAWER_OPENED=")) {

                    data[3] =
                            line.substring(14);

                } else if (line.startsWith("TIMELINE_SOLVED=")) {

                    data[4] =
                            line.substring(16);

                } else if (line.startsWith(
                        "CHRONO_CORE_ACTIVATED=")) {

                    /*
                     * "CHRONO_CORE_ACTIVATED=" has
                     * 22 characters.
                     */
                    data[5] =
                            line.substring(22);

                } else if (line.startsWith(
                        "CHRONO_CORE_SOLVED=")) {

                    /*
                     * "CHRONO_CORE_SOLVED=" has
                     * 19 characters.
                     */
                    data[6] =
                            line.substring(19);

                } else if (line.startsWith(
                        "TIMELINE_RESTORED=")) {

                    /*
                     * "TIMELINE_RESTORED=" has
                     * 18 characters.
                     */
                    data[7] =
                            line.substring(18);

                } else if (line.startsWith(
                        "OBJECTIVE=")) {

                    data[8] =
                            line.substring(10);
                }
            }

            reader.close();

            return data;

        } catch (IOException e) {

            System.out.println(
                    "Load error: "
                            + e.getMessage()
            );

            return null;
        }
    }

    /*
     * Delete the saved game file.
     */
    public boolean deleteSave() {

        File file =
                new File(SAVE_FILE);

        if (file.exists()) {

            return file.delete();
        }

        return false;
    }
}