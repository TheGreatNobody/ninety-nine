package com.ryan.ninetynine.web.provider;

import java.util.HashMap;
import java.util.Map;

public class RegionProvider {

    public Map<String, String> getRegions() {
        Map<String, String> regions = new HashMap<String, String>();
        regions.put("A", "臺北市");
        regions.put("B", "臺中市");
        regions.put("C", "基隆市");
        regions.put("D", "臺南市");
        regions.put("E", "高雄市");
        regions.put("F", "新北市");
        regions.put("G", "宜蘭縣");
        regions.put("H", "桃園市");
        regions.put("I", "嘉義市");
        regions.put("J", "新竹縣");
        regions.put("K", "苗栗縣");
        regions.put("L", "臺中縣");
        regions.put("M", "南投縣");
        regions.put("N", "彰化縣");
        regions.put("O", "新竹市");
        regions.put("P", "雲林縣");
        regions.put("Q", "嘉義縣");
        regions.put("R", "臺南縣");
        regions.put("S", "高雄縣");
        regions.put("T", "屏東縣");
        regions.put("U", "花蓮縣");
        regions.put("V", "臺東縣");
        regions.put("W", "金門縣");
        regions.put("X", "澎湖縣");
        regions.put("Y", "陽明山管理局");
        regions.put("Z", "連江縣");
        return regions;
    }

    public Map<String, Integer> getRegionCodes() {
        Map<String, Integer> regionCodes = new HashMap<>();
        regionCodes.put("A", 10);
        regionCodes.put("B", 11);
        regionCodes.put("C", 12);
        regionCodes.put("D", 13);
        regionCodes.put("E", 14);
        regionCodes.put("F", 15);
        regionCodes.put("G", 16);
        regionCodes.put("H", 17);
        regionCodes.put("I", 34);
        regionCodes.put("J", 18);
        regionCodes.put("K", 19);
        regionCodes.put("L", 20);
        regionCodes.put("M", 21);
        regionCodes.put("N", 22);
        regionCodes.put("O", 35);
        regionCodes.put("P", 23);
        regionCodes.put("Q", 24);
        regionCodes.put("R", 25);
        regionCodes.put("S", 26);
        regionCodes.put("T", 27);
        regionCodes.put("U", 28);
        regionCodes.put("V", 29);
        regionCodes.put("W", 32);
        regionCodes.put("X", 30);
        regionCodes.put("Y", 31);
        regionCodes.put("Z", 33);
        return regionCodes;
    }



}
