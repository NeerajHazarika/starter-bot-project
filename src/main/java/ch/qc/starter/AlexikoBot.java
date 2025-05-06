package ch.qc.starter;

import ch.cern.quantumconnect.core.Algorithm;
import ch.cern.quantumconnect.core.QuantumField;
// import ch.cern.quantumconnect.core.SpaceOwnership;

public class AlexikoBot implements Algorithm  {


    @Override
    public int accelerateQuark(QuantumField quantumField) {
      int columns = quantumField.getNumberOfColumns();
      int[] validColumns = new int[columns];
      int validCount = 0;
      int myRow, opponentRow = 0;
  
      
      for (int col = 0; col < columns; col++) {
        // Collect all non-full columns
        if (!quantumField.isColumnFull(col)) {
          validColumns[validCount++] = col;
        }
      }

      // iterate all valid columns for preventing opponent win
        for (int i = 0; i < validCount; i++) {
            int col = validColumns[i];

            // reinitialize opponentRow for each column
            opponentRow = 0;

            // iterate all rows in the column to check if opponent has win in next move
            for (int row = 0; row < quantumField.getNumberOfRows(); row++) {
                // check if column has atleast 5 opponentRows 
                if ((!quantumField.isSpaceEmpty(row, col) && !quantumField.belongsToMe(row, col)) || quantumField.isSpaceEmpty(row, col)) {
                    opponentRow += 1;
                }
                else {
                    opponentRow -= 1;
                }

                // check if column has atleast 5 rows for opponent and column not full
                if (opponentRow >= 5 && !quantumField.isColumnFull(col)) {
                    return col;
                }
            }
        }

        // iterate all valid columns for our win
        for (int i = 0; i < validCount; i++) {
            int col = validColumns[i];

            // reinitialize myRow for each column
            myRow = 0;

            // if opponent has not win in next move, check if column has available 6 rows empty
            for (int row = 0; row < quantumField.getNumberOfRows(); row++) {
                // Check if column has atleast 6 continous myRow 
                if (quantumField.isSpaceEmpty(row, col) || quantumField.belongsToMe(row, col)) {
                    myRow += 1;
                }
                else {
                    myRow -= 1;
                } 

                // if column has 6 empty rows, return the column
                if (myRow >= 6) {
                    return col;
                }
            }
        }
  
      // Pick a random column from the valid ones
      if (validCount > 0) {
        return validColumns[0];
      }
  
      throw new IllegalStateException("No valid moves available");
    }
}
