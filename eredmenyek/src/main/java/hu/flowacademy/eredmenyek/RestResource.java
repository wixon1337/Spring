package hu.flowacademy.eredmenyek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestResource {

    @Autowired
    private MatchService matchService;

    @GetMapping("/api/foci/merkozesek")
    public List<Match> getMatches() {
        return matchService.getMatches();
    }

    @GetMapping("/api/foci/merkozes/{id}")
    public Match getOneMatch(@PathVariable("id") Integer id) {
        return matchService.getOneMatch(id);
    }

    @PostMapping("/api/foci/merkozes")
    public Match postMatch(@RequestBody Match match) {
        return matchService.postMatch(match);
    }

    @DeleteMapping("/api/foci/merkozes/{id}")
    public void removeMatch(@PathVariable Integer id) {
        matchService.removeMatch(id);
    }

/*    @GetMapping("/api/foci/csapatok")
    @GetMapping("/api/foci/csapat/{id}")
    @GetMapping("/api/foci/csapat/{id}/eredmenyek")
    @GetMapping("/api/foci/csapat/{id}/meccsek")
    @GetMapping("/api/foci/csapat/{id}/atigazolasok")
    @GetMapping("/api/foci/jatekosok")
    @GetMapping("/api/foci/jatekos/{id}")
    @PostMapping("/api/meccseim/{id}")
    @GetMapping("/api/meccseim")
    @DeleteMapping("/api/foci/{id}")*/
}
