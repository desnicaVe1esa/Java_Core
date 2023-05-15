package rmi;

import java.rmi.Naming;


public class FirstRemoteClient {
    public static void main(String[] args) {
        new FirstRemoteClient().go();
    }

    public void go() {
        try {
            FirstRemote service = (FirstRemote) Naming.lookup("rmi://127.0.0.1/FirstRemote");
            String s = service.sayHello();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
