import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SRPN
{
	//The stacks are initialised as Long so that saturation functionality can be achieved.
    private static Stack<Long> stack = new Stack<Long>();
	private static Stack<Long> stack2 = new Stack<Long>();
	private static Stack<Long> stack3 = new Stack<Long>();
	private static InputStreamReader is = new InputStreamReader(System.in);
	private static BufferedReader input = new BufferedReader(is);
	private static int stack3count = 0;
	private static int i;
	private static int Rcount = 0;
	//An array is created in order to emulate the functionality of 'r' from the original SRPN.
	private static long[] Rarr = { 1804289383, 846930886, 1681692777, 1714636915, 1957747793, 424238335, 719885386, 1649760492, 596516649, 1189641421, 1025202362, 1350490027, 783368690, 1102520059, 2044897763, 1967513926, 1365180540, 1540383426,  304089172,  1303455736,  35005211,  521595368, 294702567,  1726956429,  336465782, 861021530, 278722862, 233665123, 2145174067, 468703135, 1101513929, 1801979802, 1315634022, 635723058, 1369133069, 1125898167, 1059961393, 2089018456, 628175011, 1656478042, 1131176229, 1653377373, 859484421, 1914544919, 608413784, 756898537, 1734575198, 1973594324, 149798315, 2038664370, 1129566413, 184803526,  412776091,  1424268980,  1911759956,  749241873, 137806862, 42999170, 982906996, 135497281, 511702305, 2084420925, 1937477084, 1827336327, 572660336, 1159126505, 805750846, 1632621729, 1100661313, 1433925857, 1141616124, 84353895,  939819582, 2001100545, 1998898814, 1548233367, 610515434, 1585990364, 1374344043, 760313750,  1477171087, 356426808, 945117276,  1889947178, 1780695788, 709393584,  491705403, 1918502651, 752392754, 1474612399, 2053999932, 1264095060, 1411549676, 1843993368, 943947739,  1984210012, 855636226,  1749698586,  1469348094,  1956297539};

	
	public static void reverseStack(Stack<Long> stack)
	{
		String hold;
		// The current state of the primary stack 'stack' is cloned into stack2
		Stack<Long> stack2 = (Stack)stack.clone();
		
		while(!stack2.empty())
		{
			hold = (String) String.valueOf(stack2.pop());
			//Replaces numbers with ""
			String holdInt = hold.replaceAll("[^0-9]", "");
			
			if(!holdInt.equals(""))
			{
				//If it was replaced then it is read by another variable and pushed onto stack 3
				long holdInt2 = Long.parseLong(holdInt);
				stack3.push(holdInt2);
				//For every element replaced one is added to stack
				stack3count++;
			}
			
		}
	}
	
	public static void Saturation()
	{
		//Looks at the top of the stack
		long TopOfStack = stack.peek();
		
		//If the value on the top of the stack is greater than the maximum value for an integer '2147483647' then it sets it back to 2147483647 
		if(TopOfStack > Integer.MAX_VALUE)
		{
			//The top of the stack is popped so that it can be replaced
			stack.pop();
			stack.push(2147483647L);
		}
		//If the value on top of the stack is less than the minimum value for an integer '-2147483648' then it sets it back to -2147483648
		else if(TopOfStack < Integer.MIN_VALUE)
		{
			//The top of the stack is popped so that it can be replaced
			stack.pop();
			stack.push(-2147483648L);
		}
	}
	
    private static void RPNCalculator() 
	{
		//Decleration of String NumberOrOperand
        String NumberOrOperand = " ";
        while (!NumberOrOperand.equals("IHateThisCourseworkWhyCan'tIJustUseScannerInstead")) 
		{
            try 
			{
				//The value of NumberOrOperand is set to what is inputted via the command line
				NumberOrOperand = input.readLine();
				//Checks if the input is a number and if so pushes it onto the stack
                long intNumberOrOperand = Long.valueOf(NumberOrOperand);
                stack.push(intNumberOrOperand);
            } 
			catch (Exception e) 
			{
                if (NumberOrOperand.equals("*")) 
				{
					//Pops the element on top of the stack and multiplies it by the next element which is popped from the stack and then pushes the result on top.
                    stack.push(stack.pop() * stack.pop());
					//The saturation function is called after the operation takes place so that it can check the result of the operation before it is printed by '='
					Saturation();
                } 
				else if (NumberOrOperand.equals("/")) 
				{
					//Pops the stack and saves the element as an long
                    long arg2 = stack.pop();
					//Pops the stack again and divides that element by the saved long. The result is then pushed onto the stack.
					stack.push(stack.pop() / arg2);
					//The saturation function is called after the operation takes place so that it can check the result of the operation before it is printed by '='
					Saturation();
                } 
				else if (NumberOrOperand.equals("+")) 
				{
					//Pops the element on top of the stack and adds it to the next element which is popped from the stack and then pushes the result on top.
                    stack.push(stack.pop() + stack.pop());
					//The saturation function is called after the operation takes place so that it can check the result of the operation before it is printed by '='
					Saturation();
                } 
				else if (NumberOrOperand.equals("-")) 
				{
					//Pops the stack and saves the element as an long
                    long arg2 = stack.pop();
					//Pops the stack again and takes the saved long away from that element. The result is then pushed onto the stack
					stack.push(stack.pop() - arg2);
					//The saturation function is called after the operation takes place so that it can check the result of the operation before it is printed by '='
					Saturation();
                } 
				else if (NumberOrOperand.equals("%"))
				{
					//Pops the stack and saves the element as a long
					long arg2 = stack.pop();
					//Pops the stack again and carrys out the modulo of that element by the saved long. The result is then pushed onto the satck.
					stack.push(stack.pop() % arg2);
					//The saturation function is called after the operation takes place so that it can check the result of the operation before it is printed by '='
					Saturation();
				}
				else if(NumberOrOperand.equals("d"))
				{
					reverseStack(stack);
					for(i = 0; i < stack3count; i++)
					{
						//Prints the stack out in reverse order
						System.out.println(stack3.pop());
					}
					stack3count = 0;
				}
				else if(NumberOrOperand.equals("r"))
				{
					//The value of 'r' in the array is pushed onto the stack based on Rcount.
					stack.push(Rarr[Rcount]);
					//Once this takes place 1 is added to Rcount so that a new value is pushed the next time 'r' is inputted
					Rcount++;
				}
				else if (NumberOrOperand.equals("=")) 
				{
					//Looks at the element ontop of the stack and prints it.
                    System.out.println(stack.peek());
                } 
            }

        }
    }

	
	
    public static void main(String[] args) 
	{
		//calls the RPNCalculator method
        RPNCalculator();
    }
}






