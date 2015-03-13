package behaviour.jone.pack;


public class ConsoleGateway {
	private String[] args;
	public ConsoleGateway(String[] arguments) {
		args = arguments;
	}
	
	public boolean hasArguments() {
		if(args.length > 0) return true;
		return false;
	}
	
	public boolean hasMessageLossClass() {
		for(String filePath: args){
			if(getFileExtension(filePath).toLowerCase().equals("loss")){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasMessageDelayClass() {
		for(String filePath: args){
			if(getFileExtension(filePath).toLowerCase().equals("delay")){
				return true;
			}
		}
		return false;
	}
	
	public FailureBehavior getMessageDelayClass() {
		FailureBehavior ret = null;
		
		for(String filePath: args){
			if(getFileExtension(filePath).toLowerCase().equals("delay")){
				ret = new FailureBehavior();
				ret.loadFromFile(filePath);
				return ret;
			}
		}
		
		return null;
	}
	
	public FailureBehavior getMessageLossClass() {
		FailureBehavior ret = null;
		
		for(String filePath: args){
			if(getFileExtension(filePath).toLowerCase().equals("loss")){
				ret = new FailureBehavior();
				ret.loadFromFile(filePath);
				return ret;
			}
		}
		
		return null;
	}
	
	private String getFileExtension(String FilePath){
		String extension = "";

		int i = FilePath.lastIndexOf('.');
		if (i >= 0) {
		    extension = FilePath.substring(i+1);
		}
		
		return extension;
	}

}
