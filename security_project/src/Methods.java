import java.util.ArrayList;

public class Methods {


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
        int refreshedPlainTextSize=refreshedPlainText.size();
        for (int i = 0; i <= refreshedPlainTextSize-2;i+=2) {
            System.out.println("i -> "+i);
            /**
             * if there is character duplicated .. put 'x' between them -> need : nexed
             */
            if (refreshedPlainText.get(i) == refreshedPlainText.get(i+1)) {
                /**
                 * Add x between duplicated text
                 */
                refreshedPlainText.add(i + 1, 'x');
                /**
                 * refresh the size of the the arrayList
                 */
                refreshedPlainTextSize=refreshedPlainText.size();
                System.out.println("refreshedPlainText : "+refreshedPlainText);
            }
        }
        /**
         * Check if the last character is alone .. add x after it .
         */
        if(refreshedPlainText.size()%2!=0){
            refreshedPlainText.add('x');
        }
        /**
         * add all char's of prepared PT to one String
         */
        for(char x : refreshedPlainText){preparedPlainText+=x;}
        System.out.println("refreshedPlainText : "+refreshedPlainText);
        System.out.println("preparedPlainText : "+preparedPlainText);
        /**
         * Return Prepared PT String
         */
        return preparedPlainText;
    }
}
