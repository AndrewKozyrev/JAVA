import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> list = new ArrayList<>();

    public void add(String todo) {
        list.add(todo);
    }

    public void add(int index, String todo) {
        if (index <= list.size() && index >= 0) {
            list.add(index, todo);
        } else {
            list.add(todo);
        }
    }

    public void edit(String todo, int index) {
        if (index < list.size() && index >= 0) {
            list.set(index, todo);
        }
    }

    public void delete(int index) {
        if (index < list.size() && index >= 0) {
            list.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        return list;
    }

    public void listTodos() {
        int i = 0;
        for (String s : getTodos()) {
            System.out.printf("%d - %s\n", i++, s);
        }
    }


}