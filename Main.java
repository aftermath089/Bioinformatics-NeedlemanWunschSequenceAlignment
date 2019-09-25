import java.util.LinkedList;
import java.util.Scanner;

class Main extends EntryValue {

    public static void main(String[] args) {
        // INIT VAR,
        String String1, String2;
        int Length1, Length2;
        char[] Char1, Char2;

        EntryValue setEntryValue = new EntryValue();

        // INPUT//
        String2 = "GCATGCU";
        Length2 = String2.length();
        Char2 = String2.toCharArray();

        String1 = "GCATGCU";
        Length1 = String1.length();
        Char1 = String1.toCharArray();

        // PREPARE MATRICES//
        int[][] ArrayValue = new int[Length1 + 1][Length2 + 1];
        int[][] ArrayTracing = new int[Length1 + 1][Length2 + 1];
        String[][] ArrayArrows = new String[Length1 + 1][Length2 + 1];
        int[][] ArrayFlag = new int[Length1][Length2];

        for (int i = 0; i < Length1 + 1; i++) {
            for (int j = 0; j < Length2 + 1; j++) {
                if (i == 0) {
                    ArrayValue[i][j] = i + (j * (-6));
                    ArrayTracing[i][j] = i + (j * (-6));
                } else if (j == 0) {
                    ArrayValue[i][j] = j - (i * 6);
                    ArrayTracing[i][j] = j - (i * 6);
                } else {
                    ArrayValue[i][j] = setEntryValue.setValue(Char1[i - 1], Char2[j - 1]);
                    ArrayTracing[i][j] = setEntryValue.setValue(Char1[i - 1], Char2[j - 1]);

                    if (Char1[i - 1] == Char2[j - 1]) {
                        ArrayFlag[i - 1][j - 1] = 1;
                    }
                }
            }
        }
        /////////////////////

        // DOT MATRICE//
        System.out.println("\nDot matrice representation: ");
        for (int i = 0; i < Length1; i++) {
            for (int j = 0; j < Length2; j++) {
                System.out.print(ArrayFlag[i][j] + " ");
            }
            System.out.println();
        }
        ///////////////////

        // SCORING MATRICE//
        System.out.println("\nScoring matrice representation: ");
        for (int i = 0; i < Length1 + 1; i++) {
            for (int j = 0; j < Length2 + 1; j++) {
                System.out.print(ArrayValue[i][j] + "\t");
            }
            System.out.println();
        }
        //////////////////

        // PUTTING SCORE//
        for (int i = 1; i < Length1 + 1; i++) {
            for (int j = 1; j < Length2 + 1; j++) {
                int atas, kiri, diagonal;

                diagonal = ArrayTracing[i][j] + ArrayTracing[i - 1][j - 1];
                kiri = ArrayTracing[i - 1][j] - 6;
                atas = ArrayTracing[i][j - 1] - 6;

                if ((atas >= kiri) && (atas >= diagonal)) {
                    // atas;
                    ArrayTracing[i][j] = atas;
                } else if ((diagonal > atas) && (diagonal >= kiri)) {
                    // diagonal;
                    ArrayTracing[i][j] = diagonal;
                } else {
                    // kiri;
                    ArrayTracing[i][j] = kiri;
                }
            }
        }

        // OUTPUT SCORE//
        System.out.println("\nOutput score matrice representation: ");
        for (int i = 0; i < Length1 + 1; i++) {
            for (int j = 0; j < Length2 + 1; j++) {
                System.out.print(ArrayTracing[i][j] + "\t");
            }
            System.out.println();
        }
        ////////////////

        // ARROW PATH//
        for (int i = Length1; i > 0; i--) {
            for (int j = Length2; j > 0; j--) {

                int atas, kiri, diagonal, comparator;
                atas = ArrayTracing[i][j] + 6 - ArrayTracing[i - 1][j];
                kiri = ArrayTracing[i][j] + 6 - ArrayTracing[i][j - 1];
                diagonal = ArrayTracing[i][j] - ArrayValue[i][j] - ArrayTracing[i - 1][j - 1];

                if (atas == 0) {
                    ArrayArrows[i][j] = "atas";
                } else if (diagonal == 0) {
                    ArrayArrows[i][j] = "diag";
                } else if (kiri == 0) {
                    ArrayArrows[i][j] = "kiri";
                }
            }
        }

        System.out.println("\nOutput Arrow matrice representation: ");
        for (int i = 0; i < Length1 + 1; i++) {
            for (int j = 0; j < Length2 + 1; j++) {
                System.out.print(ArrayArrows[i][j] + "  ");
            }
            System.out.println("\n");
        }
        ////////////////

        // BEST ALIGNMENT//
        LinkedList linkedList1 = new LinkedList<Character>();
        LinkedList linkedList2 = new LinkedList<Character>();
        int i2 = Length1;
        int j2 = Length2;

        while (i2 != 0 && j2 != 0) {
            if (ArrayArrows[i2][j2] == "diag") {
                linkedList1.push(Char2[j2 - 1]);
                i2 = i2 - 1;
                j2 = j2 - 1;

            } else if (ArrayArrows[i2][j2] == "atas") {
                i2 = i2 - 1;
                linkedList1.push('-');

            } else if (ArrayArrows[i2][j2] == "kiri") {
                j2 = j2 - 1;
                linkedList1.push('-');

            }

            if (i2 == 0 && j2 != 0) {
                for (int i = Length1 - 1; i >= 0; i--) {
                    linkedList2.push(Char1[i]);
                }
                for (int i = 0; i < j2; i++) {
                    linkedList2.push('-');
                }
            }

        }
        linkedList1.push(Char2[j2 - 1]);

        System.out.println("Generated Alignment: ");
        while (!linkedList1.isEmpty()) {
            System.out.print(linkedList1.pop());
        }
        System.out.println();
        while (!linkedList2.isEmpty()) {
            System.out.print(linkedList2.pop());
        }
        ////////////////

    }

}