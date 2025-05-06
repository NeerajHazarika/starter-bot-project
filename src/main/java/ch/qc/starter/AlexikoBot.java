package ch.qc.starter;

import ch.cern.quantumconnect.core.Algorithm;
import ch.cern.quantumconnect.core.QuantumField;

public class AlexikoBot implements Algorithm {

    @Override
    public int accelerateQuark(QuantumField quantumField) {
        int columns = quantumField.getNumberOfColumns();
        int rows = quantumField.getNumberOfRows(); // Get the number of rows
        int[] validColumns = new int[columns];
        int validCount = 0;

        for (int col = 0; col < columns; col++) {
            // Collect all non-full columns
            if (!quantumField.isColumnFull(col)) {
                validColumns[validCount++] = col;
            }
        }

        // Check for opponent win
        for (int i = 0; i < validCount; i++) {
            int col = validColumns[i];
            int opponentRow = 0;

            for (int row = 0; row < rows; row++) {
                if (!quantumField.isSpaceEmpty(row, col) && !quantumField.belongsToMe(row, col)) {
                    opponentRow++;
                } else if (quantumField.belongsToMe(row, col)) {
                    opponentRow--;
                }

                if (opponentRow >= 5) {
                    return col;
                }
                }
        }

        // Check for our win
        for (int i = 0; i < validCount; i++) {
            int col = validColumns[i];
            int myRow = 0;

            for (int row = 0; row < rows; row++) {
                if (quantumField.isSpaceEmpty(row, col) || quantumField.belongsToMe(row, col)) {
                    myRow = checkVertical(quantumField, row, col, 6); // Check for 6 in a row
                    if (myRow >= 6) {
                        return col;
                    }
                }
            }
        }

        // Pick a random column from the valid ones
        if (validCount > 0) { 
            return validColumns[0];
        }

        throw new IllegalStateException("No valid moves available");
    }

    private int checkVertical(QuantumField quantumField, int row, int col, int targetCount) {
        int count = 0;
        int rows = quantumField.getNumberOfRows();

        for (int i = row; i < rows; i++) {
            if (quantumField.isSpaceEmpty(i, col)) {
                count++;
            } else if (quantumField.belongsToMe(i, col)) {
                count++;
            } else {
                count--;
            }
        }
        return count;
    }
}
