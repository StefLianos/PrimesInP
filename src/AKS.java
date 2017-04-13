

import java.math.BigInteger;
import java.util.Arrays;


public class AKS {

    private static int n;
    private static BigInteger bN;
    private static boolean isPrime = false;

    public static Result runAKS (String input){


        //create Result type object
        Result result = new Result("",false,0);

        //create bigInteger Object
        //check input validity
        try{bN = new BigInteger(n + input);}
        catch(Exception e)
        {
            result.addResultLine("please enter a natural number");
            System.out.println("please enter a natural number");
            return result;
        }

        //Start timer
        long timerStart = System.nanoTime();

        //add input to result text
        //result.addResultLine("Testing "+bN+",");
        System.out.println("testing "+bN.toString());
        result.addResultLine(bN+",");

        //reject 1
        if(bN.compareTo(BigInteger.ONE)==0)
        {
            System.out.println("1 is neither prime or composite");
            result.addResultLine("1 is neither prime or composite");

            result.setPrimeBoolean(false);
            isPrime = false;

            long timerend = System.nanoTime()-timerStart;
            result.setTime(timerend);
            System.out.println(timerend+" ns");

            result.addResultLine(isPrime+"\r\n");

            return result;
        }

        //prelims step 1
        BigInteger bA = new BigInteger("1");
        //BigInteger b1 = new BigInteger("1");

        int b = 2;
        //int maxb = 250;
        //BigInteger maxB = new BigInteger("250");
        //int maxa = 250;
        //BigInteger maxA = new BigInteger("250");

        //Step 1 If ( n = a^b for a in natural numbers and b > 1), output COMPOSITE
        if(AKS.checkPow(bN,bN,bN))
        //here the arguments are n, max a,max b
        {
            //return Composite
            result.setPrimeBoolean(false);
            isPrime = false;

            //result.addResultLine("n = a^b");
            //result.addResultLine("a = "+bA.toString());
            //result.addResultLine("b = "+b);

            System.out.println("n = a^b");
            System.out.println("a = "+bA.toString());
            System.out.println("b = "+b);

            long timerEnd = System.nanoTime()-timerStart;

            result.setTime(timerEnd);
            System.out.println(timerEnd+" ns");

            //result.addResultLine("Failed at Step 1, "+bN+" = a^b with a = "+bA.toString()+" and b = "+b+",");
            System.out.println("Failed at Step 1, "+bN+" = a^b with a = "+bA.toString()+" and b = "+b+",");

            result.addResultLine(timerEnd+",");
            result.addResultLine(isPrime+"\r\n");

            return result;
        }



        /*
        //OLD STEP 1 IN NON LINEAR TIME
        //iterate through a's
        for (int i=0; i<bN.intValueExact(); i++)
        {
            //for every a do test until b == bMAX
            for (int j =2; j <bN.intValueExact(); j++)
            {
                if (bN.equals(bA.pow(b)))
                {
                    //return Composite
                    result.setPrimeBoolean(false);
                    isPrime = false;

                    //result.addResultLine("n = a^b");
                    //result.addResultLine("a = "+bA.toString());
                    //result.addResultLine("b = "+b);

                    System.out.println("n = a^b");
                    System.out.println("a = "+bA.toString());
                    System.out.println("b = "+b);

                    long timerEnd = System.nanoTime()-timerStart;

                    result.setTime(timerEnd);
                    System.out.println(timerEnd+" ns");

                    //result.addResultLine("Failed at Step 1, "+bN+" = a^b with a = "+bA.toString()+" and b = "+b+",");
                    System.out.println("Failed at Step 1, "+bN+" = a^b with a = "+bA.toString()+" and b = "+b+",");

                    result.addResultLine(timerEnd+",");
                    result.addResultLine(isPrime+"\r\n");

                    return result;
                }
                else
                {
                    //System.out.println(bN + " successfully passed substep 1 with a = " + bA + " and b = " + b);
                    b++;
                }
            }
            //reset b
            b = 2;
            bA = bA.add(b1);
        }

        */
        System.out.println(bN+" successfully passed step 1");


        //Step 2 Find the smallest r such that o_r(n) > log^2 n
        BigInteger k = BigInteger.ZERO;
        BigInteger r = BigInteger.ONE;


        //inspired from https://github.com/kisileno/CryptoMethods/ProbablePrimeTest/src/main/java/com/kpi/aks/AKS.java
        while (k.doubleValue()<logp2(bN.doubleValue()))
        {
            //check if k is multiplicative order with current r
            r = r.add(BigInteger.ONE);
            k = multiorder(bN,r);
        }


        //Step 3 If 1 < gcd(a,n) < n for some a <= r, output COMPOSITE
        for(BigInteger i = BigInteger.valueOf(2); i.compareTo(r) <= 0; i = i.add(BigInteger.ONE))
        {
            BigInteger gcd = bN.gcd(i);

            if(gcd.compareTo(BigInteger.ONE) > 0 && gcd.compareTo(bN) < 0)
            {

                //Return COMPOSITE

                result.setPrimeBoolean(false);
                isPrime = false;

                //result.addResultLine("failed at Step 3,");
                //result.addResultLine("1 < "+gcd.toString()+" < "+bN.toString()+",");
                System.out.println("failed at Step 3");
                System.out.println("1 < "+gcd.toString()+" < "+bN.toString());

                long timerEnd = System.nanoTime()-timerStart;
                result.setTime(timerEnd);
                System.out.println(timerEnd+" ns");

                result.addResultLine(timerEnd+",");
                result.addResultLine(isPrime+"\r\n");

                //result.addResultLine("execution time = ,"+timerEnd+" ms"+"\r\n");

                return result;
            }
        }

        System.out.println(bN+" successfully passed step 3");


        //Step 4 If n <= r, output PRIME
        if(bN.compareTo(r) == -1 || bN.compareTo(r)== 0)
        {
            result.setPrimeBoolean(true);
            isPrime = true;

            //result.addResultLine("n <= r,");
            //result.addResultLine("with n = "+ bN.toString() + "and r = " + r+",");

            System.out.println("n <= r");
            System.out.println("n = "+ bN.toString() + "and r = " + r);

            long timerEnd = System.nanoTime()-timerStart;
            result.setTime(timerEnd);

            result.addResultLine("execution time = ,"+timerEnd+" ns"+"\r\n");
            System.out.println(timerEnd +" ns");

            result.addResultLine(timerEnd+",");
            result.addResultLine(isPrime+"\r\n");

            return result;
        }

        System.out.println(bN+" failed step 4");


        //Step 5 For i = 1 to sqrt(totient)log(n) do
        // if (X+i)^n <> X^n + i (mod X^r - 1,n), output composite;


        int iMax = (int) (Math.sqrt(totient(r).doubleValue()) * Math.log(bN.doubleValue()));

        // X^r - 1
        Poly modPoly = new Poly(BigInteger.ONE, r.intValue()).minus(new Poly(BigInteger.ONE,0));
        // X^n (mod X^r - 1, n)
        Poly tempPoly = new Poly(BigInteger.ONE, 1).modPow(bN, modPoly, bN);

        for( int i = 1; i <= iMax; i++ )
        {
            Poly polyI = new Poly(BigInteger.valueOf(i),0);

            // X^n + i (mod X^r - 1, n)
            Poly compareto = tempPoly.plus(polyI);

            Poly p = new Poly(BigInteger.ONE,1).plus(polyI).modPow(bN, modPoly, bN);

            if( !compareto.equals(p) )
            {

                result.setPrimeBoolean(false);
                isPrime = false;

                //result.addResultLine("(x+" + i + ")^" + n + " (mod x^" + r + " - 1, " + n + ") = " + outcome+",");
                //result.addResultLine("x^" + n + " + " + i + " (mod x^" + r + " - 1, " + n + ") = " + p+",");
                //result.addResultLine("failed at step 5");

                System.out.println("(x+" + i + ")^" + n + " (mod x^" + r + " - 1, " + n + ") = " + compareto);
                System.out.println("x^" + n + " + " + i + " (mod x^" + r + " - 1, " + n + ") = " + p);
                System.out.println("failed at step 5");


                long timerEnd = System.nanoTime()-timerStart;
                result.setTime(timerEnd);

                //result.addResultLine("execution time = ,"+timerEnd+" ms"+"\r\n");

                result.addResultLine(timerEnd+",");
                result.addResultLine(isPrime+"\r\n");

                return result;
            }
        }


        long timerEnd = System.nanoTime()-timerStart;


        result.setPrimeBoolean(true);
        isPrime = true;

        //result.setTime(timerEnd);
        System.out.println(timerEnd+ " ns");

        //result.addResultLine("successfully passed AKS test ");
        //result.addResultLine(bN+" is prime,");

        System.out.println("successfully passed AKS test at step 5");
        System.out.println(bN+" is prime");


        //result.addResultLine("execution time = ,"+timerEnd+" ms"+"\r\n");
        result.addResultLine(timerEnd+",");
        result.addResultLine(isPrime+"\r\n");


        return result;
    }



    //create log base 2
    public static double logb2 (double n)
    {
        double logb2 = (Math.log10(n)/Math.log10(2));
        return logb2;
    }

    //create log base 8
    public static double logb8 (double n)
    {
        double logb8 = (Math.log10(n)/Math.log10(8));
        return logb8;
    }

    //calculate log^2
    public static double logp2 (double n)
    {
        double logp2 = (Math.log(n));
        return logp2 *logp2;
    }

    //find multiplicity order
    //returns multiplicative order or -1 if none exists
    public static BigInteger multiorder (BigInteger n, BigInteger r)
    {
        BigInteger k = BigInteger.ZERO;
        BigInteger result;


        do
        {
            k = k.add(BigInteger.ONE);
            result = n.modPow(k,r);
        }
        while(result.compareTo(BigInteger.ONE) != 0 && r.compareTo(k) > 0);

        /*while (result.compareTo(BigInteger.ONE) != 0 && r.compareTo(k) > 1)
        {
            result = n.modPow(k,r);
            k = k.add(BigInteger.ONE);
        }*/

        if (r.compareTo(k) <= 0)
        {
            return BigInteger.ONE.negate();
        }
        else
        {
            return k;
        }

    }

    //calculate totient of n
    public static BigInteger totient( BigInteger n)
    {
        BigInteger answer = n;

        for( BigInteger i = BigInteger.valueOf(2); n.compareTo(i.multiply(i)) > 0; i = i.add(BigInteger.ONE) )
        {
            if (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                answer = answer.subtract(answer.divide(i));

            while (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                n = n.divide(i);
        }

        if (n.compareTo(BigInteger.ONE) > 0)
            answer = answer.subtract(answer.divide(n));

        return answer;

    }

    //returns true if n = x^k
    public static boolean checkPow(BigInteger N,BigInteger K, BigInteger X)
    {
        boolean result = false;
        double f = AKS.logb2(N.doubleValue());

        System.out.println("checking POW");

        //step 1
        BigInteger b = BigInteger.ONE;


        while(true)
        {
            //step 2
            //int r = POW_b_lg8k(x,k)

            double trunc = b.doubleValue() + AKS.logb8(K.doubleValue());
            //System.out.println("trunc = "+ trunc);

            //get x^k
            BigInteger XK = X.pow(K.intValue());
            //System.out.println("XK = "+XK.toString());

            //convert to binary
            String bXK = XK.toString(2);
            //System.out.println("bXK = "+bXK);

            //trunc up to b
            String bR = bXK.substring(0, (int) trunc);
            //System.out.println("bR = "+bR);

            //add zeros
            char zero = '0';
            int number = bXK.length()-bR.length();

            char[] repeat = new char[number];
            Arrays.fill(repeat, zero);
            bR += new String(repeat);

            //System.out.println(bR);

            //set r
            BigInteger R = new BigInteger(bR,2);
            //System.out.println("R = "+R.toString());

            //step 3
            if(N.compareTo(R)<0)
            {
                result = false;
                break;
            }

            //step 4
            //build nR R*(1+2^-b)
            BigInteger b2 = new BigInteger("2");
            BigInteger nR = R.multiply(BigInteger.ONE.add(b2.pow(b.negate().intValue())));


            if(nR.compareTo(N)<=0)
            {
                result = false;
                break;
            }

            //step 5
            if(b.doubleValue()>=f)
            {
                result = true;
                break;
            }

            //step 6
            if(f<=b.multiply(b2).doubleValue())
            {

                b = new BigInteger(Double.toString(f));
            }
            if(f>b.multiply(b2).doubleValue())
            {
                b = b.multiply(b2);
            }

            System.out.println("checking step 1 with b = "+b);
        }

        return result;
    }

}
