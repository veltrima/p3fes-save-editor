package saveEditor;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SaveFile {
	
	private byte[] byteStream;
	
	private int playerLevel, playerExp, yen, plumes;
	private String playerFirstName, playerLastName, revivalFlag;
	
	private int MAX_LEVEL = 99;
	private int MAX_EXP = 1358428;
	
	// Index at level is exp needed for that level
	private int[] expNeededPerLevel = {
		-1, 0, 20, 47, 99, 185, 312, 490, 726, 1030, 1410, 1873, 2429, 
		3085, 3851, 4735, 5744, 6888,8174, 9612, 11210, 12975, 14917,
		17043, 19363, 21885, 24616, 27566, 30742, 34154, 37810, 41717,
		45885, 50321, 55035, 60035, 65328, 70924, 76830, 83056, 89610, 
		96499, 103733, 111319, 119267, 127585, 136280, 145362, 154838, 
		164718, 175010, 185721, 196861, 208437, 220459, 232935, 245872,
		259280, 273166, 287540, 302410, 317783, 333669, 350075, 367011, 
		384485, 402504, 421078, 440214, 459922, 480210, 501085, 522557, 
		544633, 567323, 590635, 614576, 639156, 664382, 690264, 716810, 
		744027, 771925, 800511, 829795, 859785, 890488, 921914, 954070, 
		986966, 1020610, 1055009, 1090173, 1126109, 1162827, 1200335, 
		1238640, 1277752, 1317678, 1358428
	};
			
	private String[] partyMember = {
		"junpei", // 0
		"yukari", // 1
		"akihiko", // 2
		"mitsuru", // 3
		"ken", // 4
		"aigis", // 5
		"shinjiro", // 6
		"koromaru", // 7
		"fuuka" // 8
	};
	
	private int[] academicsRanks = {
		0,
		20,
		80,
		140,
		200,
		260
	};
	
	private int[] charmCourageRanks = {
		0,
		15,
		30,
		45,
		60,
		80
	};
	
	// index 0 with regular persona, index 1 with ultimate persona
	int[] junpeiPersonaFlags = {198, 199};
	int[] yukariPersonaFlags = {192, 193};
	int[] akihikoPersonaFlags = {202, 203};
	int[] mitsuruPersonaFlags = {196, 197};
	int[] kenPersonaFlags = {204, 205};
	int[] aigisPersonaFlags = {194, 195};
	int[] fuukaPersonaFlags = {200, 201};
	
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
		{1, "0200 3300 D800 0000 0000 0000 0000 0000"}, // 1
		{38, "0200 3300 D800 0500 0000 0000 0000 0000"}, // 38
		{40, "0200 3300 D800 0500 2102 0000 0000 0000"}, // 40
		{42, "0200 3300 D800 0500 2102 3400 0000 0000"}, // 42
		{45, "3300 D800 0500 2102 3400 0300 0000 0000"}, // 45
		{48, "3300 D800 0500 2102 3400 0300 1002 0000"}, // 48
		{50, "3300 0500 2102 3400 0300 1002 D900 0000"}, // 50
		{52, "3300 0500 3400 0300 1002 D900 2202 0000"}, // 52
		{56, "0500 3400 0300 1002 D900 2202 3500 0000"}, // 56
		{60, "0500 3400 0300 1002 D900 2202 3500 E500"}, // 60
		{67, "3400 0300 1002 D900 2202 3500 E500 0600"}, // 67
		{71, "0300 1002 D900 2202 3500 E500 0600 3600"}, // 71
		{77, "0300 D900 2202 3500 E500 0600 3600 1102"}  // 77
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
		{1, "2302 2002 7A00 0000 0000 0000 0000 0000"}, // 1
		{39, "2302 2002 7A00 4000 0000 0000 0000 0000"}, // 39
		{42, "2302 7A00 4000 2102 0000 0000 0000 0000"}, // 42
		{50, "2302 7A00 4000 2102 DC00 0000 0000 0000"}, // 50
		{52, "2302 7A00 4000 2102 DC00 7F00 0000 0000"}, // 52
		{53, "2302 7A00 4000 2102 DC00 7F00 7500 0000"}, // 53
		{55, "2302 7A00 4000 DC00 7F00 7500 2202 0000"}, // 55
		{60, "2302 7A00 4000 DC00 7500 2202 8000 0000"}, // 60
		{65, "7A00 4000 DC00 7500 2202 8000 2402 0000"}, // 65
		{72, "7A00 4000 DC00 2202 8000 2402 7700 0000"}, // 72
		{77, "7A00 4000 DC00 2202 8000 2402 7700 7800"}  // 77
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
	
	private HashMap<String, Integer> levelMap = new HashMap<>(); 
	private HashMap<String, Integer> expMap = new HashMap<>(); 
	private HashMap<String, String> skillMap = new HashMap<>();
	private HashMap<String, Integer> statMap = new HashMap<>();
	private HashMap<String, Boolean> ultFlagMap = new HashMap<>(); // 30 vs 00
	private HashMap<String, Integer> personaFlagMap = new HashMap<>(); 
	private HashMap<String, String> indexMap = new HashMap<>();
	private HashMap<String, String> nameHexToLetter = new HashMap<>();
	private HashMap<String, String> nameLetterToHex = new HashMap<>();
	private HashMap<String, Object[][]> loadoutsMap = new HashMap<>();
	private HashMap<String, int[]> personaCharFlagsMap = new HashMap<>();
	
	//TODO get name in String wee
	public SaveFile (byte[] data) {
		initIndices();
		initLoadouts();
		initPersonaCharFlagsMap();
		populateLetterHashMaps();
		byteStream = data;
		playerLevel = getByteRangeInt(data, indexMap.get("PLAYER_LEVEL_INDEX"), 0, 1);
		playerExp = getByteRangeInt(data, indexMap.get("PLAYER_EXP_INDEX"), 0, 4);
		
		playerFirstName = getName(data, indexMap.get("PLAYER_FIRST_NAME_INDEX_1"));
		playerLastName = getName(data, indexMap.get("PLAYER_LAST_NAME_INDEX_1"));
		
		for (int i = 0; i < partyMember.length; i++) {
			String index = "BLOCK_" + partyMember[i].toUpperCase() + "_INDEX";
			
			//if (partyMember[i] == "koromaru" || partyMember[i] == "fuuka" || partyMember[i] == "shinjiro") {
			//	levelMap.put(partyMember[i] + "Level", getByteRangeInt(data, indexMap.get(index), 0, 4) - 7680);
			//} else {
			//	levelMap.put(partyMember[i] + "Level", getByteRangeInt(data, indexMap.get(index), 0, 4));
			//}
			
			if (i < 6) { // Not Shinjiro, Koromaru, or Fuuka, because their structure is different
				int f = getByteRangeInt(data, indexMap.get(index), 3, 1);
				if (f == 0) {
					ultFlagMap.put(partyMember[i], false);
				} else if (f == 30) {
					ultFlagMap.put(partyMember[i], true);
				} else {
					ultFlagMap.put(partyMember[i], null); // ERROR
					System.out.println("Oops");
				}
			} else if (i == 8) { // For Fuuka, only use personaFlagMap. If val == 201 (0xC9), yes. If val == 200 (0xC8), no.
				int f = getByteRangeInt(data, indexMap.get(index), 0, 1);
				if (f == 200) {
					ultFlagMap.put(partyMember[i], false);
				} else if (f == 201) {
					ultFlagMap.put(partyMember[i], true);
				} else {
					ultFlagMap.put(partyMember[i], null); // ERROR
					System.out.println("Oops");
				}
			}
			
			personaFlagMap.put(partyMember[i], getByteRangeInt(data, indexMap.get(index), 0, 1));
			levelMap.put(partyMember[i] + "Level", getByteRangeInt(data, indexMap.get(index), 2, 1));
			expMap.put(partyMember[i] + "Exp", getByteRangeInt(data, indexMap.get(index), 6, 4));
			
			String skills = getByteRangeString(data, indexMap.get(index), 10, 2);
			for (int j = 2; j < 16; j += 2) {
				skills += getByteRangeString(data, indexMap.get(index), 10 + j, 2);
			}

			skillMap.put(partyMember[i] + "Skills", skills);
			System.out.println(skills);
			
		}
		
		statMap.put("academics", getByteRangeInt(data, indexMap.get("STAT_ACADEMICS_INDEX"), 0, 2));
		statMap.put("charm", getByteRangeInt(data, indexMap.get("STAT_CHARM_INDEX"), 0, 2));
		statMap.put("courage", getByteRangeInt(data, indexMap.get("STAT_COURAGE_INDEX"), 0, 2));
		
		revivalFlag = getByteRangeString(data, indexMap.get("REVIVAL_FLAG_INDEX"), 0, 1);
		yen = getByteRangeInt(data, indexMap.get("YEN_INDEX"), 0, 4);
		plumes = getByteRangeInt(data, indexMap.get("PLUMES_INDEX"), 0, 1);
	}
	
	public int getPlayerLevel () {
		return this.playerLevel;
	}
	
	public void setPlayerLevel (int val) {
		this.playerLevel = val;
	}
	
	public int getPlayerExp () {
		return this.playerExp;
	}
	
	public void setPlayerExp (int val) {
		this.playerExp = val;
	}
	
	public String getPlayerFirstName () {
		return this.playerFirstName;
	}
	
	public void setPlayerFirstName (String s) {
		this.playerFirstName = s;
	}
	
	public String getPlayerLastName () {
		return this.playerLastName;
	}
	
	public void setPlayerLastName (String s) {
		this.playerLastName = s;
	}
	
	public HashMap<String, Integer> getLevelMap () {
		return this.levelMap;
	}
	
	// person name must be correct
	// level must be between 1-99 inclusive
	public void setLevel (String person, int level) {
		levelMap.replace(person + "Level", level);
	}
	
	public String[] getPartyList () {
		return partyMember;
	}
	
	public HashMap<String, Integer> getExpMap () {
		return this.expMap;
	}
	
	// person name must be correct
	// exp must be between 0-MAX_EXP inclusive
	public void setExp (String person, int exp) {
		expMap.replace(person + "Exp", exp);
	}
	
	public int getMaxLevel () {
		return MAX_LEVEL;
	}
	
	public int getMaxExp () {
		return MAX_EXP;
	}
	
	public HashMap<String, String> getSkillMap () {
		return this.skillMap;
	}
	
	public void setSkillMap (HashMap<String, String> newMap) {
		this.skillMap = newMap;
	}
	
	public HashMap<String, Boolean> getUltFlagMap () {
		return this.ultFlagMap;
	}
	
	// Name **must** be correct
	public void updateUltFlagMap (String name, boolean u) {
		ultFlagMap.replace(name, u);
	}
	
	public int getAcademicsLevel () {
		for (int c = academicsRanks.length - 1; c >= 0; c--) {
			if (this.statMap.get("academics") >= academicsRanks[c]) {
				return c + 1;
			}
		}
		return 1;
	}
	
	// Min value of 1, max of 6
	public void setAcademicsLevel (int level) {
		this.statMap.replace("academics", academicsRanks[level - 1]);
	}
	
	public int getCharmLevel () {
		for (int c = charmCourageRanks.length - 1; c >= 0; c--) {
			if (this.statMap.get("charm") >= charmCourageRanks[c]) {
				return c + 1;
			}
		}
		return 1;
	}
	
	// Min value of 1, max of 6
	public void setCharmLevel (int level) {
		this.statMap.replace("charm", charmCourageRanks[level - 1]);
	}
	
	public int getCourageLevel () {
		for (int c = charmCourageRanks.length - 1; c >= 0; c--) {
			if (this.statMap.get("courage") >= charmCourageRanks[c]) {
				return c + 1;
			}
		}
		return 1;
	}
	
	// Min value of 1, max of 6
	public void setCourageLevel (int level) {
		this.statMap.replace("courage", charmCourageRanks[level - 1]);
	}
	
	public String getRevivalFlag () {
		return this.revivalFlag;
	}
	
	public void setRevivalFlag (String newFlag) {
		this.revivalFlag = newFlag;
	}
	
	public int getYen() {
		return this.yen;
	}
	
	public void setYen(int i) {
		this.yen = i;
	}
	
	public int getPlumes() {
		return this.plumes;
	}
	
	public void setPlumes(int i) {
		this.plumes = i;
	}
	
	public byte[] getRaw () {
		return this.byteStream;
	}
	
	String getName(byte[] data, String indexString) {
		int index = Integer.parseInt(indexString, 16);
		StringBuilder sb = new StringBuilder();
		byte[] letterBytes;
		String letterHex;
		for (int i = 0; i < 16; i += 2) { // First name and last name are each 8 letters
			letterBytes = Arrays.copyOfRange(data, index + i, index + i + 2);
			letterHex = bytesToHex(letterBytes);
			sb.append(nameHexToLetter.get(letterHex));
		}
		
		System.out.println(sb.toString());	
		return sb.toString();
	}
	
	byte[] nameToBytes(String name) {
		String letter;
		byte[] b = new byte[16];
		byte[] letterBytes;
		for (int i = 0; i < 8; i++) {
			if (i >= name.length()) {
				b[i * 2] = 0;
				b[i * 2 + 1] = 0;
			} else {
				letter = name.substring(i, i + 1);
				if (nameLetterToHex.get(letter) != null) {
					letterBytes = hexToBytes(nameLetterToHex.get(letter));
				} else {
					letterBytes = hexToBytes(nameLetterToHex.get(" "));
				}
				b[i * 2] = letterBytes[0];
				b[i * 2 + 1] = letterBytes[1];
			}	
		}
		return b;
	}
	
	int getByteRangeInt (byte[] data, String indexString, int offset, int byteLength) {
		int index = Integer.parseInt(indexString, 16) + offset;
		byte[] byteRange = Arrays.copyOfRange(data, index, index + byteLength);
		arrayReverse(byteRange);
		String rangeString = bytesToHex(byteRange);
		return Integer.parseInt(rangeString, 16);
	}
	
	String getByteRangeString (byte[] data, String indexString, int offset, int byteLength) {
		int index = Integer.parseInt(indexString, 16) + offset;
		byte[] byteRange = Arrays.copyOfRange(data, index, index + byteLength);
		//arrayReverse(byteRange); Don't worry about byte order right now
		return bytesToHex(byteRange);
	}
	
	public void writeToByteArray() {
		int level, exp;
		String skills, index;
		updateByteArray(intToBytes(playerLevel, 1), indexMap.get("PLAYER_LEVEL_INDEX"), 0);
		updateByteArray(intToBytes(playerExp, 4), indexMap.get("PLAYER_EXP_INDEX"), 0);
		
		updateByteArray(nameToBytes(playerFirstName), indexMap.get("PLAYER_FIRST_NAME_INDEX_1"), 0);
		updateByteArray(nameToBytes(playerFirstName), indexMap.get("PLAYER_FIRST_NAME_INDEX_2"), 0);
		updateByteArray(nameToBytes(playerLastName), indexMap.get("PLAYER_LAST_NAME_INDEX_1"), 0);
		updateByteArray(nameToBytes(playerLastName), indexMap.get("PLAYER_LAST_NAME_INDEX_2"), 0);
		
		for (int i = 0; i < partyMember.length; i++) {
			index = "BLOCK_" + partyMember[i].toUpperCase() + "_INDEX";
			level = levelMap.get(partyMember[i] + "Level");
			exp = expMap.get(partyMember[i] + "Exp");
			skills = skillMap.get(partyMember[i] + "Skills");
			
			if (i < 6) { // Not Shinjiro, Koromaru, or Fuuka, because their structure is different
				if (!ultFlagMap.get(partyMember[i])) {
					updateByteArray(intToBytes(0, 1), indexMap.get(index), 3);
				} else {
					updateByteArray(intToBytes(30, 1), indexMap.get(index), 3);
				}
			} 
			
			updateByteArray(intToBytes(personaFlagMap.get(partyMember[i]), 1), indexMap.get(index), 0);
			updateByteArray(intToBytes(level, 1), indexMap.get(index), 2);
			updateByteArray(intToBytes(exp, 4), indexMap.get(index), 6);
			updateByteArray(hexToBytes(skills), indexMap.get(index), 10);
		}
		
		
		updateByteArray(intToBytes(statMap.get("academics"), 2), indexMap.get("STAT_ACADEMICS_INDEX"), 0);
		updateByteArray(intToBytes(statMap.get("charm"), 2), indexMap.get("STAT_CHARM_INDEX"), 0);
		updateByteArray(intToBytes(statMap.get("courage"), 2), indexMap.get("STAT_COURAGE_INDEX"), 0);
		
		updateByteArray(hexToBytes(revivalFlag), indexMap.get("REVIVAL_FLAG_INDEX"), 0);	
		updateByteArray(intToBytes(yen, 1), indexMap.get("YEN_INDEX"), 0);
		updateByteArray(intToBytes(plumes, 1), indexMap.get("PLUMES_INDEX"), 0);
		
		String headerChecksum = generateHeaderChecksum();
		String dataChecksum = generateDataChecksum();
		updateByteArray(hexToBytes(headerChecksum), indexMap.get("HEADER_CHECKSUM_INDEX"), 0);
		updateByteArray(hexToBytes(dataChecksum), indexMap.get("DATA_CHECKSUM_INDEX"), 0);	
	}
	
	void updateByteArray(byte[] newData, String startIndex, int offset) {
		int index = Integer.parseInt(startIndex, 16) + offset;
		for (int i = 0; i < newData.length; i++) {
			byteStream[index + i] = newData[i]; // Double check if pointer issue here
		}
	}
	
	byte[] intToBytes(int input, int numBytes) {
		byte[] out = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(input).array(); // Needs to allocate 4 otherwise error is thrown
		return Arrays.copyOfRange(out, 0, numBytes);
	}
	
	// Thank you https://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-javav
	byte[] hexToBytes (String input) {
		return DatatypeConverter.parseHexBinary(input);
	}
	
	// Thank you https://www.mkyong.com/java/java-how-to-convert-bytes-to-hex/
	private String bytesToHex(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	
	// In order to preserve endian-ness (little endian)
	// Thank you http://www.java67.com/2016/10/3-ways-to-reverse-array-in-java-coding-interview-question.html
	void arrayReverse (byte[] data) {
		for(int i = 0; i < data.length/2; i++) {
			byte temp = data[i];
			data[i] = data[data.length - i - 1];
			data[data.length - i - 1] = temp;
		}
	}
	
	private String generateHeaderChecksum() {
		int headerStart = Integer.parseInt(indexMap.get("HEADER_START_INDEX"), 16);
		int headerEnd = Integer.parseInt(indexMap.get("HEADER_CHECKSUM_INDEX"), 16);
		byte sum = 0;
		for (int i = headerStart; i < headerEnd; i++) {
			sum += byteStream[i]; // Lol not even XOR
		}
		return String.format("%02x", sum);
	}
	
	private String generateDataChecksum() {
		int headerStart = Integer.parseInt(indexMap.get("DATA_START_INDEX"), 16);
		int headerEnd = Integer.parseInt(indexMap.get("DATA_CHECKSUM_INDEX"), 16);
		byte sum = 0;
		for (int i = headerStart; i < headerEnd; i++) {
			sum += byteStream[i];
		}
		return String.format("%02x", sum);
	}
	
	void initIndices() {
		/* HEADER AREA 804-835
		 * HEADER CHECKSUM 836
		 * DATA AREA 837-9549
		 * DATA CHECKSUM 9551
		 */
		indexMap.put("HEADER_START_INDEX", "804");
		indexMap.put("HEADER_CHECKSUM_INDEX", "836");
		indexMap.put("DATA_START_INDEX", "837");
		indexMap.put("DATA_CHECKSUM_INDEX", "9551");
		
		indexMap.put("PLAYER_FIRST_NAME_INDEX_1", "822"); // 16 bytes
		indexMap.put("PLAYER_FIRST_NAME_INDEX_2", "852");
		indexMap.put("PLAYER_LAST_NAME_INDEX_1", "810"); // 16 bytes	
		indexMap.put("PLAYER_LAST_NAME_INDEX_2", "840"); 
		indexMap.put("PLAYER_LEVEL_INDEX", "872"); // 1 byte 
		indexMap.put("PLAYER_EXP_INDEX", "8B0");
		
		/* BLOCK STRUCTURE: 
		 * Persona flag (unsure specifics) - 1 byte
		 * 1 blank byte
		 * Level - 1 byte
		 * Ultimate persona flag - 1 byte (00 no, 1E yes)
		 * 2 blank bytes
		 * Exp - 4 bytes
		 * Ability slot - 2 bytes * 8 slots
		 */
		indexMap.put("BLOCK_JUNPEI_INDEX", "7803");
		indexMap.put("BLOCK_YUKARI_INDEX", "6CB7");
		indexMap.put("BLOCK_KOROMARU_INDEX", "8AD7");
		indexMap.put("BLOCK_SHINJIRO_INDEX", "8713");
		indexMap.put("BLOCK_AKIHIKO_INDEX", "7F8B");
		indexMap.put("BLOCK_MITSURU_INDEX", "743F");
		indexMap.put("BLOCK_KEN_INDEX", "834F");
		indexMap.put("BLOCK_AIGIS_INDEX", "707B");
		indexMap.put("BLOCK_FUUKA_INDEX", "7BC7");
		
		indexMap.put("YEN_INDEX", "6560"); // 4 bytes
		indexMap.put("PLUMES_INDEX", "25C4"); // 1 byte
		indexMap.put("REVIVAL_FLAG_INDEX", "62D0"); // 1 byte
		
		indexMap.put("STAT_ACADEMICS_INDEX", "8A8"); // all are 2 bytes
		indexMap.put("STAT_CHARM_INDEX", "8AA");
		indexMap.put("STAT_COURAGE_INDEX", "8AC");
	}
	
	void populateLetterHashMaps() {
		String hexVal;
		for (int i = 0; i < 26; i++) {
			hexVal = Integer.toHexString((161 + i));
			nameLetterToHex.put((char) (65 + i) + "","80" + hexVal); // Uppercase letters
			
			hexVal = Integer.toHexString((193 + i));
			nameLetterToHex.put((char) (97 + i) + "","80" + hexVal); // Lowercase letters
		}
		
		nameLetterToHex.put(" ", "0000");
		
		// Thank you https://stackoverflow.com/questions/20412354/reverse-hashmap-keys-and-values-in-java
		for(HashMap.Entry<String, String> entry : nameLetterToHex.entrySet()){
		    nameHexToLetter.put(entry.getValue(), entry.getKey());
		}
	}
	
	private void initLoadouts () {
		loadoutsMap.put("junpei", junpeiLoadouts);
		loadoutsMap.put("yukari", yukariLoadouts);
		loadoutsMap.put("akihiko", akihikoLoadouts);
		loadoutsMap.put("mitsuru", mitsuruLoadouts);
		loadoutsMap.put("ken", kenLoadouts);
		loadoutsMap.put("aigis", aigisLoadouts);
		loadoutsMap.put("shinjiro", shinjiroLoadouts);
		loadoutsMap.put("koromaru", koromaruLoadouts);
		loadoutsMap.put("fuuka", fuukaLoadouts);
	}
	
	private void initPersonaCharFlagsMap () {
		// Junpei 198 (0xC6) vs 199 (0xC7)
		// Yukari 192 (0xC0) vs 193 (0xC1)
		// Akihiko 202 (0xCA) vs 203 (0xCB)
		// Mitsuru 196 (0xC4) vs 197 (0xC5)
		// Ken 204 (0xCC) vs 205 (0xCD)
		// Aigis 194 (0xC2) vs 195 (0xC3)
		// Fuuka 200 (0xC8) vs 201 (0xC9)
		
		personaCharFlagsMap.put("junpei", junpeiPersonaFlags);
		personaCharFlagsMap.put("yukari", yukariPersonaFlags);
		personaCharFlagsMap.put("akihiko", akihikoPersonaFlags);
		personaCharFlagsMap.put("mitsuru", mitsuruPersonaFlags);
		personaCharFlagsMap.put("ken", kenPersonaFlags);
		personaCharFlagsMap.put("aigis", aigisPersonaFlags);
		personaCharFlagsMap.put("fuuka", fuukaPersonaFlags);	
	}
	
	// Set every character's (including MC's) level and exp to max. 
	public void setMax () {
		playerLevel = MAX_LEVEL;
		playerExp = MAX_EXP;
		for (int i = 0; i < partyMember.length; i++) {
			levelMap.put(partyMember[i] + "Level", MAX_LEVEL);
			expMap.put(partyMember[i] + "Exp", MAX_EXP);
		}
		updateCharacterSkills();
	}	
	
	// Set every party character's level and exp to that of the MC.
	public void setToMC () { 
		for (int i = 0; i < partyMember.length; i++) {
			levelMap.put(partyMember[i] + "Level", playerLevel);
			expMap.put(partyMember[i] + "Exp", playerExp);
		}
		updateCharacterSkills();
	}
	
	// Sets exp to be correct for level.
	public int expForLevel (int lvl) {
		return expNeededPerLevel[lvl];
	}
	
	// Sets level to be correct for exp.
	public int levelForExp (int xp) {
		for (int i = expNeededPerLevel.length - 1; i > 0; i--) {
			if (xp >= expNeededPerLevel[i]) {
				return i;
			}
		}
		return -1; //error
	}
	
	// Update all character's skills based on their level and ultimate persona status.
	public void updateCharacterSkills () {
		int currLevel;
		boolean isUlt = false;
		Object[][] charLoadouts;
		String loadout = "";
		for (int i = 0; i < partyMember.length; i++) {
			currLevel = levelMap.get(partyMember[i] + "Level");
			charLoadouts = loadoutsMap.get(partyMember[i]);
			
			if (i != 6 && i != 7) { // Not Shinjiro or Koromaru, who don't have ult personas
				isUlt = ultFlagMap.get(partyMember[i]);
			} 
			
			for (int j = charLoadouts.length - 1; j >= 0; j--) {
				if (currLevel >= (Integer) charLoadouts[j][0]) {
					if (i == 6 || i == 7) { // No ultimate persona
						loadout = ((String) charLoadouts[j][1]).replaceAll("\\s+","");
						break;
					} else {
						if ((Boolean) charLoadouts[j][1] && isUlt) {
							loadout = ((String) charLoadouts[j][2]).replaceAll("\\s+","");
							break;
						} else if (!(Boolean) charLoadouts[j][1]) {
							if (i == 0) { // Junpei is special case
								if (isUlt) {
									loadout = "5F02" + ((String) charLoadouts[j][2]).replaceAll("\\s+","");
									break;
								} else {
									loadout = ((String) charLoadouts[j][2]).replaceAll("\\s+","") + "0000";
									break;
								}
							} else { // Rest of party is simple.
								loadout = ((String) charLoadouts[j][2]).replaceAll("\\s+","");
								break;	
							}
						}
					}
				}
			}
		skillMap.replace(partyMember[i] + "Skills", loadout);
		}
	}
	
	public void updatePersonaFlags () {
		boolean isUlt;
		int[] currFlags;
		for (int i = 0; i < partyMember.length; i++) {
			if (i != 6 && i != 7) {
				isUlt = ultFlagMap.get(partyMember[i]);
				currFlags = personaCharFlagsMap.get(partyMember[i]);
				if (isUlt) {
					personaFlagMap.replace(partyMember[i], currFlags[1]);
				} else {
					personaFlagMap.replace(partyMember[i], currFlags[0]);
				}
			}		
		}
		
	}
	
}
