package com.example.editor;

import java.util.Arrays;

public class SaveFile {
	
	static String PLAYER_FIRST_NAME_INDEX = ""; // 8 bytes
	static String PLAYER_LAST_NAME_INDEX = ""; // 8 bytes
	
	static String PLAYER_LEVEL_INDEX = "872"; // 1 byte 
	static String JUNPEI_LEVEL_INDEX = "7805"; // rest are 4 bytes
	static String YUKARI_LEVEL_INDEX = "6CB9";
	static String KOROMARU_LEVEL_INDEX = "8AD9";
	static String SHINJIRO_LEVEL_INDEX = "8715";
	static String AKIHIKO_LEVEL_INDEX = "7F8D";
	static String MITSURU_LEVEL_INDEX = "7441";
	static String KEN_LEVEL_INDEX = "8351";
	static String AIGIS_LEVEL_INDEX = "707D";
	static String FUUKA_LEVEL_INDEX = "";
	
	static String PLAYER_EXP_INDEX = "8B0"; // all are 4 bytes
	static String JUNPEI_EXP_INDEX = "7809";
	static String YUKARI_EXP_INDEX = "6CBD";
	static String KOROMARU_EXP_INDEX = "8ADD";
	static String SHINJIRO_EXP_INDEX = "8719";
	static String AKIHIKO_EXP_INDEX = "7F91";
	static String MITSURU_EXP_INDEX = "7445";
	static String KEN_EXP_INDEX = "8355";
	static String AIGIS_EXP_INDEX = "7081";
	static String FUUKA_EXP_INDEX = "";
	
	static String YEN_INDEX = "6560"; // 4 bytes
	static String PLUMES_INDEX = "25C4"; // 1 byte
	
	String playerFirstName, playerLastName;
	
	int playerLevel, playerExp, 
	yukariLevel, yukariExp, 
	junpeiLevel, junpeiExp, 
	koromaruLevel, koromaruExp,
	akihikoLevel, akihikoExp,
	mitsuruLevel, mitsuruExp,
	kenLevel, kenExp,
	shinjiroLevel, shinjiroExp,
	aigisLevel, aigisExp,
	fuukaLevel, fuukaExp;
	
	int yen, plumes;
	
	public SaveFile (byte[] data) {
		playerLevel = GetByteRangeInt(data, PLAYER_LEVEL_INDEX, 1);
		playerExp = GetByteRangeInt(data, PLAYER_EXP_INDEX, 4);
	}
	
	String NameToString (byte[] data) {
		return null;
	}
	
	int GetByteRangeInt (byte[] data, String indexString, int byteLength) {
		int index = Integer.parseInt(indexString, 16);
		byte[] byteRange = Arrays.copyOfRange(data, index, index + byteLength);
		arrayReverse(byteRange);
		String rangeString = bytesToHex(byteRange);
		return Integer.parseInt(rangeString, 16);
	}
	
	String GetByteRangeString (byte[] data, String indexString, int byteLength) {
		int index = Integer.parseInt(indexString, 16);
		byte[] byteRange = Arrays.copyOfRange(data, index, index + byteLength);
		arrayReverse(byteRange);
		return bytesToHex(byteRange);
	}
	
	// Thank you https://www.mkyong.com/java/java-how-to-convert-bytes-to-hex/
	private static String bytesToHex(byte[] hashInBytes) {
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
}
