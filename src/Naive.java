import java.awt.*;
import java.math.BigInteger;

public class Naive {

    private static int P;
    private static BigInteger bP;

    private static boolean isPrime = false;

    public static Result runNaive(String input)
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

        //turn P to BigInteger format
        bP = new BigInteger(P+"");

        //some useful BigIntegers
        BigInteger b2 = new BigInteger("2");
        BigInteger b3 = new BigInteger("3");
        BigInteger b6 = new BigInteger("6");

        long timerStart = System.currentTimeMillis();

        if(P<=1)
        {
            result.setPrimeBoolean(false);
            isPrime = false;

            result.addResultLine("Composite because <= 1");
            System.out.println("Composite because <= 1");

            long timerEnd = System.currentTimeMillis()-timerStart;
            result.setTime(timerEnd);
            System.out.println(timerEnd);

            return result;
        }
        else if(P<=3)
        {
            result.setPrimeBoolean(true);
            isPrime = true;

            result.addResultLine("Prime because <= 3");
            System.out.println("Prime because <= 3");

            long timerEnd = System.currentTimeMillis()-timerStart;
            result.setTime(timerEnd);
            System.out.println(timerEnd +" ms");

            return result;
        }
        else if(((bP.remainder(b2)).equals(BigInteger.ZERO)||(bP.remainder(b3)).equals(BigInteger.ZERO)))
        {
            result.setPrimeBoolean(false);
            isPrime = false;

            result.addResultLine("Composite because divisible by 2 or 3");
            System.out.println("Composite because divisible by 2 or 3");

            long timerEnd = System.currentTimeMillis()-timerStart;
            result.setTime(timerEnd);
            System.out.println(timerEnd +" ms");

            return result;
        }

        //create BigInteger counter
        BigInteger bC = new BigInteger("5");

        //initialise BigInteger for square of counter
        BigInteger sbC = bC.multiply(bC);

        boolean loop;
        loop = ((sbC).compareTo(bP)==-1 || (sbC).compareTo(bP)==0);

        while(loop)
        {
           if((bP.remainder(bC)).equals(BigInteger.ZERO) || (bP.remainder(bC.add(b2))).equals(BigInteger.ZERO))
           {
               result.setPrimeBoolean(false);
               isPrime = false;

               result.addResultLine("Composite because divisible by "+bC.toString());
               System.out.println("Composite because divisible by "+bC.toString());

               long timerEnd = System.currentTimeMillis()-timerStart;
               result.setTime(timerEnd);
               System.out.println(timerEnd +" ms");

               return result;
           }
           else
           {
               result.addResultLine(bP.toString()+" not divisible by "+bC.toString()+" or "+bC.add(b2).toString());
               System.out.println(bP.toString()+" not divisible by "+bC.toString()+" or "+bC.add(b2).toString());

               bC = bC.add(b6);
               sbC = bC.multiply(bC);

               result.addResultLine(bC.toString());
               result.addResultLine(sbC.toString());

               System.out.println(bC.toString());
               System.out.println(sbC.toString());

               loop = ((sbC).compareTo(bP)==-1 || (sbC).compareTo(bP)==0);
           }

           System.out.println(loop);
        }

        result.setPrimeBoolean(true);
        isPrime = true;

        result.addResultLine("Prime");
        System.out.println("Prime");


        long timerEnd = System.currentTimeMillis()-timerStart;
        result.setTime(timerEnd);
        System.out.println(timerEnd + " ms");

        return result;

    }

}
