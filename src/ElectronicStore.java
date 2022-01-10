//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.ArrayList;

public class ElectronicStore {
  // Initializing attributes
  private final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private String name;
  private int curProducts;
  private int numSales;
  private double revenue;
  private double pricePerSale;
  private double cartTotal;
  private int numAvailableProduct;
  private int numCartProduct;
  private Product[] stock; //Array to hold all products
  private ArrayList<Product> availableStock; // Array to hold available stock products
  private ArrayList<Product> currentCart; // Array to hold products added to cart
  private Product[] popularList; // Array to hold top 3 popular products

  // Constructor
  public ElectronicStore(String initName) {
    name = initName;
    curProducts = 0;
    numSales = 0;
    revenue = 0.00;
    pricePerSale = 0.00;
    cartTotal = 0.00;
    stock = new Product[MAX_PRODUCTS];
    availableStock = new ArrayList<>();
    currentCart = new ArrayList<>();
    popularList = new Product[3];
  }

  // Get Methods
  public String getName() {
    return name;
  }
  public int getCurProducts() {
    return curProducts;
  }
  public int getNumSales() {
    return numSales;
  }
  public double getRevenue() {
    return revenue;
  }
  public double getPricePerSale() {
    return pricePerSale;
  }
  public double getCartTotal() {
    return cartTotal;
  }
  public ArrayList<Product> getAvailableStock() {
    // iterate though stock and check to see stock quantity of each item
    // if stock quantity is available, append to available stock list
    for (int i = 0; i<curProducts; i++) {
      if (stock[i].getStockQuantity() > 0)
        availableStock.add(stock[i]);
    }
    return availableStock;
  }
  public ArrayList<Product> getCurrentCart() {
    return currentCart;
  }
  public Product[] getPopularList() {
    return popularList();
  }

  // Adds a product and returns true if there is space in the array, returns false otherwise
  // From Base Code
  public boolean addProduct(Product newProduct) {
    if (curProducts < MAX_PRODUCTS) {
      stock[curProducts] = newProduct;
      curProducts++;
      return true;
    }
    return false;
  }
  // Method for 'Add to Cart' Button
  public void addToCart(Product selectedProduct) {
    // adds selected item in event handler to current cart
    currentCart.add(selectedProduct);
    // adds selected item's price in event handler to cart total
    cartTotal += selectedProduct.getPrice();
    // keeps track to the stock of that item
    numAvailableProduct -= 1;
    numCartProduct += 1;
  }
  // Method for 'Remove from Cart' Button
  public void removeFromCart(Product selectedProduct) {
    // removes selected item in event handler from current cart
    currentCart.remove(selectedProduct);
    // subtracts selected item's price in event handler from cart total
    if (selectedProduct.getPrice()<=cartTotal)
      cartTotal -= selectedProduct.getPrice();
    // keeps track to the stock of that item
    numAvailableProduct += 1;
    numCartProduct -= 1;
  }
  // Method for 'Complete Sale' Button
  public void completeSale() {
    // iterate through the currentCart and add each item's price to increase the revenue
    if (currentCart != null) {
      for (Product item: currentCart) {
        revenue += item.getPrice();
      }
      // increase number of sale by one (each time method is called)
      if (cartTotal != 0)
        numSales += 1;
      // calculate the average price per sale
      pricePerSale = revenue / numSales;
      // reset current cart and cart totals
      cartTotal = 0.00;
      currentCart.clear();
    }
  }
  // Method for 'Reset Store' Button
  public void resetStore() {
    // Reset all numbers so that textfields get updated back to 0
    numSales = 0;
    revenue = 0.00;
    pricePerSale = 0.00;
    cartTotal = 0.00;
    currentCart.clear();
  }
  // Generating 3 top popular products
  public Product[] popularList() {
    // Cannot figure out sorting method.. Sorry!
    // Change the first 3 values of a new Product Array with available items
    for (int i = 0; i<3; i++) {
      popularList[i] = availableStock.get(i);
    }
    return popularList;
  }

  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 