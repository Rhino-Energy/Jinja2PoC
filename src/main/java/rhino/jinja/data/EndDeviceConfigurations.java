package rhino.jinja.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@FieldNameConstants
public class EndDeviceConfigurations implements Serializable {
  private static final long serialVersionUID = 2230122958809673063L;

  private List<EndDeviceConfiguration> endDeviceConfigurations;

  @JsonIgnore
  private LocalDateTime created;
}
