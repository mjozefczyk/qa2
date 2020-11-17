package pl.jsystems.qa.junit;

public class GamePlay {
    public String play(int number){
        if(number==0) throw new IllegalArgumentException("Number can not be " +number);
        if(number%3==0) return "ok";
        if(number%5==0) return " Notok";
        return String.valueOf(number);
    }
}
