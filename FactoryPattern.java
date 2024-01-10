interface Shape{
    void draw();
}

class Circle implements Shape{

    @Override
    public void draw(){
     System.out.println("I am a circle");
    }
}

class Rectangle implements Shape{
   
    @Override
    public void draw(){
    System.out.println("I am a Rectangle");
    }
}

class Factory{

    static Shape CreateObject(String shapeType){
       
        if("Rectangle".equals(shapeType)){
            return new Rectangle();

        }
       else if("Circle".equals(shapeType)){
        return new Circle();
       }
       
        return null;
        
    }
}
public class FactoryPattern {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Shape shapeObj =factory.CreateObject("Circle");
        shapeObj.draw();

        Shape shapeObj2 =factory.CreateObject("Rectangle");
        shapeObj2.draw();
        
    }
}
