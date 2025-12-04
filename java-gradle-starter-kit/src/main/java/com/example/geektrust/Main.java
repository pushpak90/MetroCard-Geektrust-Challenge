package com.example.geektrust;

import com.example.geektrust.processor.CommandProcessor;

public class Main {
    public static void main(String[] args)  {
        /*
        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        */
        if (args.length == 0) {
            System.err.println("Please provide input file path.");
            return;
        }

        try {
            CommandProcessor processor = new CommandProcessor();
            processor.process(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
