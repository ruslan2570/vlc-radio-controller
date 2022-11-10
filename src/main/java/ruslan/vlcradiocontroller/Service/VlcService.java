package ruslan.vlcradiocontroller.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.util.Scanner;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class VlcService {

    @Autowired
    private static Logger LOG = Logger.getLogger(VlcService.class.getName());

    @Autowired
    StreamService streamService;

    Process proc;

    BufferedWriter writer;

    @PostConstruct
    public void init(){
        LOG.info("VLC starting");
        try {
            startProcess();
          } catch (Exception ex) {
            System.out.println("Эксперимент, очевидно, неудачный");
            ex.printStackTrace();
          }
    }

    public void restart() throws Exception{
      writer.close();
      startProcess();
    }

    public void startProcess() throws Exception {

        
        ProcessBuilder builder = new ProcessBuilder("bash", "/home/ruslan/vlc-radio.sh");
        builder.redirectErrorStream(true);
        proc = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        OutputStream output = proc.getOutputStream();
        Scanner scan = new Scanner(System.in);
    
        writer = new BufferedWriter(new OutputStreamWriter(output));

        streamService.setWriter(writer);
        streamService.setReader(reader);
    
        // Для отладки ввода и вывода
        // Thread thInput = new Thread() {
        //   @Override
        //   public void run() {
        //     try {
              
        //       while (scan.hasNextLine()) {
        //         String input = scan.nextLine();
        //         if (input.trim().equals("exit")) {
        //           writer.write("exit\n");
        //         } else {
        //           writer.write(input + "\n");
        //         }
        //         writer.flush();
        //       }
        //     } catch (Exception e) {
        //       LOG.info(this.getClass().getName() + " Сломался ):");
        //       e.printStackTrace();
        //     } finally {scan.close();}
        //   }
        // };
    
        // Thread thOutput = new Thread() {
        //   @Override
        //   public void run() {
        //     try {
        //       String line = reader.readLine();
        //       while (line != null && !line.trim().equals("--EOF--")) {
        //         System.out.println("Stdout: " + line);
        //         line = reader.readLine();
        //       }
        //       } catch (Exception e) {
        //       LOG.info(this.getClass().getName() + " Сломался ):");
        //       e.printStackTrace();
        //     }
        //   }
        // };
    
        // thInput.start();
        // thOutput.start();
        
      }    
}