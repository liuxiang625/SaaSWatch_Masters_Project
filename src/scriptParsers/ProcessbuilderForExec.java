package scriptParsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessbuilderForExec {
	public static void main(String[] args) throws Exception
    {
		List<String> command = new ArrayList<String>();
		//command.add("lsof");
		//command.add("-i");
		//command.add("TCP");
		//command.add("-p");
		//command.add("512");
		command.add("/bin/sh");
		command.add("-c");
		command.add("lsof -p 512 |grep TCP");
		
        ProcessBuilder pb = new ProcessBuilder("/bin/bash","/Users/liuxiang625/SJSU/295B/testScript/lsofScript.sh");
        Process p = pb.start();
        String output = loadStream(p.getInputStream());
        String error  = loadStream(p.getErrorStream());
        int rc = p.waitFor();
        System.out.println("Process ended with rc=" + rc);
        System.out.println("\nStandard Output:\n");
        System.out.println(output);
        System.out.println("\nStandard Error:\n");
        System.out.println(error);
    }

    private static String loadStream(InputStream s) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(s));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=br.readLine()) != null)
        	//System.out.println("Match" + RegexParsers.test(line));
            sb.append(line).append("\n");
        return sb.toString();
    }	
}
