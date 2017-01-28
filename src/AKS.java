import java.math.BigInteger;

/**
 * Created by Stef on 1/27/2017.
 */
public class AKS {

    private static int n;
    private static BigInteger bN;
    private static boolean isPrime = false;

    public Result runAKS (String input){


        //create Result type object
        Result result = new Result("",false,0);

        //check input validity
        try{n = Integer.parseInt(input);}
        catch(Exception e)
        {
            result.addResultLine("please enter a natural number");
            System.out.println("please enter a natural number");
            return result;
        }

        //Start timer
        long timerStart = System.currentTimeMillis();

        //create bigInteger Object
        bN = new BigInteger(n +"");


        //prelims step 1
        BigInteger bA = new BigInteger("0");
        BigInteger b1 = new BigInteger("1");

        int b = 2;
        int maxb = Integer.MAX_VALUE;
        int maxa = Integer.MAX_VALUE;

        //Step 1 If ( n = a^b for a in natural numbers and b > 1), output COMPOSITE
        //TODO think about limits of a and b

        //iterate through a's
        for (int i=0; i<maxa; i++)
        {

            //for every a do test until b == bMAX
            for (int j =2; j <maxb; j++)
            {
                if (bN.equals(bA.pow(b)))
                {
                    //return Composite
                    result.setPrimeBoolean(false);
                    isPrime = false;

                    result.addResultLine("n = a^b");
                    result.addResultLine("a = "+bA.toString());
                    result.addResultLine("b = "+b);
                    System.out.println("n = a^b");
                    System.out.println("a = "+bA.toString());
                    System.out.println("b = "+b);

                    long timerEnd = System.currentTimeMillis()-timerStart;
                    result.setTime(timerEnd);
                    System.out.println(timerEnd);

                    return result;
                }
                else
                {
                    b++;
                }
            }

            bA = bA.add(b1);
        }



        //Step 2 Find the smallest r such that o_r(n) > log^2 n
        // o_r(n) is the multiplicative order of n modulo r
        // the multiplicative order of n modulo r is the
        // smallest positive integer k with	n^k = 1 (mod r).
        BigInteger k = BigInteger.ONE;
        BigInteger r = BigInteger.ONE;


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

                result.addResultLine("failed at Step 3");
                result.addResultLine("1 < "+gcd.toString()+" < "+bN.toString());
                System.out.println("failed at Step 3");
                System.out.println("1 < "+gcd.toString()+" < "+bN.toString());

                long timerEnd = System.currentTimeMillis()-timerStart;
                result.setTime(timerEnd);
                System.out.println(timerEnd);

                return result;
            }
        }


        //Step 4 If n <= r, output PRIME
        if(bN.compareTo(r) == -1 || bN.compareTo(r)== 0)
        {
            result.setPrimeBoolean(true);
            isPrime = true;

            result.addResultLine("n <= r");
            result.addResultLine("n = "+ bN.toString() + "and r = " + r);

            System.out.println("n <= r");
            System.out.println("n = "+ bN.toString() + "and r = " + r);

            long timerEnd = System.currentTimeMillis()-timerStart;
            result.setTime(timerEnd);
            System.out.println(timerEnd +" ms");

            return result;
        }

        //Step 5 For i = 1 to sqrt(totient)log(n) do
        // if (X+i)^n <> X^n + i (mod X^r - 1,n), output composite;

        return result;
    }

    //calculate log base 2
    public double logp2 (double n)
    {
        double logp2 = (Math.log(n));
        return logp2 *logp2;
    }

    public BigInteger multiorder (BigInteger n, BigInteger r)
    {
        BigInteger k = BigInteger.ZERO;
        BigInteger result = n.modPow(k,r);

        while (result.compareTo(BigInteger.ONE) != 0 && r.compareTo(k) > 0)
        {
            k = k.add(BigInteger.ONE);
            result = n.modPow(k,r);
        }

        if (r.compareTo(k) <= 0)
        {
            return BigInteger.ONE.negate();
        }
        else
        {
            return k;
        }

    }

}
