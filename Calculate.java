import java.util.*;
import java.io.*;
import java.util.ArrayDeque;
import java.lang.Math;

/** 
 * Class to interpret and compute the result of arithmetic expressions 
*@author Ramsha Rauf
 *@version Spring 2022
 */
public class Calculate {

  /**
  *reads in the console input and stores it in a queue using tokenizer and sends it through the infix method and postfix method
  *@param String[] args that takes in the console input
  */

  public static void main(String[] args) {
    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Calculate <expr>");
    } else {
      Queue<Object> read_token = Tokenizer.readTokens(args[0]);
      ArrayDeque<Object> infix_queue = infix(read_token);
      System.out.println("Answer: "+Postfix.arithmetic(infix_queue));
    }
  }

  /**
  *converts an equation from infix to postfix notation using the shunting yard algorithm  
  *@param queue of objects that has been read and arranged by the tokenizer 
  *@return a queue of objects that contains the equation in postfix notation
  */
  public static ArrayDeque <Object> infix(Queue<Object> read_token){
    //new stack and queue
    ArrayDeque <Object> stack = new ArrayDeque<>();
    ArrayDeque <Object> queue = new ArrayDeque<>();

    //analyzes every object in the param queue 
    for (Object token:read_token){
      if (token instanceof Double){
        queue.addLast(token);
        //System.out.println(stack);
      }else if (token instanceof Character){
        char operator = (char)token;
        if (operator == '^'){
          stack.addFirst(token);
        }else if (operator == '*' || operator == '/'){
          if (stack.isEmpty()){
            stack.addFirst(token);
          }else{
            //if the top char is of higher or equal precedent it removes it from the stack and adds to the queue
            while ((char)stack.peek() == '^' || (char)stack.peek() == '*' || (char)stack.peek() == '/'){
              queue.addLast(stack.removeFirst());
            }
              //adds after removing the operator with higher precedence 
            stack.addFirst(token);
          }
        }else if (operator == '+' || operator == '-'){
          if (stack.isEmpty()){
            stack.addFirst(token);
          }else{
            //if the top char is of higher or equal precedent it removes it from the stack and adds to the queue
            while((char)stack.peek() == '^'|| (char)stack.peek() == '*' || (char)stack.peek() == '/'){
              queue.addLast(stack.removeFirst());
            }
            //adds after removing the operator with higher precedence 
            stack.addFirst(token);
          }
        }else if (operator == '('){
          stack.addFirst(token);
        }else if (operator == ')'){
          try{
            while ((char)stack.peek() != '('){
              queue.addLast(stack.removeFirst());
            }
            stack.removeFirst();
          }catch(Exception e){
            throw new RuntimeException("You have a mismatched parenthesis");
          }
        }
      }
       
    }

    while(!stack.isEmpty()){

        if ((char)stack.peek()== '('||(char)stack.peek()== ')'){
          throw new RuntimeException("You have a mismatched parenthesis");
        }else{
          queue.addLast(stack.removeFirst());
        }

      }
    System.out.println(queue);
    return queue;
    
  }
}

