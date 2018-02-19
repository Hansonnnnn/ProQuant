package data.update;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateService extends TimerTask{

	@Autowired
	SaveUserYesterdayPrincipal update;
	@Override
	public void run() {
		update.save();
	}

}
