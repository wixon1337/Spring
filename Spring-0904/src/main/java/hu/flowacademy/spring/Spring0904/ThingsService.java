package hu.flowacademy.spring.Spring0904;

import java.util.HashMap;

public class ThingsService {
    private HashMap<String, Thing> map;

    // create(thing), update(thing), findAll(), findOne(id), delete(id)

    public Thing create(Thing thing) {
        if (map.size() == 0) {
            this.map.put("1", thing);
            return thing;
        } else {
            // TODO
            return null;
        }
    }

    public Thing update(Thing thing) {
        // TODO
        return null;
    }

    public HashMap<String, Thing> findAll() {
        return map;
    }

    public Thing findOne(int id) {
        // TODO
        return null;
    }

    public Thing delete(int id) {
        // TODO
        return null;
    }

}
