package com.ryan.ninetynine.web.service;

import com.ryan.ninetynine.web.service.bean.NormalizationBo;
import com.ryan.ninetynine.web.service.bean.NormalizationRelayBo;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NormalizationService {

    private static final Map<String, String> REGIONS = new HashMap<>();
    private static final Map<String, Integer> REGION_CODES = new HashMap<>();

    static {
        REGIONS.put("A", "臺北市");
        REGIONS.put("B", "臺中市");
        REGIONS.put("C", "基隆市");
        REGIONS.put("D", "臺南市");
        REGIONS.put("E", "高雄市");
        REGIONS.put("F", "新北市");
        REGIONS.put("G", "宜蘭縣");
        REGIONS.put("H", "桃園市");
        REGIONS.put("I", "嘉義市");
        REGIONS.put("J", "新竹縣");
        REGIONS.put("K", "苗栗縣");
        REGIONS.put("L", "臺中縣");
        REGIONS.put("M", "南投縣");
        REGIONS.put("N", "彰化縣");
        REGIONS.put("O", "新竹市");
        REGIONS.put("P", "雲林縣");
        REGIONS.put("Q", "嘉義縣");
        REGIONS.put("R", "臺南縣");
        REGIONS.put("S", "高雄縣");
        REGIONS.put("T", "屏東縣");
        REGIONS.put("U", "花蓮縣");
        REGIONS.put("V", "臺東縣");
        REGIONS.put("W", "金門縣");
        REGIONS.put("X", "澎湖縣");
        REGIONS.put("Y", "陽明山管理局");
        REGIONS.put("Z", "連江縣");

        REGION_CODES.put("A", 10);
        REGION_CODES.put("B", 11);
        REGION_CODES.put("C", 12);
        REGION_CODES.put("D", 13);
        REGION_CODES.put("E", 14);
        REGION_CODES.put("F", 15);
        REGION_CODES.put("G", 16);
        REGION_CODES.put("H", 17);
        REGION_CODES.put("I", 34);
        REGION_CODES.put("J", 18);
        REGION_CODES.put("K", 19);
        REGION_CODES.put("L", 20);
        REGION_CODES.put("M", 21);
        REGION_CODES.put("N", 22);
        REGION_CODES.put("O", 35);
        REGION_CODES.put("P", 23);
        REGION_CODES.put("Q", 24);
        REGION_CODES.put("R", 25);
        REGION_CODES.put("S", 26);
        REGION_CODES.put("T", 27);
        REGION_CODES.put("U", 28);
        REGION_CODES.put("V", 29);
        REGION_CODES.put("W", 32);
        REGION_CODES.put("X", 30);
        REGION_CODES.put("Y", 31);
        REGION_CODES.put("Z", 33);
    }


    public NormalizationRelayBo normalizeId(NormalizationBo bo) {
        NormalizationRelayBo relayBo = new NormalizationRelayBo();

        if (bo == null || StringUtils.isBlank(bo.getData())) {
            relayBo.setMessage("物件異常");
            return relayBo;
        }

        String upperCaseData = bo.getData().toUpperCase();
        final String resultData = bo.getData().toUpperCase();

        if (!upperCaseData.matches("^[a-zA-Z][1289ABCD]\\d{8}$")) {
            relayBo.setMessage("基本格式錯誤");
            return relayBo;
        }
        // area code
        String firstLetter = upperCaseData.substring(0, 1);
//        String region = REGIONS.getOrDefault(firstLetter, "");
//        if ("".equals(region)) {
//            // 無效地區
//            relayBo.setMessage("無效區域碼");
//            return relayBo;
//        }
        // sex code
        String secondLetter = upperCaseData.substring(1, 2);
        if("ABCD".contains(secondLetter)) {
            final Integer secondTemp = REGION_CODES.get(secondLetter);
            final String secondReplace = String.valueOf(secondTemp % 10);
            upperCaseData = upperCaseData.substring(0, 1).concat(secondReplace).concat(upperCaseData.substring(2));
        }

        int[] digits = new int[11];
        Integer regionCode = REGION_CODES.get(firstLetter);
        digits[0] = regionCode / 10; // 十位數
        digits[1] = regionCode % 10; // 個位數

        for (int i = 1; i < upperCaseData.length(); i++) {
            String substring = upperCaseData.substring(i, i + 1);
            digits[i + 1] = Integer.parseInt(substring);
        }
        System.out.println(Arrays.toString(digits));

        int[] weights = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += digits[i] * weights[i];
        }
        System.out.println("sum: " + sum);
        boolean isValid = sum % 10 == 0;

        if (!isValid) {
            relayBo.setMessage("則驗證失敗");
            return relayBo;
        }
        relayBo.setResult(resultData);
        relayBo.setCode("0000");
        // identity code
        String thirdLetter = upperCaseData.substring(2, 3);
        relayBo.setMessage(idType(secondLetter, thirdLetter));
        return relayBo;
    }

    private String idType(String sexCode, String idCode) {
        return switch (sexCode) {
            case "1", "2" -> "身分證 ".concat(identity(idCode));
            case "8", "9" -> "新式居留證 ".concat(residence(idCode));
            case "A", "B" -> "舊式居留證 無戶籍國民、大陸地區人民及港澳居民";
            case "C", "D" -> "舊式居留證 外國人";
            default -> "";
        };
    }

    private String identity(String idCode) {
        return switch (idCode) {
            case "6" -> "外國人或無國籍人";
            case "7" -> "無戶籍國民";
            case "8" -> "港澳居民";
            case "9" -> "大陸地區人民";
            default -> // 0-5
                    "本國人";
        };
    }

    private String residence(String idCode) {
        return switch (idCode) {
            case "7" -> "無戶籍國民";
            case "8" -> "港澳居民";
            case "9" -> "大陸地區人民";
            default -> // 0-6
                    "外國人或無國籍人士";
        };
    }

    public static void main(String[] args) {
        NormalizationService service = new NormalizationService();
        String[] oldResidence = {"EB07240712", "EA22207775", "AC37349342", "AD50528987", "HD20062599"};

        for(String str : oldResidence) {
            NormalizationBo normalizationBo = new NormalizationBo();
            normalizationBo.setData(str);
            NormalizationRelayBo relayBo = service.normalizeId(normalizationBo);
            System.out.println("舊式居留證");
            System.out.println(relayBo.toString());
        }


        String[] residence = {"H986228859", "H812009408", "A822602030", "A974336812", "F968253487", "F825784481"};
        for(String str : residence) {
            NormalizationBo normalizationBo = new NormalizationBo();
            normalizationBo.setData(str);
            NormalizationRelayBo relayBo = service.normalizeId(normalizationBo);
            System.out.println("新式居留證");
            System.out.println(relayBo.toString());
        }
    }

}
