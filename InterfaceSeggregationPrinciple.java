//its like interface shouldn't have methods of classes which are not related to each other and do not make interface fat
interface IDataStore {
    void add();

    void delete();

    void select();
    
}


//Another interface for cache as cache dont use these DataStore methods
interface ICache{
    void swap();

    void memory();

    void update();
}

public class InterfaceSeggregationPrinciple implements IDataStore {
    @Override
   public  void select(){

    }

    @Override
    public void add() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}

class ISP implements ICache{

    @Override
    public void swap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }

    @Override
    public void memory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'memory'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}