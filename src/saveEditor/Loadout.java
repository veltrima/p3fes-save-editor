package saveEditor;

public class Loadout {
	private int levelReq;
	private boolean needsUlt;
	private String skills;
	
	public Loadout (int levelReq, boolean needsUlt, String skills) {
		this.levelReq = levelReq;
		this.needsUlt = needsUlt;
		this.skills = skills;
	}
	
	public int getLevelReq() {
		return levelReq;
	}
	
	public boolean getNeedsUlt() {
		return needsUlt;
	}
	
	public String getSkills() {
		return skills;
	}
}
