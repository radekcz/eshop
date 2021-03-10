package cz.rk.eshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.rk.eshop.entity.Watch;
import cz.rk.eshop.exception.WatchNotFoundException;
import cz.rk.eshop.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * Watch controller - rest api
 */
@RestController
@RequestMapping("/eshop")
public class WatchController {

    @Autowired
    private WatchRepository watchRepository;


    WatchController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @PostMapping("/watches")
    @ResponseStatus(HttpStatus.CREATED)
    Watch newWatch(@Valid @RequestBody Watch newWatch) throws JsonProcessingException {
        // save to database
        return watchRepository.save(newWatch);
    }

    @GetMapping("/watches/{id}")
    Watch one(@PathVariable Long id) {
        return watchRepository.findById(id)
                .orElseThrow(() -> new WatchNotFoundException(id));
    }

    @PutMapping("/watches/{id}")
    Watch replaceWatch(@RequestBody Watch updatedWatch, @PathVariable Long id) throws JsonProcessingException {
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
        watchRepository.deleteById(id);
    }


//    @InitBinder("watch")
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Watch.class, new WatchEditor());
//    }

}
