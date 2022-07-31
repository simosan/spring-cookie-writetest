package dev.sim.cokitest.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Dep {
	@NotBlank
	@Size(max = 10)
	private String uid;

	@NotBlank
	@Size(max = 20)
	private String department;

	// 子クラス（role）（1対多）
	private List<Role> rolelist;
}
