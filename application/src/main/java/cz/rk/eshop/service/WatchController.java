package cz.rk.eshop.service;

import cz.rk.eshop.entity.Watch;
import cz.rk.eshop.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Watch rest controller
 */
@RestController
public class WatchController {

    @Autowired
    private WatchRepository watchRepository;

    WatchController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @PostMapping("/watches")
    Watch newWatch(@RequestBody Watch newWatch) {
        // TODO: validate
        // save to database
        watchRepository.save(newWatch);
        return newWatch;
    }

    @GetMapping("/watches/{id}")
    Watch one(Long id) {
        return new Watch(Long.valueOf(123), "Longines");
    }

}
