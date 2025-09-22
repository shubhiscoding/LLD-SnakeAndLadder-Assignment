public class Cell {
    private Transport transport;
    
    public Cell() {
        this.transport = null;
    }
    
    public Transport getTransport() {
        return transport;
    }
    
    public void setTransport(Transport transport) {
        this.transport = transport;
    }
    
    public boolean hasTransport() {
        return transport != null;
    }
}
