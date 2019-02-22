package edu.smith.cs.csc212.p4;

public class LockedExit extends Exit{
	/*
	 * exactly like secret exit, changes marked with //
	 */
	private boolean hidden = true;
	
	// exit indicates which key will open it
	public String key;
	
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.key = key;
	}
	
	public String getTarget() {
		return super.getTarget();

	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public String toString() {
		return super.toString();
		}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean goesToSamePlace(Exit other) {
		return super.goesToSamePlace(other);
	}
	public boolean equals(Object other) {
		return super.equals(other);
	}
	@Override
	public boolean isSecret() {
		return hidden;
	}
	@Override
	public void search() {
		this.hidden = false;
		// tells which key was used
		System.out.println("You used the " + this.key);
	}
	

}
