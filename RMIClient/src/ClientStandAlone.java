import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientStandAlone
{
    public static void main(String[] args)
    {
        int portNumber;
        Scanner scanner = new Scanner(System.in);
        String serviceName;

        if(args.length<1)
        {
            System.out.println("port number: ");
            portNumber = Integer.parseInt(scanner.nextLine());
        }
        else    portNumber = Integer.parseInt(args[0]);

        if(args.length<2)
        {
            System.out.println("service name: ");
            serviceName = scanner.nextLine();
        }
        else serviceName = args[1];

        try
        {
            CalculatorInterface calculator = (CalculatorInterface) Naming.lookup("rmi://localhost:" + portNumber + "/" + serviceName);
            String line;

            while(true)
            {
                line = scanner.nextLine();
                String command[] = line.split("\\s+");

                if(command[1].equals("+"))
                    System.out.println(calculator.add(Double.parseDouble(command[0]), Double.parseDouble(command[2])));
                else if(command[1].equals("-"))
                    System.out.println(calculator.sub(Double.parseDouble(command[0]), Double.parseDouble(command[2])));
                else if(command[1].equals("*"))
                    System.out.println(calculator.mul(Double.parseDouble(command[0]), Double.parseDouble(command[2])));
                else if(command[1].equals("/"))
                    System.out.println(calculator.div(Double.parseDouble(command[0]), Double.parseDouble(command[2])));
                else
                    System.out.println("Invalid command");
            }
        }
        catch (NotBoundException e) { e.printStackTrace(); }

        catch (MalformedURLException e) { e.printStackTrace(); }

        catch (RemoteException e) { e.printStackTrace(); }

        catch(java.lang.ArithmeticException e) { e.printStackTrace(); }
    }
}
