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
      int availablerow = 0;
  
      
      for (int col = 0; col < columns; col++) {
        // Collect all non-full columns
        if (!quantumField.isColumnFull(col)) {
          validColumns[validCount++] = col;
        }
      }

      // iterate all valid columns
        for (int i = 0; i < validCount; i++) {
            int col = validColumns[i];

            // reinitialize availablerow for each column
            availablerow = 0;
            for (int row = 0; row < quantumField.getNumberOfRows(); row++) {

                // Check if column has atleast 6 rows empty
                if (quantumField.isSpaceEmpty(row, col) || quantumField.belongsToMe(row, col)) {
                    availablerow += 1;
                } 

                // if column has 6 empty rows, return the column
                if (availablerow >= 6) {
                System.err.println("Column " + col + " has 6 available rows (empty or mine)");
                return validColumns[i];
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
