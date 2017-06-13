
	import java.util.Random;
import java.util.Scanner;

import enigma.core.Enigma;


	public class Ship {
		
		public enigma.console.Console cn = Enigma.getConsole("Battleship");

		String name;
	    char h_v;
	    int size,hold_size;
	    int y_coordinate,hold_y;	     
	    char x_coordinate,hold_x;
	    
	    Scanner scn = new Scanner(System.in);
	    Random rnd;
	    static int[] control = new int[]{0,1,2,3,4,5}; // it will be used when user choose a size.The values 2,3,4,5 will be considered 
	    											   // The array values will be zero when the ship is used

	     
	    public Ship(Board myBoard) 
	    {
	    	
	    	int x=8;
	    	do{
		        cn.getTextWindow().setCursorPosition(x*2, x*2);
		        cn.getTextWindow().output("PLACE YOUR SHIP !");
		        
		        //----------------------------------------------------------------------
		        do{
		        cn.getTextWindow().setCursorPosition(x, x*2+2);
		        cn.getTextWindow().output("Enter x coordinate (letter) : ");		       
		        x_coordinate = scn.next().charAt(0); 
		        if(x_coordinate != 'A' && x_coordinate != 'B'&& x_coordinate != 'C'&& x_coordinate != 'D'&& x_coordinate != 'E'&& x_coordinate != 'F'&& x_coordinate != 'G'&& x_coordinate != 'H'&& x_coordinate != 'I')
		         {
		        	cn.getTextWindow().setCursorPosition(x*3, x*2+2);
			        cn.getTextWindow().output("                        ");
		        	cn.getTextWindow().setCursorPosition(x, x*5);
			        cn.getTextWindow().output("Wrong input! Try again..                 ");
		         }
		        else
		         {
		        	cn.getTextWindow().setCursorPosition(x, x*5);
			        cn.getTextWindow().output("                                         ");
		         }
		        } // Hiçbiþey yazmadan enter'a basýnca hata düzeltilecek!!
		        while(x_coordinate != 'A' && x_coordinate != 'B'&& x_coordinate != 'C'&& x_coordinate != 'D'&& x_coordinate != 'E'&& x_coordinate != 'F'&& x_coordinate != 'G'&& x_coordinate != 'H'&& x_coordinate != 'I');
		        //----------------------------------------------------------------------
		        
		        x_coordinate-=64; // A-->65 B-->66 C-->67 D-->68 E-->69 F-->70 G-->71 H-->72 I-->73  	        		        
		        hold_x = x_coordinate;
		        
		        //--------------------------------------------------------------------
		        do{		        	
		        cn.getTextWindow().setCursorPosition(x, 2*x+3);
		        cn.getTextWindow().output("Enter y coordinate (integer) : ");	        
		        y_coordinate = scn.nextInt(); 
		        if(y_coordinate<1 || y_coordinate>9)
		         {
		        	cn.getTextWindow().setCursorPosition(x*3, x*2+3);
			        cn.getTextWindow().output("                        ");
		        	cn.getTextWindow().setCursorPosition(x, x*5);
			        cn.getTextWindow().output("Wrong input! Try again..                  ");
		         }
		        else
		         {
		        	cn.getTextWindow().setCursorPosition(x, x*5);
			        cn.getTextWindow().output("                                        ");
		         }
		        }while(y_coordinate<1 || y_coordinate>9);
		        //---------------------------------------------------------------------

		        hold_y = y_coordinate;	
		        
		        //---------------------------------------------------------------------
		        do{
		        cn.getTextWindow().setCursorPosition(x, 2*x+4);
		        cn.getTextWindow().output("Enter the size of the ship : ");
		        size = scn.nextInt(); 
		        if(size<2 || size>5)
		         {
		        	cn.getTextWindow().setCursorPosition(x*3, 2*x+4);
			        cn.getTextWindow().output("                        ");
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
			        cn.getTextWindow().output("Wrong input! Try again..                 ");
		         }
		        else if(control[size] == 0)
		        {
		        	cn.getTextWindow().setCursorPosition(3*x, 2*x+4);
			        cn.getTextWindow().output("                        ");
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
			        cn.getTextWindow().output("The ship has already used! Try again..    ");
		        }
		        else
		         {
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
			        cn.getTextWindow().output("                                         ");
		         }
		        }while(size<2 || size>5 || control[size] == 0);
		        //--------------------------------------------------------------------
		        
		        control[size] = 0;
		        hold_size = size;
		        
		        //---------------------------------------------------------------------
		        do{
		        cn.getTextWindow().setCursorPosition(x, 2*x+5);
		        cn.getTextWindow().output("Horizontal or vertical (Enter 'H' or 'V') : ");
		        h_v = scn.next().charAt(0); 
		        if(h_v != 'H' && h_v != 'V')
		         {
		        	cn.getTextWindow().setCursorPosition(3*x, 2*x+5);
			        cn.getTextWindow().output("                                        ");
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
			        cn.getTextWindow().output("Wrong input! Try again..                 ");
		         }
		        else
		        {
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
			        cn.getTextWindow().output("                                         ");
		        }
		        }while(h_v != 'H' && h_v != 'V');
		        //--------------------------------------------------------------------
		        
		        cn.getTextWindow().setCursorPosition(2*x, 2*x);
		        cn.getTextWindow().output("                                                 ");
		        cn.getTextWindow().setCursorPosition(x, 2*x+2);
		        cn.getTextWindow().output("                                                 ");
		        cn.getTextWindow().setCursorPosition(x, 2*x+3);
		        cn.getTextWindow().output("                                                 ");	        
		       	cn.getTextWindow().setCursorPosition(x, 2*x+4);
		        cn.getTextWindow().output("                                                 ");
		        cn.getTextWindow().setCursorPosition(x, 2*x+5);
		        cn.getTextWindow().output("                                                 ");
		        cn.getTextWindow().setCursorPosition(x, 2*x+7);
		        cn.getTextWindow().output("                                                 ");
		        
		        if(!available(myBoard))
		        {
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
		        	cn.getTextWindow().output("The place was not available.. Try again.. ");  
		        	control[size] = size;
		        }
		        else
		        {
		        	cn.getTextWindow().setCursorPosition(x, 5*x);
		        	cn.getTextWindow().output("                                          ");   
		        }
	        
	    	}while(!available(myBoard));
		    		   
			putShip(myBoard);

	        if (size == 2)
	            name = "Petrol boat";
	        else if (size == 3)
	            name = "Destroyer";
	        else if (size == 4)
	            name = "Battleship";
	        else if (size == 5)
	            name = "Aircraft carrier";
	    }
	    
	    
	    
	    public Ship(Board myBoard,int size)
	    {
	    	
	    	this.size = size;
	    	hold_size = size;	    	
	    	do{
	    		
	    		rnd = new Random(); 
	    		
	    		switch(rnd.nextInt(9))
	    		{
	    		case 0:
	    			x_coordinate = 'A';
	    			break;
	    		case 1:
	    			x_coordinate = 'B';
	    			break;
	    		case 2:
	    			x_coordinate = 'C';
	    			break;
	    		case 3:
	    			x_coordinate = 'D';
	    			break;
	    		case 4:
	    			x_coordinate = 'E';
	    			break;
	    		case 5:
	    			x_coordinate = 'F';
	    			break;
	    		case 6:
	    			x_coordinate = 'G';
	    			break;
	    		case 7:
	    			x_coordinate = 'H';
	    			break;
	    		case 8:
	    			x_coordinate = 'I';
	    			break;
	    		}
	    		
	    		 x_coordinate-=64; 	    		 
	    		 hold_x = x_coordinate;
	    		 
	    		 do{
	    			 
	    		 y_coordinate = rnd.nextInt(10);
	    		 
	    		 }while(y_coordinate==0);
	    		 
			     hold_y = y_coordinate;	
			     
	    		switch(rnd.nextInt(2))
	    		{
	    		case 0:
	    			h_v = 'H';
	    			break;
	    		case 1:
	    			h_v = 'V';
	    			break;
	    		}
		
	    	}while(!available(myBoard));
	    	
	    	putShip(myBoard);
	    	
	    	if (size == 2)
	            name = "Petrol boat";
	        else if (size == 3)
	            name = "Destroyer";
	        else if (size == 4)
	            name = "Battleship";
	        else if (size == 5)
	            name = "Aircraft carrier";
	    	
	    }
	    
	    public void putShip(Board myBoard)
	    {	    	
	    	
	    	if(h_v == 'V')
        	{
        		y_coordinate = hold_y;       	
        		size = hold_size;
	
        		while(size > 0)
        		{
        			myBoard.screen[y_coordinate][x_coordinate] = 'X';
        			if(hold_size == 2)
        	    	{
        	    		myBoard.whichship[y_coordinate][x_coordinate] = 'P';
        	    	}
        			else if(hold_size == 3)
        			{
        				myBoard.whichship[y_coordinate][x_coordinate] = 'D';
        			}
        			else if(hold_size == 4)
        			{
        				myBoard.whichship[y_coordinate][x_coordinate] = 'B';
        			}
        			else if(hold_size == 5)
        			{
        				myBoard.whichship[y_coordinate][x_coordinate] = 'A';
        			}
        			size--;
        			y_coordinate++;
        		}	
        	}

        	else if(h_v == 'H')
        	{
        		x_coordinate = hold_x;
        		size = hold_size;
        		
        		while(size > 0)
        		{
        			myBoard.screen[y_coordinate][x_coordinate] = 'X';
        			if(hold_size == 2)
        	    	{
        	    		myBoard.whichship[y_coordinate][x_coordinate] = 'P';
        	    	}
        			else if(hold_size == 3)
        			{
        				myBoard.whichship[y_coordinate][x_coordinate] = 'D';
        			}
        			else if(hold_size == 4)
        			{
        				myBoard.whichship[y_coordinate][x_coordinate] = 'B';
        			}
        			else if(hold_size == 5)
        			{
        				myBoard.whichship[y_coordinate][x_coordinate] = 'A';
        			}
        			size--;
        			x_coordinate++;
        		}    		
        	}       	
        	x_coordinate = hold_x;
        	y_coordinate = hold_y;
        	size = hold_size;;     	
	    }
	    
	    
	    public boolean available(Board myBoard)
	    {
	    	
	    	if(h_v == 'V' && y_coordinate+size<=10)   	
	    	{
	    		
		    	if(myBoard.screen[y_coordinate-1][x_coordinate-1] == 'X' || myBoard.screen[y_coordinate-1][x_coordinate] == 'X' || myBoard.screen[y_coordinate-1][x_coordinate+1] == 'X')
		    	{
		    		return false;
		    	}
		    	while(size > 0)
		    	{
		    		if(myBoard.screen[y_coordinate][x_coordinate-1] == 'X' || myBoard.screen[y_coordinate][x_coordinate] == 'X' || myBoard.screen[y_coordinate][x_coordinate+1] == 'X')
		    		{
			        	y_coordinate = hold_y;
			        	size = hold_size;
		    			return false;
		    		}
		    		y_coordinate++;
		    		size--;
		    	}
		    	
		    	if(myBoard.screen[y_coordinate][x_coordinate-1] == 'X' || myBoard.screen[y_coordinate][x_coordinate] == 'X' || myBoard.screen[y_coordinate][x_coordinate+1] == 'X')
		    	{
		    		y_coordinate = hold_y;
		        	size = hold_size;
		    		return false;
		    	}
		    	else
		    	{
		    		y_coordinate = hold_y;
		        	size = hold_size;
		    		return true;
		    	}
	        	
	    	}
	    	else if(h_v == 'H' && x_coordinate+size<=10)
	    	{
	    		if(myBoard.screen[y_coordinate-1][x_coordinate-1] == 'X' || myBoard.screen[y_coordinate][x_coordinate-1] == 'X' || myBoard.screen[y_coordinate+1][x_coordinate-1] == 'X')
		    	{
		    		return false;
		    	}
	    		while(size > 0)
		    	{
		    		if(myBoard.screen[y_coordinate-1][x_coordinate] == 'X' || myBoard.screen[y_coordinate][x_coordinate] == 'X' || myBoard.screen[y_coordinate+1][x_coordinate] == 'X')
		    		{
		    			x_coordinate = hold_x;
			        	size = hold_size;
		    			return false;
		    		}
		    		x_coordinate++;
		    		size--;
		    	}
	    		if(myBoard.screen[y_coordinate-1][x_coordinate] == 'X' || myBoard.screen[y_coordinate][x_coordinate] == 'X' || myBoard.screen[y_coordinate+1][x_coordinate] == 'X')
		    	{
	    			x_coordinate = hold_x;
		        	size = hold_size;
		    		return false;
		    	}
		    	else
		    	{
		    		x_coordinate = hold_x;
		        	size = hold_size;
		    		return true;
		    	}
	    	}
	    	else
	    	{
	    		x_coordinate = hold_x;
	    		y_coordinate = hold_y;
	        	size = hold_size;
	        	return false;
	    	}
	    		
	    		
	    	
	    }
	    
	    
	}

