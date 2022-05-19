import java.util.*;

public class Methods {
    // String palinText="";
    // public Methods(){

    // }

    /**
     * *********************** Hamdy Variables *************************
     */
    char[][] matrix = new char[5][5];
    static String unique_key = "";
    // to remove Duplicate char from key

    /**
     * ************************* Amgad Variabels ****************************
     */

    /**
     * Get PT and prepare it to be encrypted
     */

    String preparePlainText(String plainText) {
        /**
         * prepared encrypted text that will be returned to be encrypted
         */
        String preparedPlainText = "";
        /**
         * Remove spaces from plain Text ->
         */
        String plainTextWithoutSpaces = plainText.replace(" ", "");
        /**
         * Refreshed Array list to track changes while adding x in PT ->
         */
        ArrayList<Character> refreshedPlainText = new ArrayList<>();
        /**
         * Convert plainTextWithOutWhiteSpaces to char[] to be easier to deal with ->
         */
        char[] plainTextWithoutSpacesInChars = plainTextWithoutSpaces.toCharArray();
        /**
         * add all plainTextWithoutSpacesInChars in array list to be easley track
         * changes while adding x
         */
        for (char x : plainTextWithoutSpacesInChars) {
            refreshedPlainText.add(x);
        }
        /**
         * for loop to start preparing PT and adding x
         */
        int refreshedPlainTextSize = refreshedPlainText.size();
        for (int i = 0; i <= refreshedPlainTextSize - 2; i += 2) {
            /**
             * if there is character duplicated .. put 'x' between them -> need : nexed
             */
            if (refreshedPlainText.get(i) == refreshedPlainText.get(i + 1)) {
                /**
                 * Add x between duplicated text
                 */
                refreshedPlainText.add(i + 1, 'x');
                /**
                 * refresh the size of the the arrayList
                 */
                refreshedPlainTextSize = refreshedPlainText.size();
            }
        }
        /**
         * Check if the last character is alone .. add x after it .
         */
        if (refreshedPlainText.size() % 2 != 0) {
            refreshedPlainText.add('x');
        }
        /**
         * add all char's of prepared PT to one String
         */
        for (char x : refreshedPlainText) {
            preparedPlainText += x;
        }
        /**
         * Return Prepared PT String
         */
        return preparedPlainText;
    }

    String removeDuplicates(String key) {

        // Create LinkedHashSet of type character
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        // Add each character of the string into LinkedHashSet
        for (int i = 0; i < key.length(); i++)
            set.add(key.charAt(i));

        // print the string after removing duplicate characters

        for (Character ch : set) {
            unique_key += ch;
        }
        return unique_key;
    }

    // function to generate table
    public char[][] generateCipherKey(String unique_key) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < unique_key.length(); i++) {
            if (unique_key.charAt(i) == 'j')
                continue;
            set.add(unique_key.charAt(i));
        }

        // from a to z without j
        String tempKey = new String(unique_key);

        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 97);
            if (ch == 'j')
                continue;

            if (!set.contains(ch))
                tempKey += ch;
        }

        // create cipher key table
        for (int i = 0, idx = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = tempKey.charAt(idx++);

        System.out.println("Playfair Cipher Key Matrix:");

        for (int i = 0; i < 5; i++)
            System.out.println(Arrays.toString(matrix[i]));

        return matrix;
    }

    /**
     *
     * Encrypt Prepared plain text and convert it to Ciper Text
     *
     */
    String encryptePlainText(String key, String plainText) {
        int counterFlag = 0;
        /**
         * generate Unique key
         */
        String uniqueKey = removeDuplicates(key);
        /**
         * Create chars matrix
         */
        char[][] matrix = generateCipherKey(uniqueKey);
        /**
         * Prepare Plain text
         */
        String preparedPlainText = preparePlainText(plainText);
        /**
         * Convert prepared plainText to char[] for easier deal
         */
        char[] preparedPlainTextInChars = preparedPlainText.toCharArray();
        /**
         * returned ciper text
         */
        String ciperText = "";
        /**
         * external for loop for taking pair of chars 2 by 2 .
         */
        for (int i = 0; i < preparedPlainText.length(); i += 2) {
            /**
             * first char obj
             */
            char firstChar = preparedPlainTextInChars[i];
            /**
             * second char obj
             */
            char secondChar = preparedPlainTextInChars[i + 1];
            /**
             * first char position
             */
            int[] firstCharPosition = new int[2];
            /**
             * second char position
             */
            int[] secondCharPosition = new int[2];
            /**
             * 2D array [row][column]
             * Outer for loop is for Rows
             */
            for (int x = 0; x < 5; x++) {
                /**
                 * inner loop is for Columns
                 */
                for (int y = 0; y < 5; y++) {
                    /**
                     * Counter
                     */
                    if (counterFlag == 2) {
                        break;
                    } else {
                        /**
                         * if you find first char
                         */
                        if (firstChar == matrix[x][y]) {
                            /**
                             * save its x and y
                             */
                            firstCharPosition[0] = x;
                            firstCharPosition[1] = y;
                            counterFlag++;
                            // System.out.println("first char position -> " +firstCharPosition[0]+"
                            // "+firstCharPosition[1]);
                        }
                        /**
                         * if you find second char
                         */
                        else if (secondChar == matrix[x][y]) {
                            /**
                             * Save second char position
                             */
                            secondCharPosition[0] = x;
                            secondCharPosition[1] = y;
                            counterFlag++;
                            // System.out.println("second char position -> " +secondCharPosition[0]+"
                            // "+secondCharPosition[1]);

                        }
                    }
                }
                if (counterFlag == 2){
                    System.out.println("columns and rows loop stopped");
                    break;
                }


            }
            /**
             * Encrypt both chars and add them to ciper Text
             * then get next 2 char's
             */
            // ciperText += CiperdChars(firstCharPosition, secondCharPosition, matrix)[0];
            // ciperText += CiperdChars(firstCharPosition, secondCharPosition, matrix)[1];
            System.out.println("Cipered 2 chars -> " + CiperdChars(firstCharPosition, secondCharPosition, matrix)[0]
                    + " " + CiperdChars(firstCharPosition, secondCharPosition, matrix)[1]);
            counterFlag = 0;

        }
        System.out.println("Ciper text -> " + ciperText);
        return ciperText;
    }

    /**
     * take 2 chars and convert it to 2 Cipered chars
     */
    char[] CiperdChars(int[] firstCharPosition, int[] secondCharPosition, char[][] matrix) {
        char[] ciperdCharsArray = new char[2];
        int[] firstCiperedCharPosition = new int[2];
        int[] secondCiperedCharPosition = new int[2];

        /**
         *
         */
        if (firstCharPosition[0] == secondCharPosition[0]) {
            firstCiperedCharPosition[0] = firstCharPosition[0];
            secondCiperedCharPosition[0] = secondCharPosition[0];

            firstCiperedCharPosition[1] = (firstCharPosition[1] + 1) % 5;
            secondCiperedCharPosition[1] = (secondCharPosition[1] + 1) % 5;

            ciperdCharsArray[0] = matrix[firstCiperedCharPosition[0]][firstCiperedCharPosition[1]];
            ciperdCharsArray[1] = matrix[secondCiperedCharPosition[0]][secondCiperedCharPosition[1]];
            return ciperdCharsArray;
        } else if (firstCharPosition[1] == secondCharPosition[1]) {

            firstCiperedCharPosition[1] = firstCharPosition[1];
            secondCiperedCharPosition[1] = secondCharPosition[1];

            firstCiperedCharPosition[0] = (firstCharPosition[0] + 1) % 5;
            secondCiperedCharPosition[0] = (secondCharPosition[0] + 1) % 5;

            return ciperdCharsArray;
        } else {
            int temp = firstCiperedCharPosition[1];
            firstCiperedCharPosition[1] = secondCiperedCharPosition[1];
            secondCiperedCharPosition[1] = temp;
        }
        return ciperdCharsArray;
    }
}
