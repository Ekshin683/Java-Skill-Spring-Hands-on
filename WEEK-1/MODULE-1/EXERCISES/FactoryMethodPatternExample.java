// package MODULE-1.EXERCISES;
public interface Document {
    void open();
}
public class WordDocument implements Document {
    public void open() { System.out.println("Opening a Word document..."); }
}
public class PdfDocument implements Document {
    public void open() { System.out.println("Opening a PDF document..."); }
}
public class ExcelDocument implements Document {
    public void open() { System.out.println("Opening an Excel document..."); }
}
public abstract class DocumentFactory {
    public abstract Document createDocument();
}
public class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}
public class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}
public class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new ExcelDocument(); }
}

public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
    }
}
