package com.cligest.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CligestRestController {

    @GetMapping("/numeroepisodio")
    public NumeroEpisodio numeroEpisodio(@RequestParam(value = "entidade", defaultValue = "415") long entidade,
                                         @RequestParam(value = "data", defaultValue = "2020-05-25") String data) {
        return new NumeroEpisodio(entidade, data);
    }

    @GetMapping("/fe2ne")
    public Fe2Ne fe2ne(@RequestParam(value = "ne", defaultValue = "0") long ne,
                       @RequestParam(value = "fe", defaultValue = "0") String fe,
                       @RequestParam(value = "memid", defaultValue = "0") String memid,
                       @RequestParam(value = "user", defaultValue = "0") String user,
                       @RequestParam(value = "data", defaultValue = "2020-05-25") String data) {
        return new Fe2Ne(ne, fe, memid, user,  data);
    }

    @GetMapping("/memidreader")
    public MemIDReader memIDReader(@RequestParam(value = "memid", defaultValue = "0") String memid)
                       {
        return new MemIDReader(memid);
    }
}
