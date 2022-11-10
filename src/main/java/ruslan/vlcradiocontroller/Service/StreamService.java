package ruslan.vlcradiocontroller.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class StreamService{
    private BufferedReader reader;
    private BufferedWriter writer;

    public void setReader(BufferedReader reader){
        this.reader = reader;
    }

    public void setWriter(BufferedWriter writer){
        this.writer = writer;
    }

    public BufferedReader getReader(){
        return reader;
    }

    public BufferedWriter getWriter(){
        return writer;
    }    
}
