package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FirstRemoteImpl extends UnicastRemoteObject implements FirstRemote{

    @Override
    public String sayHello() throws RemoteException {
        return "Шалом";
    }

    protected FirstRemoteImpl() throws RemoteException {}

    public static void main(String[] args) {
        try {
            FirstRemote service = new FirstRemoteImpl();
            Naming.rebind("И тебе не хворать", service);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
