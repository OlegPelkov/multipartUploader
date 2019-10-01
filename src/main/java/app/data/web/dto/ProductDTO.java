package app.data.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    @NotNull(message = "Name may not be null")
    @NotEmpty(message = "Name may not be empty")
    @NotBlank(message = "Name may not be blank")
    @Size(min = 1, max = 255)
    private String name;

    @NotNull(message = "Name may not be null")
    private String description;

    private Map<String, String> properties = new HashMap<>();

    public String getName() {
        return name;
    }
}
