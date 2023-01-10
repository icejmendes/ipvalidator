import org.example.IPV4Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class IPV4ValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"0.0.0.0",
            "0.0.0.1",
            "127.0.0.1",
            "1.2.3.4",              // 0-9
            "11.1.1.0",             // 10-99
            "101.1.1.0",            // 100-199
            "201.1.1.0",            // 200-249
            "255.255.255.255",      // 250-255
            "192.168.1.1",
            "192.168.1.255",
            "100.100.100.100"})
    void shouldValidateIPV4ValidAddresses(String ip) {
        var res = IPV4Validator.validateIPV4Address(ip);
        Assertions.assertTrue(res);
    }


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "000.000.000.000",          // leading 0
            "00.00.00.00",              // leading 0
            "1.2.3.04",                 // leading 0
            "1.02.03.4",                // leading 0

            "1.2",                      // 1 dot
            "1.2.3",                    // 2 dots
            "1.2.3.4.5",                // 4 dots
            "192.168.1.1.1",            // 4 dots
            "256.1.1.1",                // 256
            "1.256.1.1",                // 256
            "1.1.256.1",                // 256
            "1.1.1.256",                // 256
            "-100.1.1.1",               // -100
            "1.-100.1.1",               // -100
            "1.1.-100.1",               // -100
            "1.1.1.-100",               // -100
            "1...1",                    // empty between .
            "1..1",                     // empty between .

            //    "1.1.1.1.",                 // last . (o ultimo ponto nao e considerado)
    })
    void shouldValidateIPV4InvalidAddresses(String ip) {
        var res = IPV4Validator.validateIPV4Address(ip);
        Assertions.assertFalse(res);
    }
}
