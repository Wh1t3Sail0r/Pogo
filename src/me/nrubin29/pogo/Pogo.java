package me.nrubin29.pogo;

import me.nrubin29.pogo.ide.IDE;
import me.nrubin29.pogo.lang.Block;

import javax.swing.*;

public class Pogo {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable e) {
            	System.out.println("The following stack trace was caught and will be shown to the user:");
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
		
        new IDE();
	}

    public static String implode(String[] strs, Block block) {
        StringBuilder builder = new StringBuilder();

        boolean inQuotes = false;
        
        for (String str : strs) {
        	if (str.startsWith("\"")) inQuotes = true;
        	
        	if (inQuotes) builder.append(str.replaceAll("\"", ""));
        	
        	else {
            	if (block != null) {
                    try {
                        builder.append(block.getVariable(str).getValue());
                    } catch (InvalidCodeException e) {
                        builder.append(str);
                    }
                }
        	}
        	
        	builder.append(" ");
        	
        	if (str.endsWith("\"")) inQuotes = false;
        }
        
        return builder.toString().trim();
    }
}