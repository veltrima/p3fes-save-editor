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
	private String playerFirstName, playerLastName, playerSkills, revivalFlag;
	
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
		"yukari",
		"junpei",
		"akihiko",
		"mitsuru",
		"ken",
		"aigis",
		"shinjiro",
		"koromaru",
		"fuuka"
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
	
	// The following are based on separate table of data
	// Clear whitespace before using, whitespace for easy reading
	// If ultimate up to 58, begin with Spring of Life (5F02)
	// Else, end with 0000
	private String[] junpeiLoadouts = {
		"7900 0000 0000 0000 0000 0000 0000", // 1
		"7900 0100 0000 0000 0000 0000 0000", // 5
		"7900 0100 C900 0000 0000 0000 0000", // 7
		"7900 0100 C900 DA00 0000 0000 0000", // 9
		"7900 0100 C900 DA00 7100 0000 0000", // 18
		"7900 0100 C900 DA00 7100 7E00 0000", // 20
		"7900 0100 C900 DA00 7E00 7200 0000", // 25
		"7900 C900 DA00 7E00 7200 0200 0000", // 32
		"7900 C900 DA00 7E00 7200 0200 2002", // 35
		"7900 C900 DA00 7200 0200 2002 8C00", // 40
		"7900 C900 7200 0200 2002 8C00 DB00", // 44
		"7900 C900 7200 0200 8C00 DB00 2102", // 45
		"C900 7200 0200 8C00 DB00 2102 7F00", // 50
		"C900 0200 8C00 DB00 2102 7F00 7600", // 55
		"5F02 C900 0200 8C00 DB00 7F00 7600 2202", // 58 Ultimate
		"5F02 0200 8C00 DB00 7F00 7600 2202 E500", // 60 U
		"5F02 8C00 DB00 7F00 7600 2202 E500 0300", // 64 U
		"5F02 8C00 DB00 7600 2202 E500 0300 8600", // 70 U
		"5F02 8C00 DB00 7600 2202 E500 0300 9000" // 75 U
	};
	
	private String[] yukariLoadouts = {
		"C000 0000 0000 0000 0000 0000 0000 0000", // 1
		"C000 C700 0000 0000 0000 0000 0000 0000", // 5
		"C000 C700 0A00 0000 0000 0000 0000 0000", // 16
		"C000 C700 0A00 ED00 0000 0000 0000 0000", // 21
		"C700 0A00 ED00 0D00 C100 0000 0000 0000", // 22
		"C700 0A00 ED00 0D00 C100 CC00 0000 0000", // 25
		"C700 ED00 0D00 C100 CC00 0B00 0000 0000", // 28
		"ED00 0D00 C100 CC00 0B00 C800 0000 0000", // 32
		"ED00 0D00 C100 CC00 0B00 C800 C400 0000", // 36
		"ED00 C100 CC00 0B00 C800 C400 0E00 0000", // 43
		"ED00 CC00 0B00 C800 C400 0E00 C200 0000", // 46
		"ED00 CC00 C800 C400 0E00 C200 0C00 0000", // 52 U
		"ED00 CC00 C800 C400 0E00 C200 0C00 E700", // 57 U
		"ED00 CC00 C800 C400 C200 0C00 E700 0F00", // 65 U
		"ED00 C800 C400 C200 0C00 E700 0F00 CD00", // 68 U
		"ED00 C800 C200 0C00 E700 0F00 CD00 C500"  // 74 U
	};
	
	private String[] mitsuruLoadouts = {
		"1300 1600 C000 3900 0000 0000 0000 0000", // 1
		"1600 C000 3900 1400 0000 0000 0000 0000", // 21	
		"1600 3900 1400 C100 0000 0000 0000 0000", // 25
		"1600 3900 1400 C100 4C00 0000 0000 0000", // 27
		"1600 3900 1400 C100 4C00 3E00 0000 0000", // 32
		"3900 1400 C100 4C00 3E00 1700 0000 0000", // 42
		"3900 1400 C100 4C00 3E00 1700 1202 0000", // 45
		"3900 1400 C100 4C00 3E00 1700 1202 DD00", // 50
		"3900 C100 4C00 3E00 1700 1202 DD00 1500", // 55
		"3900 4C00 3E00 1700 1202 DD00 1500 C200", // 58 U
		"4C00 3E00 1700 1202 DD00 1500 C200 E600", // 61 U
		"4C00 3E00 1202 DD00 1500 C200 E600 1800", // 71 U
		"4C00 3E00 DD00 1500 C200 E600 1800 1302"  // 76 U
	};
	
	private String[] akihikoLoadouts = {
		"7400 1C00 C000 0000 0000 0000 0000 0000", // 1
		"7400 1C00 C000 CE00 0000 0000 0000 0000", // 16
		"7400 1C00 C000 CE00 1F00 0000 0000 0000", // 21
		"7400 1C00 C000 CE00 1F00 D200 0000 0000", // 25
		"7400 C000 CE00 1F00 D200 1D00 0000 0000", // 29
		"7400 C000 CE00 1F00 D200 1D00 D000 0000", // 33
		"7400 C000 CE00 1F00 D200 1D00 D000 1402", // 37
		"7400 CE00 1F00 D200 1D00 D000 1402 C100", // 38
		"7400 CE00 D200 1D00 D000 1402 C100 2000", // 41
		"7400 D200 1D00 D000 1402 C100 2000 CF00", // 47 U
		"D200 1D00 D000 1402 C100 2000 CF00 4A02", // 50 U
		"D200 D000 1402 C100 2000 CF00 4A02 1E00", // 54 U
		"D000 1402 C100 2000 CF00 4A02 1E00 D300", // 57 U
		"D000 1402 2000 CF00 4A02 1E00 D300 C200", // 65 U
		"1402 2000 CF00 4A02 1E00 D300 C200 D100", // 66 U
		"1402 CF00 4A02 1E00 D300 C200 D100 2100", // 74 U
		"CF00 4A02 1E00 D300 C200 D100 2100 1502"  // 76 U 
	};
	
	private String[] kenLoadouts = {
		"2D00 9200 1D00 0000 0000 0000 0000 0000", // 1
		"2D00 9200 1D00 C100 0000 0000 0000 0000", // 37
		"9200 1D00 C100 2F00 0000 0000 0000 0000", // 41
		"9200 1D00 C100 2F00 CC00 0000 0000 0000", // 42
		"9200 1D00 C100 2F00 CC00 C400 0000 0000", // 51 U
		"9200 1D00 C100 2F00 CC00 C400 5702 0000", // 54 U
		"9200 C100 2F00 CC00 C400 5702 1E00 0000", // 55 U
		"C100 2F00 CC00 C400 5702 1E00 9300 0000", // 59 U
		"2F00 CC00 C400 5702 1E00 9300 C200 0000", // 62 U
		"2F00 CC00 C400 5702 1E00 9300 C200 4602", // 65 U
		"2F00 C400 5702 1E00 9300 C200 4602 CD00", // 73 U
		"2F00 5702 1E00 9300 C200 4602 CD00 C500"  // 78 U
	};
	
	private String[] koromaruLoadouts = {
		"0200 3300 D800 0000 0000 0000 0000 0000", // 1
		"0200 3300 D800 0500 0000 0000 0000 0000", // 38
		"0200 3300 D800 0500 2102 0000 0000 0000", // 40
		"0200 3300 D800 0500 2102 3400 0000 0000", // 42
		"3300 D800 0500 2102 3400 0300 0000 0000", // 45
		"3300 D800 0500 2102 3400 0300 1002 0000", // 48
		"3300 0500 2102 3400 0300 1002 D900 0000", // 50
		"3300 0500 3400 0300 1002 D900 2202 0000", // 52
		"0500 3400 0300 1002 D900 2202 3500 0000", // 56
		"0500 3400 0300 1002 D900 2202 3500 E500", // 60
		"3400 0300 1002 D900 2202 3500 E500 0600", // 67
		"0300 1002 D900 2202 3500 E500 0600 3600", // 71
		"0300 D900 2202 3500 E500 0600 3600 1102"  // 77
	};
	
	private String[] aigisLoadouts = {
		"7200 7300 D800 0000 0000 0000 0000 0000", // 1
		"7200 7300 D800 DA00 0000 0000 0000 0000", // 32
		"7200 7300 D800 DA00 7A00 0000 0000 0000", // 35
		"7200 7300 D800 DA00 7A00 D600 0000 0000", // 36
		"7200 7300 D800 DA00 7A00 D600 D400 0000", // 42
		"7200 7300 DA00 7A00 D600 D400 D900 0000", // 47
		"7200 DA00 7A00 D600 D400 D900 7500 0000", // 51
		"7200 7A00 D600 D400 D900 7500 DB00 0000", // 56
		"7200 7A00 D600 D400 D900 7500 DB00 C200", // 59
		"7200 7A00 D400 D900 7500 DB00 C200 D700", // 60
		"7A00 D400 D900 7500 DB00 C200 D700 CD00", // 65 U
		"7A00 D400 D900 DB00 C200 D700 CD00 7700", // 73 U
		"D400 D900 DB00 C200 D700 CD00 7700 7800"  // 77 U
	};
	
	private String[] shinjiroLoadouts = {
		"2302 2002 7A00 0000 0000 0000 0000 0000", // 1
		"2302 2002 7A00 4000 0000 0000 0000 0000", // 39
		"2302 7A00 4000 2102 0000 0000 0000 0000", // 42
		"2302 7A00 4000 2102 DC00 0000 0000 0000", // 50
		"2302 7A00 4000 2102 DC00 7F00 0000 0000", // 52
		"2302 7A00 4000 2102 DC00 7F00 7500 0000", // 53
		"2302 7A00 4000 DC00 7F00 7500 2202 0000", // 55
		"2302 7A00 4000 DC00 7500 2202 8000 0000", // 60
		"7A00 4000 DC00 7500 2202 8000 2402 0000", // 65
		"7A00 4000 DC00 2202 8000 2402 7700 0000", // 72
		"7A00 4000 DC00 2202 8000 2402 7700 7800"  // 77
	};
	
	private String[] fuukaLoadouts = {
		"4601 0000 0000 0000 0000 0000 0000 0000", // 1
		"4601 4F01 0000 0000 0000 0000 0000 0000", // 23
		"4601 4F01 5001 0000 0000 0000 0000 0000", // 32
		"4601 4F01 5001 5401 0000 0000 0000 0000", // 41
		"4601 4F01 5001 5401 4501 0000 0000 0000", // 50 U
		"4601 4F01 5001 5401 4501 5301 0000 0000"  // 72 U
		
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
	
	//TODO get name in String wee
	public SaveFile (byte[] data) {
		initIndices();
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
	
	public void setLevelMap (HashMap<String, Integer> newMap) {
		this.levelMap = newMap;
	}
	
	public HashMap<String, Integer> getExpMap () {
		return this.expMap;
	}
	
	public void setExpMap (HashMap<String, Integer> newMap) {
		this.expMap = newMap;
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
	
	// Name must be correct
	public void toggleUltFlagMap (String characterName) {
		if (!this.ultFlagMap.get(characterName)) {
			this.ultFlagMap.replace(characterName, true);
		} else if (this.ultFlagMap.get(characterName)) {
			this.ultFlagMap.replace(characterName, false);
		} else {
			System.out.println("You got an oopsie on your hands.");
		}
		
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
			letter = name.substring(i, i + 1);
			letterBytes = hexToBytes(nameLetterToHex.get(letter));
			b[i * 2] = letterBytes[0];
			b[i * 2 + 1] = letterBytes[1];
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
		updateByteArray(byteStream, intToBytes(playerLevel, 1), indexMap.get("PLAYER_LEVEL_INDEX"));
		updateByteArray(byteStream, intToBytes(playerExp, 4), indexMap.get("PLAYER_EXP_INDEX"));
		
		updateByteArray(byteStream, nameToBytes(playerFirstName), indexMap.get("PLAYER_FIRST_NAME_INDEX_1"));
		updateByteArray(byteStream, nameToBytes(playerFirstName), indexMap.get("PLAYER_FIRST_NAME_INDEX_2"));
		updateByteArray(byteStream, nameToBytes(playerLastName), indexMap.get("PLAYER_LAST_NAME_INDEX_1"));
		updateByteArray(byteStream, nameToBytes(playerLastName), indexMap.get("PLAYER_LAST_NAME_INDEX_2"));
		
		for (int i = 0; i < partyMember.length; i++) {
			index = "BLOCK_" + partyMember[i].toUpperCase() + "_INDEX";
			level = levelMap.get(partyMember[i] + "Level");
			exp = expMap.get(partyMember[i] + "Exp");
			skills = skillMap.get(partyMember[i] + "Skills");	
			
			updateByteArray(byteStream, intToBytes(level, 1), indexMap.get(index));
			updateByteArray(byteStream, intToBytes(exp, 4), indexMap.get(index) + 4);
			updateByteArray(byteStream, hexToBytes(skills), indexMap.get(index) + 8);
		}
		
		updateByteArray(byteStream, intToBytes(statMap.get("academics"), 2), indexMap.get("STAT_ACADEMICS_INDEX"));
		updateByteArray(byteStream, intToBytes(statMap.get("charm"), 2), indexMap.get("STAT_CHARM_INDEX"));
		updateByteArray(byteStream, intToBytes(statMap.get("courage"), 2), indexMap.get("STAT_COURAGE_INDEX"));
		
		updateByteArray(byteStream, hexToBytes(revivalFlag), indexMap.get("REVIVAL_FLAG_INDEX"));	
		updateByteArray(byteStream, intToBytes(yen, 1), indexMap.get("YEN_INDEX"));
		updateByteArray(byteStream, intToBytes(plumes, 1), indexMap.get("PLUMES_INDEX"));
		
		String headerChecksum = generateHeaderChecksum();
		String dataChecksum = generateDataChecksum();
		updateByteArray(byteStream, hexToBytes(headerChecksum), indexMap.get("HEADER_CHECKSUM_INDEX"));
		updateByteArray(byteStream, hexToBytes(dataChecksum), indexMap.get("DATA_CHECKSUM_INDEX"));	
	}
	
	void updateByteArray(byte[] data, byte[] newData, String startIndex) {
		int index = Integer.parseInt(startIndex, 16);
		for (int i = 0; i < newData.length; i++) {
			data[index + i] = newData[i]; // Double check if pointer issue here
		}
	}
	
	byte[] intToBytes(int input, int numBytes) {
		return ByteBuffer.allocate(numBytes).order(ByteOrder.LITTLE_ENDIAN).putInt(input).array();
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
			sum ^= byteStream[i];
		}
		return String.format("%02x", sum);
	}
	
	private String generateDataChecksum() {
		int headerStart = Integer.parseInt(indexMap.get("DATA_START_INDEX"), 16);
		int headerEnd = Integer.parseInt(indexMap.get("DATA_CHECKSUM_INDEX"), 16);
		byte sum = 0;
		for (int i = headerStart; i < headerEnd; i++) {
			sum ^= byteStream[i];
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
}
