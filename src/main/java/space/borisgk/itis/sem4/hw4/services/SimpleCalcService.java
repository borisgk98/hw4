package space.borisgk.itis.sem4.hw4.services;

import java.text.ParseException;

public class SimpleCalcService implements CalcService {
    @Override
    public Number calculate(String expression) throws CalcException {
        String[] s = expression.split(" ");
        if (s.length != 2) {
            throw new CalcException("Bad expression");
        }
        Double r = Double.NaN;
        try {
            r = Double.parseDouble(s[0]) + Double.parseDouble(s[1]);
        }
        catch (NumberFormatException e) {
            throw new CalcException("Bad expression");
        }
        return r;
    }
}
