import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args)
    {
        int portNumber;
        Scanner scanner = new Scanner(System.in);
        if(args.length<1)
        {
            System.out.println("port number: ");
            portNumber = scanner.nextInt();
        }
        else    portNumber = Integer.parseInt(args[0]);


        try
        {
            Registry registry = LocateRegistry.createRegistry(portNumber);
            registry.rebind("calculator", new CalculatorServant());
            System.out.println("Listening on port " + portNumber);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
