package org.example;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class IPV4Validator {

    private final static String IPV4Group = "^(\\d{1,3})$";
    private final static int IPV4_MIN_VALUE = 0;
    private final static int IPV4_MAX_VALUE = 255;

    public static boolean validateIPV4Address(final String ip) {
        //valida se nao e nulo ou vazio
        if (StringUtils.isBlank(ip)) {
            return false;
        }

        final Pattern patternIpV4 = Pattern.compile(IPV4Group);

        final var groups = ip.split("\\.");

        // valida quantidade de grupos
        if (!(groups.length == 4)) {
            return false;
        }

        // valida o valor de cada grupo
        for (String group : groups) {
            // valida se esta no padrao do regex
            if (!patternIpV4.matcher(group).matches()) {
                return false;
            }

            if (group.charAt(0) == '0') {
                return false;
            }

            try {
                var newGroup = Integer.parseInt(group);
                if (newGroup < IPV4_MIN_VALUE || newGroup > IPV4_MAX_VALUE) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}