import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.Line;
import model.Station;
import model.Transition;

@RequiredArgsConstructor
@Getter
@Setter
public class StationIndex {

  @NonNull
  private String metroName;
  private Set<Line> lines = new LinkedHashSet<>();
  private Set<Station> stations = new LinkedHashSet<>();
  private Set<Transition> transitions = new LinkedHashSet<>();

  public void addLine(Line line) {
    lines.add(line);
  }

  public void addStations(String lineID, List<String> names) {
    var line = getLineByNumber(lineID);
    var toAdd = names.stream()
        .map(name -> new Station(name, line))
        .collect(Collectors.toList());
    stations.addAll(toAdd);
    line.addStations(toAdd);
  }

  public Line getLineByNumber(String number) {
    return lines.stream()
        .filter(line -> line.getNumber().equals(number))
        .findAny().orElse(null);
  }

  public void addTransition(Station station, Collection<Station> connectedStations) {
    transitions.add(new Transition(station, connectedStations));
  }

  public Station getStation(String name, String line) {
    return stations.stream()
        .filter(s -> s.getName().equals(name) && s.getLine().getNumber().equals(line))
        .findAny().orElse(null);
  }

  public String printTransition(Station from) {
    Transition t1 = transitions.stream()
        .filter(t -> t.getOrigin().equals(from))
        .findAny()
        .orElse(null);
    if (t1 == null) {
      return null;
    }

    var to = new ArrayList<>(t1.getToStations());
    StringBuilder builder = new StringBuilder();
    builder.append("----------------------------------------------------------------------");
    builder.append("\nПереход со станции ").append(from).append("\nна станции\n");
    Stream.iterate(1, n -> n <= to.size(), n -> n + 1)
        .forEach(n -> builder.append(n).append(". ").append(to.get(n - 1)).append("\n"));
    return builder.toString();
  }

}
