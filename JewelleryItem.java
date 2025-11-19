public class JewelleryItem {
    public int id;
    public String name;
    public String type;       // Ring, Necklace, etc.
    public String material;   // Gold, Silver, etc.
    public double weight;     // in grams
    public double price;      // per piece
    public int stock;         // quantity in stock

    public JewelleryItem(int id, String name, String type, String material,
                         double weight, double price, int stock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.material = material;
        this.weight = weight;
        this.price = price;
        this.stock = stock;
    }

    public JewelleryItem(String name, String type, String material,
                         double weight, double price, int stock) {
        this(0, name, type, material, weight, price, stock);
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + type + " | " +
               material + " | " + weight + "g | ₹" + price +
               " | stock: " + stock;
    }
}