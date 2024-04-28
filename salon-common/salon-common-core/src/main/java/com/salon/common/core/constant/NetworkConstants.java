package com.salon.common.core.constant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.constant
 * @Project：salon
 * @name：NetworkConstants
 * @Date：2024/4/24 13:48
 */
@Schema(name = "NetworkConstants", description = "网络常量")
public class NetworkConstants {

    private NetworkConstants() {
    }

    @Schema(name = "IPV4_REGEX", description = "IPV4正则表达式")
    public static final String IPV4_REGEX = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";

    @Schema(name = "IP", description = "IP参数")
    public static final String IP = "ip";

    @Schema(name = "WWW", description = "www三级域名")
    public static final String WWW = "www";

    @Schema(name = "LOCAL_IPV4", description = "本地IP-IPV4")
    public static final String LOCAL_IPV4 = "127.0.0.1";

    @Schema(name = "LOCAL_IPV6", description = "本地IP-IPV6")
    public static final String LOCAL_IPV6 = "0:0:0:0:0:0:0:1";

    @Schema(name = "UNKNOWN_IP", description = "未知IP")
    public static final String UNKNOWN_IP = "unknown";

    @Schema(name = "LOCAL_DESC", description = "本地IP描述")
    public static final String LOCAL_DESC = "内网";

    @Schema(name = "HTTP_SCHEME", description = "http协议头")
    public static final String HTTP_SCHEME = "http://";

    @Schema(name = "HTTP_PROTOCOL", description = "http协议")
    public static final String HTTP_PROTOCOL = "http";

    @Schema(name = "HTTPS_PROTOCOL", description = "https协议")
    public static final String HTTPS_PROTOCOL = "https";

    @Schema(name = "HTTPS_SCHEME", description = "HTTPS协议头")
    public static final String HTTPS_SCHEME = "https://";

}
