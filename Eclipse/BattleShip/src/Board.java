
import enigma.core.Enigma;

public class Board {

	 public enigma.console.Console cn = Enigma.getConsole("Battleship");

		 char[][] screen;
		 char[][] whichship; 
		 String name;
		 int x_pos, y_pos;
		 int hold_x,hold_y;

	     public Board(String name,int x_pos,int y_pos)
		    {
	    	this.x_pos = x_pos;
	    	this.y_pos = y_pos;
	    	 this.name = name;
	         screen = new char[11][11];
	         whichship = new char[11][11];
			    for(int i=0;i<=10;i++)
			    {
				    for(int j=0;j<=10;j++)
				    {
					    if((i == 0 && j == 0) || (i == 10 && j == 0) || (i == 0 && j == 10) || (i == 10 && j == 10))
						    screen[i][j] = '*';
					    else if(i == 0 || i == 10)
					    {
						    screen[i][j] = '-';
					    }
					    else if(j == 0 || j == 10)
					    {
						    screen[i][j] = '|';
					    }
					    else
						    screen[i][j] = '.';
				    }
			    }
		
		    } /* End of constructer */
		    
	     public void draw()
		    {
	    	 hold_x = x_pos;
	    	 hold_y = y_pos;
	    	 cn.getTextWindow().setCursorPosition(x_pos+1, y_pos-2);
	    	 cn.getTextWindow().output(name);
	    	 cn.getTextWindow().setCursorPosition(x_pos,y_pos);
	    	 cn.getTextWindow().output("  ABCDEFGHI");
	         y_pos++;
			    for(int i=0;i<=10;i++)
			    {		    	
			    	cn.getTextWindow().setCursorPosition(x_pos,y_pos);
				    if(i != 0 && i!= 10)
				    	cn.getTextWindow().output(""+i+"");
				    
				    else
				    	cn.getTextWindow().output(" ");
					
				    for(int j=0;j<=10;j++)
				    {
				    	cn.getTextWindow().output(screen[i][j]);
				    }
	             y_pos++;

			    }
		    x_pos = hold_x;
		    y_pos = hold_y;
		    
		}
	     
	     public void draw(int x,int y)
		    {
	    	 cn.getTextWindow().setCursorPosition(x+1, y-2);
	    	 cn.getTextWindow().output(name);
	    	 cn.getTextWindow().setCursorPosition(x,y);
	    	 cn.getTextWindow().output("  ABCDEFGHI");
	         y++;
			    for(int i=0;i<=10;i++)
			    {		    	
			    	cn.getTextWindow().setCursorPosition(x,y);
				    if(i != 0 && i!= 10)
				    	cn.getTextWindow().output(""+i+"");
				    
				    else
				    	cn.getTextWindow().output(" ");
					
				    for(int j=0;j<=10;j++)
				    {
				    	cn.getTextWindow().output(screen[i][j]);
				    }
	             y++;

			    }		    
		}
	     
	     public void clear(int x,int y)
		    {
	    	 cn.getTextWindow().setCursorPosition(x+1, y-2);
	    	 cn.getTextWindow().output("                              ");
	    	 cn.getTextWindow().setCursorPosition(x,y);
	    	 cn.getTextWindow().output("                              ");
	         y++;
			    for(int i=0;i<=10;i++)
			    {		    	
			    	cn.getTextWindow().setCursorPosition(x,y);
				    cn.getTextWindow().output(" ");
					
				    for(int j=0;j<=10;j++)
				    {
				    	cn.getTextWindow().output(" ");
				    }
	             y++;
			    }		    
		}
	     

	}
