package hu.flowacademy.eredmenyek;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {
    private List<Match> matches = new ArrayList<>();

    public List<Match> getMatches() {
        return matches;
    }

    public Match getOneMatch(Integer id) {
        return matches.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public Match postMatch(Match match) {
        match.setId(matches.stream().map(p -> p.getId()).max(Integer::compare).orElse(0)+1);
        matches.add(match);
        return match;
    }

    public void removeMatch(Integer id) {
        matches.removeIf(m -> m.getId().equals(id));
    }
}
