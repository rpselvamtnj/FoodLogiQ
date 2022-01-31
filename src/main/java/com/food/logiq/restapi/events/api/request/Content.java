package com.food.logiq.restapi.events.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Content request data model.
 * Annotated with {@link Data @Data},{@link Builder @Builder}
 *
 * @author Rotation5
 */
@Data
public class Content {

    @Pattern(regexp = "(^$|[0-9]{14})", message = "It Should be a 14 digit number")
    @ApiModelProperty(example = "12345678901234")
    @NotBlank(message = "It shouldn't be blank")
    private String gtin;

    @ApiModelProperty(example = "axdfc")
    @NotBlank(message = "It shouldn't be blank")
    private String lot;

    @ApiModelProperty(example = "2020-01-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bestByDate;

    @ApiModelProperty(example = "2020-01-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
}
