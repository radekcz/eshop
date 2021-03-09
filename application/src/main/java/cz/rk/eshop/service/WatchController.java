package cz.rk.eshop.service;

import cz.rk.eshop.entity.Watch;
import cz.rk.eshop.repository.WatchRepository;
import org.springframework.web.bind.annotation.*;


/**
 * Watch rest controller
 */
@RestController
public class WatchController {

//    private WatchRepository watchRepository;
//
//    WatchController(WatchRepository watchRepository) {
//        this.watchRepository = watchRepository;
//    }

    @PostMapping("/watches")
    Watch newWatch(Watch newWatch) {
        // save to database
        //watchRepository.save(newWatch);
        return  null;
    }

    @GetMapping("/watches/{id}")
    @ResponseBody
    Watch one(Long id) {
        return new Watch(123, "Longines");
    }

}
