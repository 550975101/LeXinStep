package com.lexin.step.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lexin.step.entity.LeXinUpdateBean;
import com.lexin.step.entity.LeXinUser;
import com.lexin.step.util.HttpClientUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class StepTask {

    private  ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "0 0 8,9 * * ?")
    public void test() throws IOException {
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        //大号QQ  大号支付宝
        HashMap<String, String> map = new HashMap<>();
        map.put("openId", "3AFAF7DC6C547C0FF75E09DB6A4A3FE3");
        map.put("accessToken", "3ACB77D93747BFA58DEA39BD074F14A0");
        //小号QQ 小号支付宝
        //url: "auth://www.qq.com?#access_token=18C18D173B1969CE5B0AA7E8B301A4BB&expires_in=7776000&openid=EB1148E70D8294A89A8E47C0411D2FBD&pay_token=B1E35AB109A27C62E83F5570B8AA7671&state=test&ret=0&pf=openmobile_ios&pfkey=9e3b867a894bfc4e24bffdeac1f7f310&auth_time=1586785936&page_type=0"
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("openId", "EB1148E70D8294A89A8E47C0411D2FBD");
        map1.put("accessToken", "18C18D173B1969CE5B0AA7E8B301A4BB");
        //发哥
        //auth://www.qq.com?#access_token=3CEE2D794A26076975068B57AFA6079C&expires_in=7776000&openid=1222C99E8E0467386BCCCBA145FCACF8&pay_token=439C1929781B5C3A2FC5D845DAFEA024&state=test&ret=0&pf=openmobile_ios&pfkey=891423b55a42ec2dba0377da05a6a9b9&auth_time=1586786571&page_type=0
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("openId", "1222C99E8E0467386BCCCBA145FCACF8");
        map2.put("accessToken", "3CEE2D794A26076975068B57AFA6079C");
        hashMaps.add(map);
        hashMaps.add(map1);
        hashMaps.add(map2);
        hashMaps.forEach(s-> {
            try {
                LeXinUser leXinUser = qqLogin(s.get("openId"), s.get("accessToken"));
                update(leXinUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public  LeXinUser qqLogin(String openId, String accessToken) throws IOException {
        String clientId = Objects.requireNonNull(getMD5String(openId + accessToken)).toLowerCase();
        String requestId = UUID.randomUUID().toString().replace("-", "");
        String url = "https://sports.lifesense.com/sessions_service/loginByThirdParty?" +
                "clientId="+clientId+"&" +
                "screenWidth=375&" +
                "screenHeight=667&"+
                "appType=6&" +
                "longitude=&" +
                "latitude=&" +
                "network_type=wifi&" +
                "systemType=1&" +
                "version=4.5&" +
                "systemType=2&" +
                "osversion=12.4&" +
                "platform=ios&" +
                "screenwidth=375&" +
                "screenheight=667&" +
                "requestId="+requestId+"&" +
                "area=CN&" +
                "language=zh&" +
                "openudid=&" +
                "devicemodel=iPhone%206&" +
                "os_country=CN&" +
                "os_langs=zh&" +
                "promotion_channel=app_store&" +
                "timezone=Asia%2FShanghai";
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("openId", openId);
        hashMap.put("openAccountType", "2");
        hashMap.put("clientId", clientId);
        hashMap.put("appId", "1104904286");
        hashMap.put("openAccessToken", accessToken);
        hashMap.put("appType", "6");
        hashMap.put("timestamp", System.currentTimeMillis());
        String s = objectMapper.writeValueAsString(hashMap);
        String s1 = HttpClientUtil.httpPostRequest(url, s);
        System.out.println(s1);
        JsonNode jsonNode = objectMapper.readTree(s1);
        jsonNode = jsonNode.get("data");
        boolean exist = jsonNode.get("exist").asBoolean();
        boolean hasMobile = jsonNode.get("hasMobile").asBoolean();
        String userId = jsonNode.get("userId").asText();
        String accessToken1 = jsonNode.get("accessToken").asText();
        long expireAt = jsonNode.get("expireAt").asLong();
        int userType = jsonNode.get("userType").asInt();
        boolean needInfo = jsonNode.get("needInfo").asBoolean();
        LeXinUser leXinUser = new LeXinUser();
        leXinUser.setAccessToken(accessToken1);
        leXinUser.setExist(exist);
        leXinUser.setExpireAt(expireAt);
        leXinUser.setHasMobile(hasMobile);
        leXinUser.setNeedInfo(needInfo);
        leXinUser.setUserId(userId);
        leXinUser.setUserType(userType);
        return leXinUser;
    }

    public  String update(LeXinUser user) throws UnsupportedEncodingException, JsonProcessingException {
        String clientId = Objects.requireNonNull(getMD5String("3ACB77D93747BFA58DEA39BD074F14A0" + "3AFAF7DC6C547C0FF75E09DB6A4A3FE3")).toLowerCase();
        String requestId = UUID.randomUUID().toString().replace("-", "");
        String url = "https://sports.lifesense.com/sport_service/sport/sport/uploadMobileStepV2?" +
                "accesstoken=e8d33dbc01e74421829319c660663c6f&" +
                "userId=25922119&" +
                "appType=6&" +
                "longitude=&" +
                "latitude=&" +
                "network_type=wifi&" +
                "systemType=1&" +
                "version=4.5&" +
                "osversion=12.4.6&" +
                "systemType=2&" +
                "platform=ios&" +
                "screenwidth=375&" +
                "screenheight=667&" +
                "requestId="+requestId+"&" +
                "area=CN&" +
                "language=zh&" +
                "openudid=B3326FD9-A63E-427F-BCFB-99B2C5966711&" +
                "devicemodel=iPhone6&" +
                "os_country=CN&" +
                "os_langs=zh&" +
                "promotion_channel=app_store&" +
                "city=&" +
                "cityCode=110100&" +
                "province=&" +
                "screenwidth=1080&" +
                "provinceCode=110000&" +
                "areaCode=110106&" +
                "country=&" +
                "timezone=Asia/Shanghai";
        String cookie = "appType2=6; userType2=" + user.getUserType() + "; expireAt2=" + user.getExpireAt() + "; session=%7B%22accessToken%22%3A%22" + user.getAccessToken() + "%22%2C%22appType%22%3A6%2C%22expireAt%22%3A" + user.getExpireAt() + "%2C%22loginId%22%3A%22" + user.getUserId() + "%22%2C%22userType%22%3A" + user.getUserType() + "%2C%22gray%22%3Afalse%7D; loginId2=" + user.getUserId() + "; gray2=false; accessToken2=" + user.getAccessToken() + "; accessToken=D2A6AFB93531605DBE56DC2EEE74C4C99A443998DBB716804CF698C6AB0AD164DC5906506836E2E2F24C0D3E43E097189CAE6318A8FB1594154AC591251E289B5C38C7A34E582E0B8C2A6C1331211F2D5D8AFA1D6C1ABF3F9F460DC521764C3D.FE096D508E9888BF67B063AED57283B89F6A3E883BBDCC0328FF1A8506763093; gray2=false; session=%7B%22accessToken%22%3A%22" + user.getAccessToken() + "%22%2C%22appType%22%3A6%2C%22expireAt%22%3A" + user.getExpireAt() + "%2C%22loginId%22%3A%22" + user.getUserId() + "%22%2C%22userType%22%3A" + user.getUserType() + "%2C%22gray%22%3Afalse%7D; loginId2=" + user.getUserId() + "; userType2=" + user.getUserType() + "; appType2=6; expireAt2=" + user.getExpireAt() + "; accessToken2=" + user.getAccessToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", "LSWearable/4.5 (iPhone; iOS 12.4.6; Scale/2.00)");
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Cookie", cookie);
        Date date = new Date();
        int calories = (int) (0.05 * 66666);
        int distance = (int) (0.7 * 66666);
        int duration = (int) (0.85 * 66666);
        LeXinUpdateBean listBeans = new LeXinUpdateBean();
        ArrayList<LeXinUpdateBean.ListBean> listBeans2 = new ArrayList<>();
        LeXinUpdateBean.ListBean listBean = new LeXinUpdateBean.ListBean();
        listBean.setActive(1);
        listBean.setCalories(calories);
        listBean.setCreated(DateFormat(date, "yyyy-MM-dd HH:mm:ss"));
        listBean.setDataSource(2);
        listBean.setDayMeasurementTime(DateFormat(date, "yyyy-MM-dd"));
        listBean.setDeviceId("M_A563C6182F1D815D4241884A18CBE13AE1E88DDD");
        listBean.setDistance(distance);
        listBean.setId(clientId);
        listBean.setIsUpload(0);
        listBean.setMeasurementTime(DateFormat(date, "yyyy-MM-dd HH:mm:ss"));
        listBean.setPriority(0);

        listBean.setStep(66666);
        listBean.setType(2);
        listBean.setUpdated( date.getTime() * 1000);
        listBean.setUserId(user.getUserId());
        listBean.setDataSource(2);
        listBean.setExerciseTime(duration);
        listBeans2.add(listBean);
        listBeans.setList(listBeans2);
        String s = HttpClientUtil.httpPostRequestJson(url, headers, objectMapper.writeValueAsString(listBeans));
        System.out.println(s);
        return null;
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String DateFormat(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
