public class Main {

    public static void main(String[] args) {
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        String result = text.replaceAll("<[^>]*>", placeholder);
        return result;
    }

}