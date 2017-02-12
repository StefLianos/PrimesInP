import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Fermat {

    private static int P;
    private static int A =0;
    private static BigInteger bP;
    private static BigInteger bR;
    private static BigInteger bA;
    private static BigInteger bC;

    private static boolean isPrime = true;


    public static Result runFermat(String input)
    {

        //create Result type object
        Result result = new Result("",false,0);

        //check input validity
        try{P = Integer.parseInt(input);}
        catch(Exception e)
        {
            result.addResultLine("please enter a natural number");
            System.out.println("please enter a natural number");
            return result;
        }

        //Start timer
        long timerStart = System.currentTimeMillis();

        //turn P into a BigInteger object
        bP = new BigInteger(P+"");

        //turn coefficient A into BigInteger object;
        bA = new BigInteger(A+"");

        result.addResultLine("testing if "+bP+" is prime: "+",");
        System.out.println("testing if " + bP + " is prime: \n");

        for(int i=0; i<=P;++i)
        {


            //We apply fermat little formula C=(A^p)-A
            BigInteger bC = new BigInteger("0");
            bC=(bA.pow(P));
            bC=bC.subtract(bA);

            //Find if bC is divisible by P
            bR = bC.mod(bP);

            /*
            result.addResultLine("coefficient bA = "+bA+"\r");
            result.addResultLine("result of the formula bC = "+bC+"\r");
            result.addResultLine("remainder (of bC%P) bR = "+bR+" ,");
            */

            System.out.println("coefficient bA = "+bA);
            System.out.println("result of the formula bC = "+bC);
            System.out.println("remainder (of bC%P) bR = "+bR);

            //check if remainder is equal to zero and therefore if bC is divisible with P
            if(bR.equals(BigInteger.ZERO))
            {



                //print step
                result.addResultLine(bC+" is divisible by "+bP+" because ");
                result.addResultLine(bA+"^"+bP+"-"+bA+"="+bC+",");

                System.out.println(bA + "^" + bP + "-" + bA + "=" + bC);
                System.out.println(bC+" is divisible by "+bP+"\n");

                //Increment the coefficients
                bA = bA.add(BigInteger.ONE);
            }
            else
            {

                result.addResultLine(bC+" is not divisible by " +bP+" because ");
                result.addResultLine(A+"^"+bP+"-"+A+"="+bC+" ,");
                result.addResultLine("Therefore " + bP + " is NOT prime"+",");

                System.out.println(A + "^" + bP + "-" + A + "="+bC);
                System.out.println(bC +"\n"+ "is not divisible by " + bP+"\n");
                System.out.println("Therefore " + bP + " is NOT prime");

                result.setPrimeBoolean(false);

                long timerEnd = System.currentTimeMillis()-timerStart;
                result.setTime(timerEnd);

                result.addResultLine("execution time = ,"+timerEnd+" ms"+"\r\n");
                System.out.println(timerEnd + "ms");

                return result;
            }

        }

        result.addResultLine(P+" is prime or a fermat liar"+",");
        System.out.println(P + " is prime or a fermat liar");

        long timerEnd = System.currentTimeMillis()-timerStart;
        result.setTime(timerEnd);
        result.addResultLine("execution time = ,"+timerEnd+" ms"+"\r\n");
        System.out.println(timerEnd+" ms");

        result.setPrimeBoolean(true);
        return result;
    }
}
