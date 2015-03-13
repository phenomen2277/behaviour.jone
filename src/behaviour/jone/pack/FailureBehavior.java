package behaviour.jone.pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class FailureBehavior implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int from;
	private int to;
	private int numberOfMessages;
	private int counter;
	private int failRate;
	private int numberOfSimulations;

	public FailureBehavior(int From, int To, int NumberOfMessages) {
		from = From;
		to = To;
		numberOfMessages = NumberOfMessages;
		counter = 0;
		failRate = randInt(from, to);
		numberOfSimulations = ((failRate * (numberOfMessages)) / 100);
	}
	
	public FailureBehavior() {
		from = 0;
		to = 0;
		numberOfMessages = 0;
		counter = 0;
		numberOfSimulations = 0;
	}

	private void regenerateWhenLimitReached() {
		if (counter >= numberOfMessages) {
			failRate = randInt(from, to);
			numberOfSimulations = ((failRate * (numberOfMessages)) / 100);
			counter = 0;
			System.out.println("Regenerate" + numberOfSimulations + " ");
		}
	}

	//Call this method where the simulated behavior will be executed
	public void monitor() {
		this.regenerateWhenLimitReached();
		counter++;

	}

	//Return true if the given behavior can be simulated, otherwise false
	public boolean canSimulate() {
		if(numberOfSimulations > 0) return true;
		
		return false;
	}

	//After the simulation, tell the object that you did simulate.
	public void simulate() {
		if (numberOfSimulations > 0 ) numberOfSimulations--;
	}
	
	//Return the number of simulations left.
	public int getNumberOfSimulations() {
		return numberOfSimulations;
	}
	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	//Load this object's instance from file given by the filename
	public boolean loadFromFile(String fileName)
	{
		try{
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		FailureBehavior obj = (FailureBehavior) ois.readObject();
		ois.close();
		this.from = obj.from;
		this.to = obj.to;
		this.numberOfMessages = obj.numberOfMessages;
		this.failRate = randInt(this.from, this.to);
		numberOfSimulations = ((failRate * (numberOfMessages)) / 100);
		} catch(Exception e){
			return false;
		}
		
		return true;
	}

	//Save this object instance to a file
	public boolean saveToFile(String fileName){
		try{
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		FailureBehavior fb = new FailureBehavior(from, to, numberOfMessages);
		oos.writeObject(fb);

		fos.close();
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
}
