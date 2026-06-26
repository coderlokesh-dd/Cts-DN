public class FactoryTest {

    public static void main(String[] args) {

        DocumentFactory wordFactory = new WordFactory();
        Document doc1 = wordFactory.createDocument();
        doc1.open();

        DocumentFactory pdfFactory = new PdfFactory();
        Document doc2 = pdfFactory.createDocument();
        doc2.open();

        DocumentFactory excelFactory = new ExcelFactory();
        Document doc3 = excelFactory.createDocument();
        doc3.open();
    }
}