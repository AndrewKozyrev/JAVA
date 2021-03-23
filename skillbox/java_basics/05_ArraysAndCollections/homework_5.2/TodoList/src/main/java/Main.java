import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (!(command = reader.readLine()).isEmpty()) {

            String[] cmd = command.split("\\s+", 2);
            switch (cmd[0]) {
                case "LIST":
                    todoList.listTodos();
                    break;
                case "EDIT":
                    cmd = cmd[1].split("\\s+", 2);
                    todoList.edit(cmd[1], Integer.parseInt(cmd[0]));
                    break;
                case "DELETE":
                    cmd = cmd[1].split("\\s+", 2);
                    todoList.delete(Integer.parseInt(cmd[0]));
                    break;
                case "ADD":
                    if (cmd[1].matches("\\d+\\s.*")) {
                        int index = Integer.parseInt(cmd[1].charAt(0)+"");
                        cmd = cmd[1].split("\\s+", 2);
                        todoList.add(index, cmd[1]);
                    } else {
                        todoList.add(cmd[1]);
                    }
                    break;
            }
        }

    }
}
