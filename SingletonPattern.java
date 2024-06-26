
class SingletonObject{
       
      //should have pvt constructor so that class cannot be instantiated
       private SingletonObject(){} 
       private static SingletonObject singleobj= new SingletonObject();

       static SingletonObject getObject(){
            return singleobj;
       }

       void helloMethod(){
        System.out.println("Hey Guys, Hows life?");
       }
}
//but this method doesn't work for multithreading as what if two threads try to create object at same time?
// So we can lock that part of code that if one is accessing it other cannot using mutex

public class SingletonPattern {
    public static void main(String[] args) {
        
        SingletonObject object= SingletonObject.getObject();

        object.helloMethod();

    }
}
