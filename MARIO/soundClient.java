/**
anusha
 */
public class soundClient 
{
    private soundHandler h1=  new ConcreteHandler1();
    private soundHandler h2 = new ConcreteHandler2() ;
    private soundHandler h3 = new ConcreteHandler3() ;
    private soundHandler h4 = new ConcreteHandler4() ;
    private soundHandler h5 = new ConcreteHandler5() ;
    private soundHandler h6 = new ConcreteHandler6() ;
    
    public soundClient()
    {
         h1.setSuccessor(h2);
         h2.setSuccessor(h3);
         h3.setSuccessor(h4);
         h4.setSuccessor(h5);
         h5.setSuccessor(h6);
    }

    public soundHandler geth1()
    {
        return h1;
    }
    
   public void handleSound(String command)
   {
       h1.handleRequest(command);
    }
    
}
