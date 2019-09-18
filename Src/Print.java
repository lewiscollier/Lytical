import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import javax.print.PrintService;

public class Print implements Printable{
	
	public Print(){
		
	
	}
	
	public void printTicket(String server, String name, String address, String reg, int count, Stock[] stock, String paymentMethod){
		try {
			
		      PrinterJob pjob = PrinterJob.getPrinterJob();
		      pjob.setJobName("Graphics Demo Printout");
		      pjob.setCopies(1);
		      pjob.setPrintable(new Printable() {
		        public int print(Graphics pg, PageFormat pf, int pageNum) {
		          if (pageNum > 0) // we only print one page
		            return Printable.NO_SUCH_PAGE; // ie., end of job
		          
		          pg.setFont(new Font("TimesRoman", Font.ITALIC, 35));
		          pg.setColor(Color.BLUE);
		          pg.drawString("COLLIER METALS", 300, 50); //Heading
		          
		          pg.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
		          pg.setColor(Color.BLACK);
		          pg.drawString(getDate(), 300, 70);

		          pg.drawString("Server : ",40,60); 
		          pg.drawString(server,110,60);  
		          pg.drawString("Name : ",40,90); 
		          pg.drawString(name,110,90);  
		          pg.drawString("Address : ",40,120); 
		          pg.drawString(address,110,120);  
		          pg.drawString("Reg : ",40,150); 
		          pg.drawString(reg,110,150);  
		          pg.drawString("Payment Method: ",40,180); 
		          pg.drawString(paymentMethod,160,180);  
		          
		          pg.drawString("Metal",40,220); 
		          pg.drawString("Weight",230,220); 
		          pg.drawString("Price",350,220); 
		          pg.drawString("Total",440,220); 

		          
		          pg.drawLine(0, 198, 1000, 198);
		          
		          int y = 240;
		          double amountDue = 0;
		          for(int i = 0; i < count; i++){

			          pg.drawString(stock[i].getType(),40,y);  
			          pg.drawString(String.valueOf(stock[i].getWeight()),230,y);
			          pg.drawString(String.valueOf((Math.round(stock[i].getPrice())*100)/100),350,y);
			          pg.drawString("£"+String.valueOf((Math.round(stock[i].getWeight()*(stock[i].getPrice()/1000))*100)/100), 440, y);//prints out the total weight
			          amountDue += stock[i].getWeight()*(stock[i].getPrice()/1000);
			          y += 25;
		          }
		          amountDue = Math.round(amountDue * 100)/100;
		          pg.drawString("Amount Due : £" + amountDue, 200, y); 
		          //add date and time Strings

		          return Printable.PAGE_EXISTS;
		        }
		      });
		      
		      if (pjob.printDialog() == false) // choose printer
		        return; 
		      pjob.print(); 
		    } catch (PrinterException pe) {
		      pe.printStackTrace();
		    }
		  
	}


public String getDate(){
		
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy/MM/dd --- hh:mm:ss");

	      System.out.println("Current Date: " + ft.format(dNow));
		return ft.format(dNow);

	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		return 0;
	}
	
	public void printStock(Stock[] stock){
		try {
		      PrinterJob pjob = PrinterJob.getPrinterJob();
		      pjob.setJobName("Graphics Demo Printout");
		      pjob.setCopies(1);
		      pjob.setPrintable(new Printable() {
		        public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException {
		          if (pageNum > 0) // we only print one page
		            return Printable.NO_SUCH_PAGE; // ie., end of job
		          
		          pg.setFont(new Font("TimesRoman", Font.ITALIC, 8));
		          pg.setColor(Color.BLACK);
		          pg.drawString(getDate(),70,15); 
		          
		          pg.drawString("TYPE",40,20);  
		          pg.drawString("WEIGHT",200,20);  
		          pg.drawString("WORTH",300,20);  
		          pg.drawString("PROFIT",400,20);  
		          //pg.drawLine(0, 60, 1000, 60);
		          
		          int y = 30;
		          double amountDue = 0;
		          double totalProfit =0;
		          double totalWeight = 0;
		          
		          for(int i = 0; i < 83; i++){

			          pg.drawString(stock[i].getType(),40,y);  
			          pg.drawString(String.valueOf((Math.round(stock[i].getWeight())*100)/100),200,y);
			          pg.drawString("£"+String.valueOf((Math.round(stock[i].getTotal())*100)/100),300,y);
			          pg.drawString("£"+(Math.round(stock[i].getMonthlyProfit()*100)/100), 400, y);//prints out the total weight
			          amountDue += stock[i].getTotal();
			          totalProfit += stock[i].getMonthlyProfit();
			          totalWeight += stock[i].getWeight();
			          y += 11;
		          }
		          
		          amountDue = Math.round(amountDue * 100)/100;
		          pg.drawString("Total Worth : £" + amountDue, 485, 15); 
		          pg.drawString("Total Profit : £" + totalProfit, 485, 30); 
		          pg.drawString("Total Weight : " + totalWeight, 485, 45); 


		          //add date and time Strings

		          return Printable.PAGE_EXISTS;
		        }
		      });

		      if (pjob.printDialog() == false) // choose printer
		        return; 
		      pjob.print(); 
		    } catch (PrinterException pe) {
		      pe.printStackTrace();
		    }
	}
	
	public PrintService findPrintService(String printerName)
    {
        for (PrintService service : PrinterJob.lookupPrintServices())
        {
            if (service.getName().equalsIgnoreCase(printerName))
                return service;
        }

        return null;
    }

}
