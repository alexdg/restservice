package com.cligest.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumeroEpisodioController {

    @GetMapping("/numeroepisodio")
    public NumeroEpisodio numeroEpisodio(@RequestParam(value = "entidade", defaultValue = "415") long entidade,
                                         @RequestParam(value = "data", defaultValue = "2020-05-25") String data
                                        ) {
        return new NumeroEpisodio(entidade, data);
    }

}
