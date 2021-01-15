package com.rest.workshop.presentation.dtos.user;

import javax.validation.constraints.NotNull;

public class UpdateEnabledDto {
    @NotNull
    public Boolean enabled;
}
