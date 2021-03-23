package model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "stations")
@ToString(exclude = "stations")
public class Line implements Comparable<Line> {

  @NonNull
  private String name;
  @NonNull
  private String number;
  private List<Station> stations = new ArrayList<>();

  public void addStations(List<Station> toAdd) {
    stations.addAll(toAdd);
  }

  @Override
  public int compareTo(Line o) {
    return number.compareTo(o.getNumber());
  }

}
