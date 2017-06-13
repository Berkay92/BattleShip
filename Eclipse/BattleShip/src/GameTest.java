import java.util.Random;
import java.util.Scanner;

import enigma.core.Enigma;


public class GameTest {
	

	public static enigma.console.Console cn = Enigma.getConsole("Battleship");
	
    public static void main(String[] args) throws Exception {
      //Game myGame = new Game();
	  Random rnd = new Random();
	  Scanner scn = new Scanner(System.in);
	  
	  char first_letter = ' ';
	  int second_letter;
	  int player_sunk = 0, hold_player_sunk;	  
	  int computer_sunk = 0;
	  int round=0;   
	  String target;
	  int target_x,target_y;	  
	  int nexttarget_x=0;
      char nexttarget_y=0;     
      int next_startpoint_x = -1;
      char next_startpoint_y = 'S';
      boolean hit=false;
      boolean end = false;
      int n = 8;     
      int next;
      boolean left=true,right=true,up=true,down=true;
	           
	  cn.getTextWindow().setCursorPosition(55, 7);
      cn.getTextWindow().output("Round :       0");
      cn.getTextWindow().setCursorPosition(55, 10);
      cn.getTextWindow().output("Score");
      cn.getTextWindow().setCursorPosition(55, 11);
      cn.getTextWindow().output("---------------------");
      cn.getTextWindow().setCursorPosition(55, 12);
      cn.getTextWindow().output("Player   :    0");
      cn.getTextWindow().setCursorPosition(55, 13);
      cn.getTextWindow().output("Computer :    0");
            
      Player enemy = new Player("Computer");  		 
      Player user = new Player("User");
	  
      //enemy.getShip_Board().draw(50,17);
      
      int x=3,y=16;
      
      while(!end)
      {

    	  if(round%6==0)
    	  {    		  
    		  for(int i=16;i<32;i++)
    		  {
	    		  cn.getTextWindow().setCursorPosition(3,i);
	              cn.getTextWindow().output("                                    ");            
    		  }    		  
    		  x=3;
    		  y=16;
    	  }
    	  else if(round%3==0)
    	  {
    		  for(int i=16;i<32;i++)
    		  {
	    		  cn.getTextWindow().setCursorPosition(43,i);
	              cn.getTextWindow().output("                                    ");
    		  }
    		  x=43;
    		  y=16;
    	  }
    	  	  
    	  round++;
          
//---------------------- Player turn ---------------------------------- //
    	  
          do{
        	  
              cn.getTextWindow().setCursorPosition(69, 7);
              cn.getTextWindow().output(""+round+"");       	  
              cn.getTextWindow().setCursorPosition(x,y+1);      	  
        	  cn.getTextWindow().output("                   ");        
              cn.getTextWindow().setCursorPosition(x,y);
              cn.getTextWindow().output("Round "+round);
              cn.getTextWindow().setCursorPosition(x,y+1);        	  
        	  cn.getTextWindow().output("Human target : ");
              target = scn.nextLine();
              target_x = target.charAt(0);
              target_x-=64;
              target_y = target.charAt(1);
              target_y-=48;                       
              
        	  if(enemy.getShip_Board().screen[target_y][target_x] == '+')
		      {
		    	  cn.getTextWindow().setCursorPosition(n, n*5);
			      cn.getTextWindow().output("You have already hit the target. Try another field..! ");
		      }
		      else if(enemy.getShip_Board().screen[target_y][target_x] == '-')
		      {
		    	  cn.getTextWindow().setCursorPosition(n, n*5);
			      cn.getTextWindow().output("You have already tryed the target. Try another field..!");
		      }       	  
		      
          }while(enemy.getShip_Board().screen[target_y][target_x] == '+' || enemy.getShip_Board().screen[target_y][target_x] == '-');
	      
          if(enemy.getShip_Board().screen[target_y][target_x] == 'X')
	      {
        	  if(enemy.getShip_Board().whichship[target_y][target_x] == 'A')
	          {	        	  
	        	  cn.getTextWindow().setCursorPosition(1,1);
    	          if(enemy.getAll_ship()[5].size > 1)
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	    	          cn.getTextWindow().output("--> hit");
	    	          enemy.getShip_Board().screen[target_y][target_x] = '+';      
	    	    	  user.getGuess_Board().screen[target_y][target_x] = '+';
	    	    	  user.getGuess_Board().draw();
	    	          cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 10);
	    	          cn.getTextWindow().output(""+user.getScore()+"");	    	    	  
	        		  enemy.getAll_ship()[5].size--;
	        	  }
	        	  else
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	    	          cn.getTextWindow().output("--> sunk ( A )");
	    	          for(int i=1;i<=10;i++)
	  			      {
	  				     for(int j=1;j<=10;j++)
	  				     {
	  				    	if(enemy.getShip_Board().whichship[i][j] == 'A')
	  				    	{
	  				    		user.getGuess_Board().screen[i][j] = 'X';
	  				    	}
	  				     }
	  			      }   
	    	          user.getGuess_Board().draw();
	    	          cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 50);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[5].size--;
	        		  computer_sunk++;
	        	  }        	  
	          }
	          else if(enemy.getShip_Board().whichship[target_y][target_x] == 'B')
	          {
	        	  cn.getTextWindow().setCursorPosition(x+18,y+1);
	        	  if(enemy.getAll_ship()[4].size > 1)
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	    	          cn.getTextWindow().output("--> hit");
	    	          enemy.getShip_Board().screen[target_y][target_x] = '+';      
	    	    	  user.getGuess_Board().screen[target_y][target_x] = '+';
	    	    	  user.getGuess_Board().draw();
	    	          cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 10);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[4].size--;
	        	  }
	        	  else
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	        		  cn.getTextWindow().output("--> sunk ( B )");
	        		  for(int i=1;i<=10;i++)
	  			      {
	  				     for(int j=1;j<=10;j++)
	  				     {
	  				    	if(enemy.getShip_Board().whichship[i][j] == 'B')
	  				    	{
	  				    		user.getGuess_Board().screen[i][j] = 'X';
	  				    	}
	  				     }
	  			      }
	        		  user.getGuess_Board().draw();
	        		  cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 50);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[4].size--;
	        		  computer_sunk++;
	        	  }        	   
	          }
	          else if(enemy.getShip_Board().whichship[target_y][target_x] == 'D')
	          {
	        	  cn.getTextWindow().setCursorPosition(x+18,y+1);
	        	  if(enemy.getAll_ship()[3].size > 1)
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	    	          cn.getTextWindow().output("--> hit");
	    	          enemy.getShip_Board().screen[target_y][target_x] = '+';      
	    	    	  user.getGuess_Board().screen[target_y][target_x] = '+';
	    	    	  user.getGuess_Board().draw();
	    	          cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 10);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[3].size--;
	        	  }
	        	  else
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	        		  cn.getTextWindow().output("--> sunk ( D )");
	        		  for(int i=1;i<=10;i++)
	  			      {
	  				     for(int j=1;j<=10;j++)
	  				     {
	  				    	if(enemy.getShip_Board().whichship[i][j] == 'D')
	  				    	{
	  				    		user.getGuess_Board().screen[i][j] = 'X';
	  				    	}
	  				     }
	  			      }
	        		  user.getGuess_Board().draw();
	        		  cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 50);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[3].size--;
	        		  computer_sunk++;
	        	  }        	  
	          }
	          else if(enemy.getShip_Board().whichship[target_y][target_x] == 'P')
	          {
	        	  cn.getTextWindow().setCursorPosition(x+18,y+1);
	        	  if(enemy.getAll_ship()[2].size > 1)
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	    	          cn.getTextWindow().output("--> hit");
	    	          enemy.getShip_Board().screen[target_y][target_x] = '+';      
	    	    	  user.getGuess_Board().screen[target_y][target_x] = '+';
	    	    	  user.getGuess_Board().draw();
	    	          cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 10);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[2].size--;
	        	  }
	        	  else
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+18,y+1);
	        		  cn.getTextWindow().output("--> sunk ( P )");
	        		  for(int i=1;i<=10;i++)
	  			      {
	  				     for(int j=1;j<=10;j++)
	  				     {
	  				    	if(enemy.getShip_Board().whichship[i][j] == 'P')
	  				    	{
	  				    		user.getGuess_Board().screen[i][j] = 'X';
	  				    	}
	  				     }
	  			      }
	        		  user.getGuess_Board().draw();
	        		  cn.getTextWindow().setCursorPosition(69, 12);
	    	          user.setScore(user.getScore() + 50);
	    	          cn.getTextWindow().output(""+user.getScore()+"");
	        		  enemy.getAll_ship()[2].size--;
	        		  computer_sunk++;
	        	  }        	  
	          }
	    	  
	      }
	      else if(enemy.getShip_Board().screen[target_y][target_x] == '.')
	      {
	    	  cn.getTextWindow().setCursorPosition(x+18,y+1);
	          cn.getTextWindow().output("--> miss");
	    	  enemy.getShip_Board().screen[target_y][target_x] = '-';
	    	  user.getGuess_Board().screen[target_y][target_x] = '-';
	    	  user.getGuess_Board().draw();
	      }
                    
          cn.getTextWindow().setCursorPosition(n, n*5);
	      cn.getTextWindow().output("                                                               ");

// -------------------------- Computer turn --------------------------------//
	          	      
	      do{
		      if(nexttarget_x == 0 && nexttarget_y == 0)
		      {
		    	  do{		    		  
		    	  
		  		  switch(rnd.nextInt(9))
		  		  {
		  		  case 0:
		  		   	first_letter = 'A';
		  			break;
		  		  case 1:
		  			first_letter = 'B';
		  			break;
		  		  case 2:
		  			first_letter = 'C';
		  			break;
		  		  case 3:
		  			first_letter = 'D';
		  			break;
		  		  case 4:
		  			first_letter = 'E';
		  			break;
		  		  case 5:
		  			first_letter = 'F';
		  			break;
		  		  case 6:
		  			first_letter = 'G';
		  			break;
		  		  case 7:
		  			first_letter = 'H';
		  			break;
		  		  case 8:
		  			first_letter = 'I';
		  			break;
		  		}
			 
			   do{
			  	   second_letter = rnd.nextInt(10);
			   	 }while(second_letter==0);
			   
			   first_letter-=64;
		    	  }while(enemy.getGuess_Board().screen[second_letter-1][first_letter-1] == 'X' || enemy.getGuess_Board().screen[second_letter-1][first_letter] == 'X' || enemy.getGuess_Board().screen[second_letter-1][first_letter+1] == 'X' || enemy.getGuess_Board().screen[second_letter][first_letter+1] == 'X' || enemy.getGuess_Board().screen[second_letter+1][first_letter+1] == 'X' || enemy.getGuess_Board().screen[second_letter+1][first_letter] == 'X' || enemy.getGuess_Board().screen[second_letter+1][first_letter-1] == 'X' || enemy.getGuess_Board().screen[second_letter][first_letter-1] == 'X');
		    	  
		     first_letter+=64;
		     }
		     else
		     {
		    	  first_letter = nexttarget_y;		    	  
		    	  second_letter = nexttarget_x;	    	  
		    	  first_letter+=64;	    	  
		      }
		      
		      first_letter-=64;
	      
	      }while(user.getShip_Board().screen[second_letter][first_letter] == '+' || user.getShip_Board().screen[second_letter][first_letter] == '-');
	      
	      first_letter+=64;
	      
	  	  cn.getTextWindow().setCursorPosition(x,y+2);	  		
		  cn.getTextWindow().output("Computer target : " + first_letter + second_letter);		    
		  first_letter-=64; 	 
	      
		  hold_player_sunk = player_sunk;
	    
		  if(user.getShip_Board().screen[second_letter][first_letter] == 'X')
	      {
		      next_startpoint_x = second_letter;
		      next_startpoint_y = first_letter;
		      hit = true;
		    
		      if(user.getShip_Board().whichship[second_letter][first_letter] == 'A')
		      {	        	  		        	  
	    	      if(user.getAll_ship()[5].size > 1)
		          {
		              cn.getTextWindow().setCursorPosition(x+21,y+2);
		    	      cn.getTextWindow().output("--> hit");
		    	      user.getShip_Board().screen[second_letter][first_letter] = '+';
			    	  user.getShip_Board().draw();      
			    	  enemy.getGuess_Board().screen[second_letter][first_letter] = '+';
		    	      cn.getTextWindow().setCursorPosition(69, 13);
	    	          enemy.setScore(enemy.getScore() + 10);
	    	          cn.getTextWindow().output(""+enemy.getScore()+"");
		              user.getAll_ship()[5].size--;
		          }
		          else
		          {
		               cn.getTextWindow().setCursorPosition(x+21,y+2);
		               cn.getTextWindow().output("--> sunk ( A )");
		               for(int i=1;i<=10;i++)
		  			   {
		  				  for(int j=1;j<=10;j++)
		  				  {
		  				    if(user.getShip_Board().whichship[i][j] == 'A')
		  				    {
		  				    		enemy.getGuess_Board().screen[i][j] = 'X';
		  				    }
		  				  }
		  			   }
		               user.getShip_Board().screen[second_letter][first_letter] = '+';
				       user.getShip_Board().draw();
		               cn.getTextWindow().setCursorPosition(69, 13);
		    	       enemy.setScore(enemy.getScore() + 50);
		    	       cn.getTextWindow().output(""+enemy.getScore()+"");
		        	   user.getAll_ship()[5].size--;
		        	   player_sunk++;
		          }        	  
		      }
		      else if(user.getShip_Board().whichship[second_letter][first_letter] == 'B')
		      {	        	  
		          if(user.getAll_ship()[4].size > 1)
		          {
		        	  cn.getTextWindow().setCursorPosition(x+21,y+2);
		    	      cn.getTextWindow().output("--> hit");
		    	      user.getShip_Board().screen[second_letter][first_letter] = '+';
			    	  user.getShip_Board().draw();      
			    	  enemy.getGuess_Board().screen[second_letter][first_letter] = '+';
		    	      cn.getTextWindow().setCursorPosition(69, 13);
	    	          enemy.setScore(enemy.getScore() + 10);
	    	          cn.getTextWindow().output(""+enemy.getScore()+"");
		              user.getAll_ship()[4].size--;
		          }
		          else
		          {
		        	  cn.getTextWindow().setCursorPosition(x+21,y+2);
		        	  cn.getTextWindow().output("--> sunk ( B )");
		        	  for(int i=1;i<=10;i++)
		  			  {
		  				 for(int j=1;j<=10;j++)
		  				 {
		  				   if(user.getShip_Board().whichship[i][j] == 'B')
		  				   {
		  				    		enemy.getGuess_Board().screen[i][j] = 'X';
		  				   }
		  				 }
		  			  }
		        	  user.getShip_Board().screen[second_letter][first_letter] = '+';
			    	  user.getShip_Board().draw();
		        	  cn.getTextWindow().setCursorPosition(69, 13);
	    	          enemy.setScore(enemy.getScore() + 50);
	    	          cn.getTextWindow().output(""+enemy.getScore()+"");
		        	  user.getAll_ship()[4].size--;
		        	  player_sunk++;
		          }        	   
		       }
		       else if(user.getShip_Board().whichship[second_letter][first_letter] == 'D')
		       {		        	 
		           if(user.getAll_ship()[3].size > 1)
		           {
		        	   cn.getTextWindow().setCursorPosition(x+21,y+2);
		    	       cn.getTextWindow().output("--> hit");
		    	       user.getShip_Board().screen[second_letter][first_letter] = '+';
		 	    	   user.getShip_Board().draw();      
		 	    	   enemy.getGuess_Board().screen[second_letter][first_letter] = '+';
		    	       cn.getTextWindow().setCursorPosition(69, 13);
		    	       enemy.setScore(enemy.getScore() + 10);
		    	       cn.getTextWindow().output(""+enemy.getScore()+"");
		        	   user.getAll_ship()[3].size--;
		           }
	        	  else
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+21,y+2);
	        		  cn.getTextWindow().output("--> sunk ( D )");
	        		  for(int i=1;i<=10;i++)
		  			  {
		  				 for(int j=1;j<=10;j++)
		  				 {
		  				   if(user.getShip_Board().whichship[i][j] == 'D')
		  				   {
		  				    		enemy.getGuess_Board().screen[i][j] = 'X';
		  				   }
		  				 }
		  			  }
	        		  user.getShip_Board().screen[second_letter][first_letter] = '+';
			    	  user.getShip_Board().draw();
	        		  cn.getTextWindow().setCursorPosition(69, 13);
	    	          enemy.setScore(enemy.getScore() + 50);
	    	          cn.getTextWindow().output(""+enemy.getScore()+"");
	        		  user.getAll_ship()[3].size--;
	        		  player_sunk++;
	        	  }        	  
		       }
	          else if(user.getShip_Board().whichship[second_letter][first_letter] == 'P')
	          {	        	  
	        	  if(user.getAll_ship()[2].size > 1)
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+21,y+2);
	    	          cn.getTextWindow().output("--> hit");
	    	          user.getShip_Board().screen[second_letter][first_letter] = '+';
	    	    	  user.getShip_Board().draw();      
	    	    	  enemy.getGuess_Board().screen[second_letter][first_letter] = '+';
	    	          cn.getTextWindow().setCursorPosition(69, 13);
	    	          enemy.setScore(enemy.getScore() + 10);
	    	          cn.getTextWindow().output(""+enemy.getScore()+"");
	        		  user.getAll_ship()[2].size--;
	        	  }
	        	  else
	        	  {
	        		  cn.getTextWindow().setCursorPosition(x+21,y+2);
	        		  cn.getTextWindow().output("--> sunk ( P )");
	        		  for(int i=1;i<=10;i++)
		  			  {
		  				 for(int j=1;j<=10;j++)
		  				 {
		  				   if(user.getShip_Board().whichship[i][j] == 'P')
		  				   {
		  				    		enemy.getGuess_Board().screen[i][j] = 'X';
		  				   }
		  				 }
		  			  }
	        		  user.getShip_Board().screen[second_letter][first_letter] = '+';
			    	  user.getShip_Board().draw();
	        		  cn.getTextWindow().setCursorPosition(69, 13);
	    	          enemy.setScore(enemy.getScore() + 50);
	    	          cn.getTextWindow().output(""+enemy.getScore()+"");
	        		  user.getAll_ship()[2].size--;
	        		  player_sunk++;
	        	  }        	  
	          }
	    	  
	      }
		    
		  
	    if(user.getShip_Board().screen[second_letter][first_letter] == '.')
	      {
	    	  cn.getTextWindow().setCursorPosition(x+21,y+2);
	          cn.getTextWindow().output("--> miss");
	    	  user.getShip_Board().screen[second_letter][first_letter] = '-';
	    	  enemy.getGuess_Board().screen[second_letter][first_letter] = '-';
	    	  user.getShip_Board().draw(); 	  
	      }
		    

		  if(hit)
		  {		    	
	    	  if(user.getShip_Board().screen[second_letter-1][first_letter] == '-')
	    		{
	    			up=false;
	    		}	    	
		    	if(user.getShip_Board().screen[second_letter][first_letter-1] == '-' || user.getShip_Board().screen[second_letter][first_letter-1] == '|')
	    		{
	    			left = false;
	    		}
		    	if(user.getShip_Board().screen[second_letter+1][first_letter] == '-')
	    		{
	    			down = false;
	    		}
		    	if(user.getShip_Board().screen[second_letter][first_letter+1] == '-' || user.getShip_Board().screen[second_letter][first_letter+1] == '|')
	    		{
	    			right = false;
	    		}

			    	
		    do{

		    	if(hold_player_sunk != player_sunk) 
		    	{
		    		next = 4;
		    		left=true;
		    		right=true;
		    		up=true;
		    		down=true;
		    		hit = false;
		    	}
		    	else if(left && right && up && down)
		    	{
		    		next = rnd.nextInt(4);
		    	}
		    	else if(!left && right && up && down)
		    	{
		    		do{
		    			next = rnd.nextInt(4);
		    		}while(next == 3);
		    	}
		    	else if(left && !right && up && down)
		    	{
		    		do{
		    			next = rnd.nextInt(4);
		    		}while(next == 1);
		    	}
		    	else if(left && right && !up && down)
		    	{
		    		do{
		    			next = rnd.nextInt(4);
		    		}while(next == 0);
		    	}
		    	else if(left && right && up && !down)
		    	{
		    		do{
		    			next = rnd.nextInt(4);
		    		}while(next == 2);
		    	}
		    	else if(!left && !right && up && down)
		    	{
		    		do{
			    		next = rnd.nextInt(4);
			    	}while(next == 3 || next == 1);
		    	}
		    	else if(!left && right && !up && down)
		    	{
		    		do{
			    		next = rnd.nextInt(4);
			    	}while(next == 3 || next == 0);
		    	}
		    	else if(!left && right && up && !down)
		    	{
		    		do{
			    		next = rnd.nextInt(4);
			    	}while(next == 3 || next == 2);
		    	}
		    	else if(left && !right && !up && down)
		    	{
		    		do{
			    		next = rnd.nextInt(4);
			    	}while(next == 1 || next == 0);
		    	}
		    	else if(left && !right && up && !down)
		    	{
		    		do{
			    		next = rnd.nextInt(4);
			    	}while(next == 1 || next == 2);
		    	}
		    	else if(left && right && !up && !down)
		    	{
		    		do{
			    		next = rnd.nextInt(4);
			    	}while(next == 0 || next == 2);
		    	}
		    	else if(!left && !right && !up && down)
		    	{
		    		next = 2;
		    	}
		    	else if(!left && !right && up && !down)
		    	{
		    		next = 0;
		    	}
		    	else if(!left && right && !up && !down)
		    	{
		    		next = 1;
		    	}
		    	else if(left && !right && !up && !down)
		    	{
		    		next = 3;
		    	}
		    	else
		    	{
		    		next = 4; 
		    		left=true;
		    		right=true;
		    		up=true;
		    		down=true;
		    	}
		    		
		    	switch(next)
		    	{
		    	
		    	case 0:
		    		
		    		if(user.getShip_Board().screen[second_letter][first_letter] != '+')
				      {
			    		 second_letter = next_startpoint_x;
			    		 first_letter = next_startpoint_y;				    		 
				      }
			    					    		
		    		do{
		    			nexttarget_x = second_letter-1;
		    			nexttarget_y = first_letter;
		    			if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    			{
		    				second_letter--;
		    			}
		    		}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '-' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    		{
		    			do{
		    				nexttarget_x++;
		    			}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    			up = false;
		    		}
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y]=='X')
		    		{
		    			right=false;
		    			left=false;
		    		}
		    		else
		    		{
		    			up=false;			    			
		    		}   		
		    		break;
		    		
		    	case 1:
		    		
		    		if(user.getShip_Board().screen[second_letter][first_letter] != '+')
				      {
		    			 second_letter = next_startpoint_x;
			    		 first_letter = next_startpoint_y;				    		 
				      }
			    			    		
		    		do{
		    			nexttarget_x = second_letter;
		    			nexttarget_y = first_letter;
		    			nexttarget_y++;
		    			if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    			{
		    				first_letter++;
		    			}
		    		}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '|' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '-' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    		{
		    			do{
		    				nexttarget_y--;	    				
		    			}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    			right = false;
		    		}
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y]=='X')
		    		{
		    			up=false;
		    			down=false;
		    		}
		    		else
		    		{
		    			right=false;		    			
		    		}
		    		    		
		    		break;
		    	case 2:
		    		
		    		if(user.getShip_Board().screen[second_letter][first_letter] != '+')
				      {
		    			 second_letter = next_startpoint_x;
			    		 first_letter = next_startpoint_y;				    		 
				      }
			    					    		
		    		do{
		    			nexttarget_x = second_letter+1;
		    			nexttarget_y = first_letter;
		    			if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    			{
		    				second_letter++;
		    			}
		    		}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '-' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    		{
		    			do{
		    				nexttarget_x--;		    				
		    			}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    			down = false;
		    		}
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y]=='X')
		    		{
		    			left=false;
		    			right = false;
		    		}
		    		else
		    		{
		    			down=false;			    			
		    		}
		    					    		
		    		break;
		    	case 3:
		    					    		
		    		if(user.getShip_Board().screen[second_letter][first_letter] != '+')
				      {
		    			 second_letter = next_startpoint_x;
			    		 first_letter = next_startpoint_y;				    		 
				      }
			    					    		
		    		do{
		    			nexttarget_x = second_letter;
		    			nexttarget_y = first_letter;
		    			nexttarget_y--;
		    			if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    			{
		    				first_letter--;
		    			}
		    		}while(enemy.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '|' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '-' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+')
		    		{
		    			do{
		    				nexttarget_y++;
		    			}while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+');
		    			left = false;
		    		}
		    		
		    		if(user.getShip_Board().screen[nexttarget_x][nexttarget_y]=='X')
		    		{
		    			up=false;
		    			down=false;
		    		}
		    		else
		    		{
		    			left=false;		    			
		    		}			    	
		    		
		    		break;
		    	case 4 :
		    		nexttarget_x = 0;
		    		nexttarget_y = 0;
		    		break;		    		
		  		}		    	 
		    	  
		      }while(user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '+' || user.getShip_Board().screen[nexttarget_x][nexttarget_y] == '-');
    	
		  }
    
	      cn.getTextWindow().setCursorPosition(n, n*5);
		  cn.getTextWindow().output("                                                               ");

	      y+=5;
	    
	      if(computer_sunk == 4 || player_sunk == 4)
	      {
	          end = true;
	      }
	    
      }
      scn.close();
	 
      x=3;
	  y=16;
	  for(int i=16;i<32;i++)
	  {
		  cn.getTextWindow().setCursorPosition(3,i);
          cn.getTextWindow().output("                                                                          ");            
	  }    	
   
	  if(computer_sunk == 4)
	  {
		  cn.getTextWindow().setCursorPosition(15,20);
		  cn.getTextWindow().output("Player wins !");
		  cn.getTextWindow().setCursorPosition(10,22);
		  user.setScore(user.getScore() - enemy.getScore());
		  cn.getTextWindow().output("Final Score : "+ user.getScore());
		  cn.getTextWindow().setCursorPosition(69, 12);
		  cn.getTextWindow().output(""+user.getScore()+"   ");	
		  cn.getTextWindow().setCursorPosition(69, 13);
		  cn.getTextWindow().output("0   ");
	  }
	  else if(player_sunk == 4)
	  {
		  cn.getTextWindow().setCursorPosition(15,20);
		  cn.getTextWindow().output("Computer wins !");
		  cn.getTextWindow().setCursorPosition(10,22);
		  enemy.setScore(enemy.getScore() - user.getScore());
		  cn.getTextWindow().output("Final Score : "+ enemy.getScore());
		  cn.getTextWindow().setCursorPosition(69, 13);
		  cn.getTextWindow().output(""+enemy.getScore()+"   ");	
		  cn.getTextWindow().setCursorPosition(69, 12);
		  cn.getTextWindow().output("0   ");
	  }

   }

}
