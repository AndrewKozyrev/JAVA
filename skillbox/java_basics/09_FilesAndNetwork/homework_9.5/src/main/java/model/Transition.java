package model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "origin")
public class Transition {

  private Station origin;
  private Set<Station> connected = new LinkedHashSet();

  public Transition(Station from, Collection<Station> to) {
    origin = from;
    connected.add(from);
    connected.addAll(to);
  }

  public Set<Station> getToStations() {
    return connected.stream()
        .filter(c -> !c.equals(origin))
        .collect(Collectors.toSet());
  }

  public boolean contains(Station station) {
    return connected.contains(station);
  }

}
