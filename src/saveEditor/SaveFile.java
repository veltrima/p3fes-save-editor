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
	
	private static final int MAX_LEVEL = 99;
	private static final int MAX_EXP = 1358428;
	
	// Index at level is exp needed for that level
	private static final int[] expNeededPerLevel = {
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
			
	private static final String[] partyMember = {
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
	
	private static final int[] academicsRanks = {
		0,
		20,
		80,
		140,
		200,
		260
	};
	
	private static final int[] charmCourageRanks = {
		0,
		15,
		30,
		45,
		60,
		80
	};
	
	// index 0 with regular persona, index 1 with ultimate persona
	private static final int[] junpeiPersonaFlags = {198, 199};
	private static final int[] yukariPersonaFlags = {192, 193};
	private static final int[] akihikoPersonaFlags = {202, 203};
	private static final int[] mitsuruPersonaFlags = {196, 197};
	private static final int[] kenPersonaFlags = {204, 205};
	private static final int[] aigisPersonaFlags = {194, 195};
	private static final int[] fuukaPersonaFlags = {200, 201};

	private HashMap<String, PartyMember> partyMemberMap = new HashMap<>();
	private HashMap<String, int[]> personaCharFlagsMap = new HashMap<>();
	private HashMap<String, Integer> statMap = new HashMap<>();
	private HashMap<String, String> indexMap = new HashMap<>();
	private HashMap<String, String> nameHexToLetter = new HashMap<>();
	private HashMap<String, String> nameLetterToHex = new HashMap<>();
	
	public SaveFile (byte[] data) {
		initIndices();
		initPartyMemberMap();
		initPersonaCharFlagsMap();
		populateLetterHashMaps();
		byteStream = data;
		playerLevel = getByteRangeInt(data, indexMap.get("PLAYER_LEVEL_INDEX"), 0, 1);
		playerExp = getByteRangeInt(data, indexMap.get("PLAYER_EXP_INDEX"), 0, 4);
		
		playerFirstName = getName(data, indexMap.get("PLAYER_FIRST_NAME_INDEX_1"));
		playerLastName = getName(data, indexMap.get("PLAYER_LAST_NAME_INDEX_1"));
		
		for (int i = 0; i < partyMember.length; i++) {
			String index = "BLOCK_" + partyMember[i].toUpperCase() + "_INDEX";
			PartyMember currMember = partyMemberMap.get(partyMember[i]);
			
			if (i < 6) { // Not Shinjiro, Koromaru, or Fuuka, because their structure is different
				int ultFlag = getByteRangeInt(data, indexMap.get(index), 3, 1);
				if (ultFlag == 0) {
					currMember.setHasUlt(false);
				} else if (ultFlag == 30) {
					currMember.setHasUlt(true);
				} else {
					System.out.println("Oops");
				}
			} else if (i == 8) { // For Fuuka, only use personaFlagMap. If val == 201 (0xC9), yes. If val == 200 (0xC8), no.
				int ultFlag = getByteRangeInt(data, indexMap.get(index), 0, 1);
				if (ultFlag == 200) {
					currMember.setHasUlt(false);
				} else if (ultFlag == 201) {
					currMember.setHasUlt(true);
				} else {
					System.out.println("Oops");
				}
			} else {
				currMember.setHasUlt(false);
			}
			
			currMember.setLevel(getByteRangeInt(data, indexMap.get(index), 2, 1));
			currMember.setExp(getByteRangeInt(data, indexMap.get(index), 6, 4));
			currMember.setPersonaFlag(getByteRangeInt(data, indexMap.get(index), 0, 1));
			
			String skills = getByteRangeString(data, indexMap.get(index), 10, 2);
			for (int j = 2; j < 16; j += 2) {
				skills += getByteRangeString(data, indexMap.get(index), 10 + j, 2);
			}

			currMember.setCurrentSkills(skills);
			
			// Double check these indices are correct
			currMember.initAttributes(getByteRangeInt(data, indexMap.get(index), 26, 1),
									  getByteRangeInt(data, indexMap.get(index), 27, 1),
									  getByteRangeInt(data, indexMap.get(index), 28, 1),
									  getByteRangeInt(data, indexMap.get(index), 29, 1),
									  getByteRangeInt(data, indexMap.get(index), 30, 1));
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
	
	public void setPlayerFirstName (String firstName) {
		this.playerFirstName = firstName;
	}
	
	public String getPlayerLastName () {
		return this.playerLastName;
	}
	
	public void setPlayerLastName (String lastName) {
		this.playerLastName = lastName;
	}
	
	public String[] getPartyList () {
		return partyMember;
	}
	
	public HashMap<String, PartyMember> getPartyMemberMap () {
		return partyMemberMap;
	}
	
	// person name must be correct
	// level must be between 1-99 inclusive
	public void setLevel (String person, int level) {
		partyMemberMap.get(person).setLevel(level);
	}
	
	// person name must be correct
	// exp must be between 0-MAX_EXP inclusive
	public void setExp (String person, int exp) {
		partyMemberMap.get(person).setExp(exp);
	}
	
	public static int getMaxLevel () {
		return MAX_LEVEL;
	}
	
	public static int getMaxExp () {
		return MAX_EXP;
	}
	
	// Name **must** be correct
	public void updateUltFlagMap (String person, boolean flag) {
		partyMemberMap.get(person).setHasUlt(flag);
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
			PartyMember currMember = partyMemberMap.get(partyMember[i]);
			CharacterAttributes currAttributes = currMember.getAttributes();
			level = currMember.getLevel();
			exp = currMember.getExp();
			skills = currMember.getCurrentSkills();
			
			if (i < 6) { // Not Shinjiro, Koromaru, or Fuuka, because their structure is different
				if (!currMember.getHasUlt()) {
					updateByteArray(intToBytes(0, 1), indexMap.get(index), 3);
				} else {
					updateByteArray(intToBytes(30, 1), indexMap.get(index), 3);
				}
			} 
			
			updateByteArray(intToBytes(currMember.getPersonaFlag(), 1), indexMap.get(index), 0);
			updateByteArray(intToBytes(level, 1), indexMap.get(index), 2);
			updateByteArray(intToBytes(exp, 4), indexMap.get(index), 6);
			updateByteArray(hexToBytes(skills), indexMap.get(index), 10);
			
			updateByteArray(intToBytes(currAttributes.getStrength(), 1), indexMap.get(index), 26);
			updateByteArray(intToBytes(currAttributes.getMagic(), 1), indexMap.get(index), 27);
			updateByteArray(intToBytes(currAttributes.getEndurance(), 1), indexMap.get(index), 28);
			updateByteArray(intToBytes(currAttributes.getAgility(), 1), indexMap.get(index), 29);
			updateByteArray(intToBytes(currAttributes.getLuck(), 1), indexMap.get(index), 30);
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
	
	static byte[] intToBytes(int input, int numBytes) {
		byte[] out = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(input).array(); // Needs to allocate 4 otherwise error is thrown
		return Arrays.copyOfRange(out, 0, numBytes);
	}
	
	// Thank you https://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-javav
	static byte[] hexToBytes (String input) {
		return DatatypeConverter.parseHexBinary(input);
	}
	
	// Thank you https://www.mkyong.com/java/java-how-to-convert-bytes-to-hex/
	static private String bytesToHex(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	
	// In order to preserve endian-ness (little endian)
	// Thank you http://www.java67.com/2016/10/3-ways-to-reverse-array-in-java-coding-interview-question.html
	static void arrayReverse (byte[] data) {
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
			partyMemberMap.get(partyMember[i]).setLevel(MAX_LEVEL);
			partyMemberMap.get(partyMember[i]).setExp(MAX_EXP);
		}
		updateCharacterSkills();
		updatePartyAttributes();
	}	
	
	// Set every party character's level and exp to that of the MC.
	public void setToMC () { 
		for (int i = 0; i < partyMember.length; i++) {
			partyMemberMap.get(partyMember[i]).setLevel(playerLevel);
			partyMemberMap.get(partyMember[i]).setExp(playerExp);
		}
		updateCharacterSkills();
		updatePartyAttributes();
	}
	
	// Sets exp to be correct for level.
	public static int expForLevel (int lvl) {
		return expNeededPerLevel[lvl];
	}
	
	// Sets level to be correct for exp.
	public static int levelForExp (int xp) {
		for (int i = expNeededPerLevel.length - 1; i > 0; i--) {
			if (xp >= expNeededPerLevel[i]) {
				return i;
			}
		}
		return -1; //error
	}
	
	// Update all character's skills based on their level and ultimate persona status.
	public void updateCharacterSkills () {
		for (int i = 0; i < partyMember.length; i++) {
			partyMemberMap.get(partyMember[i]).updateCurrentSkills();
		}
	}
	
	public void updatePartyAttributes () {
		for (int i = 0; i < partyMember.length; i++) {
			partyMemberMap.get(partyMember[i]).updateAttributes();
		}
	}
	
	// Update the persona flag value of each character based on the ult flag value
	public void updatePersonaFlags () {
		boolean isUlt;
		int[] currFlags;
		for (int i = 0; i < partyMember.length; i++) {
			PartyMember currMember = partyMemberMap.get(partyMember[i]);
			if (i != 6 && i != 7) { // Shinjiro and Koromaru have no ult persona
				isUlt = currMember.getHasUlt();
				currFlags = personaCharFlagsMap.get(partyMember[i]);
				if (isUlt) {
					currMember.setPersonaFlag(currFlags[1]);
				} else {
					currMember.setPersonaFlag(currFlags[0]);
				}
			}		
		}
		
	}
	
	private void initPartyMemberMap() {
		for (int i = 0; i < partyMember.length; i++) {
			String currChar = partyMember[i];
			partyMemberMap.put(currChar, new PartyMember(currChar));
		}
	}
	
}
