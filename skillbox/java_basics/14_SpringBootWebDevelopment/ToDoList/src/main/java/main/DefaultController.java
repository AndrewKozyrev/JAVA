package main;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController
{
  @Autowired
  TodoRepository todoRepository;

  @RequestMapping("/")
  public String index(Model model)
  {
    var all = todoRepository.findAll();
    var todos = new ArrayList<>();
    all.forEach(todos::add);
    model.addAttribute("todos", todos);
    model.addAttribute("todosCount", todos.size());
    model.addAttribute("counter", new AtomicInteger());
    return "index";
  }
}
