package rhino.jinja.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@FieldNameConstants
public class EndDeviceConfiguration implements Serializable {
  private static final long serialVersionUID = -1754519925615299966L;

  private Long numericEndDeviceId;
  private List<SubDeviceConfiguration> subDeviceConfigurations;
}
