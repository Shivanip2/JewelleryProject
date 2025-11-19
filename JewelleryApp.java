import java.util.List;
import java.util.Scanner;

public class JewelleryApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        JewelleryDAO dao = new JewelleryDAO();

        while (true) {
            System.out.println("\n=== Jewellery Management System ===");
            System.out.println("1. Add Jewellery Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;

            int ch;
            try {
                ch = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (ch == 1) {
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Type (Ring/Necklace/etc): ");
                String type = sc.nextLine();

                System.out.print("Material (Gold/Silver/etc): ");
                String material = sc.nextLine();

                System.out.print("Weight (grams): ");
                double weight = Double.parseDouble(sc.nextLine());

                System.out.print("Price: ");
                double price = Double.parseDouble(sc.nextLine());

                System.out.print("Stock quantity: ");
                int stock = Integer.parseInt(sc.nextLine());

                JewelleryItem item = new JewelleryItem(name, type, material, weight, price, stock);
                dao.addItem(item);
                System.out.println("Added: " + item);
            }

            else if (ch == 2) {
                List<JewelleryItem> all = dao.getAll();
                if (all.isEmpty()) {
                    System.out.println("(no items)");
                } else {
                    all.forEach(System.out::println);
                }
            }

            else if (ch == 3) {
                System.out.print("Enter item id to update: ");
                int id = Integer.parseInt(sc.nextLine());
                JewelleryItem item = dao.findById(id);

                if (item == null) {
                    System.out.println("No item found with id " + id);
                    continue;
                }

                System.out.println("Current: " + item);

                System.out.print("New name (blank = keep same): ");
                String name = sc.nextLine();
                if (!name.isBlank()) item.name = name;

                System.out.print("New type (blank = keep same): ");
                String type = sc.nextLine();
                if (!type.isBlank()) item.type = type;

                System.out.print("New material (blank = keep same): ");
                String material = sc.nextLine();
                if (!material.isBlank()) item.material = material;

                System.out.print("New weight (blank = keep same): ");
                String w = sc.nextLine();
                if (!w.isBlank()) item.weight = Double.parseDouble(w);

                System.out.print("New price (blank = keep same): ");
                String p = sc.nextLine();
                if (!p.isBlank()) item.price = Double.parseDouble(p);

                System.out.print("New stock (blank = keep same): ");
                String s = sc.nextLine();
                if (!s.isBlank()) item.stock = Integer.parseInt(s);

                boolean ok = dao.updateItem(item);
                System.out.println(ok ? "Updated successfully." : "Update failed.");
            }

            else if (ch == 4) {
                System.out.print("Enter item id to delete: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Are you sure? (y/n): ");
                String confirm = sc.nextLine().trim().toLowerCase();
                if (!confirm.equals("y")) {
                    System.out.println("Delete cancelled.");
                    continue;
                }
                boolean ok = dao.deleteById(id);
                System.out.println(ok ? "Deleted successfully." : "No item deleted (id not found).");
            }

            else if (ch == 0) {
                System.out.println("Goodbye!");
                break;
            }

            else {
                System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}