import java.util.*;

public class Methods {

    /**
     * Hamdy Variables
     */
    private char[][] matrix = new char[5][5];
    static String unique_key = "";
    // to remove Duplicate char from key

    /**
     * Amgad Variabels
     */
    /**
     * Get PT and prepare it to be encrypted
     */
    String plainTextpreparing(String plainText) {
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
         * add all plainTextWithoutSpacesInChars in array list to be easley track changes while adding x
         */
        for (char x : plainTextWithoutSpacesInChars) {
            refreshedPlainText.add(x);
        }
        /**
         * for loop to start preparing PT and adding x
         */
        int refreshedPlainTextSize = refreshedPlainText.size();
        for (int i = 0; i <= refreshedPlainTextSize - 2; i += 2) {
            System.out.println("i -> " + i);
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
                System.out.println("refreshedPlainText : " + refreshedPlainText);
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
        System.out.println("refreshedPlainText : " + refreshedPlainText);
        System.out.println("preparedPlainText : " + preparedPlainText);
        /**
         * Return Prepared PT String
         */
        return preparedPlainText;
    }


    static void removeDuplicates(String key) {

        //Create LinkedHashSet of type character
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        //Add each character of the string into LinkedHashSet
        for (int i = 0; i < key.length(); i++)
            set.add(key.charAt(i));

        // print the string after removing duplicate characters

        for (Character ch : set) {
            unique_key += ch;
            System.out.print(ch);
        }
    }

    // function to generate  table
    public void generateCipherKey() {
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
    }

}


