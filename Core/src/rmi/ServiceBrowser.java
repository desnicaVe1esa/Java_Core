package rmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class ServiceBrowser {

    public static void main(String[] args) {
        new ServiceBrowser().buildGUI();
    }
    JPanel mainPanel;
    JComboBox serviceList;
    ServiceServer server;

    public void buildGUI() {

        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        Object[] services = getServicesList();

        frame.getContentPane().add(BorderLayout.NORTH, serviceList);

        serviceList.addActionListener(new ListListener());

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void loadService(Object serviceSelection) {
        try {
            Service svc = server.getService(serviceSelection);
            mainPanel.removeAll();
            mainPanel.add(svc.getGuiPanel());
            mainPanel.validate();
            mainPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[] getServicesList() {
        Object obj = null;
        Object[] services = null;
        try {
            obj = Naming.lookup("rmi://127.0.0.1/ServiceServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        server = (ServiceServer) obj;

        try {
            services = server.getServiceList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    class ListListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object selection = serviceList.getSelectedItem();
            loadService(selection);
        }
    }
}
