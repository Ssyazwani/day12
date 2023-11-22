package sg.iss.ssf.day12.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.iss.ssf.day12.demo.model.Generate;

@Controller
@RequestMapping(path={"/"})
public class GenerateRandController {
    
    @GetMapping()
    public String showRandomForm(Model m){
        Generate g = new Generate();
        m.addAttribute("generateObj", g);
        return "generate";

    }
    @GetMapping (path ="/generate")
    public String generate(@RequestParam Integer numberVal,Model m){
        this.randomizeNumber(m,numberVal.intValue());
        return "result";

    }

    private void randomizeNumber(Model m, int noOfGenerateNo){
        int maxGen = 31;
        String[]imgNumbers = new String[maxGen];

        if (noOfGenerateNo<1||noOfGenerateNo>maxGen-1){
            throw new NumberFormatException();
        }

        for(int x =0; x<maxGen;x++){
            if(x>0)
            imgNumbers[x] = "number" + x + ".jpg"; //construct the number filename and put it into an array
        }

        List<String>RandomNumbers = new ArrayList<>();
        Random rand = new Random();
        Set<Integer> RandNum = new HashSet<>();
        while(RandNum.size()<noOfGenerateNo){ //among 30 numbers
            int RandomNum = rand.nextInt();
            if (RandomNum > 0 && RandomNum <= 30) {
                RandNum.add(RandomNum);
            } // check it is not null and not zero int RandomNum = Math.abs(rand.nextInt()) % maxGen;

        }

        Integer currElem= null; //initiate currElem
        for(Iterator iter = RandNum.iterator(); iter.hasNext();){
            currElem = (Integer)iter.next();
            System.out.println(currElem);
            if(currElem != null)
                RandomNumbers.add(imgNumbers[currElem]);  // linking the picture to the number
        }

        m.addAttribute("noOfGenerateNo", noOfGenerateNo);
        m.addAttribute("RandomNumbers",RandomNumbers);
        System.out.println(" >>> " + RandomNumbers);




        

    





    }

    //"${generateObj.*numberVal}" for indexhtml

    

}
