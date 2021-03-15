package cz.rk.eshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.rk.eshop.entity.Watch;
import cz.rk.eshop.exception.WatchNotFoundException;
import cz.rk.eshop.repository.WatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * Watch controller - rest api
 */
@RestController
@RequestMapping("/eshop/v1")
public class WatchController {

    @Autowired
    private WatchRepository watchRepository;

    Logger logger = LoggerFactory.getLogger(WatchController.class);


    /**
     * constructor
     * @param watchRepository
     */
    WatchController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @PostMapping("/watches")
    @ResponseStatus(HttpStatus.CREATED)
    Watch newWatch(@Valid @RequestBody Watch newWatch) {
        // log
        logger.info("Post request; parameters: " + newWatch);
        // save to database
        return watchRepository.save(newWatch);
    }

    @GetMapping("/watches/{id}")
    Watch one(@PathVariable Long id) {
        // log
        logger.info("Get request; parameters: " + id);
        return watchRepository.findById(id)
                .orElseThrow(() -> new WatchNotFoundException(id));
    }

    @PutMapping("/watches/{id}")
    Watch replaceWatch(@Valid @RequestBody Watch updatedWatch, @PathVariable Long id) throws JsonProcessingException {
        // log
        logger.info("Put request; parameters: " + updatedWatch + "; " + id);
        return watchRepository.findById(id)
                .map(watch -> {
                    watch.setTitle(updatedWatch.getTitle());
                    watch.setDescription(updatedWatch.getDescription());
                    watch.setPrice(updatedWatch.getPrice());
                    watch.setFountain(updatedWatch.getFountain());
                    return watchRepository.save(watch);
                })
                .orElseGet(() -> {
                    updatedWatch.setId(id);
                    return watchRepository.save(updatedWatch);
                });
    }

    @DeleteMapping("/watches/{id}")
    void deleteWatch(@PathVariable Long id) {
        // log
        logger.info("Delete request; parameters: " + id);
        watchRepository.deleteById(id);
    }

}
