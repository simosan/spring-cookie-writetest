package dev.sim.cokitest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Role {

	/*
	 * @NotBlank
	 * 
	 * @Size(max = 10) private String uid;
	 */

	@NotBlank
	@Size(max = 10)
	private String rolename;
}
