package com.salon.common.core.utils;

import cn.hutool.core.util.ObjectUtil;
import com.salon.common.core.constant.NetworkConstants;
import com.salon.common.core.constant.StringConstants;
import jakarta.servlet.http.HttpServletRequest;
import jodd.util.StringUtil;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.utils
 * @Project：salon
 * @name：IpUtil
 * @Date：2024/4/24 13:46
 */
public class IpUtil {

    /**
     * 解析IP地址.
     * @param request 请求对象
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (ObjectUtil.isNull(request)) {
            return NetworkConstants.UNKNOWN_IP;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (conditionNull(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (conditionNull(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (conditionNull(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (conditionNull(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (conditionNull(ip)) {
            ip = request.getRemoteAddr();
        }
        return NetworkConstants.LOCAL_IPV6.equals(ip) ? NetworkConstants.LOCAL_IPV4 : ip.split(StringConstants.COMMA)[0];
    }

    /**
     * 判断内部IP.
     * @param ip IP地址
     * @return 判断结果
     */
    public static boolean internalIp(String ip) {
        if (NetworkConstants.LOCAL_IPV6.equals(ip)) {
            return true;
        }
        byte[] bytes = textToNumericFormatV4(ip);
        return ObjectUtil.isNotNull(bytes) && (internalIp(bytes) || NetworkConstants.LOCAL_IPV4.equals(ip));
    }

    /**
     * 判断IP不存在或未知.
     * @param ip IP地址
     * @return 判断结果
     */
    private static boolean conditionNull(String ip) {
        return StringUtil.isEmpty(ip) || NetworkConstants.UNKNOWN_IP.equalsIgnoreCase(ip);
    }

    /**
     * 判断内部IP.
     * @param addr 字节数组
     * @return 判断结果
     */
    private static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte section1 = 0x0A;
        // 172.16.x.x/12
        final byte section2 = (byte) 0xAC;
        final byte section3 = (byte) 0x10;
        final byte section4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte section5 = (byte) 0xC0;
        final byte section6 = (byte) 0xA8;
        return switch (b0) {
            case section1 -> true;
            case section2 -> b1 >= section3 && b1 <= section4;
            case section5 -> b1 == section6;
            default -> false;
        };
    }

    /**
     * 将IPv4地址转换成字节.
     * @param text IPv4地址
     * @return 字节
     */
    public static byte[] textToNumericFormatV4(String text) {
        if (text.isEmpty()) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            long j;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    j = 4294967295L;
                    if ((l < 0L) || (l > j)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    j = 255;
                    if (l < 0L || l > j) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    j = 16777215;
                    if (l < 0L || l > j) {
                        return null;
                    }
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    j = 2;
                    for (int i = 0; i < j; i++) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    j = 65535L;
                    if ((l < 0L) || (l > j)) {
                        return null;
                    }
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    j = 4;
                    for (int i = 0; i < j; i++) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        }
        catch (NumberFormatException e) {

            return null;
        }
        return bytes;
    }

}
