package cz.rk.eshop.service;

import cz.rk.eshop.entity.Watch;
import org.springframework.web.bind.annotation.*;

/**
 * Watch rest controller
 */
@RestController
public class WatchController {

    @PostMapping("/watches")
    Watch newWatch(Watch newWatch) {
        // save to database - return repository.save(newWatch);
        return  null;
    }

    @GetMapping("/watches/{id}")
    @ResponseBody
    Watch one(Long id) {
        return new Watch(123, "Longines");
    }

}
