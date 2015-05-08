
package cs4311.finalreview;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class CS4311FinalReview {

    public static void main(String[] args) {
        
        
    }
    
    // Remote observer interface
    interface RemoteOvserver extends Remote {
        
        void update(Object observalbe, Object updateMessage) throws RemoteException;
    }
    
    // RMI Client - implements interface
    class RmiClient extends UnicastRemoteObject implements RemoteObserver {
        
        protected RmiClient() throws RemoteException {
            super();
        }
        
        private static final long serialVersionID = 1L;
        
        public static void main(String[] args) {
            if (System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());
            
            try {
                RmiService remoteService = (RMIService) Naming.lookup("//localhost:9999/RmiService");
                RmiClient client = new RmiClient();
                remoteService.addObserver(client);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        @Override
        public void update(Object observalbe, Object updateMsg) throws RemoteException {
            System.out.println("got message: " + updateMsg);
        }
    }
    
    // RMI Server - the observable
    class RmiServer extends Observable implements RmiService {
        
        private class WrappedObserver implements Observer, Serializable {
            private static final long serialVersionUID = 1L;
            private RemoteObserver ro = null;
            
            public WrappedObserver(RemoteObserver ro) {
                this.ro = ro;
            }
            @Override
            public void update(Observable o, Object arg) {
                try {
                    ro.update(o.toString(), arg);
                }
                catch (RemoteException e) {
                    System.out.println("Remote exception removing observer: " + this);
                    o.deleteObserver(this);
                }
            }
        }
        
        @Override
        public void addObserver(RemoteObserver o) throws RemoteException {
            WrappedObserver mo = new WrappedObserver(o);
            addObserver(mo);
            System.out.println("Added observer: " + no);
        }
        
    }
    
}
