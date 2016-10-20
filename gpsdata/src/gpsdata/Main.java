package gpsdata;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String validgps = null;
		String input = null;
		String time = null;
		int startindex,endindex;
		int checksum,postfix1,postfix2;
		int hour,minute,second;
		do
		{
			input = in.nextLine();
			if(input.startsWith("$GPRMC,")){
				startindex = input.indexOf('$');
				endindex = input.indexOf('*');
				checksum = input.charAt(startindex+1);
				for(int i = startindex+2;i < endindex;i++)
				{
					checksum ^= input.charAt(i);
				}
				checksum %= 65536;
				if(input.charAt(endindex+1) >= 65){
					postfix1 = input.charAt(endindex+1)-55; 
				}
				else{
					postfix1 = input.charAt(endindex+1) - 48;
				}
				if(input.charAt(endindex+2) >= 65){
					postfix2 = input.charAt(endindex+2) - 55;
				}
				else{
					postfix2 = input.charAt(endindex+2) - 48;
				}
				if(checksum == postfix1*16 + postfix2){
					validgps = input;
				}
			}	
		}while(!(input.equals("END")));
		in.close();
		//time output
		if(validgps != null){
			startindex = validgps.indexOf(',');
			time = validgps.substring(startindex + 1);
			hour = (time.charAt(0) - 48)*10 + time.charAt(1) - 48 + 8;
			if(hour > 24){
				hour -= 24;
			}
			minute = (time.charAt(2) - 48)*10 + time.charAt(3) - 48;
			second = (time.charAt(4) - 48)*10 + time.charAt(5) - 48;
			if(hour < 10){
				System.out.print("0");
			}
			System.out.print(hour + ":");
			if(minute < 10){
				System.out.print("0");
			}
			System.out.print(minute + ":");
			if(second < 10){
				System.out.print("0");
			}
			System.out.println(second);
		}
	}

}
