package model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Station implements Comparable<Station> {

  @NonNull
  private String name;
  @NonNull
  private Line line;

  @Override
  public int compareTo(Station o) {
    return line.compareTo(o.getLine()) == 0 ?
        name.compareTo(o.getName()) : line.compareTo(o.getLine());
  }
}
