package practice;

interface IReport {
    String Generate();
}
abstract class ReportDecorator implements IReport{
    IReport iReport;

    public ReportDecorator(IReport iReport) {
        this.iReport = iReport;
    }

    public String Generate() {
        return "this basic";
    }
}
class SalesReport implements IReport{
    private IReport report;
    public SalesReport(IReport report){
        this.report = report;
    }

    public SalesReport(){

    }
    public String Generate() {
        return "this sale report";
    }
}
class UserReport implements  IReport{
    private IReport report;
    public UserReport(IReport report){
        this.report = report;
    }

    public String Generate() {
        return "this user report";
    }
}

class DateFilterDecorator extends ReportDecorator{
    int data;

    public DateFilterDecorator(IReport iReport, int data) {
        super(iReport);
        this.data = data;
    }

    public String Generate() {
        return "data filtered";
    }
}

class SortingDecorator extends ReportDecorator{
    String sort;

    public SortingDecorator(IReport iReport, String sort) {
        super(iReport);
        this.sort = sort;
    }
    public String Generate(){
        return "data sorted";
    }
}

class CsvExportDecorator extends ReportDecorator{
    public CsvExportDecorator(IReport iReport) {
        super(iReport);
    }
    public String Generate(){
        return "data exported to csv";
    }
}

class PdfExportDecorator extends ReportDecorator{
    public PdfExportDecorator(IReport iReport) {
        super(iReport);
    }
    public String Generate(){
        return "data exported to pdf";
    }
}
class Main2{
    public static void main(String[] args) {
        IReport iReport = new SalesReport();
        iReport = new DateFilterDecorator(iReport, 4);
        System.out.println(iReport.Generate());

        iReport = new SortingDecorator(iReport, "buble");
        System.out.println(iReport.Generate());

        iReport = new CsvExportDecorator(iReport);
        System.out.println(iReport.Generate());

        iReport = new PdfExportDecorator(iReport);
        System.out.println(iReport.Generate());
    }
}