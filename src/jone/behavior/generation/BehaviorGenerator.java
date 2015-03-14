package jone.behavior.generation;

import java.util.Scanner;

import behaviour.jone.pack.FailureBehavior;

public class BehaviorGenerator {

	public static void main(String[] args) {
		FailureBehavior fb = null;
		int from = 0;
		int to = 0;
		int numberOfMessages = 0;
		String[] fileExtensions = {"loss", "delay"};
		int choice = 0;
		String storagePath = "";
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("The application will generate behavior files.");
		
		System.out.print("The failure rate will start from: ");
		from = input.nextInt();
		
		System.out.print("\nThe failure rate will end at: ");
		to = input.nextInt();
		
		System.out.print("\nMaximum number of messages for which the behavior will repeat: ");
		numberOfMessages = input.nextInt();
		
		System.out.print("\nWhat type of behavior. (1) loss, (2) delay: ");
		choice = input.nextInt();
		
		if(choice < 0 || choice > 2){
			System.out.println("YOUR CHOICE IS INVALID");
			return;
		}
		
		System.out.print("\nThe path of folder in which the file will be stored: ");
		storagePath = input.next();
		
		System.out.println("\n\nYour input is...");
		System.out.println("from: " + from);
		System.out.println("To: " + to);
		System.out.println("Number of messages: " + numberOfMessages);
		System.out.println("Behavior type: " + fileExtensions[choice-1]);
		System.out.println("Storage path: " + storagePath);
		
		
		String fullPath = storagePath + "profile." + fileExtensions[choice-1];
		fb = new FailureBehavior(from, to, numberOfMessages);
		if(fb.saveToFile(fullPath)){
			System.out.println("the file has been saved in " + fullPath);
		} else {
			System.out.println("Error when saving the file");
		}
	}

}
