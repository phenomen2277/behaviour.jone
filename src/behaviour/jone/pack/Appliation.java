package behaviour.jone.pack;

public class Appliation {

	public static void main(String[] args) {
		FailureBehavior fb2 = new FailureBehavior(1, 5, 100);
		FailureBehavior fb = new FailureBehavior();
		fb.loadFromFile("/tmp/profile.loss");
		for (int i = 0; i < 500; i++) {
			fb.monitor();
			if (fb.canSimulate()) {
				System.out.println(fb.canSimulate());
				fb.simulate();

			} else {
				System.out.println("-");
			}

		}
		
		ConsoleGateway cg = new ConsoleGateway(args);
		
		if(cg.hasMessageLossClass()){
			FailureBehavior fb3 = cg.getMessageLossClass();
			System.out.println("getting message loss class " + fb3.getNumberOfSimulations());
		}
		
		if(cg.hasMessageDelayClass()){
			FailureBehavior fb4 = cg.getMessageDelayClass();
			System.out.println("getting message delay class " + fb4.getNumberOfSimulations());
		}
			
		

	}

}
