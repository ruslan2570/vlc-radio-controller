package ruslan.vlcradiocontroller.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {

    @Autowired
    StreamService streamService;

    @Autowired
    VlcService vlcService;



    public void next() throws Exception{
        var writer = streamService.getWriter();
        writer.write("next");
        writer.newLine();
        writer.flush();
    }

    public void prev() throws Exception{
        var writer = streamService.getWriter();
        writer.write("prev");
        writer.newLine();
        writer.flush();
    }

    public HashMap<String, String> info() throws Exception{
        var writer = streamService.getWriter();
        var reader = streamService.getReader();
        writer.write("info");
        writer.newLine();
        writer.flush();

        HashMap<String, String> map = new HashMap<>();

        String line;
        while(!(line = reader.readLine()).contains("end of stream info")){
            String[] split = line.split(":");
            if(split.length != 2)
                continue;
            
            if(split[0].contains("album"))
                map.put("album", split[1]);
            if(split[0].contains("title"))
                map.put("title", split[1]);
            if(split[0].contains("artist"))
                map.put("artist", split[1]);            
            if(split[0].contains("filename"))
                map.put("filename", split[1]);            
            
        }
        return map;
    }

    public void restart() throws Exception{
        vlcService.restart();
    }

}
