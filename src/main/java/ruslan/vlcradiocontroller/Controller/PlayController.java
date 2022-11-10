package ruslan.vlcradiocontroller.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ruslan.vlcradiocontroller.Service.PlayService;

@RestController
@RequestMapping()
public class PlayController {

    @Autowired
    PlayService playService;

    @GetMapping("/next")
    public void next() throws Exception{
        playService.next();
    }

    @GetMapping("/prev")
    public void prev() throws Exception{
        playService.prev();
    }

    @GetMapping("/info")
    public Map<String, String> info(@RequestHeader(HttpHeaders.USER_AGENT) String userAgent) throws Exception{
        System.out.println(userAgent);
        return playService.info();
    }    

    @GetMapping("/restart")
    public void restart() throws Exception{
        playService.restart();
    }
}
