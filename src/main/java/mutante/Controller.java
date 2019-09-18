package mutante;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/mutant",
            consumes = "application/json")
    
    public ResponseEntity<?> esMutante(@RequestBody Mutante adn) throws Exception {
        int count=0;
        int resp = 0;
        int a = 0, b = 1;
        try{    
            //Para las filas (Horizontal)
            for (String adn1 : adn.getAdn()) {
                if (resp<2) {
                    while (b < adn1.length() && count<4) {
                        if (adn1.charAt(a) == adn1.charAt(b)) {
                            count++;
                            a++;b++;
                            if(count==3){
                                resp++;
                                count=0;
                                a = a - 2;
                                b = b - 2;
                            }
                        } else {
                            a++;b++;
                            count=0;
                        }
                    }
                    count=0;
                    a=0;
                    b=1;
                }
            }
            //Para las columnas (Vertical)
            for(int c = 0;c < adn.getAdn().length;c++){
                if(resp<2){
                    while(b<adn.getAdn()[c].length() && count<4){
                        if(adn.getAdn()[a].charAt(c)==adn.getAdn()[b].charAt(c)){
                            count++;
                            b++;
                            if(count==3){
                                resp++;
                                count=0;
                                b = b - 2;
                            }
                        }else{
                            b++;
                            count=0;
                        }
                    }
                    count=0;
                    a=0;
                    b=1;
                }
            }
        
            //Para las diagonales (Oblicuo)
            for (String adn1 : adn.getAdn()) {
                if (resp<2) {
                    while (b < adn1.length() && count<4) {
                        if(adn.getAdn()[a].charAt(a)==adn.getAdn()[b].charAt(b)){
                            count++;
                            a++;
                            b++;
                            if(count==3){
                                resp++;
                                count=0;
                                a = a - 2;
                                b = b - 2;
                            }
                        }else{
                            count=0;
                            a++;
                            b++;
                        }
                    }
                    count=0;
                    a=0;
                    b=1;
                }
            }
            if (resp>1){
                return new ResponseEntity<>("Mutante", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No-Mutante", HttpStatus.FORBIDDEN);
            }  
        }catch (Exception ex){
            return new ResponseEntity<>("Exception: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/stats",
            consumes = "application/json",
            produces = "application/json")
    
    public @ResponseBody Result contador(@RequestBody Result adn) throws Exception {
        Result res = new Result();
        res.setCount_human_dna(40);
        res.setCount_mutant_dna(100);
        res.setRatio(0.4);
        return res;
    }
}

