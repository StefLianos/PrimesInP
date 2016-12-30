/**
 * Created by Stef on 11/6/2016.
 */
public class Result {

    private String st;
    private boolean b;
    private long t;

    public Result(String result, boolean isPrime, long time)
    {
        st=result;
        b=isPrime;
        t=time;
    }

    public boolean getPrimeBoolean()
    {
        return b;
    }

    public void setPrimeBoolean(boolean prime)
    {
        b=prime;
    }

    public String getText()
    {
       return  st;
    }

    public void addResultLine(String newline)
    {
        st=st+"\r\n"+newline;
    }

    public long getTime()
    {
        return t;
    }

    public String getTimeString()
    {
        return t+" ms";
    }

    public void setTime(long time)
    {
        t = time;
    }
}
