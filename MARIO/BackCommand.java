public class BackCommand implements Command {

private Receiver r;


public void setReceiver(Receiver r1){
    this.r = r1;
}

	public void execute() {
		this.r.doAction();
	}
	
}
