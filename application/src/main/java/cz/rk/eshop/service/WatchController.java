package cz.rk.eshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.rk.eshop.entity.Watch;
import cz.rk.eshop.exception.WatchNotFoundException;
import cz.rk.eshop.repository.WatchRepository;
import cz.rk.eshop.utils.WatchProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * Watch rest api controller
 */
@RestController
public class WatchController {

    @Autowired
    private WatchRepository watchRepository;


    WatchController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @PostMapping("/watches")
    @ResponseStatus(HttpStatus.CREATED)
    Watch newWatch(@RequestBody String watchPayload) throws JsonProcessingException {
        // process parameters
        Watch newWatch = processParameters(watchPayload);
        // save to database
        return watchRepository.save(newWatch);
    }

    @GetMapping("/watches/{id}")
    Watch one(@PathVariable Long id) {
        return watchRepository.findById(id)
                .orElseThrow(() -> new WatchNotFoundException(id));
    }

    @PutMapping("/watches/{id}")
    Watch replaceWatch(@RequestBody String watchPayload, @PathVariable Long id) throws JsonProcessingException {
        // process parameters
        Watch newWatch = processParameters(watchPayload);
        return watchRepository.findById(id)
                .map(watch -> {
                    watch.setTitle(newWatch.getTitle());
                    watch.setDescription(newWatch.getDescription());
                    watch.setPrice(newWatch.getPrice());
                    watch.setFountain(newWatch.getFountain());
                    return watchRepository.save(watch);
                })
                .orElseGet(() -> {
                    newWatch.setId(id);
                    return watchRepository.save(newWatch);
                });
    }

    @DeleteMapping("/watches/{id}")
    void deleteWatch(@PathVariable Long id) {
        watchRepository.deleteById(id);
    }

    /**
     * method processes input string parameters, default in JSON
     *
     * can be switch to process XML parameters
     *
     * @param watchPayload
     * @return
     */
    private Watch processParameters(String watchPayload) throws JsonProcessingException {
        return processProcessJSONParameters(watchPayload);
    }

    private Watch processProcessJSONParameters(String watchPayload) throws JsonProcessingException {
        return WatchProcessor.processInputJSON(watchPayload);
    }


//    @PostMapping("/watches")
//    Watch newWatch(@ResponseBody Watch newWatch) throws JsonProcessingException {
//        // save to database
//        watchRepository.save(newWatch);
//        return newWatch;
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Watch.class, new WatchEditor(objectMapper));
//    }

}
