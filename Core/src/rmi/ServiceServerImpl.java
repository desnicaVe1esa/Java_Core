package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {

    public static void main(String[] args) {
        try {
            Naming.rebind("ServiceServer", new ServiceServerImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    HashMap serviceList;

    public ServiceServerImpl() throws RemoteException{
        setUpService();
    }

    public void setUpService() {
        serviceList = new HashMap<>();
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Day of the Week Service", new DayOfTheWeekService());
        serviceList.put("Visual Music Service", new MiniMusicService());
    }

    @Override
    public Object[] getServiceList() throws RemoteException {
        System.out.println("In remote");
        return serviceList.keySet().toArray();
    }

    @Override
    public Service getService(Object serviceKey) throws RemoteException {
        Service theService = (Service) serviceList.get(serviceKey);
        return theService;
    }
}
