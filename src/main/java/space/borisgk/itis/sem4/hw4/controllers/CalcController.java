package space.borisgk.itis.sem4.hw4.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import space.borisgk.itis.sem4.hw4.services.CalcException;
import space.borisgk.itis.sem4.hw4.services.CalcService;

@Controller
@RequestMapping("/calc")
public class CalcController {
    @Autowired
    CalcService calcService;

    @RequestMapping()
    public ResponseEntity<String> calc(String exp) {
        if (exp == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/plain; charset=utf-8");

        String resp = "";
        Number result = Double.NaN;
        try {
            result = calcService.calculate(exp);
            resp = result.toString();
        }
        catch (CalcException e) {
            resp = "Exception: " + e.getMessage();
        }
        return new ResponseEntity<>(resp, responseHeaders, HttpStatus.OK);
    }
}
