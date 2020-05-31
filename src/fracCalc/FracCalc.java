package fracCalc;
import java.util.Scanner;
import java.util.StringTokenizer;

//Rahul Ganesh

public class FracCalc {

    public static void main(String[] args) {

        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a fraction problem: ");
        String userResponse = console.nextLine();


        while (!userResponse.equalsIgnoreCase("quit")) {


            System.out.println(produceAnswer(userResponse));
            System.out.print("Enter a fraction problem: ");
            userResponse = console.nextLine();

        }

    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer1(String input) {
        // TODO: Implement this function to produce the solution to the input
        Scanner console = new Scanner(input);
        String operand1 = console.next();
        String operator = console.next();
        String operand2 = console.next();
        String x = fractionCalc(operand1);
        String y = fractionCalc(operand2);
        String[] tokens = x.split(" ");
        int num1whole = Integer.parseInt(tokens[1]);
        int num1num = Integer.parseInt(tokens[3]);
        int num1denom = Integer.parseInt(tokens[5]);
        String[] tokens2 = y.split(" ");
        int num2whole = Integer.parseInt(tokens2[1]);
        int num2num = Integer.parseInt(tokens2[3]);
        int num2denom = Integer.parseInt(tokens2[5]);
        String result;

        if(operator.equals("+")) {
            result = addition(num1whole, num1num, num1denom, num2whole, num2num, num2denom);
            return result;
        }
        else if(operator.equals("-")) {
            result = subtraction(num1whole, num1num, num1denom, num2whole, num2num, num2denom);
            return result;
        }
        else if (operator.equals("*")) {
            result = multiplication(num1whole, num1num, num1denom, num2whole, num2num, num2denom);
            return result;
        }
        else if(operator.equals("/")) {
            result = division(num1whole, num1num, num1denom, num2whole, num2num, num2denom);
            return result;
        }
        else if(!operator.equals("+") ||!operator.equals("-") ||!operator.equals("*") ||!operator.equals("/")) {
            System.out.print("Invalid operator");
        }


        return " ";
    }
    public static String produceAnswer(String input) {

        StringTokenizer tokenizer = new StringTokenizer(input," ",false);
        String result = "0 +";
        while (tokenizer.hasMoreTokens()) {
            String str1 = tokenizer.nextToken();
           
            String str4 = result + " " + str1;
            
            result = produceAnswer1(str4);
            if(result.startsWith("Error: ")) {
            	return result;
            }
            if(tokenizer.hasMoreTokens()) {
                result = result + " " +  tokenizer.nextToken();
            }

        }
        return result;
    }
    public static int gcf(int numerator, int denominator) {
        int temp;
        while(numerator!=0 && denominator!=0) {
            temp = denominator;
            denominator = numerator%denominator;
            numerator = temp;
        }
        return Math.abs(numerator + denominator);
    }
    public static String reduce(int num, int denom) {
        int gcf = gcf(num,denom);
        num = num / gcf;
        denom = denom/gcf;
        int wholeNumber = num/denom;
        num = num % denom;

        String wholeNumStr = "";
        String numStr = "";
        String denStr = "";

        if (wholeNumber == 0 && num == 0)
        {
            return "0";
        } else if (num == 0 ) {
            numStr = "";
            denStr = "";
            wholeNumStr = new String(String.valueOf(wholeNumber));
            return wholeNumStr;
        }
        else if(num < 0 && denom < 0) {
            numStr = new String(String.valueOf( Math.abs(num)));
            denStr = new String (String.valueOf(Math.abs(denom)));
            if (wholeNumber != 0) {
                wholeNumStr = new String(String.valueOf(wholeNumber));
                return wholeNumStr + "_" + numStr + "/" + denStr;
            } else {
                return numStr + "/" + denStr;
            }
        }
        else if (num < 0) {
            numStr = new String(String.valueOf( Math.abs(num)));
            denStr = new String (String.valueOf(denom));
            if (wholeNumber != 0) {
                wholeNumStr = new String(String.valueOf(wholeNumber));
                return wholeNumStr + "_" + numStr + "/" + denStr;
            } else {
                return "-" + numStr + "/" + denStr;
            }
        }
        else if (denom < 0) {
            numStr = new String(String.valueOf(num));
            denStr = new String (String.valueOf(Math.abs(denom)));
            if (wholeNumber != 0) {
                wholeNumStr = new String(String.valueOf(wholeNumber));
                return wholeNumStr + "_" + numStr + "/" + denStr;
            } else {
                return "-" + numStr + "/" + denStr;
            }
        } else {
            numStr = new String(String.valueOf(num));
            denStr = new String (String.valueOf(denom));
            if (wholeNumber != 0) {
                wholeNumStr = new String(String.valueOf(wholeNumber));
                return wholeNumStr + "_" + numStr + "/" + denStr;
            } else {
                return numStr + "/" + denStr;
            }
        }
    }

    public static String division(int num1whole, int num1num, int num1denom, int num2whole, int num2num, int num2denom) {
        int newNum1num = Math.abs(num1denom)*Math.abs(num1whole) + num1num;
        int newNum2num = Math.abs(num2denom)*Math.abs(num2whole) + num2num;
        if(num1denom==0 || num2denom==0) {
            return "Error: Can't divide by 0";
        }
        if(num1whole<0) {
            newNum1num = -1 * newNum1num;
        }
        if(num2whole < 0) {
            newNum2num = -1 * newNum2num;
        }
        num1whole = 0;
        num2whole = 0;
        num1num= newNum1num;
        num2num = newNum2num;

        String reduced = reduce((num1num*num2denom),(num2num*num1denom));
        return reduced;

    }
    public static String multiplication(int num1whole, int num1num, int num1denom, int num2whole, int num2num, int num2denom) {
        int newNum1num = Math.abs(num1denom)*Math.abs(num1whole) + num1num;
        int newNum2num = Math.abs(num2denom)*Math.abs(num2whole) + num2num;

        if(num1denom==0 || num2denom==0) {
            return "Error: Can't divide by 0";
        }
        if(num1whole<0) {
            newNum1num = -1 * newNum1num;
        }
        if(num2whole < 0) {
            newNum2num = -1 * newNum2num;
        }
        num1whole = 0;
        num2whole = 0;
        num1num= newNum1num;
        num2num = newNum2num;

        String reduced = reduce((num1num*num2num), (num2denom*num1denom));
        return reduced;

    }

    public static String subtraction(int num1whole, int num1num, int num1denom, int num2whole, int num2num, int num2denom) {
        if(num1denom==0 || num2denom==0) {
            return "Error: Can't divide by 0";
        }
        int newNum1num = Math.abs(num1denom)*Math.abs(num1whole) + num1num;
        int newNum2num = Math.abs(num2denom)*Math.abs(num2whole) + num2num;
        if(num1whole<0) {
            newNum1num = -1 * newNum1num;
        }
        if(num2whole < 0) {
            newNum2num = -1 * newNum2num;
        }
        num1whole = 0;
        num2whole = 0;
        num1num= newNum1num;
        num2num = newNum2num;

        if(num1denom==1 && num2denom==1) {
            return Integer.toString(num1num - num2num);
        }
        String reduced = reduce((num1num*num2denom - num1denom*num2num),(num1denom*num2denom));

        return reduced;

    }
    public static String addition(int num1whole, int num1num, int num1denom, int num2whole, int num2num, int num2denom) {

        if(num1denom==0 || num2denom==0) {
            return "Error: Can't divide by 0";
        }
        int newNum1num = Math.abs(num1denom)*Math.abs(num1whole) + num1num;
        int newNum2num = Math.abs(num2denom)*Math.abs(num2whole) + num2num;

        if(num1whole<0) {
            newNum1num = -1 * newNum1num;
        }
        if(num2whole < 0) {
            newNum2num = -1 * newNum2num;
        }
        num1whole = 0;
        num2whole = 0;
        num1num= newNum1num;
        num2num = newNum2num;

        if(num1denom==1 && num2denom==1) {
            return Integer.toString(num1num + num2num);
        }
        String reduced = reduce((num1num*num2denom + num1denom*num2num),(num1denom*num2denom));

        return reduced;




    }

    public static String fractionCalc(String input) {
        int wholeNumber = 0;
        int numerator = 0;
        int denominator = 1;
        int underScore = input.indexOf("_");
        int slash = input.indexOf("/");
        if (underScore != -1 && slash != -1) {
            wholeNumber = Integer.parseInt(input.substring(0, underScore));
            numerator = Integer.parseInt(input.substring(underScore + 1, slash));
            denominator = Integer.parseInt(input.substring(slash + 1, input.length()));
        }
        else if (underScore == -1 && slash != -1) {

            numerator = Integer.parseInt(input.substring(underScore + 1, slash));
            denominator = Integer.parseInt(input.substring(slash + 1, input.length()));
        }
        else if (slash == -1) {
            wholeNumber = Integer.parseInt(input);


        }

        return "whole: " + wholeNumber + " numerator: " + numerator + " denominator: " + denominator;


        // TODO: Fill in the space below with any helper methods that you think you will need
    }


}