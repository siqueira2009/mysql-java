// Classe do produto
public class Product {
    private int id; // Atributo de ID
    private String name; // Atributo de nome
    private double price; // Atributo de preço
    
    // Cria um construtor com nome e preço apenas (ID vem do banco)
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters e setters (já que os atributos são privados)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}