package hu.flowacademy.eredmenyek;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> squad = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public void setSquad(List<Player> squad) {
        this.squad = squad;
    }
}
