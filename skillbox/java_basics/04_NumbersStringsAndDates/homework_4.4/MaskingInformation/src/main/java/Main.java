public class Main {

    public static void main(String[] args) {
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        if (!text.contains("<") || !text.contains(">")) {
            // если больше нет специальных символов - добавляем остаток
            return text;
        }

        StringBuilder result = new StringBuilder();
        String chunk = text;

        // добавляем всё до правой <
        result.append(chunk.substring(0, chunk.indexOf('<')));
        // добавляем заместитель
        result.append(placeholder);
        // повторяем те же действия для остатка
        chunk = chunk.substring(chunk.indexOf('>')+1);

        return result.toString() + searchAndReplaceDiamonds(chunk, placeholder);
    }

}