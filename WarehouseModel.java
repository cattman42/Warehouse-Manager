/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehousemanager;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Cattc
 */
public class WarehouseModel {
    private Map<String,Integer> data = new TreeMap<String,Integer>();
    
    public WarehouseModel() {
        Scanner input = null;
        
        try {
            input = new Scanner(new File("stock.txt"));
            while(input.hasNext()) {
                String variety = input.next();
                int quantity = input.nextInt();
                
                data.put(variety, quantity);
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void save() {
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter("stock.txt");
            Set<String> keys = data.keySet();
            Iterator<String> iter = keys.iterator();
            while(iter.hasNext()) {
                String variety = iter.next();
                int amount = data.get(variety);
                outFile.println(variety + " " + amount);
            }
            outFile.close();
        } catch(Exception ex) {
            
        }
    }
    
    public Collection<String> getAllVarieties() { return data.keySet(); }
    
    public void addNewVariety(String name) { data.put(name, 0); }
    
    public int getStock(String variety) { return data.get(variety); }
    
    public int addStock(String variety,int amount) {
        int currentStock = data.get(variety);
        currentStock += amount;
        data.put(variety, currentStock);
        return currentStock;
    }
    
    public int removeStock(String variety,int amount) {
        int currentStock = data.get(variety);
        currentStock -= amount;
        data.put(variety, currentStock);
        return currentStock;
    }
}
