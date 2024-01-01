interface  Salad{
    String getdescription();
    int cost();
}

abstract class AddDecorator implements Salad{
    public abstract String getdescription();
    public abstract int cost();
}


class VegSalad implements Salad {

    public String getdescription(){
      return "Veg Salad of fruits / vegetable";
    }

    public int cost(){
        return 100;
    }

}

class Jalapeno extends AddDecorator{

    Salad salad;

    public Jalapeno(Salad s) {
        salad = s;    }
    public String getdescription(){
        return salad.getdescription()+", Jalapeno";
    }
    public int cost(){
        return salad.cost()+10;
    }
}

class Peanuts extends AddDecorator{

    Salad salad;

    public Peanuts(Salad s) {
        salad = s;    }
    public String getdescription(){
        return salad.getdescription()+" ,Peanuts";
    }
    public int cost(){
        return salad.cost()+10;
    }
}
public class decoratorLLD {

    public static void main(String[] args) {
        Salad order=new Peanuts(new Jalapeno(new VegSalad()));
        System.out.println(order.getdescription());
        System.out.println("Cost of the order "+ order.cost());
    }
}