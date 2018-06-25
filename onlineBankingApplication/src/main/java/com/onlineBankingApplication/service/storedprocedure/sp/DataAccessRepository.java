package com.onlineBankingApplication.service.storedprocedure.sp;

import java.util.List;

public interface DataAccessRepository {

	List<Result> storedProcCall(Long input);
}
