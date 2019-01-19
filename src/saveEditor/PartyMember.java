package saveEditor;

import java.util.ArrayList;
import java.util.HashMap;

public class PartyMember {
	// The following are based on separate table of data
	// Clear whitespace before using, whitespace for easy reading
	// If ultimate up to 58, begin with Spring of Life (5F02)
	// Else, end with 0000
	// Format: int level, bool ult persona, string skills
	private Object[][] junpeiLoadouts = {
		{1, false, "7900 0000 0000 0000 0000 0000 0000"}, // 1
		{5, false, "7900 0100 0000 0000 0000 0000 0000"}, // 5
		{7, false, "7900 0100 C900 0000 0000 0000 0000"}, // 7
		{9, false, "7900 0100 C900 DA00 0000 0000 0000"}, // 9
		{18, false, "7900 0100 C900 DA00 7100 0000 0000"}, // 18
		{20, false, "7900 0100 C900 DA00 7100 7E00 0000"}, // 20
		{25, false, "7900 0100 C900 DA00 7E00 7200 0000"}, // 25
		{32, false, "7900 C900 DA00 7E00 7200 0200 0000"}, // 32
		{35, false, "7900 C900 DA00 7E00 7200 0200 2002"}, // 35
		{40, false, "7900 C900 DA00 7200 0200 2002 8C00"}, // 40
		{44, false, "7900 C900 7200 0200 2002 8C00 DB00"}, // 44
		{45, false, "7900 C900 7200 0200 8C00 DB00 2102"}, // 45
		{50, false, "C900 7200 0200 8C00 DB00 2102 7F00"}, // 50
		{55, false, "C900 0200 8C00 DB00 2102 7F00 7600"}, // 55
		{58, true, "5F02 C900 0200 8C00 DB00 7F00 7600 2202"}, // 58 Ultimate
		{60, true, "5F02 0200 8C00 DB00 7F00 7600 2202 E500"}, // 60 U
		{64, true, "5F02 8C00 DB00 7F00 7600 2202 E500 0300"}, // 64 U
		{70, true, "5F02 8C00 DB00 7600 2202 E500 0300 8600"}, // 70 U
		{75, true, "5F02 8C00 DB00 7600 2202 E500 0300 9000"} // 75 U
	};
	
	// Format: int level, bool ult persona, string skills
	private Object[][] yukariLoadouts = {
		{1, false, "C000 0000 0000 0000 0000 0000 0000 0000"}, // 1
		{5, false, "C000 C700 0000 0000 0000 0000 0000 0000"}, // 5
		{16, false, "C000 C700 0A00 0000 0000 0000 0000 0000"}, // 16
		{21, false, "C000 C700 0A00 ED00 0000 0000 0000 0000"}, // 21
		{22, false, "C700 0A00 ED00 0D00 C100 0000 0000 0000"}, // 22
		{25, false, "C700 0A00 ED00 0D00 C100 CC00 0000 0000"}, // 25
		{28, false, "C700 ED00 0D00 C100 CC00 0B00 0000 0000"}, // 28
		{32, false, "ED00 0D00 C100 CC00 0B00 C800 0000 0000"}, // 32
		{36, false, "ED00 0D00 C100 CC00 0B00 C800 C400 0000"}, // 36
		{43, false, "ED00 C100 CC00 0B00 C800 C400 0E00 0000"}, // 43
		{46, false, "ED00 CC00 0B00 C800 C400 0E00 C200 0000"}, // 46
		{52, true, "ED00 CC00 C800 C400 0E00 C200 0C00 0000"}, // 52 U
		{57, true, "ED00 CC00 C800 C400 0E00 C200 0C00 E700"}, // 57 U
		{65, true, "ED00 CC00 C800 C400 C200 0C00 E700 0F00"}, // 65 U
		{68, true, "ED00 C800 C400 C200 0C00 E700 0F00 CD00"}, // 68 U
		{74, true, "ED00 C800 C200 0C00 E700 0F00 CD00 C500"}  // 74 U
	};
	
	// Format: int level, bool ult persona, string skills
	private Object[][] mitsuruLoadouts = {
		{1, false, "1300 1600 C000 3900 0000 0000 0000 0000"}, // 1
		{21, false, "1600 C000 3900 1400 0000 0000 0000 0000"}, // 21	
		{25, false, "1600 3900 1400 C100 0000 0000 0000 0000"}, // 25
		{27, false, "1600 3900 1400 C100 4C00 0000 0000 0000"}, // 27
		{32, false, "1600 3900 1400 C100 4C00 3E00 0000 0000"}, // 32
		{42, false, "3900 1400 C100 4C00 3E00 1700 0000 0000"}, // 42
		{45, false, "3900 1400 C100 4C00 3E00 1700 1202 0000"}, // 45
		{50, false, "3900 1400 C100 4C00 3E00 1700 1202 DD00"}, // 50
		{55, false, "3900 C100 4C00 3E00 1700 1202 DD00 1500"}, // 55
		{58, true, "3900 4C00 3E00 1700 1202 DD00 1500 C200"}, // 58 U
		{61, true, "4C00 3E00 1700 1202 DD00 1500 C200 E600"}, // 61 U
		{71, true, "4C00 3E00 1202 DD00 1500 C200 E600 1800"}, // 71 U
		{76, true, "4C00 3E00 DD00 1500 C200 E600 1800 1302"}  // 76 U
	};
	
	// Format: int level, bool ult persona, string skills
	private Object[][] akihikoLoadouts = {
		{1, false, "7400 1C00 C000 0000 0000 0000 0000 0000"}, // 1
		{16, false, "7400 1C00 C000 CE00 0000 0000 0000 0000"}, // 16
		{21, false, "7400 1C00 C000 CE00 1F00 0000 0000 0000"}, // 21
		{25, false, "7400 1C00 C000 CE00 1F00 D200 0000 0000"}, // 25
		{29, false, "7400 C000 CE00 1F00 D200 1D00 0000 0000"}, // 29
		{33, false, "7400 C000 CE00 1F00 D200 1D00 D000 0000"}, // 33
		{37, false, "7400 C000 CE00 1F00 D200 1D00 D000 1402"}, // 37
		{38, false, "7400 CE00 1F00 D200 1D00 D000 1402 C100"}, // 38
		{41, false, "7400 CE00 D200 1D00 D000 1402 C100 2000"}, // 41
		{47, true, "7400 D200 1D00 D000 1402 C100 2000 CF00"}, // 47 U
		{50, true, "D200 1D00 D000 1402 C100 2000 CF00 4A02"}, // 50 U
		{54, true, "D200 D000 1402 C100 2000 CF00 4A02 1E00"}, // 54 U
		{57, true, "D000 1402 C100 2000 CF00 4A02 1E00 D300"}, // 57 U
		{65, true, "D000 1402 2000 CF00 4A02 1E00 D300 C200"}, // 65 U
		{66, true, "1402 2000 CF00 4A02 1E00 D300 C200 D100"}, // 66 U
		{74, true, "1402 CF00 4A02 1E00 D300 C200 D100 2100"}, // 74 U
		{76, true, "CF00 4A02 1E00 D300 C200 D100 2100 1502"}  // 76 U 
	};
	
	// Format: int level, bool ult persona, string skills
	private Object[][] kenLoadouts = {
		{1, false, "2D00 9200 1D00 0000 0000 0000 0000 0000"}, // 1
		{37, false, "2D00 9200 1D00 C100 0000 0000 0000 0000"}, // 37
		{41, false, "9200 1D00 C100 2F00 0000 0000 0000 0000"}, // 41
		{42, false, "9200 1D00 C100 2F00 CC00 0000 0000 0000"}, // 42
		{51, true, "9200 1D00 C100 2F00 CC00 C400 0000 0000"}, // 51 U
		{54, true, "9200 1D00 C100 2F00 CC00 C400 5702 0000"}, // 54 U
		{55, true, "9200 C100 2F00 CC00 C400 5702 1E00 0000"}, // 55 U
		{59, true, "C100 2F00 CC00 C400 5702 1E00 9300 0000"}, // 59 U
		{62, true, "2F00 CC00 C400 5702 1E00 9300 C200 0000"}, // 62 U
		{65, true, "2F00 CC00 C400 5702 1E00 9300 C200 4602"}, // 65 U
		{73, true, "2F00 C400 5702 1E00 9300 C200 4602 CD00"}, // 73 U
		{78, true, "2F00 5702 1E00 9300 C200 4602 CD00 C500"}  // 78 U
	};
	
	// Format: int level, string skills
	private Object[][] koromaruLoadouts = {
		{1, false, "0200 3300 D800 0000 0000 0000 0000 0000"}, // 1
		{38, false, "0200 3300 D800 0500 0000 0000 0000 0000"}, // 38
		{40, false, "0200 3300 D800 0500 2102 0000 0000 0000"}, // 40
		{42, false, "0200 3300 D800 0500 2102 3400 0000 0000"}, // 42
		{45, false, "3300 D800 0500 2102 3400 0300 0000 0000"}, // 45
		{48, false, "3300 D800 0500 2102 3400 0300 1002 0000"}, // 48
		{50, false, "3300 0500 2102 3400 0300 1002 D900 0000"}, // 50
		{52, false, "3300 0500 3400 0300 1002 D900 2202 0000"}, // 52
		{56, false, "0500 3400 0300 1002 D900 2202 3500 0000"}, // 56
		{60, false, "0500 3400 0300 1002 D900 2202 3500 E500"}, // 60
		{67, false, "3400 0300 1002 D900 2202 3500 E500 0600"}, // 67
		{71, false, "0300 1002 D900 2202 3500 E500 0600 3600"}, // 71
		{77, false, "0300 D900 2202 3500 E500 0600 3600 1102"}  // 77
	};
	
	// Format: int level, bool ult persona, string skills
	private Object[][] aigisLoadouts = {
		{1, false, "7200 7300 D800 0000 0000 0000 0000 0000"}, // 1
		{32, false, "7200 7300 D800 DA00 0000 0000 0000 0000"}, // 32
		{35, false, "7200 7300 D800 DA00 7A00 0000 0000 0000"}, // 35
		{36, false, "7200 7300 D800 DA00 7A00 D600 0000 0000"}, // 36
		{42, false, "7200 7300 D800 DA00 7A00 D600 D400 0000"}, // 42
		{47, false, "7200 7300 DA00 7A00 D600 D400 D900 0000"}, // 47
		{51, false, "7200 DA00 7A00 D600 D400 D900 7500 0000"}, // 51
		{56, false, "7200 7A00 D600 D400 D900 7500 DB00 0000"}, // 56
		{59, false, "7200 7A00 D600 D400 D900 7500 DB00 C200"}, // 59
		{60, false, "7200 7A00 D400 D900 7500 DB00 C200 D700"}, // 60
		{65, true, "7A00 D400 D900 7500 DB00 C200 D700 CD00"}, // 65 U
		{73, true, "7A00 D400 D900 DB00 C200 D700 CD00 7700"}, // 73 U
		{77, true, "D400 D900 DB00 C200 D700 CD00 7700 7800"}  // 77 U
	};
	
	// Format: int level, string skills
	private Object[][] shinjiroLoadouts = {
		{1, false, "2302 2002 7A00 0000 0000 0000 0000 0000"}, // 1
		{39, false, "2302 2002 7A00 4000 0000 0000 0000 0000"}, // 39
		{42, false, "2302 7A00 4000 2102 0000 0000 0000 0000"}, // 42
		{50, false, "2302 7A00 4000 2102 DC00 0000 0000 0000"}, // 50
		{52, false, "2302 7A00 4000 2102 DC00 7F00 0000 0000"}, // 52
		{53, false, "2302 7A00 4000 2102 DC00 7F00 7500 0000"}, // 53
		{55, false, "2302 7A00 4000 DC00 7F00 7500 2202 0000"}, // 55
		{60, false, "2302 7A00 4000 DC00 7500 2202 8000 0000"}, // 60
		{65, false, "7A00 4000 DC00 7500 2202 8000 2402 0000"}, // 65
		{72, false, "7A00 4000 DC00 2202 8000 2402 7700 0000"}, // 72
		{77, false, "7A00 4000 DC00 2202 8000 2402 7700 7800"}  // 77
	};
	
	// Format: int level, bool ult persona, string skills
	private Object[][] fuukaLoadouts = {
		{1, false, "4601 0000 0000 0000 0000 0000 0000 0000"}, // 1
		{23, false, "4601 4F01 0000 0000 0000 0000 0000 0000"}, // 23
		{32, false, "4601 4F01 5001 0000 0000 0000 0000 0000"}, // 32
		{41, false, "4601 4F01 5001 5401 0000 0000 0000 0000"}, // 41
		{50, true, "4601 4F01 5001 5401 4501 0000 0000 0000"}, // 50 U
		{72, true, "4601 4F01 5001 5401 4501 5301 0000 0000"}  // 72 U
	};
	
	// Format {min stats, max stats}
	// Stats format: level, strength, magic, endurance, agility, luck
	int[][] junpeiAttributes = {
			{1, 3, 2, 2, 2, 1},
			{99, 82, 44, 69, 56, 53}
	};
	int[][] yukariAttributes = {
			{1, 1, 3, 2, 1, 3},
			{99, 50, 91, 55, 55, 53}
	};
	int[][] mitsuruAttributes = {
			{18, 11, 16, 11, 12, 11},
			{99, 55, 85, 52, 61, 51}
	};
	int[][] akihikoAttributes = {
			{12, 9, 10, 8, 9, 7},
			{99, 69, 70, 55, 63, 47}
	};
	int[][] kenAttributes = {
			{36, 21, 25, 23, 26, 20},
			{99, 55, 66, 58, 70, 55}
	};
	int[][] koromaruAttributes = {
			{35, 20, 22, 21, 30, 19},
			{99, 58, 56, 58, 82, 50}
	};
	int[][] aigisAttributes = {
			{29, 19, 18, 25, 18, 14},
			{99, 61, 58, 84, 56, 45}
	};
	int[][] shinjiroAttributes = {
			{37, 32, 20, 27, 22, 17},
			{99, 84, 52, 69, 57, 45}
	};
	
	// Fuuka's attributes are constant I believe
		
	private String name;
	private String currentSkills;
	private int level;
	private int exp;
	private int personaFlag;
	private CharacterAttributes attributes;
	private HashMap<String, Object[][]> loadoutMap = new HashMap<>(); // Used to populate loadout array list
	private HashMap<String, int[][]> attributesMap = new HashMap<>(); // Used to make updating attributes simpler
	private ArrayList<Loadout> loadouts = new ArrayList<Loadout>();
	private boolean hasUlt;
	
	public PartyMember (String characterName) {
		name = characterName;
		initLoadouts();
	}
	
	public String getName () {
		return name;
	}
	
	public String getCurrentSkills () {
		return currentSkills;
	}
	public void setCurrentSkills (String skills) {
		currentSkills = skills;
	}
	
	public int getLevel () {
		return level;
	}
	public void setLevel (int inLevel) {
		level = inLevel;
	}
	
	public int getExp () {
		return exp;
	}
	public void setExp (int inExp) {
		exp = inExp;
	}
	
	public int getPersonaFlag () {
		return personaFlag;
	}
	public void setPersonaFlag (int inFlag) {
		personaFlag = inFlag;
	}
	
	public boolean getHasUlt () {
		return hasUlt;
	}
	public void setHasUlt (boolean ultStatus) {
		hasUlt = ultStatus;
	}
	
	public void initAttributes(int strength, int magic, int endurance, int agility, int luck) {
		attributes = new CharacterAttributes(strength, magic, endurance, agility, luck);
	}
	public CharacterAttributes getAttributes() {
		return attributes;
	}
	// Use player level to calculate attributes
	public void updateAttributes() {
		// Format {min stats, max stats}
		// Stats format: level, strength, magic, endurance, agility, luck
		if (!name.equals("fuuka")) { // Fuuka's attributes are constant
			int[][] referenceAttributes = attributesMap.get(name);
			if (level <= referenceAttributes[0][0]) {
				attributes.setStrength(referenceAttributes[0][1]);
				attributes.setMagic(referenceAttributes[0][2]);
				attributes.setEndurance(referenceAttributes[0][3]);
				attributes.setAgility(referenceAttributes[0][4]);
				attributes.setLuck(referenceAttributes[0][5]);
			} else if (level >= referenceAttributes[1][0]) {
				attributes.setStrength(referenceAttributes[1][1]);
				attributes.setMagic(referenceAttributes[1][2]);
				attributes.setEndurance(referenceAttributes[1][3]);
				attributes.setAgility(referenceAttributes[1][4]);
				attributes.setLuck(referenceAttributes[1][5]);
			} else {
				int levelDiff = referenceAttributes[1][0] - referenceAttributes[0][0];
				int pointLevels = level - referenceAttributes[0][0]; // Point earning levels
				float strengthPerLevel = (referenceAttributes[1][1] - referenceAttributes[0][1]) / levelDiff;
				float magicPerLevel = (referenceAttributes[1][2] - referenceAttributes[0][2]) / levelDiff;
				float endurancePerLevel = (referenceAttributes[1][3] - referenceAttributes[0][3]) / levelDiff;
				float agilityPerLevel = (referenceAttributes[1][4] - referenceAttributes[0][4]) / levelDiff;
				float luckPerLevel = (referenceAttributes[1][5] - referenceAttributes[0][5]) / levelDiff;
				
				attributes.setStrength(referenceAttributes[0][1] + Math.round(pointLevels * strengthPerLevel));
				attributes.setMagic(referenceAttributes[0][2] + Math.round(pointLevels * magicPerLevel));
				attributes.setEndurance(referenceAttributes[0][3] + Math.round(pointLevels * endurancePerLevel));
				attributes.setAgility(referenceAttributes[0][4] + Math.round(pointLevels * agilityPerLevel));
				attributes.setLuck(referenceAttributes[0][5] + Math.round(pointLevels * luckPerLevel)); 
			}
		}
	}
	
	// Update current skills based on current level and ult status
	public void updateCurrentSkills() {	
		// Starting from highest requirements and working backwards to lowest
		for (int j = loadouts.size() - 1; j >= 0; j--) {
			Loadout currLoadout = loadouts.get(j);
			if (level >= currLoadout.getLevelReq()) {
				if (currLoadout.getNeedsUlt() && hasUlt) {
					currentSkills = currLoadout.getSkills().replaceAll("\\s+","");
					break;
				} else if (!currLoadout.getNeedsUlt()) {
					if (name.equals("junpei")) { // Junpei is special case
						if (hasUlt) {
							currentSkills = "5F02" + currLoadout.getSkills().replaceAll("\\s+","");
							break;
						} else {
							currentSkills = currLoadout.getSkills().replaceAll("\\s+","") + "0000";
							break;
						}
					} else { // Rest of party is simple.
						currentSkills = currLoadout.getSkills().replaceAll("\\s+","");
						break;	
					}
				}
			}
		}
	}
	
	// To make initLoadouts() cleaner.
	private void initLoadoutMap () {
		loadoutMap.put("junpei", junpeiLoadouts);
		loadoutMap.put("yukari", yukariLoadouts);
		loadoutMap.put("akihiko", akihikoLoadouts);
		loadoutMap.put("mitsuru", mitsuruLoadouts);
		loadoutMap.put("ken", kenLoadouts);
		loadoutMap.put("koromaru", koromaruLoadouts);
		loadoutMap.put("shinjiro", shinjiroLoadouts);
		loadoutMap.put("aigis", aigisLoadouts);
		loadoutMap.put("fuuka", fuukaLoadouts);
	}
	
	// To make updateAttribute() cleaner
	private void initAttributeMap () {
		attributesMap.put("junpei", junpeiAttributes);
		attributesMap.put("yukari", yukariAttributes);
		attributesMap.put("akihiko", akihikoAttributes);
		attributesMap.put("mitsuru", mitsuruAttributes);
		attributesMap.put("ken", kenAttributes);
		attributesMap.put("koromaru", koromaruAttributes);
		attributesMap.put("shinjiro", shinjiroAttributes);
		attributesMap.put("aigis", aigisAttributes);
		// Fuuka attributes not needed
	}
		
	// Initialize the loadout lists for each character.
	private void initLoadouts() {
		Object[][] loadoutList = loadoutMap.get(name);
		for (int i = 0; i < loadoutList.length; i++) {
			Loadout currLoadout = new Loadout((Integer) loadoutList[i][0], 
											  (Boolean) loadoutList[i][1], 
											  (String) loadoutList[i][2]);
			loadouts.add(currLoadout);
		}
	}
}
