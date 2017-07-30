package com.onlineBankingApplication.service.sp;

import java.util.List;

public interface DataAccessRepository {

	List<Result> storedProcCall(Long input);
}
