package com.example.Testing.Controller;


import com.example.Testing.Entity.LogDetails;
import com.example.Testing.Repository.LogDetailsRepository;
import org.example.InsertIntoElasticsearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.Test;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")


@RequestMapping("/api/log-details")
public class LogDetailsController {
    @Autowired
    private LogDetailsRepository logDetailsRepository;



    @GetMapping
    public List<LogDetails> getAllLogDetails() {
        return logDetailsRepository.findAll();
    }

    @GetMapping("/{id}")
    public LogDetails getLogDetailsById(@PathVariable Long id) {
        return logDetailsRepository.findById(id).orElse(null);
    }

    @PostMapping("/change")
    public LogDetails insertToElasticsearch(@RequestBody LogDetails logDetails){
        String username =logDetails.getUsername();
        String neededLog = logDetails.getNeededLog();
        List<String> fieldsNeeded = logDetails.getFieldsNeeded();
        boolean includeTimestamp = false;
        boolean includeEventCode = false;
        boolean includeSourcename = false;
        boolean includeMessage = false;

        for (String field : fieldsNeeded) {
            if ("eventcode".equals(field)) {
                includeEventCode = true;
            }
            if ("sourcename".equals(field)) {
                includeSourcename = true;
            }
            if ("message".equals(field)) {
                includeMessage = true;
            }
            if("timegenerated".equals(field)){
                includeTimestamp=true;
            }
        }

        Test sample =new Test();
        Object[] result = sample.getArray(neededLog);
        try {
            InsertIntoElasticsearch.insertion(result,includeEventCode,includeSourcename,includeTimestamp,includeMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return logDetailsRepository.save(logDetails);

    }



    @PostMapping
    public LogDetails createLogDetails(@RequestBody LogDetails logDetails) {

        return logDetailsRepository.save(logDetails);
    }

    @PutMapping("/{id}")
    public LogDetails updateLogDetails(@PathVariable Long id, @RequestBody LogDetails updatedLogDetails) {
        updatedLogDetails.setId(id);
        return logDetailsRepository.save(updatedLogDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteLogDetails(@PathVariable Long id) {
        logDetailsRepository.deleteById(id);
    }
}
