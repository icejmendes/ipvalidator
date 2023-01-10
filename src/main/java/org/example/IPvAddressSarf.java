package org.example;

import org.apache.commons.validator.routines.InetAddressValidator;

// usada para comparar com o comportamento da biblioteca utilizada no sarf
public class IPvAddressSarf {

    public static boolean isValidIpSarfLib(String ip) {
        return InetAddressValidator.getInstance().isValidInet6Address(ip);
    }
}
