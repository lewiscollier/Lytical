import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Stock {
	
	
	
	String type;
	
	double weight, total, price, monthlyProfit;

	public Stock(String type, double weight, double total, double profit) {
		this.type = type;
		this.weight = weight;
		this.total = total;
		this.monthlyProfit = profit;
	}
	
	public Stock(String type, double weight, double total){
		this.type = type;
		this.weight = weight;
		this.total = total;
	}
	
	public Stock(){
		
	}
	
    public String getType(){
		return type;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public void updateWeight(double change){
		this.weight += change;
	}
	
	public void updateTotal(double change){
		this.total += change;
	}
	
	public double getTotal(){
		return total;
	}
	
	public double getPrice() {
		return (total/weight)*1000;
	}
	
	public double getMonthlyProfit(){
		
		return monthlyProfit;
	}
	
	public Stock[] readStock() throws IOException{
		
		Stock[] stock = new Stock[85];
		
		File file = new File("C:/Users/lewis/stock.txt");
		
		    if (!file.exists()) {
		    	file.createNewFile();
			  }
		
		String tempType;
		double tempWeight;
		double tempPrice;
		double tempMonthlyProfit; 
		
		String tempLine;
		
		StringTokenizer st;
		
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    
	    for(int i =0; i < 84;i++){
	    
	    tempLine = br.readLine();
	    st = new StringTokenizer(tempLine, ",");
	    tempType = st.nextToken();
	    tempWeight = Double.parseDouble(st.nextToken());
	    tempPrice = Double.parseDouble(st.nextToken());
	    tempMonthlyProfit = Double.parseDouble(st.nextToken());
	    
	    stock[i] = new Stock(tempType,tempWeight,tempPrice,tempMonthlyProfit);
	    
	    }
	    
	    br.close();
	    
	    return stock;
	}

	public void updateStock(String type, double weight, double totalWorth, double monthlyProfit) throws IOException {
		
		Stock[] stock = readStock(); 
		File file = new File("C:/Users/lewis/stock.txt");

	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
	    	    
		for(int i = 0; i < 84; i++){
			if(stock[i].getType().equals(type)){
				  stock[i].updateWeight(weight);
				  stock[i].updateTotal(totalWorth);
				  stock[i].updateMonthlyProfit(monthlyProfit);
			}
		}
		
		for(int i = 0; i < 84; i++){
			 bw.write(stock[i].getType()+","+stock[i].getWeight()+","+stock[i].getTotal()+","+stock[i].getMonthlyProfit());
			 bw.newLine();
		}
		
		bw.close();
	}

	private void updateMonthlyProfit(double monthlyProfit) {
		this.monthlyProfit += monthlyProfit;
	}

	public void updateStock(String type, double weight, double totalWorth) throws IOException {
		
		Stock[] stock = readStock(); 
		File file = new File("C:/Users/lewis/stock.txt");

	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
	    	    
		for(int i = 0; i < 84; i++){
			if(stock[i].getType().equals(type)){
				  stock[i].updateWeight(weight);
				  stock[i].updateTotal(totalWorth);
			}
		}
		
		for(int i = 0; i < 84; i++){
			 bw.write(stock[i].getType()+","+stock[i].getWeight()+","+stock[i].getTotal()+","+stock[i].getMonthlyProfit());
			 bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}
