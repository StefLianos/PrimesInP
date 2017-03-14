import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Stef on 2/7/2017.
 */


public class ExecuteFromSCV {

    public static void executeNaiveFromSCV(String path)
    {

        String currentDate = ExecuteFromSCV.getDate();

        String [] inputs = ExecuteFromSCV.readFile(path);
        System.out.println("input read !");

        //run algorithms and create CSV

        File output = new File(currentDate+" output.csv");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));

            for(int i =0;i < inputs.length; i++) {

                //write result on csv
                Result result = Naive.runNaive(inputs[i]);
                bw.write(result.getText());
                //add comma
                //bw.write(",");
                System.out.println(result.getText());

            }


            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeFermatFromSCV(String path)
    {

        String currentDate = ExecuteFromSCV.getDate();

        String [] inputs = ExecuteFromSCV.readFile(path);

        //run Fermat algorithms and create CSV

        File output = new File(currentDate+" output.csv");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));

            for(int i =0;i < inputs.length; i++) {

                //write result on csv
                Result result = Fermat.runFermat(inputs[i]);
                bw.write(result.getText());
                //add comma
                //bw.write(",");
                System.out.println(result.getText());

            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeAKSFromSCV(String path)
    {

        String currentDate = ExecuteFromSCV.getDate();

        String [] inputs = ExecuteFromSCV.readFile(path);

        //run AKS algorithms and create CSV

        File output = new File(currentDate+" output.csv");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            System.out.println("bw created");

            for(int i =0;i < inputs.length; i++) {

                //write result on csv
                System.out.println("test "+i+"/"+inputs.length);
                Result result = AKS.runAKS(inputs[i]);
                bw.write(result.getText());
                //add comma
                //bw.write(",");
                //System.out.println(result.getText());

            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] readFile(String path)
    {
        //read file
        String[] inputs = null;

        File file = new File(path);
        try {

            Scanner inputStrem = new Scanner(file);

            while(inputStrem.hasNext())
            {
                String data = inputStrem.next();
                //System.out.println(data);

                //split data every ,
                inputs = data.split(",");
                //System.out.println(Arrays.toString(inputs));
            }
            inputStrem.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("finished reading input");
        return inputs;
    }

    public  static  String getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
        Date date = new Date();
        return(dateFormat.format(date)); //16/11/2016 12:08:43
    }
}
