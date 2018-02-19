package data.spider;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface BasicDataSpiderService {

	void test(String code, int start, int end);

	void sharesCrawl(String code, int year, int season);

}