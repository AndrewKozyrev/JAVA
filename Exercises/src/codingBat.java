public class codingBat {
    public static String deFront(String str) {
        int n = str.length();
        StringBuilder result = new StringBuilder();
        if ( n >= 2 )
        {
            switch ( str.charAt(0) ) {
                case 'a': result.append('a');
                default: break;
            }
            switch ( str.charAt(1) ) {
                case 'b': result.append('b');
                default: break;
            }
            result.append( str.substring(2) );
        }
        else if ( n == 1 ) {
            return str.equals("a") ? "a" : "";
        }

        return result.toString();
    }

    public static void main(String[] args) {
        deFront("Hello");
    }
}