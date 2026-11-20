public class Main {
    public static void main(String[] args) {
        try {
            ProductDAO dao = new ProductDAO();

            dao.add("Rice 2kg", 320.00, 40);

            System.out.println("All products:");
            for (Product p : dao.getAll()) {
                System.out.println(p);
            }

            System.out.println("\nProduct #1: " + dao.getById(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}