import java.awt.Graphics;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.StringTokenizer;

public class Ticket {
	
	String date,server,name, address, reg;
	Stock[] stock;
	int metalCount;
	double totalAmount;
	String temp;
	
	public Ticket(String date, String server, String name, String address, String reg, double totalAmount, int metalCount, Stock[] stock){
		this.date = date;
		this.server = server;
		this.name = name;
		this.address = address;
		this.reg = reg;
		this.totalAmount = totalAmount;
		this.metalCount = metalCount;
		this.stock = stock;
	}
	
	public Ticket(String date, String server, String name, String address, String reg, String totalAmount){
		this.date = date;
		this.server = server;
		this.name = name;
		this.address = address;
		this.reg = reg;
		this.temp = totalAmount;
	}
	
	public Ticket(){
		
	}

	public void showRecent() {
		
	}

	public void print(String server, String name, String address, String reg, int count, Stock[] stock, String paymentMethod) {

		Print print = new Print();
		print.printTicket(server, name, address, reg, count, stock, paymentMethod);
	}
	
	public void saveTicket(String server, String name, String address, String reg, double totalAmount,int count, Stock[] stock, String paymentMethod) throws IOException{
		 File file = new File("C:/Users/lewis/invoice.txt");
		    if (!file.exists()) {
		    	file.createNewFile();
			  }
		    FileWriter fw = new FileWriter(file, true);
		    BufferedWriter bw = new BufferedWriter(fw);
	    	

		    bw.newLine();
		    bw.write(getCurrentDate()+","+server+","+name+","+address+","+reg+","+totalAmount+","+ count +",");
		    
		    for(int i = 0; i < count; i++){
		    	
			    bw.write(stock[i].getType()+","+stock[i].getWeight()+","+stock[i].getPrice());
			    if(i != count-1){
			    	bw.write(",");
			    }
		    }
		    
		    bw.close();
	}

	public Ticket[] readTickets() throws IOException{
		
		Ticket[] tickets = new Ticket[getTicketCount()];
		String tempLine;
		
		  File file = new File("C:/Users/lewis/invoice.txt");
		    if (!file.exists()) {
		    	file.createNewFile();
			  }		
		
		StringTokenizer st ;
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    
	    int i =0;
	    tempLine = br.readLine();
	    while(tempLine != null && i < getTicketCount()){
		    	
		    	st = new StringTokenizer(tempLine, ",");
		    	
		    	String date,server,name, address, reg;
		    	String type, weight, price;
		    	int metalCount;
		    	double totalAmount;
		    	int count = 0;
		    	
		    	date = st.nextToken();
		    	server = st.nextToken();
		    	name = st.nextToken();
		    	address = st.nextToken();
		    	reg = st.nextToken();
		    	totalAmount = Double.parseDouble(st.nextToken());
		    	metalCount = (int)Double.parseDouble(st.nextToken());
		    	Stock[] stock = new Stock[metalCount];
		    	for(int x =0; x<metalCount;x++){
		    		type = st.nextToken();
		    		weight = st.nextToken();
		    		price = st.nextToken();
		    		stock[x] = new Stock(type, Double.parseDouble(weight), (Double.parseDouble(price)/1000)*Double.parseDouble(weight));
		    	}
		    	
		    	tickets[i] = new Ticket(date,server,name,address,reg,totalAmount,metalCount,stock);
			    tempLine = br.readLine();

		    	i++;
	    }

	    br.close();
	    
	    return tickets;
	}
	
	public String getCurrentDate(){
		
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy/MM/dd --- hh:mm:ss");

	      System.out.println("Current Date: " + ft.format(dNow));
		return ft.format(dNow);

	}

	public String getDate(){
		return date;
	}
	
	public String getServer(){
		return server;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getReg(){
		return reg;
	}
	
	public int getMetalCount(){
		return metalCount;
	}
	
	public Stock[] getStock(){
		return stock;
	}

	public double getTotalAmount(){
		return totalAmount;
	}
	
	public int getTicketCount() throws IOException{
		Ticket[] tickets = new Ticket[10000];
		String tempLine;
		
		  File file = new File("C:/Users/lewis/invoice.txt");
		    if (!file.exists()) {
		    	file.createNewFile();
			  }		
		
		StringTokenizer st ;
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    
	    int i =0;
	    tempLine = br.readLine();
	    while(tempLine != null && i < 10000){
		    	
		    	st = new StringTokenizer(tempLine, ",");
			    tempLine = br.readLine();

		    	i++;
	    }

	    br.close();
	    
	    return i;
		
	}

}
