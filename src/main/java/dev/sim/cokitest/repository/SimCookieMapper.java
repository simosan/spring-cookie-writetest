package dev.sim.cokitest.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import dev.sim.cokitest.model.*;

@Mapper
public interface SimCookieMapper {

	public List<Dep> selectUid(String uid);

}
