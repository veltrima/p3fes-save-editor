package saveEditor;

public class CharacterAttributes {
	private int strength, magic, endurance, agility, luck;
	
	public CharacterAttributes (int strength, int magic, int endurance, int agility, int luck) {
		this.strength = strength;
		this.magic = magic;
		this.endurance = endurance;
		this.agility = agility;
		this.luck = luck;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}
}
