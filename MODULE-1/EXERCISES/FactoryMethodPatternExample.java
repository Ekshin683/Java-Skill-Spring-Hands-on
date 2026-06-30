// package MODULE-1.EXERCISES;
public interface Document {
    void open();
}

// WordDocument.java
public class WordDocument implements Document {
    public void open() { System.out.println("Opening a Word document..."); }
}

// PdfDocument.java
public class PdfDocument implements Document {
    public void open() { System.out.println("Opening a PDF document..."); }
}

// ExcelDocument.java
public class ExcelDocument implements Document {
    public void open() { System.out.println("Opening an Excel document..."); }
}

// DocumentFactory.java
public abstract class DocumentFactory {
    public abstract Document createDocument();
}

// WordDocumentFactory.java
public class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

// PdfDocumentFactory.java
public class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

// ExcelDocumentFactory.java
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
