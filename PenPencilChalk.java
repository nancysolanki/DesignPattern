interface WritingTools{
    
   public static void write(){
        //logic to Write
    }
}

interface Eraser{
    public static void erase(){
        //logic to Erase
    }
}
class Pencil implements WritingTools , Eraser{

}
public class PenPencilChalk{
    public static void main(String[] args){
    WritingTools.write();
    }
}