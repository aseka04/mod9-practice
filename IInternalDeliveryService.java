package practice;

interface IInternalDeliveryService{
    void DeliverOrder(String orderId);
    String GetDeliveryStatus(String orderId);
}
class InternalDeliveryService implements IInternalDeliveryService{

    public void DeliverOrder(String orderId) {
        System.out.println("Delivery order: "+orderId);
    }

    public String GetDeliveryStatus(String orderId) {
        return "Order status in process: "+ orderId;
    }
}

class ExternalLogisticsServiceA{
    void ShipItem(int itemId){
        System.out.println("ShipItem: "+itemId);
    }

    String TrackShipment(int shipmentId){
        return "TrackShipment: "+shipmentId;
    }
}
class ExternalLogisticsServiceB{
    void SendPackage(String packageInfo){
        System.out.println("SendPackage: "+packageInfo);
    }
    String CheckPackageStatus(String trackingCode){
        return "CheckPackageStatus: "+trackingCode;
    }
}
class LogisticsAdapterA implements IInternalDeliveryService{
    private ExternalLogisticsServiceA serviceA;

    public LogisticsAdapterA(ExternalLogisticsServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @Override
    public String toString() {
        return "LogisticsAdapterA{" +
                "serviceA=" + serviceA +
                '}';
    }

    public void DeliverOrder(String orderId) {
        int itemId = Integer.parseInt(orderId);
        serviceA.ShipItem(itemId);
    }

    public String GetDeliveryStatus(String orderId) {
        int shipmentId = Integer.parseInt(orderId);
        return serviceA.TrackShipment(shipmentId);
    }
}

class LogisticsAdapterB implements IInternalDeliveryService{
    private ExternalLogisticsServiceB serviceB;

    public LogisticsAdapterB(ExternalLogisticsServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void DeliverOrder(String orderId) {
        serviceB.SendPackage(orderId);
    }

    public String GetDeliveryStatus(String orderId) {
        return serviceB.CheckPackageStatus(orderId);
    }
}

class DeliveryServiceFactory{
    public static IInternalDeliveryService GetDeliveryStatus(String serviceType){
            switch (serviceType){
                case "service1":
                    return new LogisticsAdapterA(new ExternalLogisticsServiceA());
                default:
                    return new InternalDeliveryService();
            }

    }
}
class Main{
    public static void main(String[] args) {
        IInternalDeliveryService service = DeliveryServiceFactory.GetDeliveryStatus("service1");
        service.DeliverOrder("1254");
        System.out.println(service.GetDeliveryStatus("1254"));
    }
}