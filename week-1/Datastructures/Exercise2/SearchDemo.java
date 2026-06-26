public class SearchDemo {

    
    public static Product linearSearch(Product[] products, String name) {

        for (Product p : products) {
            if (p.productName.equalsIgnoreCase(name)) {
                return p;
            }
        }

        return null;
    }

    
    public static Product binarySearch(Product[] products, String name) {

        int low = 0;
        int high = products.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result = products[mid].productName.compareToIgnoreCase(name);

            if (result == 0)
                return products[mid];

            else if (result < 0)
                low = mid + 1;

            else
                high = mid - 1;
        }

        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Apple", "Fruits"),
                new Product(102, "Banana", "Fruits"),
                new Product(103, "Laptop", "Electronics"),
                new Product(104, "Mobile", "Electronics"),
                new Product(105, "Watch", "Accessories")
        };

        System.out.println("Linear Search");

        Product p1 = linearSearch(products, "Laptop");

        if (p1 != null)
            p1.display();
        else
            System.out.println("Product Not Found");

        System.out.println();

        System.out.println("Binary Search");

        Product p2 = binarySearch(products, "Mobile");

        if (p2 != null)
            p2.display();
        else
            System.out.println("Product Not Found");
    }
}