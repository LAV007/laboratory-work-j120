package ru.avalon.j120.order_accounting_system.write_read_to_from_file;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.Product;
import ru.avalon.j120.order_accounting_system.configuration.Configuration;

import java.io.*;
import java.util.ArrayList;

public class WriteReadProductToFromList {
    Configuration configuration = Configuration.getInitialisation();
    final String productsConfig = configuration.getProperties().getProperty("products");

    public WriteReadProductToFromList() throws IOException {
    }

    public void write(ArrayList<Product> products) throws IOException {
        File file = new File(productsConfig);

        if(!file.exists()) file.createNewFile();

        ArrayList<Product> context = read();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
            context = products;
            for (Product product : context) {
                bw.write(product.toString() + "\n");
            }
        }
    }

    public ArrayList<Product> read() throws IOException{
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(productsConfig))) {
            String line;
            while((line = br.readLine()) != null){
                String[] lin = line.split(";");
                Product product = new Product(/*articleNumber*/lin[0],/*name*/lin[1], /*color*/lin[2],
                            /*price*/Integer.parseInt(lin[3]),
                            /*stockBalance*/Integer.parseInt(lin[4]));
                products.add(product); //exception Couldn't read the product" + lin[0]
            }
            return products;
        }
    }
}