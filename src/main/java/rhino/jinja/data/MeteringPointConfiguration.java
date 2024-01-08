package rhino.jinja.data;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldNameConstants
public class MeteringPointConfiguration implements Serializable {
  private static final long serialVersionUID = -3275707070979113057L;

  public static final String TYPE_PARAMETER = "Type";
  public static final String INTERVAL_PARAMETER = "Interval";
  public static final String TYPE_INDEX_PARAMETER = "TypeIndex";
  public static final String DISABLE_INTERVAL_ALIGNMENT_PARAMETER = "DisableIntervalAlignment";

  private Long id;

  private Long type;

  private Integer interval;

  private Integer typeIndex;

  private Boolean disableIntervalAlignment;
}
