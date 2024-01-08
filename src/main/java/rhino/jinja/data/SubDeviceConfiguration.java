package rhino.jinja.data;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldNameConstants
public class SubDeviceConfiguration implements Serializable {
  private static final long serialVersionUID = 7268299483618983604L;

  private String model;

  private Map<String, String> transmissionParameters;

  private List<MeteringPointConfiguration> meteringPointConfigurations;
}
