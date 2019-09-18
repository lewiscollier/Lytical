import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.*;

public class Driver {
	
	static int count = 0;
	static double total = 0;
	static double profit = 0.0;

	public static void main(String[] args) {
		menuGUI();
		
	}

	private static void menuGUI() { //the Menu display and handles the clicks
		
		JFrame frame = new JFrame("Analit 2.0");
		JButton newTickBtn = new JButton("New Ticket");
		JButton stockBtn = new JButton("Stock");
		JButton recentBtn = new JButton("Recent");
		JButton sellBtn = new JButton("Sell");
		JButton analyticsBtn = new JButton("Analytics");

				
		//sets the location and size of the menu buttons
		newTickBtn.setBounds(560, 144, 200, 60);
		sellBtn.setBounds(560, 214, 200, 60);
		stockBtn.setBounds(560, 284, 200, 60);
		recentBtn.setBounds(560, 354, 200, 60);
		analyticsBtn.setBounds(560, 424, 200, 60);
		
		JLabel header = new JLabel("COLLIER METALS LTD", JLabel.CENTER);

		header.setBounds(223, 70, 800, 60);

		header.setFont(new Font("TimesRoman", Font.ITALIC,70));
        header.setForeground(Color.BLUE);
 
        frame.add(header);
		
		newTickBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		sellBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		stockBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		recentBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		analyticsBtn.setFont(new Font("Arial", Font.PLAIN, 30));

		//sets up frame
		frame.add(newTickBtn);frame.add(sellBtn);frame.add(stockBtn);frame.add(recentBtn);frame.add(analyticsBtn);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.setLayout(null);
		frame.setVisible(true);
		
		newTickBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  
				addTicket();
			            
			        }  
			    }); 
		sellBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  
						try {
							sellStock();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
			        }
			    }); 
		stockBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  
						
				try {
					viewStock();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			        }  
				});  
		recentBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  
						
				try {
					viewRecent();
				} catch (IOException e1) {
					System.out.println("Could not show recent invoices");
				}
				
			        }

				});  
		analyticsBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  
						
				loginScreen();
				
			        }



				});  
	}

	public static void addTicket(){
		
		String[] metals = {"12C","316 Solids","316 Swarf","98 Granules","98% Copper","99% Granules","Plastic Granules","AB Solids","AB Swarf","ALU Copper Rads Clean","ALU Rads","Ally Copper Rads Dirty","Ally Quad Baled","Ally Rads","Baled Litho","Batteries","Bill Payment","Brake Discs","Brass & Cu Rads","Brass Cuts","Braziery","Bright Granules","Cast ALU","Clean Copper Tanks","Clean Copper Tube","Clean H9","Clean New Prod Flat Electro","Clean Pyro","Comm ALU Swarf","Commercial Pure Wire","Commercial Pure","Copper Swarf","Dry Bright Wire","Duplex","Electric Motors","Elements","Greasy Bright Wire","Gun Metal Solid","Gun Metal Swarf","H.H. Cable","Hard Drawn Wire","Inco 625 Solid","Inco 625 Swarf","Inco 718 Solids","Inco 718 Swarf","Inco 925 Solid","Inco 925 Swarf","Irony ALU","JLR Panels","L.G. Cable","Lead Washed Rads","Lead","Light Iron","Mixed Brass Swarf","Mixed Brass","Mixed Clean","Molly","New Pro Thermal Brake","Nickel Alloys","Nickel Elements","Nickel Runnings","Nickel","No 2 Copper Wire","Old Rolled ALU","Oversize Motors","PB Solids","PB Swarf","PVC Pyro","Radio Swarf","Rod Brass Solids","SMO 254","Shearing","Singles","Stainless Solids","Stainless Swarf","Starters/Alternators","Steel Cuts","Steel Swarf","TV Cones","Tin","Titanium","Tungsten Sludge","Tungsten","UBC’s","WM Motors","Wheels","Zinc"};	
		String[] paymentMethods1 = {"Account","Bill Payment","Cheque"};
		Stock[] stock = new Stock[60];
		
		JLabel header = new JLabel("COLLIER METALS LTD", JLabel.CENTER);
		header.setBounds(223, 50, 800, 60);
		header.setFont(new Font("TimesRoman", Font.ITALIC,70));
        header.setForeground(Color.BLUE);
		
		Font font = new Font("Times New Roman",Font.PLAIN,34);
		
		JFrame frame = new JFrame("new ticket");
		JTextField serverIn = new JTextField();
		JTextField nameIn = new JTextField();
		JTextField addressIn = new JTextField();
		JTextField regIn = new JTextField();
		JButton addMetalBtn = new JButton("Add Metal");
		JLabel serverLbl = new JLabel("Server : ");
		JLabel nameLbl = new JLabel("Name : ");
		JLabel addressLbl = new JLabel("Address : ");
		JLabel regLbl = new JLabel("Reg : ");
		JLabel weightLbl = new JLabel("Weight :");
		JLabel priceLbl = new JLabel("Price :");
		JComboBox metalList = new JComboBox(metals);
		JComboBox paymentMethod = new JComboBox(paymentMethods1);
		JTextField weightIn = new JTextField();
		JTextField priceIn = new JTextField();
		JLabel totalLbl = new JLabel("Amount Due: £");
		JButton done = new JButton("Done");
		JButton cancel = new JButton("Cancel");
		
		serverIn.setBounds(300,150,180,36);
		nameIn.setBounds(300,200,180,36);
		addressIn.setBounds(300,250,180,36);
		regIn.setBounds(300,300,180,36);
		
		serverLbl.setBounds(100,150,180,36);
		nameLbl.setBounds(100,200,280,36);
		addressLbl.setBounds(100,250,180,36);
		regLbl.setBounds(100,300,300,36);
		
		weightIn.setBounds(300,350,160,40);
		weightLbl.setBounds(100, 350, 150, 40);
		priceIn.setBounds(300,400,160,40);
		priceLbl.setBounds(100, 400, 120, 40);
		metalList.setBounds(100, 450, 400, 40);
		
		serverIn.setFont(font);
		nameIn.setFont(font);
		addressIn.setFont(font);
		regIn.setFont(font);
		serverLbl.setFont(font);
		nameLbl.setFont(font);
		addressLbl.setFont(font);
		regLbl.setFont(font);
		weightIn.setFont(font);
		weightLbl.setFont(font);
		priceIn.setFont(font);
		priceLbl.setFont(font);
		metalList.setFont(font);
		totalLbl.setFont(font);
		paymentMethod.setFont(font);
		done.setFont(font);
		addMetalBtn.setFont(new Font("Arial",Font.PLAIN,30));
		cancel.setFont(font);
		
		totalLbl.setBounds(700, 500, 500, 40);
		
		paymentMethod.setBounds(100, 550, 200, 40);
		done.setBounds(600, 600, 130, 40);
		cancel.setBounds(200, 600, 200, 40);
		addMetalBtn.setBounds(100, 500, 180, 30);
		
		//add metal action listener
		addMetalBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){ 
				
				String type = metalList.getSelectedItem().toString();
				double weight = Math.round((Double.parseDouble(weightIn.getText()))*100)/100; // gets the weight form the user
				double price = Math.round(Double.parseDouble(priceIn.getText())*100)/100; // gets the price from the user
				total += weight * (price/1000);
				
				stock[count] = new Stock(type,weight,Math.round((weight*(price/1000))*100)/100);
				total= Math.round(total*100)/100;
				totalLbl.setText("Amount Due: £"+total);
				frame.repaint();

				count++;
				weightIn.setText("");
				priceIn.setText("");
			}
			    });  
		
		done.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  				
			            Ticket ticket = new Ticket();
			            ticket.print(serverIn.getText(),nameIn.getText(),addressIn.getText(),regIn.getText(),count , stock, paymentMethod.getSelectedItem().toString());
			            ticket.print(serverIn.getText(),nameIn.getText(),addressIn.getText(),regIn.getText(),count , stock, paymentMethod.getSelectedItem().toString());
			           
			            if(serverIn.getText().equals(""))
			            	serverIn.setText(" ");
			            if(nameIn.getText().equals(""))
			            	nameIn.setText(" ");
			            if(addressIn.getText().equals(""))
			            	addressIn.setText(" ");
			            if(regIn.getText().equals(""))
			            	regIn.setText(" ");
			            
			            try {
							ticket.saveTicket(serverIn.getText(),nameIn.getText(),addressIn.getText(),regIn.getText(),total,count , stock, paymentMethod.getSelectedItem().toString());
						} catch (IOException e1) {
							System.out.println("could not save ticket");
						}
			            
			            Stock stock1 = new Stock();
			            for(int i = 0; i < count; i++){
			            	try {
			            		System.out.println( (stock[i].getWeight()*(stock[i].getPrice()/1000)));
								stock1.updateStock(stock[i].getType(),stock[i].getWeight(), (stock[i].getWeight()*(stock[i].getPrice()/1000)));
							} catch (IOException e1) {
								System.out.println("could not update stock!!!");
							}
			            }
			            
			            
			            
			            count =0;
			            total =0;
			            frame.dispose();
			        }


			    });  
		
		cancel.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){ 
			            count =0;
			            total =0;
			            frame.dispose();
			}
			    });  
		
		frame.add(header);frame.add(serverIn);frame.add(nameIn);frame.add(addressIn);frame.add(regIn);frame.add(serverLbl);frame.add(nameLbl);frame.add(addressLbl);frame.add(regLbl);frame.add(weightLbl);frame.add(priceLbl);frame.add(done);frame.add(cancel);frame.add(addMetalBtn);frame.add(priceIn); frame.add(weightIn);frame.add(metalList);frame.add(paymentMethod);frame.add(totalLbl);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.setVisible(true);
		
		}

	public static void viewStock() throws IOException{


		String[] columnName = {"METAL", "TOTAL WEIGHT", "TOTAL VALUE","AVERAGE PRICE PER TONNE","PROFIT"};
		String[][] stockData = new String[84][5];
		
		Stock[] stock = new Stock[84];
		Stock stock1 = new Stock();
		stock = stock1.readStock();
		
		for(int i = 0; i < 84;i++){
	    stockData[i][0] = stock[i].getType();
	    stockData[i][1] = String.valueOf(stock[i].getWeight());
	    stockData[i][2] = String.valueOf(Math.round(stock[i].getTotal()*100)/100);
	    stockData[i][3] = String.valueOf(Math.round((((stock[i].getTotal())/stock[i].getWeight())*1000)*100)/100);
	    stockData[i][4] = String.valueOf(stock[i].getMonthlyProfit());
	    }
		
		JFrame frame = new JFrame("Stocks");
				
		JTable table = new JTable(stockData,columnName);
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		table.setBounds(40, 50, 200, 100);
		table.setRowHeight(40);
		
		
	    JScrollPane sp =new JScrollPane(table);
		frame.add(sp, BorderLayout.CENTER);
		
		Print print = new Print();
		print.printStock(stock);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	public static void viewRecent() throws IOException{


		Ticket ticket = new Ticket();
		Ticket[] tickets = new Ticket[ticket.getTicketCount()];
		String[] columnName = {"DATE", "SERVER", "CUSTOMER","ADDRESS","REGISTRATION","WORTH"};
		
		tickets = ticket.readTickets();
		String[][] invoices = new String[ticket.getTicketCount()][6];
		JButton printTicket = new JButton("Print");
		
		
		for(int i = 0; i < ticket.getTicketCount(); i++){
		    invoices[i][0] = tickets[i].getDate();
		    invoices[i][1] = tickets[i].getServer();
		    invoices[i][2] = tickets[i].getName();
		    invoices[i][3] = tickets[i].getAddress();
		    invoices[i][4] = tickets[i].getReg();
		    invoices[i][5] = String.valueOf(tickets[i].getTotalAmount());
		}
		
		JFrame frame = new JFrame("Invoices");
		
		
		
		JTable table = new JTable(invoices,columnName);
		
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		table.setBounds(40, 50, 200, 100);
		table.setRowHeight(40);
		printTicket.setFont(new Font("Serif", Font.PLAIN, 20));
		printTicket.setBounds(40, 50, 200, 100);
		
	    JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
        pan1.setLayout(new BorderLayout());
        frame.getContentPane().add(pan1, BorderLayout.CENTER);
        frame.getContentPane().add(pan2, BorderLayout.SOUTH);

	    JScrollPane sp =new JScrollPane(table);
	    
        pan1.add(sp,BorderLayout.CENTER);
        
        pan2.add(printTicket, BorderLayout.LINE_END);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		printTicket.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  
						
				try {
					Ticket ticket = new Ticket();
					Ticket[] tickets = new Ticket[ticket.getTicketCount()];					
					tickets = ticket.readTickets();
					for(int i = 0; i < ticket.getTicketCount(); i++){
					    if(i == table.getSelectedRow()){
				            ticket.print(tickets[i].getServer(),tickets[i].getName(),tickets[i].getAddress(),tickets[i].getReg(),tickets[i].getMetalCount() , tickets[i].getStock(), " ");
					    }
					}
					
		           
				} catch (IOException e1) {
					System.out.println("Could not print invoice!");
				}
				
			        }

				});
	}

	public static void sellStock() throws IOException{
		
		String[] metals = {"12C","316 Solids","316 Swarf","98 Granules","98% Copper","99% Granules","Plastic Granules","AB Solids","AB Swarf","ALU Copper Rads Clean","ALU Rads","Ally Copper Rads Dirty","Ally Quad Baled","Ally Rads","Baled Litho","Batteries","Brake Discs","Brass & Cu Rads","Brass Cuts","Braziery","Bright Granules","Cast ALU","Clean Copper Tanks","Clean Copper Tube","Clean H9","Clean New Prod Flat Electro","Clean Pyro","Comm ALU Swarf","Commercial Pure Wire","Commercial Pure","Copper Swarf","Dry Bright Wire","Duplex","Electric Motors","Elements","Greasy Bright Wire","Gun Metal Solid","Gun Metal Swarf","H.H. Cable","Hard Drawn Wire","Inco 625 Solid","Inco 625 Swarf","Inco 718 Solids","Inco 718 Swarf","Inco 925 Solid","Inco 925 Swarf","Irony ALU","JLR Panels","L.G. Cable","Lead Washed Rads","Lead","Light Iron","Mixed Brass Swarf","Mixed Brass","Mixed Clean","Molly","New Pro Thermal Brake","Nickel Alloys","Nickel Elements","Nickel Runnings","Nickel","No 2 Copper Wire","Old Rolled ALU","Oversize Motors","PB Solids","PB Swarf","PVC Pyro","Radio Swarf","Rod Brass Solids","SMO 254","Shearing","Singles","Stainless Solids","Stainless Swarf","Starters/Alternators","Steel Cuts","Steel Swarf","TV Cones","Tin","Titanium","Tungsten Sludge","Tungsten","UBC’s","WM Motors","Wheels","Zinc"};	
		JFrame frame = new JFrame("Sell Stock");

		JTextField nameIn = new JTextField();
		JButton addMetalBtn = new JButton("add metal");
		JLabel nameLbl = new JLabel("Customer Name : ");
		JLabel weightLbl = new JLabel("Weight :");
		JLabel amountLbl = new JLabel("Amount :");
		JComboBox metalList = new JComboBox(metals);
		JTextField weightIn = new JTextField();
		JTextField amountIn = new JTextField();
		JLabel profitLbl = new JLabel("Profit : £");
		JButton done = new JButton("Done");
		JButton cancel = new JButton("Cancel");
		JButton allWeightBtn = new JButton("All Weight");
		
		nameIn.setBounds(200,130,100,20);
		
		nameLbl.setBounds(100,130,120,20);
		
		profitLbl.setBounds(200, 275, 200, 20);
		weightIn.setBounds(265,220,60,20);
		weightLbl.setBounds(210, 220, 50, 20);
		amountIn.setBounds(400,220,60,20);
		amountLbl.setBounds(340, 220, 60, 20);
		metalList.setBounds(100, 220, 100, 24);
		
		done.setBounds(400, 600, 70, 20);
		addMetalBtn.setBounds(100, 250, 90, 20);
		allWeightBtn.setBounds(205,250, 100, 20);
		
		//add metal action listener
		addMetalBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){ 
				
				String type = metalList.getSelectedItem().toString();
				double weight = Double.parseDouble(weightIn.getText()); // gets the weight form the user
				double amount = Double.parseDouble(amountIn.getText()); // gets the price from the user
				
				Stock[] stock = new Stock[50];
				Stock stock1 = new Stock();
				try {
					stock = stock1.readStock();
				} catch (IOException e1) {
					System.out.println("Could not read stock!!!");
				}
				
				for(int i = 0; i < 84; i++){
					if(stock[i].getType().equals(type)){
						System.out.println(stock[i].getPrice());

						profit = Math.round((amount - ((stock[i].getPrice()/1000)*weight))*100)/100 ; //calculates the profit
					}
				}
					
				total= Math.round(total*100)/100;
				profitLbl.setText("Profit: £"+profit);
				frame.repaint();

			}
			    });  
		
		allWeightBtn.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){ 
				
				String type = metalList.getSelectedItem().toString();
				Stock[] stock = new Stock[85];
				Stock stock1 = new Stock();
				
				try {
					stock = stock1.readStock();
				} catch (IOException e1) {
					System.out.println("Could not read stock!!!");
				}
				
				for(int i = 0; i < 84; i++){
					if(stock[i].getType().equals(type)){
						weightIn.setText(String.valueOf(stock[i].getWeight()));
					}
				}

			}
			    });  
		
		done.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  				
			            
			        
				String type = metalList.getSelectedItem().toString();
				double weight = Math.round(Double.parseDouble(weightIn.getText())*100)/100; // gets the weight form the user
				double price = Math.round(Double.parseDouble(amountIn.getText())*100)/100; // gets the price from the user
				
				Stock[] stock = new Stock[50];
				Stock stock1 = new Stock();
				
				try {
					stock = stock1.readStock();  // reads the saved stock and adds it to the array
				} catch (IOException e1) {
					System.out.println("Could not read stock!!!");
				}
				
				for(int i = 0; i < 84; i++){ //finds the stock we want to update
					if(stock[i].getType().equals(type)){
						try {
							stock[i].updateStock(type, -(weight), -(Math.round((stock[i].getPrice()*(weight/1000))*100)/100), profit);
						} catch (IOException e1) {
							System.out.println("Could not update stock!!!");
						}
					}
					}
				profit = 0;
				frame.dispose(); //closes the frame when done
				
				}
			    });  
		
		frame.add(done);frame.add(allWeightBtn);frame.add(profitLbl);frame.add(amountLbl);frame.add(weightLbl);frame.add(amountIn);frame.add(weightIn);frame.add(metalList);frame.add(addMetalBtn);frame.add(profitLbl);
		
		frame.setSize(500, 700);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}

	public static String getDate(){
		
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy/MM/dd --- hh:mm:ss");

	      System.out.println("Current Date: " + ft.format(dNow));
		return ft.format(dNow);

	}
	
	private static void loginScreen() {
		
		JFrame frame = new JFrame("LOG IN");
		
		JLabel passwordLbl = new JLabel("ENTER PASSWORD :");
		JPasswordField passIn = new JPasswordField();
		JButton submitButton = new JButton("Enter");
		JLabel lbl = new JLabel();
		
		int count = 0;
		
		passwordLbl.setBounds(30, 50, 200, 30);
		passIn.setBounds(150, 50, 170, 30);
		lbl.setBounds(30, 70, 200, 30);
		submitButton.setBounds(30, 100, 90, 30);
		
		frame.add(passwordLbl);frame.add(passIn);frame.add(lbl);frame.add(submitButton);
		frame.setSize(400, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		

		
		frame.getRootPane().setDefaultButton(submitButton);
		
		submitButton.addActionListener(new ActionListener(){  //if done button clicked the action listener will get called
			public void actionPerformed(ActionEvent e){  				
					if(passIn.getText().equals("dexter")){
						frame.dispose();
						try {
							Analytics();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else{
						lbl.setText("INCORRECT ENTRY");
					}
				}
			    });  
	}
	
	private static void Analytics() throws IOException {
		
		JFrame frame = new JFrame("ANALYTICS");
		
		double totalWorth = 0;
		double tempWeight = 0;
		String mostStock = "";
		
		Ticket ticket = new Ticket();
		Ticket[] tickets = new Ticket[ticket.getTicketCount()];
		Stock[] stock = new Stock[85];
		Stock stock1 = new Stock();
		stock = stock1.readStock();		
		tickets = ticket.readTickets();
		Font heading = new Font("arial",Font.BOLD,30);
		Font font = new Font("arial",Font.PLAIN,20);
		
		for(int i = 0; i < 84;i++){
		    totalWorth += stock[i].getTotal();
		    if(stock[i].getWeight() > tempWeight){
		    	tempWeight = stock[i].getWeight();
		    	mostStock= stock[i].getType();
		    }
		    }
		
		JLabel totalWorthLbl = new JLabel("TOTAL WORTH : " + totalWorth);
		JLabel headingLbl = new JLabel("OVERVIEW");
		JLabel mostStockLbl = new JLabel("MOST STOCK :" + mostStock);

		headingLbl.setFont(heading);
		totalWorthLbl.setFont(font);
		mostStockLbl.setFont(font);
		
		headingLbl.setBounds(40, 80, 400, 30);
		totalWorthLbl.setBounds(40, 150, 300, 30);
		mostStockLbl.setBounds(40, 180, 100, 30);
		
		frame.add(totalWorthLbl);frame.add(headingLbl);frame.add(mostStockLbl);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
}
