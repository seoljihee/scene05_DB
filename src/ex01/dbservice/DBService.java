package ex01.dbservice;

import ex01.dto.MemDTO;

public interface DBService {

	public int insertMember(MemDTO dto);
	public MemDTO loginCheck(String userId);
}
