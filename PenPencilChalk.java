interface WritingTools{
    
   public void write();
}

interface Eraser{

    public  void erase();
}
class Pencil implements WritingTools , Eraser{
 
  @Override
  public void write() {
      // logic   
  }

  @Override
  public void erase(){
    //logic 
  }
}

class Chalk implements WritingTools , Eraser{
 
  @Override
  public void write() {
      // logic   
  }

  @Override
  public void erase(){
    //logic 
  }
}

class Pen implements WritingTools{
    
    @Override
   public void write() {
     //logic
      
    }

}
public class PenPencilChalk{
    public static void main(String[] args){
    
    }
}