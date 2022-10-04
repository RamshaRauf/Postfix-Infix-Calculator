import java.util.*;
import java.io.*;
import java.util.ArrayDeque;
import java.lang.Math;


/** 
 * Class to compute the arithmetic expressions in Postfix notation 
 *@author Ramsha Rauf
 *@version Spring 2022
 */

public class Postfix{
  
  /**
  *reads in the console input and stores it in a queue using tokenizer and sends it through the arithmetic method
  *@param String[] args that takes in the console input
  */
  public static void main(String[] args) {

    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Calculate <expr>");
    } else {
      Scanner input = new Scanner(new StringReader(args[0]));

      Queue<Object> queue = Tokenizer.readTokens(args[0]);
      //System.out.println(queue);
      double answer = arithmetic(queue);
      System.out.println("Answer: " + answer);
    }
  }
  
  /**
  *computes the arithmetic equation that is in postfix method 
  *@param queue containing the arithmetic equatioin
  *@return a double which is the answer
  */
  public static double arithmetic(Queue<Object> queue){
    double answer = 0 ;
    ArrayDeque <Double> stack = new ArrayDeque<Double>();
    for (Object element:queue){
      if (element instanceof Double){
        stack.push((Double)element);
      }else if(element instanceof Character){
        try{
          double num1 = stack.pop();
          double num2 = stack.pop();
          char operator = (char)element;
          if (operator == '+'){
            double new_num = num1 + num2;
            stack.push((Double)new_num);
          }else if (operator == '-'){
            double new_num = num2 - num1;
            stack.push((Double)new_num);
          }else if (operator == '*'){
            double new_num = num1 * num2;
            stack.push((Double)new_num);
          }else if (operator == '/'){
            double new_num = num2 / num1;
            stack.push((Double)new_num);
          }else if (operator == '^'){
            double new_num = Math.pow(num2, num1);
            stack.push((Double)new_num);
          }

          //System.out.println(stack);
        }catch(Exception e){
          throw new RuntimeException("ERROR: You have provided too many operators");
        }        
      }
    }
     if (stack.size()>1){
       throw new ArrayIndexOutOfBoundsException("More than one number remains for the result.");

     }else{
       answer = stack.removeFirst();
       return answer; 
     }
    
  }
}