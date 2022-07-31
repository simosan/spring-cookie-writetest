package dev.sim.cokitest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import dev.sim.cokitest.model.Dep;
import dev.sim.cokitest.repository.SimCookieMapper;

@RequiredArgsConstructor
@Transactional
@Service
public class SimCookieService {

	private final SimCookieMapper mapper;

	public List<Dep> selectUid(String uid) {
		List<Dep> deps = mapper.selectUid(uid);
		return deps;
	}

}
