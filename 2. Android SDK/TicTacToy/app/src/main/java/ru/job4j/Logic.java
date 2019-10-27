package ru.job4j;

import android.util.Log;

import java.util.Random;

public class Logic {

    private boolean whosTurn = true;
    private char[] playField = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    private onGameOver callback;

    private boolean botIsTurned = false;
    private Random random = new Random();

    public Logic(onGameOver callback) {
        printDebug();
        this.callback = callback;
    }

    void updateField(int cellIndex) {

        if (playField[cellIndex] == ' ') {

            if (whosTurn) {
                playField[cellIndex] = 'X';
            } else {
                playField[cellIndex] = 'O';
            }


            if (!checkResults(cellIndex)) {
                if (isDraw()) {
                    wipe();
                    whosTurn = false;
                    callback.wipeUI(true);
                } else
                    callback.updateUI(playField);
            } else {
                wipe();
                whosTurn = false;
                callback.wipeUI(false);
            }
            printDebug();

            whosTurn = !whosTurn;

            if (botIsTurned && !whosTurn) {
                botsTurn();
            }
        }
    }

    private boolean checkResults(int cellIndex) {

        int row = cellIndex - cellIndex % 3; //getting row

        if (playField[row] == playField[row + 1] && playField[row] == playField[row + 2])
            return true; //checking row

        int column = cellIndex % 3; //getting column

        if (playField[column] == playField[column + 3] && playField[column] == playField[column + 6])
            return true; //checking column

        if (cellIndex % 2 != 0) return false; //if corner value

        if (cellIndex % 4 == 0) {
            if (playField[0] == playField[4] && playField[0] == playField[8])
                return true; //checking left diagonal
            if (cellIndex != 4) return false;
        }

        return (playField[2] == playField[4] && playField[2] == playField[6]); //checking right diagonal
    }

    private void wipe() {
        for (int i = 0; i < 9; i++) {
            playField[i] = ' ';
        }
    }

    private void printDebug() {
        Log.d("print", "Debug" + "\n" + playField[0] + playField[1] + playField[2] + "\n" +
                playField[3] + playField[4] + playField[5] + "\n" +
                playField[6] + playField[7] + playField[8]);
    }

    public interface onGameOver {
        void updateUI(char[] playField);

        void wipeUI(boolean draw);
    }

    private boolean isDraw() {
        boolean d = true;
        for (char c : playField) {
            if (c == ' ')
                d = false;
        }
        return d;
    }

    void switchBot() {
        Log.d("bot", "switchBot: ");
        botIsTurned = !botIsTurned;
        if (!whosTurn)
            botsTurn();
    }

    private void botsTurn() {

        int b;
        for (; ; ) {
            b = random.nextInt(8);
            if (playField[b] == ' ')
                break;
        }
        updateField(b);
    }
}
