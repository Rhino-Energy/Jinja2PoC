package rhino.jinja;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.FatalTemplateErrorsException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rhino.jinja.data.EndDeviceConfiguration;
import rhino.jinja.data.EndDeviceConfigurations;
import rhino.jinja.data.MeteringPointConfiguration;
import rhino.jinja.data.SubDeviceConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JinJavaTest {
    //https://hub.synerise.com/developers/inserts/insert-usage/
    private Jinjava jinjava;
    private EndDeviceConfigurations configuration;

    @BeforeEach
    void before() {
        jinjava = new Jinjava();
        configuration = prepareEndDeviceConfigurations();
    }

    private EndDeviceConfigurations prepareEndDeviceConfigurations() {

        EndDeviceConfiguration endDeviceConfiguration = new EndDeviceConfiguration();
        endDeviceConfiguration.setNumericEndDeviceId(1342179996L);
        Map<String, String> transmissionParams = new HashMap<>();
        transmissionParams.put("baudrate", "38400");
        transmissionParams.put("dataLength", "8");
        transmissionParams.put("deviceAddress", "1");
        transmissionParams.put("interface", "RS485");
        transmissionParams.put("parity", "Even");
        transmissionParams.put("protocol", "ModBus");
        transmissionParams.put("stopLength", "1");
        endDeviceConfiguration.setSubDeviceConfigurations(List.of(SubDeviceConfiguration
                .builder()
                .model("SchneiderElectric;PM5100")
                .transmissionParameters(transmissionParams)
                .meteringPointConfigurations(List.of(MeteringPointConfiguration
                                .builder()
                                .id(54073426L)
                                .type(7L)
                                .typeIndex(0)
                                .interval(900)
                                .disableIntervalAlignment(false)
                                .build(),
                        MeteringPointConfiguration
                                .builder()
                                .id(54073428L)
                                .type(7L)
                                .typeIndex(0)
                                .interval(900)
                                .disableIntervalAlignment(false)
                                .build()))
                .build(), SubDeviceConfiguration
                .builder()
                .model("SchneiderElectric;PM5200")
                .build()));
        return new EndDeviceConfigurations(List.of(endDeviceConfiguration), LocalDateTime.of(2023, 12, 31, 12, 0));
    }

    @Test
    void fail() {
        Map<String, Object> context = Map.of("name", "John Doe");

        Assertions.assertThrows(FatalTemplateErrorsException.class, () -> {
            jinjava.render("Hello {{}{ name }}!", context);
        });
    }

    @Test
    void correctSimpleTemplate() {
        Map<String, Object> context = Map.of("name", "test");
        Assertions.assertEquals("Hello test!", jinjava.render("Hello {{ name }}!", context));
    }

    @Test
    void complexTemplateTest() throws IOException {
        Map<String, Object> context = Map.of("configuration", configuration);

        String template = Files.readString(Paths.get("src/test/resources/template/template1"));
        String result = Files.readString(Paths.get("src/test/resources/result/result1"));
        Assertions.assertEquals(StringUtils.deleteWhitespace(result), StringUtils.deleteWhitespace(jinjava.render(template, context)));
    }
}
