package main;

import java.util.ArrayList;
import java.util.List;
import main.model.TODO;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

  @Autowired
  private TodoRepository todoRepository;

  @GetMapping("/todos/")
  public List<TODO> list()
  {
    var iterable = todoRepository.findAll();
    var todos = new ArrayList<TODO>();
    iterable.forEach(todos::add);
    return todos;
  }

  @PostMapping("/todos/")
  public int add(TODO todo)
  {
    var newTodo = todoRepository.save(todo);
    return newTodo.getId();
  }

  @GetMapping("/todos/{id}")
  public ResponseEntity get(@PathVariable int id)
  {
    var optionalTODO = todoRepository.findById(id);
    if (!optionalTODO.isPresent())
    {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity(optionalTODO.get(), HttpStatus.OK);
  }

  @DeleteMapping("/todos/")
  public ResponseEntity delete()
  {
    todoRepository.deleteAll();
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @DeleteMapping("/todos/{id}")
  public ResponseEntity delete(@PathVariable int id)
  {
    boolean isFound = todoRepository.existsById(id);
    if (!isFound)
    {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    todoRepository.deleteById(id);
    return new ResponseEntity(id, HttpStatus.OK);
  }

  @PutMapping("/todos/{id}")
  public ResponseEntity update(@RequestBody TODO newTodo,@PathVariable int id)
  {
    if (!todoRepository.existsById(id))
    {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    newTodo.setId(id);
    todoRepository.save(newTodo);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
