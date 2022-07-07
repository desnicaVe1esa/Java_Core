package rmi;

import java.rmi.*;

public interface FirstRemote extends Remote {
    public String sayHello() throws RemoteException;
}
